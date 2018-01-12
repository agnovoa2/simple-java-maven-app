package ligaaas.teamc.jsf;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.CompetitionType;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.User;
import ligaaas.teamc.service.CompetitionEJB;
import ligaaas.teamc.service.UserEJB;

/**
 * ManagedBean for editCompetition
 * 
 * @author teamC
 *
 */

@Named("newCompetition")
@RequestScoped
public class NewCompetitionManagedBean {
	@Inject
	private CompetitionEJB competitionEJB;

	@Inject
	private UserEJB userEJB;

	@Inject
	private Principal currentUserPrincipal;

	private User user;

	private Competition competition;

	private String competitionSportType;

	private String competitionType;

	@PostConstruct
	public void init() {
		this.competition = new Competition();
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getCompetitionSportType() {
		return competitionSportType;
	}

	public void setCompetitionSportType(String competitionSportType) {
		this.competitionSportType = competitionSportType;
	}

	public String getCompetitionType() {
		return competitionType;
	}

	public void setCompetitionType(String competitionType) {
		this.competitionType = competitionType;
	}

	/**
	 * Create Competition.
	 * 
	 * Creates the current Competition and redirects to the index
	 * 
	 * @return index redirect to the index.xhtml page.
	 */
	public String createCompetition() {
		switch (this.competitionType) {
		case "Simple":
			this.competition.setCompetitionType(CompetitionType.SIMPLE);
			break;
		case "Double":
			this.competition.setCompetitionType(CompetitionType.DOUBLE);
			break;
		default:
			this.competition.setCompetitionType(CompetitionType.SIMPLE);
			break;
		}

		switch (this.competitionSportType) {
		case "Football 11":
			this.competition.setCompetitionSportType(SportType.FOOTBALL11);
			break;
		case "Football 7":
			this.competition.setCompetitionSportType(SportType.FOOTBALL7);
			break;
		case "Basketball":
			this.competition.setCompetitionSportType(SportType.BASKETBALL);
			break;
		case "Videogames":
			this.competition.setCompetitionSportType(SportType.VIDEOGAMES);
			break;
		default:
			this.competition.setCompetitionSportType(SportType.FOOTBALL11);
			break;
		}

		this.competition.setCompetitionDeleted(false);
		List<User> dbUsers = userEJB.findByLogin(currentUserPrincipal.getName());
		if (dbUsers.size() > 0) {
			this.user = dbUsers.get(0);
		}
		this.competition.setCompetitionUser(this.user);

		competitionEJB.create(this.competition);

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/views/private/userCompetitions.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

}
