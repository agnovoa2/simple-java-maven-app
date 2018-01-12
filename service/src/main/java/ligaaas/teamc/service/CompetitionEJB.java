package ligaaas.teamc.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.User;

/**
 * EJB for Competition
 *
 * @author teamC
 *
 */

@Stateless
@RolesAllowed("registered")
public class CompetitionEJB {

	@EJB
	private UserEJB userEJB;

	@PersistenceContext
	private EntityManager em;

	@Resource
	private SessionContext ctx;

	/**
	 * Creates a new {@link Competition}. If the {@link Competition} already has
	 * other entities related to it, they will be created too.
	 * 
	 * @param competition.
	 *            A new competition to be stored.
	 * @return The persistent version of the {@link Competition} created.
	 * @throws IllegalArgumentException
	 *             if {@link Competition} is <code>null</code> or it already
	 *             exist.
	 */
	public Competition create(Competition competition) {
		if (competition == null) {
			throw new IllegalArgumentException("Competition can't be null");
		}

		Competition existentCompetition = em.find(Competition.class, competition.getCompetitionId());
		if (existentCompetition != null) {
			throw new IllegalArgumentException("Competition already exists");
		}

		return em.merge(competition);
	}

	/**
	 * Updates a competition. If the {@link Competition} is not stored, it will
	 * be persisted.
	 * 
	 * @param competition.
	 *            A modified {@link Competition} to be persisted.
	 * 
	 * @return The persistent version of the {@link Competition} modified.
	 * @throws IllegalArgumentException
	 *             if the {@link Competition} is <code>null</code> or does not
	 *             exist.
	 */
	public Competition update(Competition competition) {
		if (competition == null) {
			throw new IllegalArgumentException("Competition can't be null");
		}

		Competition existentCompetition = em.find(Competition.class, competition.getCompetitionId());
		if (existentCompetition == null) {
			throw new IllegalArgumentException("Competition does not exists");
		}

		return em.merge(competition);
	}

	/**
	 * Deletes a {@link Competition}.
	 * 
	 * @param competitionId.
	 *            The id of the {@link Competition} to be deleted.
	 * @throws IllegalArgumentException
	 *             if the {@link Competition} does not exist.
	 */
	public void delete(long competitionId) {
		Competition toDelete = this.find(competitionId);
		if (toDelete == null) {
			throw new IllegalArgumentException("The competition does not exist");
		}
		toDelete.setCompetitionDeleted(true);
		em.merge(toDelete);
	}

	/**
	 * Returns the {@link Competition} identified by id. If there is no
	 * {@link Competition} with the specified id, <code>null</code> will be
	 * returned.
	 * 
	 * @param competitionID.
	 *            The id of the competition to be returned.
	 * @return The {@link Competition} with the given id.
	 */
	public Competition find(long competitionID) {
		return em.find(Competition.class, competitionID);
	}

	/**
	 * Returns a {@link List} of {@link Competition} with the specified name.
	 * 
	 * @param competitionName.
	 *            The name of the {@link Competition} to be searched.
	 * @return A {@link List} of {@link Competition} with the specified name.
	 * @throws IllegalArgumentException
	 *             if the competition name is null or its length is not between
	 *             5 and 60 characters.
	 */
	public List<Competition> findByName(String competitionName) {
		if (competitionName == null || competitionName.length() < 5 || competitionName.length() > 60) {
			throw new IllegalArgumentException(
					"Competition name can't be null and has to be between 5 and 60 characters long");
		}
		final String query = "SELECT c FROM Competition c WHERE c.competitionName LIKE :competitionName AND c.competitionDeleted = false";
		return em.createQuery(query, Competition.class).setParameter("competitionName", competitionName)
				.getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Competition} with the specified short
	 * name.
	 * 
	 * @param competitionShortName.
	 *            The short name of the {@link Competition} to be searched.
	 * @return A {@link List} of {@link Competition} with the specified short
	 *         name.
	 * @throws IllegalArgumentException
	 *             if the competition short name is null or its length is not
	 *             between 2 and 10 characters.
	 */
	public List<Competition> findByShortName(String competitionShortName) {
		if (competitionShortName == null || competitionShortName.length() < 2 || competitionShortName.length() > 10) {
			throw new IllegalArgumentException(
					"Competition short name can't be null and has to be between 2 and 10 characters long");
		}
		final String query = "SELECT c FROM Competition c WHERE c.competitionShortName LIKE :competitionShortName AND c.competitionDeleted = false";
		return em.createQuery(query, Competition.class).setParameter("competitionShortName", competitionShortName)
				.getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Competition} with the specified
	 * description.
	 * 
	 * @param competitionDescription.
	 *            The description of the {@link Competition} to be searched.
	 * @return A {@link List} of {@link Competition} with the specified
	 *         description.
	 * @throws IllegalArgumentException
	 *             if the competition description is null or its length is not
	 *             between 10 and 240 characters.
	 */
	public List<Competition> findByDescription(String competitionDescription) {
		if (competitionDescription == null || competitionDescription.length() < 10
				|| competitionDescription.length() > 240) {
			throw new IllegalArgumentException(
					"Competition description can't be null and has to be between 10 and 240 characters long");
		}
		final String query = "SELECT c FROM Competition c WHERE c.competitionDescription LIKE :competitionDescription AND c.competitionDeleted = false";
		return em.createQuery(query, Competition.class).setParameter("competitionDescription", competitionDescription)
				.getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Competition} with the specified sport
	 * type.
	 * 
	 * @param competitionSportType.
	 *            The sport type of the {@link Competition} to be searched.
	 * @return A {@link List} of {@link Competition} with the specified sport
	 *         type.
	 * @throws IllegalArgumentException
	 *             if the competition sport type is null
	 */
	public List<Competition> findBySportType(SportType competitionSportType) {
		if (competitionSportType == null) {
			throw new IllegalArgumentException("Competition sport type can't be null");
		}
		final String query = "SELECT c FROM Competition c WHERE c.competitionSportType = :competitionSportType AND c.competitionDeleted = false";
		return em.createQuery(query, Competition.class).setParameter("competitionSportType", competitionSportType)
				.getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Competition} with the specified user.
	 * 
	 * @param competitionUser.
	 *            The user of the {@link Competition} to be searched.
	 * @return A {@link List} of {@link Competition} with the specified user.
	 * @throws IllegalArgumentException
	 *             if the competition user is null
	 */
	public List<Competition> findByUser(User competitionUser) {
		if (competitionUser == null) {
			throw new IllegalArgumentException("Competition user can't be null");
		}
		final String query = "SELECT c FROM Competition c WHERE c.competitionUser = :competitionUser AND c.competitionDeleted = false";
		return em.createQuery(query, Competition.class).setParameter("competitionUser", competitionUser)
				.getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Competition} with the specified team.
	 * 
	 * @param competitionTeam.
	 *            The team of the {@link Competition} to be searched.
	 * @return A {@link List} of {@link Competition} with the specified team.
	 * @throws IllegalArgumentException
	 *             if the competition team is null
	 */
	public List<Competition> findByTeam(Team competitionTeam) {
		if (competitionTeam == null) {
			throw new IllegalArgumentException("Competition team can't be null");
		}
		final String query = "SELECT c FROM Competition c JOIN c.competitionTeams t WHERE t= :competitionTeam AND c.competitionDeleted = false";
		return em.createQuery(query, Competition.class).setParameter("competitionTeam", competitionTeam)
				.getResultList();
	}

	/**
	 * Returns a {@link List} of public {@link Competition}.
	 * 
	 * @return A {@link List} of public {@link Competition}.
	 */
	@PermitAll
	public List<Competition> findPublicCompetition() {
		final String query = "SELECT c FROM Competition c WHERE c.competitionPublic = true and c.competitionDeleted = false ";
		return em.createQuery(query, Competition.class).getResultList();
	}

	/**
	 * Returns a {@link List} of public {@link Competition}.
	 * 
	 * @param competitionId
	 *            Id of {@link Competition}.
	 * @return A {@link List} of public {@link Competition}.
	 */
	@PermitAll
	public List<Competition> findPublicCompetitionById(long competitionId) {
		final String query = "SELECT c FROM Competition c WHERE c.competitionPublic = true and c.competitionDeleted = false and c.competitionId = :competitionId";
		return em.createQuery(query, Competition.class).setParameter("competitionId", competitionId).getResultList();
	}

	/**
	 * Returns a {@link List} of public {@link Competition} filtered by a
	 * {@link SportType}.
	 * 
	 * @param competitionSportType
	 *            the {@link SportType} to be filtered of
	 * @return a {@link List} of public {@link Competition} filtered by a
	 *         {@link SportType}.
	 */
	@PermitAll
	public List<Competition> findPublicCompetitionBySportType(SportType competitionSportType) {
		if (competitionSportType == null) {
			throw new IllegalArgumentException("Competition sport type can't be null");
		}
		final String query = "SELECT c FROM Competition c WHERE c.competitionSportType = :competitionSportType and c.competitionPublic = true and c.competitionDeleted = false";
		return em.createQuery(query, Competition.class).setParameter("competitionSportType", competitionSportType)
				.getResultList();
	}

	/**
	 * Returns a {@link List} of public {@link Competition} filtered by the
	 * locality of the competition.
	 * 
	 * @param competitionLocality
	 *            the locality to be filtered of
	 * @return a {@link List} of public {@link Competition} filtered by the
	 *         locality of the competition.
	 */
	@PermitAll
	public List<Competition> findPublicCompetitionByLocality(String competitionLocality) {
		if (competitionLocality == null) {
			throw new IllegalArgumentException("Competition locality type can't be null");
		}
		final String query = "SELECT c FROM Competition c JOIN c.competitionHeadQuarter h WHERE h.headquarterLocality= :competitionLocality and c.competitionDeleted = false";
		return em.createQuery(query, Competition.class).setParameter("competitionLocality", competitionLocality)
				.getResultList();
	}

	/**
	 * Returns the List of {@link Competition}s managed by the authorized user
	 * {@link User}.
	 * 
	 * @return The list of {@link Competition}s managed by the {@link User}.
	 */
	public List<Competition> findByUser() {
		String userLogin = ctx.getCallerPrincipal().getName();
		List<User> user = userEJB.findByLogin(userLogin);
		if (user.isEmpty())
			return new ArrayList<Competition>();
		else {
			final String query = "SELECT c FROM Competition c WHERE c.competitionUser.userId  LIKE :userId";
			List<Competition> listCompetition = em.createQuery(query, Competition.class)
					.setParameter("userId", user.get(0).getUserId()).getResultList();
			return listCompetition;
		}
	}
}
