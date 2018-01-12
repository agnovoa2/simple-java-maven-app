package ligaaas.teamc.domain;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.isTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Team {

	@Id
	@Column(name = "teamId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long teamId;

	@NotNull
	@Size(min = 5, max = 60)
	private String teamName;
	@NotNull
	@Size(min = 2, max = 10)
	private String teamShortName;
	@Size(min = 10, max = 240)
	private String teamDescription;
	@NotNull
	@Enumerated(EnumType.STRING)
	private SportType teamSportType;
	@NotNull
	private Boolean teamOpen;
	@Min(1)
	@NotNull
	private int teamMinPlayers;
	@Min(1)
	@NotNull
	private int teamMaxPlayers;
	@NotNull
	private Boolean teamPublic;
	@NotNull
	private Boolean teamDeleted;
	@JoinColumn(name = "headQuarter_HeadQuarterId")
	@ManyToOne(fetch = FetchType.LAZY)
	private HeadQuarter teamHeadQuarter;
	@JoinColumn(name = "contact_ContactId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Contact teamContact;
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "team_TeamId", referencedColumnName = "teamId"), inverseJoinColumns = @JoinColumn(name = "competition_CompetitionId", referencedColumnName = "competitionId"))
	private List<Competition> teamCompetitions = new ArrayList<Competition>();
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "team_TeamId", referencedColumnName = "teamId"), inverseJoinColumns = @JoinColumn(name = "player_PlayerId", referencedColumnName = "playerId"))
	private List<Player> teamPlayers = new ArrayList<Player>();
	@JoinColumn(name = "user_UserId")
	@ManyToOne(fetch = FetchType.LAZY)
	private User teamUser;

	/**
	 * Creates a new instance of {@code Team}.
	 * 
	 * @param teamId
	 *            the id of the team. This parameter must be a non empty and non
	 *            {@code null} long .
	 * @param teamName
	 *            the name of the team. This parameter must be a non empty and
	 *            non {@code null} String.
	 * @param teamShortName
	 *            the short name of the team. This parameter must be a non empty
	 *            and non {@code null} String.
	 * @param teamDescription
	 *            the description of the team
	 * @param teamSportType
	 *            the sport type of the team. This parameter must be a non empty
	 *            and non {@code null} {@code SportType}.
	 * @param teamOpen
	 *            if the team is open. This parameter must be a non {@code null}
	 *            Boolean.
	 * @param teamMinPlayers
	 *            the min number of players of the team. This parameter must be
	 *            a non empty and non {@code null} int.
	 * @param teamMaxPlayers
	 *            the max number of players of the team. This parameter must be
	 *            a non empty and non {@code null} int.
	 * @param teamPublic
	 *            if the team is public. This parameter must be a non empty and
	 *            non {@code null} boolean.
	 * @param teamDeleted
	 *            if the team is logicaly deleted. This parameter must be a non
	 *            {@code null} boolean.
	 * @param teamHeadQuarter
	 *            the Head Quarter of the team. This parameter must be a
	 *            {@code HeadQuarter}
	 * @param teamContact
	 *            the Contact of the team. This parameter must be a
	 *            {@code Contact}
	 * 
	 * @param teamUser
	 *            the User of the team. This parameter must be a {@code User}.
	 * 
	 * @param teamUser
	 *            the User of the team. This parameter must be a {@code User}.
	 * 
	 * @param teamUser
	 *            the User of the team. This parameter must be a {@code User}.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed as the value for any
	 *             parameter.
	 * @throws IllegalArgumentException
	 *             if value provided for any parameter is not valid according to
	 *             its description.
	 */
	public Team(long teamId, String teamName, String teamShortName, String teamDescription, SportType teamSportType,
			Boolean teamOpen, int teamMinPlayers, int teamMaxPlayers, Boolean teamPublic, Boolean teamDeleted,
			HeadQuarter teamHeadQuarter, Contact teamContact, User teamUser) {
		setTeamId(teamId);
		setTeamName(teamName);
		setTeamShortName(teamShortName);
		setTeamDescription(teamDescription);
		setTeamSportType(teamSportType);
		setTeamOpen(teamOpen);
		setTeamMinPlayers(teamMinPlayers);
		setTeamMaxPlayers(teamMaxPlayers);
		setTeamPublic(teamPublic);
		setTeamDeleted(teamDeleted);
		setTeamHeadQuarter(teamHeadQuarter);
		setTeamContact(teamContact);
		setTeamUser(teamUser);
	}

	/**
	 * Creates a new empty instance of {@code Team}.
	 */
	public Team() {
	}

	/**
	 * Returns the id of the team.
	 * 
	 * @return the id of the team.
	 */
	public long getTeamId() {
		return teamId;
	}

	/**
	 * Sets the id of the team.
	 * 
	 * @param teamId
	 *            the id of the team. This parameter must be a non empty and non
	 *            {@code null} long.
	 * 
	 */
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	/**
	 * Returns the name of the team.
	 * 
	 * @return the name of the team.
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Sets the name of the team.
	 * 
	 * @param teamName
	 *            the name of the team. This parameter must be a non empty and
	 *            non {@code null} string with a maximun length of 60 chars and
	 *            a minumun length of 10 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setTeamName(String teamName) {
		requireNonNull(teamName, "team name can't be null");
		inclusiveBetween(5, 60, teamName.length(), "team name must have a length between 5 and 60");
		this.teamName = teamName;
	}

	/**
	 * Returns the short name of the team.
	 * 
	 * @return the short name of the team.
	 */
	public String getTeamShortName() {
		return teamShortName;
	}

	/**
	 * Sets the short name of the team.
	 * 
	 * @param teamShortName
	 *            the short name of the team. This parameter must be a non empty
	 *            and non {@code null} string with a maximun length of 10 chars
	 *            and a minumun length of 2 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setTeamShortName(String teamShortName) {
		requireNonNull(teamShortName, "team short name can't be null");
		inclusiveBetween(2, 10, teamShortName.length(), "team short name must have a length between 2 and 10");
		this.teamShortName = teamShortName;
	}

	/**
	 * Returns the description of the team.
	 * 
	 * @return the description of the team.
	 */
	public String getTeamDescription() {
		return teamDescription;
	}

	/**
	 * Sets the description of the team.
	 * 
	 * @param teamDescription
	 *            the description of the team. This parameter must be a non
	 *            empty string with a maximun length of 240 chars and a minumun
	 *            length of 10 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setTeamDescription(String teamDescription) {
		if(teamDescription != null) {
			inclusiveBetween(10, 240, teamDescription.length(), "team description must have a length between 10 and 240");
		}
		this.teamDescription = teamDescription;
	}

	/**
	 * Returns the sport type of the team.
	 * 
	 * @return the sport type of the team.
	 */
	public SportType getTeamSportType() {
		return teamSportType;
	}

	/**
	 * Sets the description of the team.
	 * 
	 * @param teamSportType
	 *            the description of the team. This parameter must be a non
	 *            empty and non {@code null} {@code SportType}.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setTeamSportType(SportType teamSportType) {
		requireNonNull(teamSportType, "team sport type can't be null");
		this.teamSportType = teamSportType;
	}

	/**
	 * Returns if the team is open.
	 * 
	 * @return if the team is open.
	 */
	public Boolean getTeamOpen() {
		return teamOpen;
	}

	/**
	 * Sets if team is open.
	 * 
	 * @param teamOpen
	 *            if team is open. This parameter must be a non empty and non
	 *            {@code null} boolean.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setTeamOpen(Boolean teamOpen) {
		requireNonNull(teamOpen, "team open can't be null");
		this.teamOpen = teamOpen;
	}

	/**
	 * Returns the min number of players of the team.
	 * 
	 * @return the min number of players of the team.
	 */
	public int getTeamMinPlayers() {
		return teamMinPlayers;
	}

	/**
	 * Sets the min number of players of the team.
	 * 
	 * @param teamMinPlayers
	 *            min number of players of the team. This parameter must be a
	 *            non empty and non {@code null} int greater than 0.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setTeamMinPlayers(int teamMinPlayers) {
		requireNonNull(teamMinPlayers, "team min players can't be null");
		isTrue(teamMinPlayers > 0, "team min players must be greater than zero");
		this.teamMinPlayers = teamMinPlayers;
	}

	/**
	 * Returns the max number of players of the team.
	 * 
	 * @return the max number of players of the team.
	 */
	public int getTeamMaxPlayers() {
		return teamMaxPlayers;
	}

	/**
	 * Sets the max number of players of the team.
	 * 
	 * @param teamMaxPlayers
	 *            max number of players of the team. This parameter must be a
	 *            non empty and non {@code null} int greater than 0.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setTeamMaxPlayers(int teamMaxPlayers) {
		requireNonNull(teamMaxPlayers, "team max players can't be null");
		isTrue(teamMaxPlayers > 0, "team max players must be greater than zero");
		this.teamMaxPlayers = teamMaxPlayers;
	}

	/**
	 * Returns if the team is public.
	 * 
	 * @return if the team is public.
	 */
	public Boolean getTeamPublic() {
		return teamPublic;
	}

	/**
	 * Sets if team is public.
	 * 
	 * @param teamPublic
	 *            if team is public. This parameter must be a non empty and non
	 *            {@code null} boolean.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setTeamPublic(Boolean teamPublic) {
		requireNonNull(teamPublic, "team public can't be null");
		this.teamPublic = teamPublic;
	}

	/**
	 * Returns if the team is logically deleted.
	 * 
	 * @return if the team is logically deleted.
	 */
	public Boolean getTeamDeleted() {
		return teamDeleted;
	}

	/**
	 * Sets if team is deleted.
	 * 
	 * @param teamDeleted
	 *            if team is deleted. This parameter must be a non empty and non
	 *            {@code null} boolean.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setTeamDeleted(Boolean teamDeleted) {
		requireNonNull(teamDeleted, "team deleted can't be null");
		this.teamDeleted = teamDeleted;
	}

	/**
	 * Returns the team {@code HeadQuarter}.
	 * 
	 * @return the team {@code HeadQuarter}.
	 */
	public HeadQuarter getTeamHeadQuarter() {
		return teamHeadQuarter;
	}

	/**
	 * Sets the team {@code HeadQuarter}.
	 * 
	 * @param teamHeadQuarter
	 *            the Head quarter of the team. This parameter must be a
	 *            {@code HeadQuarter}.
	 */
	public void setTeamHeadQuarter(HeadQuarter teamHeadQuarter) {
		this.teamHeadQuarter = teamHeadQuarter;
	}

	/**
	 * Returns the team {@code Contact}.
	 * 
	 * @return the team {@code Contact}.
	 */
	public Contact getTeamContact() {
		return teamContact;
	}

	/**
	 * Sets the team {@code Contact}.
	 * 
	 * @param teamContact
	 *            the contact of the team. This parameter must be a
	 *            {@code Contact}.
	 */
	public void setTeamContact(Contact teamContact) {
		this.teamContact = teamContact;
	}

	/**
	 * Returns the team {@code Competition}s.
	 * 
	 * @return the team {@code Competition}s.
	 */
	public List<Competition> getTeamCompetitions() {
		return teamCompetitions;
	}

	/**
	 * Sets the {@code Competition}s of the team.
	 * 
	 * @param teamCompetitions
	 *            {@code List} of {@code Competition}s of a Team. This parameter
	 *            must be a non {@code null} {@code List} of {@code Competition}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setTeamCompetitions(List<Competition> teamCompetitions) {
		requireNonNull(teamCompetitions, "team competitions can't be null");
		this.teamCompetitions = teamCompetitions;
	}

	/**
	 * Returns the team {@code Player}s.
	 * 
	 * @return the team {@code Player}s.
	 */
	public List<Player> getTeamPlayers() {
		return teamPlayers;
	}

	/**
	 * Sets the {@code Player}s of the team.
	 * 
	 * @param teamPlayers
	 *            {@code List} of {@code Player}s of a Team. This parameter must
	 *            be a non {@code null} {@code List} of {@code Player}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setTeamPlayers(List<Player> teamPlayers) {
		requireNonNull(teamPlayers, "team players can't be null");
		this.teamPlayers = teamPlayers;
	}

	/**
	 * Returns the team {@code User}.
	 * 
	 * @return the team {@code User}.
	 */
	public User getTeamUser() {
		return teamUser;
	}

	/**
	 * Sets the team {@code User}.
	 * 
	 * @param teamUser
	 *            the User of the team. This parameter must be a {@code User}.
	 */
	public void setTeamUser(User teamUser) {
		this.teamUser = teamUser;
	}

	/**
	 * Returns the hascCode of the contact.
	 * 
	 * @return the hashCode of the contact.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teamDeleted == null) ? 0 : teamDeleted.hashCode());
		result = prime * result + ((teamDescription == null) ? 0 : teamDescription.hashCode());
		result = prime * result + (int) (teamId ^ (teamId >>> 32));
		result = prime * result + teamMaxPlayers;
		result = prime * result + teamMinPlayers;
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		result = prime * result + ((teamOpen == null) ? 0 : teamOpen.hashCode());
		result = prime * result + ((teamPublic == null) ? 0 : teamPublic.hashCode());
		result = prime * result + ((teamShortName == null) ? 0 : teamShortName.hashCode());
		result = prime * result + ((teamSportType == null) ? 0 : teamSportType.hashCode());
		return result;
	}

	/**
	 * Returns if two classes are equals
	 * 
	 * @param obj
	 *            the object to compare
	 * @return if object and this are equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (teamDeleted == null) {
			if (other.teamDeleted != null)
				return false;
		} else if (!teamDeleted.equals(other.teamDeleted))
			return false;
		if (teamDescription == null) {
			if (other.teamDescription != null)
				return false;
		} else if (!teamDescription.equals(other.teamDescription))
			return false;
		if (teamId != other.teamId)
			return false;
		if (teamMaxPlayers != other.teamMaxPlayers)
			return false;
		if (teamMinPlayers != other.teamMinPlayers)
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		if (teamOpen == null) {
			if (other.teamOpen != null)
				return false;
		} else if (!teamOpen.equals(other.teamOpen))
			return false;
		if (teamPublic == null) {
			if (other.teamPublic != null)
				return false;
		} else if (!teamPublic.equals(other.teamPublic))
			return false;
		if (teamShortName == null) {
			if (other.teamShortName != null)
				return false;
		} else if (!teamShortName.equals(other.teamShortName))
			return false;
		if (teamSportType != other.teamSportType)
			return false;
		return true;
	}

}
