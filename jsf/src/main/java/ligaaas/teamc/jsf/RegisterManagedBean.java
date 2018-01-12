package ligaaas.teamc.jsf;

import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ligaaas.teamc.domain.User;
import ligaaas.teamc.service.UserEJB;

/**
 * ManagedBean for register
 * 
 * @author teamC
 *
 */

@Named("register")
@RequestScoped
public class RegisterManagedBean {

	@Inject
	private HttpServletRequest request;

	@Inject
	private UserEJB userEJB;

	
	private String userLogin;

	private String userName;

	private String userSurname;

	private String userPassword;

	private String userPasswordConfirmation;

	private String userIdentificationDocument;

	private String userPhone;

	private String userEmail;
	

	@Inject
	private Principal currentUserPrincipal;

	private boolean error = false;

	private boolean registerSuccess = false;

	private boolean activateSuccess = false;

	private String registerSuccessMessage = "";
	private String registerSuccessTitle = "";

	

	/**
	 * Validates the parameter token. If the token is valid, activate the
	 * corresponding user.
	 * 
	 */
	@PostConstruct
	public void checkToken() {
		String token = request.getParameter("token");
		if(token != null) {
			List<User> user = userEJB.findByToken(token);
			if(user.size() > 0) {
				User u = userEJB.activateUser(user.get(0).getUserId());
				activateSuccess = true;
			} else {
				activateSuccess = false;
			}
		}
	}

	/**
	 * Register a user and return the id of the register view.
	 * 
	 * @return the id of the register view.
	 */
	public String doRegisterUser() {
		if (userPassword.equals(userPasswordConfirmation)) {
			try {
				User user = new User();
				user.setUserLogin(userLogin);
				user.setUserName(userName);
				user.setUserSurname(userSurname);
				user.setUserPassword(userPassword);
				user.setUserIdentificationDocument(userIdentificationDocument);
				user.setUserPhone(userPhone);
				user.setUserEmail(userEmail);
				user.setUserConfirmed(false);
				userEJB.registerUser(user);
				registerSuccess = true;

			} catch (Exception e) {
				error = true;
			}

		} else
			error = true;
		return this.getViewId();
	}

	private String getViewId() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}

	/**
	 * Return the Register Success Title
	 * 
	 * @return title for the success message
	 */
	public String getRegisterSuccessTitle() {

		registerSuccessTitle = "Confirmation email sent!";
		return registerSuccessTitle;
	}

	/**
	 * Return the Register Success Message
	 * 
	 * @return the success message
	 */
	public String getRegisterSuccessMessage() {
		registerSuccessMessage = "We've sent an email to " + this.userEmail
				+ ". In the email you'll find a link to confirm your account.";
		return registerSuccessMessage;
	}

	/**
	 * Return the User login.
	 * 
	 * @return login of the User.
	 */
	public String getUserLogin() {
		return userLogin;
	}

	/**
	 * Set the user login.
	 * 
	 * @param userLogin
	 *            The new user login.
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * Return the User name.
	 * 
	 * @return name of the User.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the user name.
	 * 
	 * @param userName
	 *            The new user name.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Return the User surname.
	 * 
	 * @return surname of the User.
	 */
	public String getUserSurname() {
		return userSurname;
	}

	/**
	 * Set the user surname.
	 * 
	 * @param userSurname
	 *            The new user surname.
	 */
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	/**
	 * Return the User password.
	 * 
	 * @return password of the User.
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Set the user password.
	 * 
	 * @param userPassword
	 *            The new user password.
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * Return the User password confirmation.
	 * 
	 * @return password confirmation of the User.
	 */
	public String getUserPasswordConfirmation() {
		return userPasswordConfirmation;
	}

	/**
	 * Set the user password confirmation.
	 * 
	 * @param userPasswordConfirmation
	 *            The new user password confirmation.
	 */
	public void setUserPasswordConfirmation(String userPasswordConfirmation) {
		this.userPasswordConfirmation = userPasswordConfirmation;
	}

	/**
	 * Return the User identification document.
	 * 
	 * @return identification document confirmation of the User.
	 */
	public String getUserIdentificationDocument() {
		return userIdentificationDocument;
	}

	/**
	 * Set the user Identification Document.
	 * 
	 * @param userIdentificationDocument
	 *            The new user Identification Document.
	 */
	public void setUserIdentificationDocument(String userIdentificationDocument) {
		this.userIdentificationDocument = userIdentificationDocument;
	}

	/**
	 * Return the User phone.
	 * 
	 * @return phone confirmation of the User.
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * Set the user Phone.
	 * 
	 * @param userPhone
	 *            The new user Phone.
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * Return the User email.
	 * 
	 * @return email confirmation of the User.
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Set the user Email.
	 * 
	 * @param userEmail
	 *            The new user Email.
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Return the current User.
	 * 
	 * @return current user.
	 */
	public Principal getCurrentUser() {
		return this.currentUserPrincipal;
	}

	/**
	 * Return true if there are errors in the register and false otherwise.
	 * 
	 * @return true if there are errors in the register and false otherwise.
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Return true if the user was registered and false otherwise.
	 * 
	 * @return true if the user was registered and false otherwise.
	 */
	public boolean isRegisterSuccess() {
		return registerSuccess;
	}

	/**
	 * Return true if token parameter is valid and the user was successfully
	 * activated, false otherwise.
	 * 
	 * @return true if token parameter is valid and the user was successfully
	 *         activated, false otherwise.
	 */
	public boolean isActivateSuccess() {
		return activateSuccess;
	}

	/**
	 * Return the id of the login view.
	 * 
	 * @return the id of the login ("login.xhtml")
	 */
	public String doRedirectToLogin() {
		return "login.xhtml";
	}
}
