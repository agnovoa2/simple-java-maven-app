package ligaaas.teamc.jsf;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.User;
import ligaaas.teamc.service.CompetitionEJB;
import ligaaas.teamc.service.UserEJB;

/**
 * ManagedBean for userCompetitions
 * 
 * @author teamC
 *
 */

@Named("userCompetitions")
@RequestScoped
public class UserCompetitionsManagedBean {

	@Inject
	private UserEJB userEJB;

	@Inject
	private CompetitionEJB competitionEJB;

	@Inject
	private Principal currentUserPrincipal;

	private User user;

	private List<Competition> competitions;

	private List<Competition> filteredCompetitions;

	private boolean showTable;

	/**
	 * 
	 * Retrieves the Competitions from the database
	 * 
	 */
	private void retrieveCompetitions() {
		this.competitions = competitionEJB.findByUser(this.user);
		for (Competition c : new ArrayList<Competition>(this.competitions)) {
			if (c.getCompetitionDeleted()) {
				this.competitions.remove(c);
			}
		}
		this.showTable = competitions.size() > 0;
	}

	@PostConstruct
	public void init() {

		List<User> dbUsers = userEJB.findByLogin(currentUserPrincipal.getName());
		if (dbUsers.size() > 0) {
			this.user = dbUsers.get(0);
		}

		if (this.user != null) {
			retrieveCompetitions();
		}

	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public boolean getshowTable() {
		return this.showTable;
	}

	public List<Competition> getFilteredCompetitions() {
		return filteredCompetitions;
	}

	public void setFilteredCompetitions(List<Competition> filteredCompetitions) {
		this.filteredCompetitions = filteredCompetitions;
	}

	/**
	 * 
	 * Deletes a Competition
	 * 
	 */
	public void deleteCompetition() {
		long competitionId = Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("competitionId"));
		retrieveCompetitions();
		competitionEJB.delete(competitionId);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/views/private/userCompetitions.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
