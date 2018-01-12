package ligaaas.teamc.domain;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.isTrue;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Round {

	@Id
	@Column(name = "roundId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long roundId;

	@NotNull
	@Min(1)
	private int roundNumber;

	@NotNull
	private Date roundDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EventState roundState;

	@NotNull
	@Size(min = 0, max = 240)
	private String roundDescription;

	@NotNull
	@ElementCollection
	private List<String> roundComments;

	@JoinColumn(name = "headQuarter_HeadQuarterId")
	@ManyToOne(fetch = FetchType.LAZY)
	private HeadQuarter roundHeadQuarter;

	@NotNull
	@JoinColumn(name = "competition_CompetitionId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Competition roundCompetition;

	/**
	 * Creates a new instance of {@code Round}.
	 * 
	 * @param roundId
	 *            the id of the round. This parameter must be a non empty and non
	 *            {@code null} long.
	 * @param roundNumber
	 *            the number of the round. This parameter must be greater than zero
	 *            and non {@code null} int.
	 * @param roundDate
	 *            the date of the round. This parameter must be a non empty and non
	 *            {@code null} Date.
	 * @param roundState
	 *            the state of the round. This parameter must be a EventState.
	 * @param roundDescription
	 *            the round description. This parameter must be a non empty and non
	 *            {@code null} String.
	 * @param roundComments
	 *            {@code List} of comments. This parameter must be a non
	 *            {@code null} {@code List} of {@code String}
	 * @param roundHeadQuarter
	 *            the Head Quarter of the round. This parameter must be a
	 *            {@code HeadQuarter}
	 * @param roundCompetition
	 *            the Competition of the round. This parameter must be a
	 *            {@code Competition}.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed as the value for any parameter
	 *             except roundHeadQuarter.
	 * @throws IllegalArgumentException
	 *             if value provided for any parameter is not valid according to its
	 *             description.
	 */
	public Round(long roundId, int roundNumber, Date roundDate, EventState roundState, String roundDescription,
			List<String> roundComments, HeadQuarter roundHeadQuarter, Competition roundCompetition) {
		super();
		setRoundId(roundId);
		setRoundNumber(roundNumber);
		setRoundDate(roundDate);
		setRoundState(roundState);
		setRoundDescription(roundDescription);
		setRoundComments(roundComments);
		setRoundHeadQuarter(roundHeadQuarter);
		setRoundCompetition(roundCompetition);
	}

	/**
	 * Creates a new empty instance of {@code Round}.
	 */
	public Round() {
		super();
	}

	/**
	 * Returns the id of the round.
	 * 
	 * @return the id of the round.
	 */
	public long getRoundId() {
		return roundId;
	}

	/**
	 * Sets the id of the round.
	 * 
	 * @param roundId
	 *            the id of the round. This parameter must be a non empty and non
	 *            {@code null} long.
	 */
	public void setRoundId(long roundId) {
		this.roundId = roundId;
	}

	/**
	 * Returns the number of the round.
	 * 
	 * @return the id number the round.
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

	/**
	 * Sets the number of the round.
	 * 
	 * @param roundNumber
	 *            the number of the round. This parameter must be greater than zero
	 *            and non {@code null} int.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the value is zero or less.
	 */
	public void setRoundNumber(int roundNumber) {
		requireNonNull(roundNumber, "round number can't be null");
		isTrue(roundNumber > 0, "round number must be greater than zero");
		this.roundNumber = roundNumber;
	}

	/**
	 * Returns the date of the round.
	 * 
	 * @return the date of the round.
	 */
	public Date getRoundDate() {
		return roundDate;
	}

	/**
	 * Sets the round date.
	 * 
	 * @param roundDate
	 *            the date of the round. This parameter must be a non empty and non
	 *            {@code null} Date.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setRoundDate(Date roundDate) {
		requireNonNull(roundDate, "round date can't be null");
		this.roundDate = roundDate;
	}

	/**
	 * Returns the {@code HeadQuarter} of the round.
	 * 
	 * @return the {@code HeadQuarter} of the round.
	 */
	public HeadQuarter getRoundHeadQuarter() {
		return roundHeadQuarter;
	}

	/**
	 * Sets the {@code HeadQuarter} of the round.
	 * 
	 * @param roundHeadQuarter
	 *            the Head Quarter of the round. This parameter must be a
	 *            {@code HeadQuarter}
	 */
	public void setRoundHeadQuarter(HeadQuarter roundHeadQuarter) {
		this.roundHeadQuarter = roundHeadQuarter;
	}

	/**
	 * Returns the state of the round.
	 * 
	 * @return the state of the round.
	 */
	public EventState getRoundState() {
		return roundState;
	}

	/**
	 * Sets the state of the round.
	 * 
	 * @param roundState
	 *            the state of the round. This parameter must be a EventState.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setRoundState(EventState roundState) {
		requireNonNull(roundState, "round state can't be null");
		this.roundState = roundState;
	}

	/**
	 * Returns the description of the round.
	 * 
	 * @return the id of the round.
	 */
	public String getRoundDescription() {
		return roundDescription;
	}

	/**
	 * Sets the description of the round.
	 * 
	 * @param roundDescription
	 *            the round description. This parameter must be a non empty and non
	 *            {@code null} String.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setRoundDescription(String roundDescription) {
		requireNonNull(roundDescription, "round description can't be null");
		inclusiveBetween(0, 240, roundDescription.length(), "round description must have a length between 0 and 240");
		this.roundDescription = roundDescription;
	}

	/**
	 * Returns the comments of the round.
	 * 
	 * @return the comments of the round.
	 */
	public List<String> getRoundComments() {
		return roundComments;
	}

	/**
	 * Sets the comments of the round.
	 * 
	 * @param roundComments
	 *            {@code List} of comments. This parameter must be a non
	 *            {@code null} {@code List} of {@code String}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setRoundComments(List<String> roundComments) {
		requireNonNull(roundComments, "round comments can't be null");
		this.roundComments = roundComments;
	}

	/**
	 * Returns the {@code Competition} of the round.
	 * 
	 * @return the {@code Competition} of the round.
	 */
	public Competition getRoundCompetition() {
		return roundCompetition;
	}

	/**
	 * Sets the {@code Competition} of the round.
	 * 
	 * @param roundCompetition
	 *            the Competition of the round. This parameter must be a
	 *            {@code Competition}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setRoundCompetition(Competition roundCompetition) {
		requireNonNull(roundCompetition, "round competition can't be null");
		this.roundCompetition = roundCompetition;
	}

	/**
	 * Returns the hascCode of the round.
	 * 
	 * @return the hashCode of the round.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roundComments == null) ? 0 : roundComments.hashCode());
		result = prime * result + ((roundCompetition == null) ? 0 : roundCompetition.hashCode());
		result = prime * result + ((roundDate == null) ? 0 : roundDate.hashCode());
		result = prime * result + ((roundDescription == null) ? 0 : roundDescription.hashCode());
		result = prime * result + ((roundHeadQuarter == null) ? 0 : roundHeadQuarter.hashCode());
		result = prime * result + (int) (roundId ^ (roundId >>> 32));
		result = prime * result + roundNumber;
		result = prime * result + ((roundState == null) ? 0 : roundState.hashCode());
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
		Round other = (Round) obj;
		if (roundComments == null) {
			if (other.roundComments != null)
				return false;
		} else if (!roundComments.equals(other.roundComments))
			return false;
		if (roundCompetition == null) {
			if (other.roundCompetition != null)
				return false;
		} else if (!roundCompetition.equals(other.roundCompetition))
			return false;
		if (roundDate == null) {
			if (other.roundDate != null)
				return false;
		} else if (!roundDate.equals(other.roundDate))
			return false;
		if (roundDescription == null) {
			if (other.roundDescription != null)
				return false;
		} else if (!roundDescription.equals(other.roundDescription))
			return false;
		if (roundHeadQuarter == null) {
			if (other.roundHeadQuarter != null)
				return false;
		} else if (!roundHeadQuarter.equals(other.roundHeadQuarter))
			return false;
		if (roundId != other.roundId)
			return false;
		if (roundNumber != other.roundNumber)
			return false;
		if (roundState != other.roundState)
			return false;
		return true;
	}

}
