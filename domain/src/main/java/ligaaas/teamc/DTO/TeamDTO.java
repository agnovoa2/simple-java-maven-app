package ligaaas.teamc.DTO;

import ligaaas.teamc.domain.SportType;

/**
 * DTO for Team
 * 
 * @author teamC
 *
 */
public class TeamDTO {

	private long teamId;
	private String teamName;
	private String teamShortName;
	private String teamDescription;
	private SportType teamSportType;
	private Boolean teamOpen;
	private int teamMinPlayers;
	private int teamMaxPlayers;
	private Boolean teamPublic;
	private ContactDTO teamContact;
	private HeadQuarterDTO teamHeadQuarter;

	/**
	 * Returns the id of the Team.
	 * 
	 * @return the id of the Team.
	 */
	public long getTeamId() {
		return teamId;
	}

	/**
	 * Sets the id of the Team.
	 * 
	 * @param teamId
	 *            the id of the Team.
	 */
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	/**
	 * Returns the name of the Team.
	 * 
	 * @return the name of the Team.
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Sets the name of the Team.
	 * 
	 * @param teamName
	 *            the name of the Team.
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * Returns the short name of the Team.
	 * 
	 * @return the short name of the Team.
	 */
	public String getTeamShortName() {
		return teamShortName;
	}

	/**
	 * Sets the short name of the Team.
	 * 
	 * @param teamShortName
	 *            the short name of the Team.
	 */
	public void setTeamShortName(String teamShortName) {
		this.teamShortName = teamShortName;
	}

	/**
	 * Returns the description of the Team.
	 * 
	 * @return the description of the Team.
	 */
	public String getTeamDescription() {
		return teamDescription;
	}

	/**
	 * Sets the description of the Team.
	 * 
	 * @param teamDescription
	 *            the description of the Team.
	 */
	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}

	/**
	 * Returns the {@code SportType} of the Team.
	 * 
	 * @return the {@code SportType} of the Team.
	 */
	public SportType getTeamSportType() {
		return teamSportType;
	}

	/**
	 * Sets the {@code SportType} of the Team.
	 * 
	 * @param teamSportType
	 *            the {@code SportType} of the Team.
	 */
	public void setTeamSportType(SportType teamSportType) {
		this.teamSportType = teamSportType;
	}

	/**
	 * Returns if team is open or no.
	 * 
	 * @return if it's open the team.
	 */
	public Boolean getTeamOpen() {
		return teamOpen;
	}

	/**
	 * Sets if the Team is open.
	 * 
	 * @param teamOpen
	 *            sets if the Team is open
	 */
	public void setTeamOpen(Boolean teamOpen) {
		this.teamOpen = teamOpen;
	}

	/**
	 * Returns the minimum number of players of the Team.
	 * 
	 * @return the minimum number of players of the Team.
	 */
	public int getTeamMinPlayers() {
		return teamMinPlayers;
	}

	/**
	 * Sets the minimum number of players of the Team.
	 * 
	 * @param teamMinPlayers
	 *            the minimum number of players of the Team.
	 */
	public void setTeamMinPlayers(int teamMinPlayers) {
		this.teamMinPlayers = teamMinPlayers;
	}

	/**
	 * Returns the maximum number of players of the Team.
	 * 
	 * @return the maximum number of players of the Team.
	 */
	public int getTeamMaxPlayers() {
		return teamMaxPlayers;
	}

	/**
	 * Sets the maximum number of players of the Team.
	 * 
	 * @param teamMaxPlayers
	 *            the maximum number of players of the Team.
	 */
	public void setTeamMaxPlayers(int teamMaxPlayers) {
		this.teamMaxPlayers = teamMaxPlayers;
	}

	/**
	 * Returns if the team is public.
	 * 
	 * @return if it's public the team.
	 */
	public Boolean getTeamPublic() {
		return teamPublic;
	}

	/**
	 * Sets if the Team is public.
	 * 
	 * @param teamPublic
	 *            sets if the Team is public
	 */
	public void setTeamPublic(Boolean teamPublic) {
		this.teamPublic = teamPublic;
	}

	/**
	 * Returns the {@code ContactDTO} of the Team.
	 * 
	 * @return the {@code ContactDTO} of the Team.
	 */
	public ContactDTO getTeamContact() {
		return teamContact;
	}

	/**
	 * Sets the {@code ContactDTO} of the Team.
	 * 
	 * @param teamContact
	 *            the {@code ContactDTO} of the Team.
	 */
	public void setTeamContact(ContactDTO teamContact) {
		this.teamContact = teamContact;
	}

	/**
	 * Returns the team {@link HeadQuarterDTO}.
	 * 
	 * @return the team {@link HeadQuarterDTO}.
	 */
	public HeadQuarterDTO getTeamHeadQuarter() {
		return teamHeadQuarter;
	}

	/**
	 * Sets the team {@link HeadQuarterDTO}.
	 * 
	 * @param teamHeadQuarter
	 *            the Head quarter of the team. This parameter must be a
	 *            {@link HeadQuarterDTO}.
	 */
	public void setTeamHeadQuarter(HeadQuarterDTO teamHeadQuarter) {
		this.teamHeadQuarter = teamHeadQuarter;
	}

}
