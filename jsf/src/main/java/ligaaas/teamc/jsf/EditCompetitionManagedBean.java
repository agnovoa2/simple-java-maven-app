package ligaaas.teamc.jsf;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.CompetitionType;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.service.CompetitionEJB;
import ligaaas.teamc.service.UserEJB;

/**
 * ManagedBean for editCompetition
 * 
 * @author teamC
 *
 */

@Named("editCompetition")
@RequestScoped
public class EditCompetitionManagedBean {
	@Inject
	private CompetitionEJB competitionEJB;

	@Inject
	private UserEJB userEJB;

	private Competition competition;

	private String competitionType;

	private String competitionSportType;

	private long competitionUserId;

	@PostConstruct
	public void init() {
		try {
			int competitionId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("competitionId"));
			this.competition = competitionEJB.find(competitionId);
			this.competitionType = this.competition.getCompetitionType().toString();
			this.competitionSportType = this.competition.getCompetitionSportType().toString();
			//this.competitionUserId = this.competition.getCompetitionUser().getUserId();
		} catch (Exception e) {
			this.competition = new Competition();
		}
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getCompetitionType() {
		return competitionType;
	}

	public void setCompetitionType(String competitionType) {
		this.competitionType = competitionType;
	}

	public String getCompetitionSportType() {
		return competitionSportType;
	}

	public void setCompetitionSportType(String competitionSportType) {
		this.competitionSportType = competitionSportType;
	}

	public long getCompetitionUserId() {
		return competitionUserId;
	}

	public void setCompetitionUserId(long competitionUserId) {
		this.competitionUserId = competitionUserId;
	}

	/**
	 * Update Competition.
	 * 
	 * Updates the current Competition and redirects to the index
	 * 
	 * @return index redirect to the index.xhtml page.
	 */
	public String updateCompetition() {
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

		Competition dbCompetition = competitionEJB.find(this.competition.getCompetitionId());
		dbCompetition.setCompetitionName(this.competition.getCompetitionName());
		dbCompetition.setCompetitionShortName(this.competition.getCompetitionShortName());
		dbCompetition.setCompetitionDescription(this.competition.getCompetitionName());
		dbCompetition.setCompetitionSportType(this.competition.getCompetitionSportType());
		dbCompetition.setCompetitionType(this.competition.getCompetitionType());
		dbCompetition.setCompetitionOpen(this.competition.getCompetitionOpen());
		dbCompetition.setCompetitionMinTeams(this.competition.getCompetitionMinTeams());
		dbCompetition.setCompetitionMaxTeams(this.competition.getCompetitionMaxTeams());
		dbCompetition.setCompetitionPublic(this.competition.getCompetitionPublic());
		dbCompetition.setCompetitionDeleted(this.competition.getCompetitionDeleted());

		competitionEJB.update(dbCompetition);
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
