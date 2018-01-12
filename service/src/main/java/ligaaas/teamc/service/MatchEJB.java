package ligaaas.teamc.service;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ligaaas.teamc.domain.EventState;
import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.Match;
import ligaaas.teamc.domain.Round;
import ligaaas.teamc.domain.Team;

/**
 * EJB for Match
 *
 * @author teamC
 *
 */

@Stateless
@RolesAllowed("registered")
public class MatchEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Creates a new {@link Match}. If the {@link Match} already has other entities
	 * related to it, they will be created too.
	 * 
	 * @param match.
	 *            A new {@link Match} to be stored.
	 * @return The persistent version of the {@link Match} created.
	 * @throws IllegalArgumentException
	 *             if {@link Match} is <code>null</code> or it already exist.
	 */
	public Match create(Match match) {
		if (match == null) {
			throw new IllegalArgumentException("Match can't be null");
		}

		Match existentMatch = em.find(Match.class, match.getMatchId());
		if (existentMatch != null) {
			throw new IllegalArgumentException("Match already exists");
		}

		return em.merge(match);
	}

	/**
	 * Updates a {@link Match}. If the {@link Match} is not stored, it will be persisted.
	 * 
	 * @param match.
	 *            A modified {@link Match} to be persisted.
	 * 
	 * @return The persistent version of the {@link Match} modified.
	 * @throws IllegalArgumentException
	 *             if the {@link Match} is <code>null</code> or does not exist.
	 */
	public Match update(Match match) {
		if (match == null) {
			throw new IllegalArgumentException("Match can't be null");
		}

		Match existentMatch = em.find(Match.class, match.getMatchId());
		if (existentMatch == null) {
			throw new IllegalArgumentException("Match does not exists");
		}

		return em.merge(match);
	}

	/**
	 * Deletes a {@link Match}.
	 * 
	 * @param matchId.
	 *            The id of the {@link Match} to be deleted.
	 * @throws IllegalArgumentException
	 *             if the {@link Match} does not exist.
	 */
	public void delete(long matchId) {
		Match toDelete = this.find(matchId);
		if (toDelete == null) {
			throw new IllegalArgumentException("The Match does not exist");
		}
		em.remove(toDelete);
	}

	/**
	 * Returns the {@link Match} identified by id. If there is no {@link Match} with
	 * the specified id, <code>null</code> will be returned.
	 * 
	 * @param matchID.
	 *            The id of the {@link Match} to be returned.
	 * @return The {@link Match} with the given id.
	 */
	public Match find(long matchID) {
		return em.find(Match.class, matchID);
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified short name.
	 * 
	 * @param matchDate.
	 *            The date of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified date.
	 * @throws IllegalArgumentException
	 *             if the {@link Match} date is null.
	 */
	public List<Match> findByDate(Date matchDate) {
		if (matchDate == null) {
			throw new IllegalArgumentException("Match date cannot be null");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchDate = :matchDate";
		return em.createQuery(query, Match.class).setParameter("matchDate", matchDate).getResultList();
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified EventState.
	 * 
	 * @param matchState.
	 *            The EventState of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified state.
	 * @throws IllegalArgumentException
	 *             if the {@link Match} state is null.
	 */
	public List<Match> findByState(EventState matchState) {
		if (matchState == null) {
			throw new IllegalArgumentException("Match state cannot be null");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchState = :matchState";
		return em.createQuery(query, Match.class).setParameter("matchState", matchState).getResultList();
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified duration.
	 * 
	 * @param matchDuration.
	 *            The duration of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified duration.
	 * @throws IllegalArgumentException
	 *             if the {@link Match} duration is null or is lower than 0
	 */
	public List<Match> findByDuration(int matchDuration) {
		if (matchDuration < 0) {
			throw new IllegalArgumentException("Match duration has to be greater than 0");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchDuration = :matchDuration";
		return em.createQuery(query, Match.class).setParameter("matchDuration", matchDuration).getResultList();
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified localPoints.
	 * 
	 * @param matchLocalPoints.
	 *            The local points of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified localPoints.
	 * @throws IllegalArgumentException
	 *             if the {@link Match} localPoints is lower than 0
	 */
	public List<Match> findByLocalPoints(int matchLocalPoints) {
		if (matchLocalPoints < 0) {
			throw new IllegalArgumentException("Match localPoints has to be greater than 0");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchLocalPoints = :matchLocalPoints";
		return em.createQuery(query, Match.class).setParameter("matchLocalPoints", matchLocalPoints).getResultList();
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified visitingPoints.
	 * 
	 * @param matchVisitingPoints.
	 *            The local points of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified visitingPoints.
	 * @throws IllegalArgumentException
	 *             if the {@link Match} visitingPoints is lower than 0
	 */
	public List<Match> findByVisitingPoints(int matchVisitingPoints) {
		if (matchVisitingPoints < 0) {
			throw new IllegalArgumentException("Match visitingPoints has to be greater than 0");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchVisitingPoints = :matchVisitingPoints";
		return em.createQuery(query, Match.class).setParameter("matchVisitingPoints", matchVisitingPoints).getResultList();
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified description.
	 * 
	 * @param matchDescription.
	 *            The description of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified description.
	 * @throws IllegalArgumentException
	 *             if the {@link Match} description is null or its length is not between 0
	 *             and 240 characters.
	 */
	public List<Match> findByDescription(String matchDescription) {
		if (matchDescription == null || matchDescription.length() > 240) {
			throw new IllegalArgumentException(
					"Match description type can't be null and has to have a length between 0 and 240 characters");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchDescription LIKE :matchDescription";
		return em.createQuery(query, Match.class).setParameter("matchDescription", matchDescription).getResultList();
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified HeadQuarter.
	 * 
	 * @param matchHeadQuarter.
	 *            The HeadQuarter of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified HeadQuarter.
	 */
	public List<Match> findByHeadQuarter(HeadQuarter matchHeadQuarter) {
		final String query = "SELECT m FROM Match m WHERE m.matchPlace = :matchPlace";
		return em.createQuery(query, Match.class).setParameter("matchPlace", matchHeadQuarter).getResultList();
	}
	
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified Round.
	 * 
	 * @param matchRound.
	 *            The Round of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified Round.
	 * @throws IllegalArgumentException
	 *             if the Match Round is null
	 */
	public List<Match> findByRound(Round matchRound) {
		if (matchRound == null) {
			throw new IllegalArgumentException("Match round can't be null");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchRound = :matchRound";
		return em.createQuery(query, Match.class).setParameter("matchRound", matchRound).getResultList();
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified home Team.
	 * 
	 * @param matchHomeTeam.
	 *            The Team of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified Team.
	 * @throws IllegalArgumentException
	 *             if the Match Team is null
	 */
	public List<Match> findByHomeTeam(Team matchHomeTeam) {
		if (matchHomeTeam == null) {
			throw new IllegalArgumentException("Match home team can't be null");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchHomeTeam = :matchHomeTeam";
		return em.createQuery(query, Match.class).setParameter("matchHomeTeam", matchHomeTeam).getResultList();
	}
	
	/**
	 * Returns a {@link List} of {@link Match} with the specified visiting Team.
	 * 
	 * @param matchVisitingTeam.
	 *            The Team of the {@link Match} to be searched.
	 * @return A {@link List} of {@link Match} with the specified Team.
	 * @throws IllegalArgumentException
	 *             if the Match Team is null
	 */
	public List<Match> findByVisitingTeam(Team matchVisitingTeam) {
		if (matchVisitingTeam == null) {
			throw new IllegalArgumentException("Match visiting team can't be null");
		}
		final String query = "SELECT m FROM Match m WHERE m.matchVisitingTeam = :matchVisitingTeam";
		return em.createQuery(query, Match.class).setParameter("matchVisitingTeam", matchVisitingTeam).getResultList();
	}
}
