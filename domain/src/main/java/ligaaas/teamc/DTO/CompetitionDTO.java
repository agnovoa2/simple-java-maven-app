package ligaaas.teamc.DTO;

import ligaaas.teamc.domain.Contact;
import ligaaas.teamc.domain.HeadQuarter;

/**
 * DTO of a Competition
 *
 * @author teamC
 *
 */

public class CompetitionDTO {
	private String competitionName;
	private String competitionShortName;
	private String competitionDescription;
	private String competitionSportType;
	private String competitionType;
	private int competitionMinTeams;
	private int competitionMaxTeams;
	private Boolean competitionOpen;
	private ContactDTO competitionContact;
	private HeadQuarterDTO competitionHeadQuarter;

	public CompetitionDTO() {
	}
	
	/**
	 * Returns the name of the competition.
	 * 
	 * @return the name of the competition.
	 */
	public String getCompetitionName() {
		return competitionName;
	}

	/**
	 * Sets the name of the competition.
	 * 
	 * @param competitionName
	 *            the name of the competition. 
	 */
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	/**
	 * Returns the short name of the competition.
	 * 
	 * @return the short name of the competition.
	 */
	public String getCompetitionShortName() {
		return competitionShortName;
	}

	/**
	 * Sets the short name of the competition.
	 * 
	 * @param competitionShortName
	 *            the short name of the competition.
	 */
	public void setCompetitionShortName(String competitionShortName) {
		this.competitionShortName = competitionShortName;
	}

	/**
	 * Returns the description of the competition.
	 * 
	 * @return the description of the competition.
	 */
	public String getCompetitionDescription() {
		return competitionDescription;
	}

	/**
	 * Sets the description of the competition.
	 * 
	 * @param competitionDescription
	 *            the description of the competition.
	 */
	public void setCompetitionDescription(String competitionDescription) {
		this.competitionDescription = competitionDescription;
	}

	/**
	 * Returns the sport type of the competition.
	 * 
	 * @return the sport type of the competition.
	 */
	public String getCompetitionSportType() {
		return competitionSportType;
	}

	/**
	 * Sets the sportType of the competition.
	 * 
	 * @param competitionSportType
	 *            the sportType of the competition. 
	 */
	public void setCompetitionSportType(String competitionSportType) {
		this.competitionSportType = competitionSportType;
	}

	/**
	 * Returns the competition type.
	 * 
	 * @return the competition type.
	 */
	public String getCompetitionType() {
		return competitionType;
	}

	/**
	 * Sets the type of the competition.
	 * 
	 * @param competitionType
	 *            the type of the competition.
	 */
	public void setCompetitionType(String competitionType) {
		this.competitionType = competitionType;
	}

	/**
	 * Returns the min number of teams of the competition.
	 * 
	 * @return the min number of teams of the competition.
	 */
	public int getCompetitionMinTeams() {
		return competitionMinTeams;
	}

	/**
	 * Sets the min number of teams of the competition.
	 * 
	 * @param competitionMinTeams
	 *            min number of teams of the competition.
	 */
	public void setCompetitionMinTeams(int competitionMinTeams) {
		this.competitionMinTeams = competitionMinTeams;
	}

	/**
	 * Returns the max number of teams of the competition.
	 * 
	 * @return the max number of teams of the competition.
	 */
	public int getCompetitionMaxTeams() {
		return competitionMaxTeams;
	}

	/**
	 * Sets the max number of teams of the competition.
	 * 
	 * @param competitionMaxTeams
	 *            max number of teams of the competition.
	 */
	public void setCompetitionMaxTeams(int competitionMaxTeams) {
		this.competitionMaxTeams = competitionMaxTeams;
	}

	/**
	 * Returns if the competition is open.
	 * 
	 * @return if the competition is open.
	 */
	public Boolean getCompetitionOpen() {
		return competitionOpen;
	}

	/**
	 * Sets if competition is open.
	 * 
	 * @param competitionOpen
	 *            if competition is open.
	 */
	public void setCompetitionOpen(Boolean competitionOpen) {
		this.competitionOpen = competitionOpen;
	}

	/**
	 * Returns the competition {@link Contact}.
	 * 
	 * @return the competition {@link Contact}.
	 */
	public ContactDTO getContact() {
		return competitionContact;
	}

	/**
	 * Sets the competition {@link Contact}.
	 * 
	 * @param competitionContact
	 *            the contact of the competition. This parameter must be a
	 *            {@code Contact}.
	 */
	public void setContact(ContactDTO competitionContact) {
		this.competitionContact = competitionContact;
	}

	/**
	 * Returns the competition {@link HeadQuarter}.
	 * 
	 * @return the competition {@link HeadQuarter}.
	 */
	public HeadQuarterDTO getHeadQuarter() {
		return competitionHeadQuarter;
	}

	/**
	 * Sets the competition {@link HeadQuarter}.
	 * 
	 * @param competitionHeadQuarter
	 *            the Head quarter of the competition. This parameter must be a
	 *            {@link HeadQuarterDTO}.
	 */
	public void setHeadQuarter(HeadQuarterDTO competitionHeadQuarter) {
		this.competitionHeadQuarter = competitionHeadQuarter;
	}
	
	
}
