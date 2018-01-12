package ligaaas.teamc.jsf;

import java.text.ParseException;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ligaaas.teamc.domain.User;
import ligaaas.teamc.service.UserEJB;

/**
 * ManagedBean for userProfile
 * 
 * @author teamC
 *
 */

@Named("userCreateProfile")
@RequestScoped
public class UserCreateProfileManagedBean {

	@Inject
	private UserEJB userEJB;

	private User user;
	private String userPasswordConfirmation;

	/**
	 * Gets the current user and loads it's information
	 * 
	 */
	@PostConstruct
	public void init() {
		this.user = new User();
	}

	/**
	 * Return the User.
	 * 
	 * @return user the current User.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Update User.
	 * 
	 * Updates the current User and redirects to the index
	 * 
	 * @return index redirect to the index.xhtml page.
	 */
	public String createUser() throws ParseException {
		if (user.getUserPassword().equals(userPasswordConfirmation)) {
			userEJB.registerUser(user);
		}
		return "index";
	}
		


	public String getUserPasswordConfirmation() {
		return userPasswordConfirmation;
	}

	public void setUserPasswordConfirmation(String userPasswordConfirmation) {
		this.userPasswordConfirmation = userPasswordConfirmation;
	}
}
