package ligaaas.teamc.service;

import static java.util.Objects.requireNonNull;

import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Asynchronous;
import javax.ejb.EJBAccessException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ligaaas.teamc.domain.User;

/**
 * EJB for User
 * 
 * @author teamC
 *
 */

@Stateless
@PermitAll
public class UserEJB {

	@PersistenceContext
	private EntityManager em;

	@Resource(name = "java:/ligaaas/mail")
	private Session session;

	@Inject
	private Principal currentUser;

	@Asynchronous
	private void sendEmail(String email, String subject, String body) {
		try {
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
		} catch (MessagingException e) {
			Logger.getLogger(UserEJB.class.getName()).log(Level.WARNING, "Cannot send email", e);
		}
	}

	private void sendConfirmationEmail(User user) {
		this.sendEmail(user.getUserEmail(), "User registered on LigaaaS",
				"This e-mail has been registered on LigaaaS. Please confirm it now!\nhttp://localhost:9080/ligaaasC/jsf/views/activateAccount.xhtml?token="
						+ user.getUserToken());
	}

	/**
	 * Generates the hash of a given user.
	 * 
	 * @param user
	 *            The user used to calculate the hash.
	 * @return The hash of the user
	 */
	public String generateHash(User user) {
		String hash = String.valueOf(user.getUserLogin().hashCode()) + String.valueOf(user.getUserEmail().hashCode())
				+ String.valueOf(user.getUserPassword().hashCode());
		hash = hash.replace("-", "");
		return hash;
	}

	private User create(User user) {
		User existentUser = em.find(User.class, user.getUserId());
		if (existentUser != null) {
			throw new IllegalArgumentException("User already exists");
		}
		user.setUserDeleted(false);
		return em.merge(user);
	}

	/**
	 * Updates a user.
	 * 
	 * @param user
	 *            A modified {@link User} to be persisted.
	 * @return The persistent version of the {@link User} modified.
	 * @throws NullPointerException
	 *             if the {@link User} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link User} doesn't exist.
	 * @throws IllegalArgumentException
	 *             if the password is distinct to the persisted.
	 * @throws EJBAccessException
	 *             if user to update isn't the current principal.
	 * @throws EJBAccessException
	 *             if the current {@link User} isn't registered.
	 */
	@RolesAllowed("registered")
	public User update(User user) {
		requireNonNull(user, "User can't be null");

		User existentUser = em.find(User.class, user.getUserId());
		if (existentUser == null) {
			throw new IllegalArgumentException("User does not exist");
		}

		User actualUser = this.findByLogin(currentUser.getName()).get(0);
		if (existentUser.getUserLogin().equals(actualUser.getUserLogin())) {
			if (!existentUser.getUserPassword().equals(user.getUserPassword())) {
				throw new IllegalArgumentException("User password can't be changed. Please use updatePassword()");
			}
		} else {
			throw new EJBAccessException("User is not the current principal");
		}
		return em.merge(user);

	}

	/**
	 * Updates a user password.
	 * 
	 * @param user
	 *            A user to modify the password.
	 * @param newUserPassword
	 *            The new password to add.
	 * @return The persistent version of the {@link User} modified.
	 * @throws NullPointerException
	 *             if the {@link User} is <code>null</code>.
	 * @throws NullPointerException
	 *             if the newUserPassword is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link User} doesn't exist.
	 * @throws EJBAccessException
	 *             if user to update isn't the current principal.
	 * @throws EJBAccessException
	 *             if the current {@link User} isn't registered.
	 */
	@RolesAllowed("registered")
	public User updatePassword(User user, String newUserPassword) {
		requireNonNull(user, "User can't be null");
		requireNonNull(newUserPassword, "New password can't be null");

		User existentUser = em.find(User.class, user.getUserId());
		if (existentUser == null) {
			throw new IllegalArgumentException("User does not exist");
		}

		User actualUser = this.findByLogin(currentUser.getName()).get(0);
		if (existentUser.getUserLogin().equals(actualUser.getUserLogin())) {
			user.setUserPassword(newUserPassword);
			return em.merge(user);
		} else {
			throw new EJBAccessException("User is not the current principal");
		}
	}

	/**
	 * Deletes a {@link User}.
	 * 
	 * @param userId.
	 *            The id of the {@link User} to be deleted.
	 * @throws NullPointerException
	 *             if the {@link User} does not exist.
	 * @throws EJBAccessException
	 *             if user to delete isn't the current principal.
	 * @throws EJBAccessException
	 *             if the current {@link User} isn't registered.
	 * 
	 */
	@RolesAllowed("registered")
	public void delete(long userId) {
		User toDelete = this.find(userId);
		requireNonNull(toDelete, "User can't be null");

		User actualUser = this.findByLogin(currentUser.getName()).get(0);
		if (toDelete.getUserLogin().equals(actualUser.getUserLogin())) {
			toDelete.setUserDeleted(true);
			em.merge(toDelete);
		} else {
			throw new EJBAccessException("User is not the current principal");
		}
	}

	/**
	 * Returns the {@link User} identified by id. If there is no {@link User}
	 * with the specified id, <code>null</code> will be returned.
	 * 
	 * @param userLogin.
	 *            The id of the user to be returned.
	 * @return The {@link User} with the given id.
	 *
	 * @throws IllegalArgumentException
	 *             if {@link User} login is <code>null</code>
	 */
	public List<User> findByLogin(String userLogin) {
		if (userLogin == null) {
			throw new IllegalArgumentException("User login can't be null");
		}

		return em.createQuery("SELECT u FROM User u WHERE userLogin = :userLogin", User.class)
				.setParameter("userLogin", userLogin).getResultList();
	}

	/**
	 * Returns the {@link User} identified by token. If there is no {@link User}
	 * with the specified token, <code>null</code> will be returned.
	 * 
	 * @param userToken.
	 *            The token of the user to be returned.
	 * @return The {@link User} with the given token.
	 *
	 * @throws IllegalArgumentException
	 *             if {@link User} token is <code>null</code>
	 */
	public List<User> findByToken(String userToken) {
		if (userToken == null) {
			throw new IllegalArgumentException("User token can't be null");
		}

		return em.createQuery("SELECT u FROM User u WHERE userToken = :userToken", User.class)
				.setParameter("userToken", userToken).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link User} with the specified email.
	 * 
	 * @param userEmail.
	 *            The email of the {@link User} to be searched.
	 * @return A {@link List} of {@link User} with the specified email.
	 *
	 * @throws IllegalArgumentException
	 *             if {@link User} email is <code>null</code>
	 */
	public List<User> findByEmail(String userEmail) {
		if (userEmail == null) {
			throw new IllegalArgumentException("User email can't be null");
		}

		return em.createQuery("SELECT u FROM User u WHERE userEmail = :userEmail", User.class)
				.setParameter("userEmail", userEmail).getResultList();
	}

	/**
	 * Register a new {@link User}.
	 * 
	 * @param user.
	 *            A new {@link User} to be registered.
	 * @return A new {@link User} is created with an notification sent to the
	 *         email.
	 * 
	 * @throws IllegalArgumentException
	 *             if {@link User} is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if {@link User} login is already in use.
	 * @throws IllegalArgumentException
	 *             if {@link User} email is already in use.
	 */
	public User registerUser(User user) {
		List<User> toRegister = null;

		if (user == null)
			throw new IllegalArgumentException("User can't be null");

		toRegister = findByLogin(user.getUserLogin());
		if (!toRegister.isEmpty()) {
			throw new IllegalArgumentException("Login is already in use");
		}

		toRegister = findByEmail(user.getUserEmail());
		if (!toRegister.isEmpty()) {
			throw new IllegalArgumentException("Email is already in use");
		}

		user.setUserToken(this.generateHash(user));
		User registeredUser = create(user);

		this.sendConfirmationEmail(registeredUser);

		return user;

	}

	/**
	 * Returns the {@link User} identified by id. If there is no {@link User}
	 * with the specified id, <code>null</code> will be returned.
	 * 
	 * @param userId
	 *            The id of the user to be returned.
	 * @return The {@link User} with the given id.
	 */
	public User find(long userId) {
		return em.find(User.class, userId);
	}

	/**
	 * Activates the account of a user.
	 * 
	 * @param userId
	 *            The id of the user to be activated.
	 * @return The persistence version of the User activated or null if there is
	 *         not an User with the specified userId or if the user was already
	 *         activated.
	 * @throws IllegalArgumentException
	 *             if the userId is less than one.
	 * @throws EJBAccessException
	 *             if current user is not anonymous.
	 */
	public User activateUser(Long userId) {
		if (currentUser != null && currentUser.getName() != null && !currentUser.getName().equals("anonymous"))
			throw new EJBAccessException("User can't be authenticated for do this action");

		if (userId <= 0)
			throw new IllegalArgumentException("User id can't be less than one");

		User user = this.find(userId);

		if (user == null || user.isUserConfirmed())
			return null;
		else {
			user.setUserConfirmed(true);
			return em.merge(user);
		}
	}

}
