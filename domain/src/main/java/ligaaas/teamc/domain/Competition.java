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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A Competition
 *
 * @author teamC
 *
 */

@Entity
public class Competition {

	@Id
	@Column(name = "competitionId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long competitionId;

	@NotNull
	@Size(min = 5, max = 60)
	private String competitionName;
	@NotNull
	@Size(min = 2, max = 10)
	private String competitionShortName;
	@Size(min = 10, max = 240)
	private String competitionDescription;
	@NotNull
	@Enumerated(EnumType.STRING)
	private SportType competitionSportType;
	@NotNull
	@Enumerated(EnumType.STRING)
	private CompetitionType competitionType;
	@NotNull
	private Boolean competitionOpen;
	@Min(1)
	@NotNull
	private int competitionMinTeams;
	@Min(1)
	@NotNull
	private int competitionMaxTeams;
	@NotNull
	private Boolean competitionPublic;
	@NotNull
	private Boolean competitionDeleted;
	@JoinColumn(name = "headQuarter_HeadQuarterId")
	@ManyToOne(fetch = FetchType.LAZY)
	private HeadQuarter competitionHeadQuarter;
	@JoinColumn(name = "user_UserId")
	@ManyToOne(fetch = FetchType.LAZY)
	private User competitionUser;
	@JoinColumn(name = "contact_ContactId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Contact competitionContact;
	@ManyToMany(mappedBy = "teamCompetitions")
	private List<Team> competitionTeams = new ArrayList<Team>();

	/**
	 * Creates a new instance of {@code Competition}.
	 * 
	 * @param competitionId
	 *            the id of the competition. This parameter must be a non empty and
	 *            non {@code null} long .
	 * @param competitionName
	 *            the name of the competition. This parameter must be a non empty
	 *            and non {@code null} String.
	 * @param competitionShortName
	 *            the short name of the competition. This parameter must be a non
	 *            empty and non {@code null} String.
	 * @param competitionDescription
	 *            the description of the competition
	 * @param competitionSportType
	 *            the sport type of the competition. This parameter must be a non
	 *            empty and non {@code null} {@code SportType}.
	 * @param competitionType
	 *            the type of the competition. This parameter must be a non
	 *            empty and non {@code null} {@code CompetitonType}.
	 * @param competitionOpen
	 *            if the competition is open. This parameter must be a non
	 *            {@code null} Boolean.
	 * @param competitionMinTeams
	 *            the min number of teams of the competition. This parameter must be
	 *            a non empty and non {@code null} int.
	 * @param competitionMaxTeams
	 *            the max number of teams of the competition. This parameter must be
	 *            a non empty and non {@code null} int.
	 * @param competitionPublic
	 *            if the competition is public. This parameter must be a non empty
	 *            and non {@code null} boolean.
	 * @param competitionDeleted
	 *            if the competition is logicaly deleted. This parameter must be a
	 *            non {@code null} boolean.
	 * @param competitionHeadQuarter
	 *            the Head Quarter of the competition. This parameter must be a
	 *            {@code HeadQuarter}
   	 * @param competitionUser
     *			  the User of the competition. This parameter must be a
	 * 			  {@code User}
	 * @param competitionContact
	 *            the Contact of the competition. This parameter must be a
	 *            {@code Contact}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed as the value for any parameter.
	 * @throws IllegalArgumentException
	 *             if value provided for any parameter is not valid according to its
	 *             description.
	 */
	public Competition(long competitionId, String competitionName, String competitionShortName,
			String competitionDescription, SportType competitionSportType, CompetitionType competitionType,
			Boolean competitionOpen, int competitionMinTeams, int competitionMaxTeams, Boolean competitionPublic,
			Boolean competitionDeleted, HeadQuarter competitionHeadQuarter, User competitionUser, Contact competitionContact) {
		setCompetitionId(competitionId);
		setCompetitionName(competitionName);
		setCompetitionShortName(competitionShortName);
		setCompetitionDescription(competitionDescription);
		setCompetitionSportType(competitionSportType);
		setCompetitionType(competitionType);
		setCompetitionOpen(competitionOpen);
		setCompetitionMinTeams(competitionMinTeams);
		setCompetitionMaxTeams(competitionMaxTeams);
		setCompetitionPublic(competitionPublic);
		setCompetitionDeleted(competitionDeleted);
		setCompetitionHeadQuarter(competitionHeadQuarter);
		setCompetitionUser(competitionUser);
		setCompetitionContact(competitionContact);
	}

	/**
	 * Creates a new empty instance of {@code Competition}.
	 */
	public Competition() {
	}

	/**
	 * Returns the id of the competition.
	 * 
	 * @return the id of the competition.
	 */
	public long getCompetitionId() {
		return competitionId;
	}

	/**
	 * Sets the id of the competition.
	 * 
	 * @param competitionId
	 *            the id of the competition. This parameter must be a non empty and
	 *            non {@code null} long.
	 * 
	 */
	public void setCompetitionId(long competitionId) {
		this.competitionId = competitionId;
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
	 *            the name of the competition. This parameter must be a non empty
	 *            and non {@code null} string with a maximun length of 60 chars and
	 *            a minumun length of 10 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setCompetitionName(String competitionName) {
		requireNonNull(competitionName, "competition name can't be null");
		inclusiveBetween(5, 60, competitionName.length(), "competition name must have a length between 5 and 60");
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
	 *            the short name of the competition. This parameter must be a non
	 *            empty and non {@code null} string with a maximun length of 10
	 *            chars and a minumun length of 2 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setCompetitionShortName(String competitionShortName) {
		requireNonNull(competitionShortName, "competition short name can't be null");
		inclusiveBetween(2, 10, competitionShortName.length(),
				"competition short name must have a length between 2 and 10");
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
	 *            the description of the competition. This parameter must be a non
	 *            empty string with a maximun length of 240 chars and a minimun
	 *            length of 10 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setCompetitionDescription(String competitionDescription) {
		inclusiveBetween(10, 240, competitionDescription.length(),
				"competition description must have a length between 10 and 240");
		this.competitionDescription = competitionDescription;
	}

	/**
	 * Returns the sport type of the competition.
	 * 
	 * @return the sport type of the competition.
	 */
	public SportType getCompetitionSportType() {
		return competitionSportType;
	}

	/**
	 * Sets the description of the competition.
	 * 
	 * @param competitionSportType
	 *            the description of the competition. This parameter must be a non
	 *            empty and non {@code null} {@code SportType}.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setCompetitionSportType(SportType competitionSportType) {
		requireNonNull(competitionSportType, "competition sport type can't be null");
		this.competitionSportType = competitionSportType;
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
	 *            if competition is open. This parameter must be a non empty and non
	 *            {@code null} boolean.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setCompetitionOpen(Boolean competitionOpen) {
		requireNonNull(competitionOpen, "competition open can't be null");
		this.competitionOpen = competitionOpen;
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
	 *            min number of teams of the competition. This parameter must be a
	 *            non empty and non {@code null} int greater than 0.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the value is zero or less.
	 */
	public void setCompetitionMinTeams(int competitionMinTeams) {
		requireNonNull(competitionMinTeams, "competition min teams can't be null");
		isTrue(competitionMinTeams > 0, "competition min teams must be greater than zero");
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
	 *            max number of teams of the competition. This parameter must be a
	 *            non empty and non {@code null} int greater than 0.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the value is zero or less.
	 */
	public void setCompetitionMaxTeams(int competitionMaxTeams) {
		requireNonNull(competitionMaxTeams, "competition max teams can't be null");
		isTrue(competitionMaxTeams > 0, "competition max teams must be greater than zero");
		this.competitionMaxTeams = competitionMaxTeams;
	}

	/**
	 * Returns if the competition is public.
	 * 
	 * @return if the competition is public.
	 */
	public Boolean getCompetitionPublic() {
		return competitionPublic;
	}

	/**
	 * Sets if competition is public.
	 * 
	 * @param competitionPublic
	 *            if competition is public. This parameter must be a non empty and
	 *            non {@code null} boolean.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setCompetitionPublic(Boolean competitionPublic) {
		requireNonNull(competitionPublic, "competition public can't be null");
		this.competitionPublic = competitionPublic;
	}

	/**
	 * Returns if the competition is logically deleted.
	 * 
	 * @return if the competition is logically deleted.
	 */
	public Boolean getCompetitionDeleted() {
		return competitionDeleted;
	}

	/**
	 * Sets if competition is deleted.
	 * 
	 * @param competitionDeleted
	 *            if competition is deleted. This parameter must be a non empty and
	 *            non {@code null} boolean.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setCompetitionDeleted(Boolean competitionDeleted) {
		requireNonNull(competitionDeleted, "competition deleted can't be null");
		this.competitionDeleted = competitionDeleted;
	}

	/**
	 * Returns the competition {@code HeadQuarter}.
	 * 
	 * @return the competition {@code HeadQuarter}.
	 */
	public HeadQuarter getCompetitionHeadQuarter() {
		return competitionHeadQuarter;
	}

	/**
	 * Sets the competition {@code HeadQuarter}.
	 * 
	 * @param competitionHeadQuarter
	 *            the Head quarter of the competition. This parameter must be a
	 *            {@code HeadQuarter}.
	 */
	public void setCompetitionHeadQuarter(HeadQuarter competitionHeadQuarter) {
		this.competitionHeadQuarter = competitionHeadQuarter;
	}
	
	/**
	 * Returns the competition {@code User}.
	 * 
	 * @return the competition {@code User}.
	 */
	public User getCompetitionUser() {
		return competitionUser;
	}
	
	/**
	 * Sets the competition {@code User}.
	 * 
	 * @param competitionUser
	 *            the User of the competition. This parameter must be a
	 *            {@code User}.
	 */
	public void setCompetitionUser(User competitionUser) {
		this.competitionUser = competitionUser;
	}

	/**
	 * Returns the competition {@code Contact}.
	 * 
	 * @return the competition {@code Contact}.
	 */
	public Contact getCompetitionContact() {
		return competitionContact;
	}

	/**
	 * Sets the competition {@code Contact}.
	 * 
	 * @param competitionContact
	 *            the contact of the competition. This parameter must be a
	 *            {@code Contact}.
	 */
	public void setCompetitionContact(Contact competitionContact) {
		this.competitionContact = competitionContact;
	}

	/**
	 * Returns the competition type.
	 * 
	 * @return the competition type.
	 */
	public CompetitionType getCompetitionType() {
		return competitionType;
	}

	/**
	 * Sets the type of the competition.
	 * 
	 * @param competitionType
	 *            the sport type of the competition. This parameter must be a non
	 *            empty and non {@code null} {@code CompetitionType	}.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setCompetitionType(CompetitionType competitionType) {
		requireNonNull(competitionType, "competition type can't be null");
		this.competitionType = competitionType;
	}

	/**
	 * Returns the competition {@code Team}s.
	 * 
	 * @return the competition {@code Team}s.
	 */
	public List<Team> getCompetitionTeams() {
		return competitionTeams;
	}

	/**
	 * Sets the teams of the competition.
	 * 
	 * @param competitionTeams
	 *            {@code List} of {@code Team}s that participates in a competition.
	 *            This parameter must be a non {@code null} {@code List} of
	 *            {@code Team}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setCompetitionTeams(List<Team> competitionTeams) {
		requireNonNull(competitionTeams, "competition teams can't be null");
		this.competitionTeams = competitionTeams;
	}

	/**
	 * Returns the hascCode of the competition.
	 * 
	 * @return the hashCode of the competition.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competitionContact == null) ? 0 : competitionContact.hashCode());
		result = prime * result + ((competitionDeleted == null) ? 0 : competitionDeleted.hashCode());
		result = prime * result + ((competitionDescription == null) ? 0 : competitionDescription.hashCode());
		result = prime * result + ((competitionHeadQuarter == null) ? 0 : competitionHeadQuarter.hashCode());
		result = prime * result + (int) (competitionId ^ (competitionId >>> 32));
		result = prime * result + competitionMaxTeams;
		result = prime * result + competitionMinTeams;
		result = prime * result + ((competitionName == null) ? 0 : competitionName.hashCode());
		result = prime * result + ((competitionOpen == null) ? 0 : competitionOpen.hashCode());
		result = prime * result + ((competitionPublic == null) ? 0 : competitionPublic.hashCode());
		result = prime * result + ((competitionShortName == null) ? 0 : competitionShortName.hashCode());
		result = prime * result + ((competitionSportType == null) ? 0 : competitionSportType.hashCode());
		result = prime * result + ((competitionType == null) ? 0 : competitionType.hashCode());
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
		Competition other = (Competition) obj;
		if (competitionContact == null) {
			if (other.competitionContact != null)
				return false;
		} else if (!competitionContact.equals(other.competitionContact))
			return false;
		if (competitionDeleted == null) {
			if (other.competitionDeleted != null)
				return false;
		} else if (!competitionDeleted.equals(other.competitionDeleted))
			return false;
		if (competitionDescription == null) {
			if (other.competitionDescription != null)
				return false;
		} else if (!competitionDescription.equals(other.competitionDescription))
			return false;
		if (competitionHeadQuarter == null) {
			if (other.competitionHeadQuarter != null)
				return false;
		} else if (!competitionHeadQuarter.equals(other.competitionHeadQuarter))
			return false;
		if (competitionId != other.competitionId)
			return false;
		if (competitionMaxTeams != other.competitionMaxTeams)
			return false;
		if (competitionMinTeams != other.competitionMinTeams)
			return false;
		if (competitionName == null) {
			if (other.competitionName != null)
				return false;
		} else if (!competitionName.equals(other.competitionName))
			return false;
		if (competitionOpen == null) {
			if (other.competitionOpen != null)
				return false;
		} else if (!competitionOpen.equals(other.competitionOpen))
			return false;
		if (competitionPublic == null) {
			if (other.competitionPublic != null)
				return false;
		} else if (!competitionPublic.equals(other.competitionPublic))
			return false;
		if (competitionShortName == null) {
			if (other.competitionShortName != null)
				return false;
		} else if (!competitionShortName.equals(other.competitionShortName))
			return false;
		if (competitionSportType != other.competitionSportType)
			return false;
		if (competitionType != other.competitionType)
			return false;
		return true;
	}
}
