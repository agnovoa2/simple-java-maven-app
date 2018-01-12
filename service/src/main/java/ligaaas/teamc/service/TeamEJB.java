package ligaaas.teamc.service;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.inclusiveBetween;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.User;

/**
 * EJB for Team
 * 
 * @author teamC
 *
 */
@Stateless
@RolesAllowed("registered")
public class TeamEJB {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UserEJB userEJB;

	@EJB
	private PlayerEJB playerEJB;

	@Inject
	private Principal currentUser;

	@Resource
	private SessionContext ctx;

	/**
	 * Creates a new {@link Team}. If the {@link Team} already has other
	 * entities related to it, they will be created too.
	 * 
	 * @param team
	 *            A new team to be stored.
	 * @return The persistent version of the {@link Team} created.
	 * @throws NullPointerException
	 *             if {@link Team} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link Team} already exists.
	 */
	public Team create(Team team) {
		requireNonNull(team, "Team can't be null");
		Team existentTeam = em.find(Team.class, team.getTeamId());
		if (existentTeam != null) {
			throw new IllegalArgumentException("Team already exists");
		}
		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		team.setTeamUser(teamUser);
		team.setTeamDeleted(false);

		return em.merge(team);
	}

	/**
	 * Updates a team. If the {@link Team} is not stored, it will be persisted.
	 * 
	 * @param team
	 *            A modified {@link Team} to be persisted.
	 * @return The persistent version of the {@link Team} modified.
	 * @throws NullPointerException
	 *             if the {@link Team} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link Team} doesn't exist.
	 * @throws EJBAccessException
	 *             if team's user isn't the current principal.
	 */
	public Team update(Team team) {
		requireNonNull(team, "Team can't be null");

		Team existentTeam = em.find(Team.class, team.getTeamId());
		if (existentTeam == null) {
			throw new IllegalArgumentException("Team does not exist");
		}

		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		if (team.getTeamUser().getUserLogin().equals(teamUser.getUserLogin())) {
			return em.merge(team);
		} else {
			throw new EJBAccessException("Team's user is not the current principal");
		}

	}

	/**
	 * Deletes a {@link Team}.
	 * 
	 * @param teamId.
	 *            The id of the {@link Team} to be deleted.
	 * @throws NullPointerException
	 *             if the {@link Team} does not exist.
	 * @throws EJBAccessException
	 *             if team's user isn't the current principal.
	 * 
	 */
	public void delete(long teamId) {
		Team toDelete = this.find(teamId);
		requireNonNull(toDelete, "Team can't be null");

		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		if (toDelete.getTeamUser().getUserLogin().equals(teamUser.getUserLogin())) {
			toDelete.setTeamDeleted(true);
			em.merge(toDelete);
		} else {
			throw new EJBAccessException("Team's user is not the current principal");
		}
	}

	/**
	 * Returns the {@link Team} identified by id. If there is no {@link Team}
	 * with the specified id, <code>null</code> will be returned.
	 * 
	 * @param teamID.
	 *            The id of the {@link Team} to be returned.
	 * @return The {@link Team} with the given id.
	 */
	@PermitAll
	public Team find(long teamID) {
		return em.find(Team.class, teamID);
	}

	/**
	 * Returns a {@link List} of {@link Team} with the specified name.
	 * 
	 * @param teamName.
	 *            The name of the {@link Team} to be searched.
	 * @return A {@link List} of {@link Team} with the specified name.
	 * @throws NullPointerException
	 *             if the team name is null.
	 * @throws IllegalArgumentException
	 *             if the team name length is not between 5 and 60 characters.
	 */
	public List<Team> findByName(String teamName) {
		requireNonNull(teamName, "Team name can't be null");
		inclusiveBetween(5, 60, teamName.length(), "team name must have a length between 5 and 60");
		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		return em.createQuery("SELECT t FROM Team t WHERE t.teamName LIKE :name AND t.teamUser = :user AND t.teamDeleted = false", Team.class)
				.setParameter("name", teamName).setParameter("user", teamUser).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Team} with the specified description.
	 * 
	 * @param teamDescription.
	 *            The description of the {@link Team} to be searched.
	 * @return A {@link List} of {@link Team} with the specified description.
	 * @throws NullPointerException
	 *             if the team descripcion is null.
	 * @throws IllegalArgumentException
	 *             if the team description length is not between 10 and 240
	 *             characters.
	 */
	public List<Team> findByDescription(String teamDescription) {
		requireNonNull(teamDescription, "Team description can't be null");
		inclusiveBetween(10, 240, teamDescription.length(), "team description must have a length between 10 and 240");
		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		return em
				.createQuery("SELECT t FROM Team t WHERE t.teamDescription LIKE :description AND t.teamUser = :user AND t.teamDeleted = false",
						Team.class)
				.setParameter("description", teamDescription).setParameter("user", teamUser).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Team} with the specified user.
	 * 
	 * @param user.
	 *            The user of the {@link Team} to be searched.
	 * @return A {@link List} of {@link Team} with the specified user.
	 * @throws NullPointerException
	 *             if the team user is null
	 */
	public List<Team> findByUser(User user) {
		requireNonNull(user, "Team user can't be null");
		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		if (!teamUser.getUserLogin().equals(user.getUserLogin())) {
			throw new EJBAccessException("Team's user is not the current principal");
		}
		return em.createQuery("SELECT t FROM Team t WHERE t.teamUser = :user AND t.teamDeleted = false", Team.class)
				.setParameter("user", teamUser).getResultList();
	}

	/**
	 * Returns a {@link List} of {@link Team} with the specified sportType.
	 * 
	 * @param teamSportType.
	 *            The sport type of the {@link Team} to be searched.
	 * @return A {@link List} of {@link Team} with the specified sport type.
	 * @throws IllegalArgumentException
	 *             if the team sport type is null
	 */
	public List<Team> findBySportType(SportType teamSportType) {
		requireNonNull(teamSportType, "Team sport type can't be null");
		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		final String query = "SELECT t FROM Team t WHERE t.teamSportType = :teamSportType AND t.teamUser = :user AND t.teamDeleted = false";
		return em.createQuery(query, Team.class).setParameter("teamSportType", teamSportType)
				.setParameter("user", teamUser).getResultList();
	}

	/**
	 * Adds players to team.
	 * 
	 * @param teamPlayers
	 *            A {@link Player} list to add.
	 * @param team
	 *            A {@link Team} to add players.
	 * @return The persistent version of the {@link Team} modified.
	 * @throws NullPointerException
	 *             if the {@link Team} is <code>null</code>.
	 * @throws NullPointerException
	 *             if the {@link Player} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link Team} has been deleted.
	 * @throws IllegalArgumentException
	 *             if the new players already are in the team.
	 * @throws EJBAccessException
	 *             if team's user isn't the current principal.
	 */
	public Team addPlayers(List<Player> teamPlayers, Team team) {
		requireNonNull(teamPlayers, "Team players can't be null");
		requireNonNull(team, "Team can't be null");

		team = find(team.getTeamId());
		if (team.getTeamDeleted()) {
			throw new IllegalArgumentException("Team has been deleted");
		}

		if (!Collections.disjoint(teamPlayers, team.getTeamPlayers())) {
			throw new IllegalArgumentException("Team players contains players that already are in the team");
		}

		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		if (!teamUser.getUserLogin().equals(team.getTeamUser().getUserLogin())) {
			throw new EJBAccessException("Team's user is not the current principal");
		}

		team.getTeamPlayers().addAll(teamPlayers);

		for (Player teamPlayer : teamPlayers) {
			teamPlayer = playerEJB.find(teamPlayer.getPlayerId());
			teamPlayer.getPlayerTeams().add(team);
			em.merge(teamPlayer);
		}

		return em.merge(team);
	}

	/**
	 * Removes players from team.
	 * 
	 * @param teamPlayers
	 *            A {@link Player} list to remove.
	 * @param team
	 *            A {@link Team} to remove players.
	 * @return The persistent version of the {@link Team} modified.
	 * @throws NullPointerException
	 *             if the {@link Team} is <code>null</code>.
	 * @throws NullPointerException
	 *             if the {@link Player} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if the players to remove aren't in the team.
	 * @throws EJBAccessException
	 *             if team's user isn't the current principal.
	 */
	public Team removePlayers(List<Player> teamPlayers, Team team) {
		requireNonNull(teamPlayers, "Team players can't be null");
		requireNonNull(team, "Team can't be null");

		team = find(team.getTeamId());

		if (!team.getTeamPlayers().containsAll(teamPlayers)) {
			throw new IllegalArgumentException("Team playes does not contains players that already are in the team");
		}

		User teamUser = userEJB.findByLogin(currentUser.getName()).get(0);
		if (!teamUser.getUserLogin().equals(team.getTeamUser().getUserLogin())) {
			throw new EJBAccessException("Team's user is not the current principal");
		}

		team.getTeamPlayers().removeAll(teamPlayers);

		for (Player teamPlayer : teamPlayers) {
			teamPlayer = playerEJB.find(teamPlayer.getPlayerId());
			teamPlayer.getPlayerTeams().remove(team);
			em.merge(teamPlayer);
		}

		return em.merge(team);
	}

	/**
	 * Returns the List of {@link Team} managed by the authorized user
	 * {@link User}.
	 * 
	 * @return The list of {@link Team} managed by the {@link User}.
	 */
	public List<Team> findByUser() {
		String userLogin = ctx.getCallerPrincipal().getName();
		List<User> user = userEJB.findByLogin(userLogin);
		if (user.isEmpty())
			return new ArrayList<Team>();
		else {
			final String query = "SELECT t FROM Team t WHERE t.teamUser.userId LIKE :userId AND t.teamDeleted = false";
			List<Team> listTeam = em.createQuery(query, Team.class).setParameter("userId", user.get(0).getUserId())
					.getResultList();
			return listTeam;
		}
	}

	/**
	 * Returns a {@link List} of public {@link Team}
	 * 
	 * @return a {@link List} of public {@link Team}
	 */
	@PermitAll
	public List<Team> findPublicTeam() {
		final String query = "SELECT t FROM Team t WHERE t.teamPublic = true AND t.teamDeleted = false";
		return em.createQuery(query, Team.class).getResultList();
	}

	/**
	 * Returns a {@link List} of public {@link Team} filtered by a
	 * {@link SportType}.
	 * 
	 * @param teamSportType
	 *            the {@link SportType} to be filtered of
	 * @return a {@link List} of public {@link Team} filtered by a
	 *         {@link SportType}.
	 */
	@PermitAll
	public List<Team> findPublicTeamBySportType(SportType teamSportType) {
		if (teamSportType == null) {
			throw new IllegalArgumentException("Team sport type can't be null");
		}
		final String query = "SELECT t FROM Team t WHERE t.teamSportType = :teamSportType and t.teamPublic = true and t.teamDeleted = false";
		return em.createQuery(query, Team.class).setParameter("teamSportType", teamSportType).getResultList();
	}

	/**
	 * Returns a {@link List} of public {@link Team} filtered by the locality of
	 * the team.
	 * 
	 * @param teamLocality
	 *            the locality to be filtered of
	 * @return a {@link List} of public {@link Team} filtered by the locality of
	 *         the team.
	 */
	@PermitAll
	public List<Team> findPublicTeamByLocality(String teamLocality) {
		if (teamLocality == null) {
			throw new IllegalArgumentException("Team locality type can't be null");
		}
		final String query = "SELECT t FROM Team t JOIN t.teamHeadQuarter h WHERE h.headquarterLocality= :teamLocality and t.teamDeleted = false and t.teamPublic = true";
		return em.createQuery(query, Team.class).setParameter("teamLocality", teamLocality).getResultList();
	}

	/**
	 * Returns a {@link List} of public {@link Team} filtered by the competition
	 * associated.
	 * 
	 * @param competition
	 *            the competition associated.
	 * @return a {@link List} of public {@link Team}.
	 */
	@PermitAll
	public List<Team> findPublicTeamByCompetition(Competition competition) {
		final String query = "SELECT t FROM Team t JOIN t.teamCompetitions c WHERE c= :competition and t.teamDeleted = false and t.teamPublic = true";
		return em.createQuery(query, Team.class).setParameter("competition", competition).getResultList();
	}

}
