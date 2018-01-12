package ligaaas.teamc.jsf;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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

@Named("userProfile")
@RequestScoped
public class UserProfileManagedBean {

	@Inject
	private UserEJB userEJB;

	@Inject
	private Principal currentUserPrincipal;

	private User user;

	/**
	 * Gets the current user and loads it's information
	 * 
	 */
	@PostConstruct
	public void init() {
		List<User> dbUsers = userEJB.findByLogin(currentUserPrincipal.getName());
		if (dbUsers.size() > 0) {
			this.user = dbUsers.get(0);
		}
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
	public String updateUser() throws ParseException {
		userEJB.update(user);
		return "index";
	}

}
