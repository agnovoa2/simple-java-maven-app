package ligaaas.teamc.jsf;

import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.User;
import ligaaas.teamc.service.HeadQuarterEJB;
import ligaaas.teamc.service.UserEJB;

/**
 * ManagedBean for userHeadQuarters
 * 
 * @author teamC
 *
 */

@Named("userHeadQuarters")
@RequestScoped
public class UserHeadQuartersManagedBean {

	@Inject
	private UserEJB userEJB;

	@Inject
	private HeadQuarterEJB headQuarterEJB;

	@Inject
	private Principal currentUserPrincipal;

	private User user;

	private List<HeadQuarter> headQuarters;
	
	private boolean showTable;

	@PostConstruct
	public void init() {
		User currentUser = null;
		List<User> dbUsers = userEJB.findByLogin(currentUserPrincipal.getName());
		if (dbUsers.size() > 0) {
			currentUser = dbUsers.get(0);
		}

		if (currentUser != null) {
			this.headQuarters = headQuarterEJB.findByUser(currentUser);
			this.showTable = headQuarters.size() > 0;
		}
	}

	public List<HeadQuarter> getHeadQuarters() {
		return headQuarters;
	}

	public boolean getShowTable() {
		return showTable;
	}

}
