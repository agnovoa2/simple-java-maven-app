package ligaaas.teamc.service;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.EventState;
import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.Round;

/**
 * EJB for Round
 *
 * @author teamC
 *
 */

@Stateless
@RolesAllowed("registered")
public class RoundEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Creates a new {@link Round}. If the {@link Round} already has other entities
	 * related to it, they will be created too.
	 * 
	 * @param round.
	 *            A new Round to be stored.
	 * @return The persistent version of the {@link Round} created.
	 * @throws IllegalArgumentException
	 *             if {@link Round} is <code>null</code> or it already exist.
	 */
	public Round create(Round round) {
		if (round == null) {
			throw new IllegalArgumentException("Round can't be null");
		}

		Round existentRound = em.find(Round.class, round.getRoundId());
		if (existentRound != null) {
			throw new IllegalArgumentException("Round already exists");
		}

		return em.merge(round);
	}

	/**
	 * Updates a Round. If the {@link Round} is not stored, it will be persisted.
	 * 
	 * @param round.
	 *            A modified {@link Round} to be persisted.
	 * 
	 * @return The persistent version of the {@link Round} modified.
	 * @throws IllegalArgumentException
	 *             if the {@link Round} is <code>null</code> or does not exist.
	 */
	public Round update(Round round) {
		if (round == null) {
			throw new IllegalArgumentException("Round can't be null");
		}

		Round existentRound = em.find(Round.class, round.getRoundId());
		if (existentRound == null) {
			throw new IllegalArgumentException("Round does not exists");
		}

		return em.merge(round);
	}

	/**
	 * Deletes a {@link Round}.
	 * 
	 * @param roundId.
	 *            The id of the {@link Round} to be deleted.
	 * @throws IllegalArgumentException
	 *             if the {@link Round} does not exist.
	 */
	public void delete(long roundId) {
		Round toDelete = this.find(roundId);
		if (toDelete == null) {
			throw new IllegalArgumentException("The Round does not exist");
		}
		em.remove(toDelete);
	}

	/**
	 * Returns the {@link Round} identified by id. If there is no {@link Round} with
	 * the specified id, <code>null</code> will be returned.
	 * 
	 * @param roundID.
	 *            The id of the Round to be returned.
	 * @return The {@link Round} with the given id.
	 */
	public Round find(long roundID) {
		return em.find(Round.class, roundID);
	}

	/**
	 * Returns a {@link List} of {@link Round} with the specified number.
	 * 
	 * @param roundNumber.
	 *            The name of the {@link Round} to be searched.
	 * @return A {@link List} of {@link Round} with the specified number.
	 * @throws IllegalArgumentException
	 *             if the Round number is null or is lower than 1
	 */
	public List<Round> findByNumber(int roundNumber) {
		if (roundNumber < 1) {
			throw new IllegalArgumentException("Round number has to be greater than 1");
		}
		final String query = "SELECT r FROM Round r WHERE r.roundNumber = :roundNumber";
		return em.createQuery(query, Round.class).setParameter("roundNumber", roundNumber).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Round} with the specified short name.
	 * 
	 * @param roundDate.
	 *            The date of the {@link Round} to be searched.
	 * @return A {@link List} of {@link Round} with the specified date.
	 * @throws IllegalArgumentException
	 *             if the {@link Round} date is null.
	 */
	public List<Round> findByDate(Date roundDate) {
		if (roundDate == null) {
			throw new IllegalArgumentException("Round date cannot be null");
		}
		final String query = "SELECT r FROM Round r WHERE r.roundDate = :roundDate";
		return em.createQuery(query, Round.class).setParameter("roundDate", roundDate).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Round} with the specified EventState.
	 * 
	 * @param roundState.
	 *            The EventState of the {@link Round} to be searched.
	 * @return A {@link List} of {@link Round} with the specified state.
	 * @throws IllegalArgumentException
	 *             if the Round state is null.
	 */
	public List<Round> findByState(EventState roundState) {
		if (roundState == null) {
			throw new IllegalArgumentException("Round state cannot be null");
		}
		final String query = "SELECT r FROM Round r WHERE r.roundState = :roundState";
		return em.createQuery(query, Round.class).setParameter("roundState", roundState).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Round} with the specified description.
	 * 
	 * @param roundDescription.
	 *            The description of the {@link Round} to be searched.
	 * @return A {@link List} of {@link Round} with the specified description.
	 * @throws IllegalArgumentException
	 *             if the Round description is null or its length is not between 0
	 *             and 240 characters.
	 */
	public List<Round> findByDescription(String roundDescription) {
		if (roundDescription == null || roundDescription.length() > 240) {
			throw new IllegalArgumentException(
					"Round description type can't be null and has to have a length between 0 and 240 characters");
		}
		final String query = "SELECT r FROM Round r WHERE r.roundDescription LIKE :roundDescription";
		return em.createQuery(query, Round.class).setParameter("roundDescription", roundDescription).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Round} with the specified HeadQuarter.
	 * 
	 * @param roundHeadQuarter.
	 *            The HeadQuarter of the {@link Round} to be searched.
	 * @return A {@link List} of {@link Round} with the specified HeadQuarter.
	 * @throws IllegalArgumentException
	 *             if the Round HeadQuarter is null
	 */
	public List<Round> findByHeadQuarter(HeadQuarter roundHeadQuarter) {
		if (roundHeadQuarter == null) {
			throw new IllegalArgumentException("Round headquarter can't be null");
		}
		final String query = "SELECT r FROM Round r WHERE r.roundHeadQuarter = :roundHeadQuarter";
		return em.createQuery(query, Round.class).setParameter("roundHeadQuarter", roundHeadQuarter).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Round} with the specified round competition.
	 * 
	 * @param roundCompetition.
	 *            The round competition of the {@link Round} to be searched.
	 * @return A {@link List} of {@link Round} with the specified round competition.
	 * @throws IllegalArgumentException
	 *             if the Round competition is null
	 */
	public List<Round> findByCompetition(Competition roundCompetition) {
		if (roundCompetition == null) {
			throw new IllegalArgumentException("Round competition can't be null");
		}
		final String query = "SELECT r FROM Round r WHERE r.roundCompetition = :roundCompetition";
		return em.createQuery(query, Round.class).setParameter("roundCompetition", roundCompetition).getResultList();
	}

}
