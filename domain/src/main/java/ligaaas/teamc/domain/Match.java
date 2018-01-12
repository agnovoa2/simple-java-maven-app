package ligaaas.teamc.domain;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.isTrue;

import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Game")
public class Match {

	@Id
	@Column(name = "matchId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long matchId;

	@NotNull
	@JoinColumn(name = "team_homeTeamId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Team matchHomeTeam;

	@NotNull
	@JoinColumn(name = "team_visitingTeamId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Team matchVisitingTeam;

	@NotNull
	private Date matchDate;

	@JoinColumn(name = "headQuarter_HeadQuarterId")
	@ManyToOne(fetch = FetchType.LAZY)
	private HeadQuarter matchPlace;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EventState matchState;

	@NotNull
	@Min(0)
	private int matchDuration;

	@NotNull
	@Min(0)
	private int matchLocalPoints;

	@NotNull
	@Min(0)
	private int matchVisitingPoints;

	@NotNull
	@Size(min = 0, max = 240)
	private String matchDescription;

	@NotNull
	@ElementCollection
	private List<String> matchComments;

	@NotNull
	@JoinColumn(name = "round_roundId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Round matchRound;

	/**
	 * Instantiates a new {@link Match}
	 * 
	 * @param matchId
	 *            The {@link Match} Id. This parameter must be non null.
	 * @param matchHomeTeam
	 *            The {@link Match} playing home {@link Team}. This parameter must
	 *            be non null.
	 * @param matchVisitingTeam
	 *            The {@link Match} playing visiting {@link Team}. This parameter
	 *            must be non null.
	 * @param matchDate
	 *            The {@link Match} {@link DateTimeException}. This parameter must
	 *            be non null.
	 * @param matchPlace
	 *            The {@link HeadQuarter} in which the {@link Match} will be played.
	 * @param matchState
	 *            The {@link EventState} of the {@link Match}. This parameter must
	 *            be non <code>null</code>.
	 * @param matchDuration
	 *            The duration of the {@link Match}. This parameter must be non null
	 *            and with a minimum of 0.
	 * @param matchLocalPoints
	 *            The local {@link Team} points in the {@link Match}. This parameter
	 *            must be non null and with a minimum of 0.
	 * @param matchVisitingPoints
	 *            The visiting {@link Team} points in the {@link Match}. This
	 *            parameter must be non null and with a minimum of 0.
	 * @param matchDescription
	 *            The description of the {@link Match}. This parameter must be non
	 *            null and have a length between 0 and 240 characters.
	 * @param matchComments
	 *            The comments posted for the {@link Match}. This parameter must be
	 *            non null.
	 * @param matchRound
	 *            The {@link Round} which the {@link Match} belongs to. This
	 *            parameter must be non null.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed as the value of any parameters
	 *             with that restriction.
	 * @throws IllegalArgumentException
	 *             if a value provided for any parameter is not valid according to
	 *             its constraints
	 */
	public Match(long matchId, Team matchHomeTeam, Team matchVisitingTeam, Date matchDate, HeadQuarter matchPlace,
			EventState matchState, int matchDuration, int matchLocalPoints, int matchVisitingPoints,
			String matchDescription, List<String> matchComments, Round matchRound) {
		super();
		this.setMatchId(matchId);
		this.setMatchHomeTeam(matchHomeTeam);
		this.setMatchVisitingTeam(matchVisitingTeam);
		this.setMatchDate(matchDate);
		this.setMatchPlace(matchPlace);
		this.setMatchState(matchState);
		this.setMatchDuration(matchDuration);
		this.setMatchLocalPoints(matchLocalPoints);
		this.setMatchVisitingPoints(matchVisitingPoints);
		this.setMatchDescription(matchDescription);
		this.setMatchComments(matchComments);
		this.setMatchRound(matchRound);
	}

	/**
	 * Instantiates a new user
	 */
	public Match() {
		super();
	}

	/**
	 * Gets the {@link Match} id.
	 * 
	 * @return the {@link Match} id
	 */
	public long getMatchId() {
		return matchId;
	}

	/**
	 * Sets the {@link Match} Id
	 * 
	 * @throws NullPointerException
	 *             if {code matchId} is {@code null}
	 * @param matchId
	 *            The id for the {@link Match}
	 */
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	/**
	 * Gets the {@link Match} home {@link Team}
	 * 
	 * @return the {@link Match} home {@link Team}
	 */
	public Team getMatchHomeTeam() {
		return matchHomeTeam;
	}

	/**
	 * Sets the {@link Match} home {@link Team}
	 * 
	 * @param matchHomeTeam
	 *            The home {@link Team} for the {@link Match}
	 * @throws NullPointerException
	 *             if the parameter is {@code null}
	 */
	public void setMatchHomeTeam(Team matchHomeTeam) {
		requireNonNull(matchHomeTeam, "match home team can't be null");
		this.matchHomeTeam = matchHomeTeam;
	}

	/**
	 * Gets the {@link Match} visiting {@link Team}
	 * 
	 * @return the {@link Match} visiting {@link Team}
	 */
	public Team getMatchVisitingTeam() {
		return matchVisitingTeam;
	}

	/**
	 * Sets the {@link Match} visiting {@link Team}
	 * 
	 * @throws NullPointerException
	 *             if the {@link Team} is {@code null}
	 * 
	 * @param matchVisitingTeam the visiting {@link Team}
	 */
	public void setMatchVisitingTeam(Team matchVisitingTeam) {
		requireNonNull(matchVisitingTeam, "match visiting team can't be null");
		this.matchVisitingTeam = matchVisitingTeam;
	}

	/**
	 * Gets the {@link Match} {@link Date}
	 * 
	 * @return the {@link Match} {@link Date}
	 */
	public Date getMatchDate() {
		return matchDate;
	}

	/**
	 * Sets the {@link Match} {@link DateTimeException}
	 * 
	 * @throws NullPointerException
	 *             if the match {@link Date} is {@code null}
	 * @param matchDate the {@link Match} {@link Date}
	 */
	public void setMatchDate(Date matchDate) {
		requireNonNull(matchDate, "match date can't be null");
		this.matchDate = matchDate;
	}

	/**
	 * Gets the {@link Match} place (a {@link HeadQuarter})
	 * 
	 * @return the {@link Match} place
	 */
	public HeadQuarter getMatchPlace() {
		return matchPlace;
	}

	/**
	 * Sets the {@link Match} place (a {@link HeadQuarter})
	 * 
	 * @param matchPlace
	 *            the {@link Match} {@link HeadQuarter} for the place
	 */
	public void setMatchPlace(HeadQuarter matchPlace) {
		this.matchPlace = matchPlace;
	}

	/**
	 * Gets the current {@link Match} {@link EventState}
	 * 
	 * @return the current {@link Match} {@link EventState}
	 */
	public EventState getMatchState() {
		return matchState;
	}

	/**
	 * Sets the current {@link Match} {@link EventState}
	 * 
	 * @param matchState
	 *            the current {@link Match} {@link EventState}
	 * @throws NullPointerException
	 *             if the matchState is {@code null}
	 */
	public void setMatchState(EventState matchState) {
		requireNonNull(matchState, "match state can't be null");
		this.matchState = matchState;
	}

	/**
	 * Gets the match duration
	 * 
	 * @return the match duration
	 */
	public int getMatchDuration() {
		return matchDuration;
	}

	/**
	 * Sets the {@link Match} duration
	 * 
	 * @param matchDuration
	 *            the duration of the {@link Match}
	 * @throws NullPointerException
	 *             if the match duration is {@code null}
	 * @throws IllegalArgumentException
	 *             if the match duration is less than 0
	 */
	public void setMatchDuration(int matchDuration) {
		requireNonNull(matchDuration, "match duration can't be null");
		isTrue(matchDuration >= 0, "match duration must be greater or equals to zero");
		this.matchDuration = matchDuration;
	}

	/**
	 * Gets the {@link Match} local points
	 * 
	 * @return the {@link Match} local points
	 */
	public int getMatchLocalPoints() {
		return matchLocalPoints;
	}

	/**
	 * Sets the {@link Match} local points
	 * 
	 * @param matchLocalPoints
	 *            the {@link Match} local points
	 * @throws NullPointerException
	 *             if the argument is null
	 * @throws IllegalArgumentException
	 *             if the local points aren't greater or equal than 0
	 */
	public void setMatchLocalPoints(int matchLocalPoints) {
		requireNonNull(matchLocalPoints, "match local points can't be null");
		isTrue(matchLocalPoints >= 0, "match local points must be greater or equal tozero");
		this.matchLocalPoints = matchLocalPoints;
	}

	/**
	 * Gets the {@link Match} visiting points
	 * 
	 * @return the {@link Match} visiting points
	 */
	public int getMatchVisitingPoints() {
		return matchVisitingPoints;
	}

	/**
	 * Sets the {@link Match} visiting points
	 * 
	 * @param matchVisitingPoints
	 *            the {@link Match} visiting points
	 * 
	 * @throws NullPointerException
	 *             if the argument is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if the visiting points are not greater or equal to 0
	 */
	public void setMatchVisitingPoints(int matchVisitingPoints) {
		requireNonNull(matchVisitingPoints, "match visiting points can't be null");
		isTrue(matchVisitingPoints >= 0, "match visiting points must be greater or equal tozero");
		this.matchVisitingPoints = matchVisitingPoints;
	}

	/**
	 * Gets the {@link Match} description
	 * 
	 * @return the {@link Match} description
	 */
	public String getMatchDescription() {
		return matchDescription;
	}

	/**
	 * Sets the {@link Match} description
	 * 
	 * @param matchDescription
	 *            the {@link Match} description
	 * @throws NullPointerException
	 *             if the argument is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if the match description character count is not between 0 and 240
	 */
	public void setMatchDescription(String matchDescription) {
		requireNonNull(matchDescription, "match description can't be null");
		inclusiveBetween(0, 240, matchDescription.length(), "match description must have a length between 0 and 240");
		this.matchDescription = matchDescription;
	}

	/**
	 * Gets the {@link Match} comments.
	 * 
	 * @return the {@link Match} comments
	 */
	public List<String> getMatchComments() {
		return matchComments;
	}

	/**
	 * Sets the {@link Match} comments.
	 * 
	 * @param matchComments
	 *            the {@link Match} comments
	 * @throws NullPointerException
	 *             if the argument is <code>null</code>
	 */
	public void setMatchComments(List<String> matchComments) {
		requireNonNull(matchComments, "match comments can't be null");
		this.matchComments = matchComments;
	}

	/**
	 * Gets the {@link Match} current {@link Round}
	 * 
	 * @return the {@link Match} current {@link Round}
	 */
	public Round getMatchRound() {
		return matchRound;
	}

	/**
	 * Sets the {@link Match} current {@link Round}
	 * 
	 * @param matchRound
	 *            the current {@link Match} {@link Round}
	 * @throws NullPointerException
	 *             if the argument is <code>null</code>
	 */
	public void setMatchRound(Round matchRound) {
		requireNonNull(matchRound, "match round can't be null");
		this.matchRound = matchRound;
	}

	/**
	 * Returns the hashCode for the {@link Match} object
	 * 
	 * @return the hacshCode for the {@link Match} object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matchComments == null) ? 0 : matchComments.hashCode());
		result = prime * result + ((matchDate == null) ? 0 : matchDate.hashCode());
		result = prime * result + ((matchDescription == null) ? 0 : matchDescription.hashCode());
		result = prime * result + matchDuration;
		result = prime * result + ((matchHomeTeam == null) ? 0 : matchHomeTeam.hashCode());
		result = prime * result + (int) (matchId ^ (matchId >>> 32));
		result = prime * result + matchLocalPoints;
		result = prime * result + ((matchPlace == null) ? 0 : matchPlace.hashCode());
		result = prime * result + ((matchRound == null) ? 0 : matchRound.hashCode());
		result = prime * result + ((matchState == null) ? 0 : matchState.hashCode());
		result = prime * result + matchVisitingPoints;
		result = prime * result + ((matchVisitingTeam == null) ? 0 : matchVisitingTeam.hashCode());
		return result;
	}

	/**
	 * Returns if two classes are equals
	 * 
	 * @param obj
	 *            The object to compare to
	 * 
	 * @return if the object and the current {@link Match} are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (matchComments == null) {
			if (other.matchComments != null)
				return false;
		} else if (!matchComments.equals(other.matchComments))
			return false;
		if (matchDate == null) {
			if (other.matchDate != null)
				return false;
		} else if (!matchDate.equals(other.matchDate))
			return false;
		if (matchDescription == null) {
			if (other.matchDescription != null)
				return false;
		} else if (!matchDescription.equals(other.matchDescription))
			return false;
		if (matchDuration != other.matchDuration)
			return false;
		if (matchHomeTeam == null) {
			if (other.matchHomeTeam != null)
				return false;
		} else if (!matchHomeTeam.equals(other.matchHomeTeam))
			return false;
		if (matchId != other.matchId)
			return false;
		if (matchLocalPoints != other.matchLocalPoints)
			return false;
		if (matchPlace == null) {
			if (other.matchPlace != null)
				return false;
		} else if (!matchPlace.equals(other.matchPlace))
			return false;
		if (matchRound == null) {
			if (other.matchRound != null)
				return false;
		} else if (!matchRound.equals(other.matchRound))
			return false;
		if (matchState != other.matchState)
			return false;
		if (matchVisitingPoints != other.matchVisitingPoints)
			return false;
		if (matchVisitingTeam == null) {
			if (other.matchVisitingTeam != null)
				return false;
		} else if (!matchVisitingTeam.equals(other.matchVisitingTeam))
			return false;
		return true;
	}

}
