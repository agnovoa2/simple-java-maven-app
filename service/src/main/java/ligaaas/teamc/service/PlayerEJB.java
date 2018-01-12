package ligaaas.teamc.service;

import static java.util.Objects.requireNonNull;

import java.security.Principal;
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

import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.User;

/**
 * EJB for Player
 * 
 * @author teamC
 *
 */
@Stateless
@RolesAllowed("registered")
public class PlayerEJB {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UserEJB userEJB;

	@Inject
	private Principal currentUser;

	@Resource
	private SessionContext ctx;

	/**
	 * Creates a new {@link Player}.
	 * 
	 * @param player
	 *            A new player to be stored.
	 * @return The persistent version of the {@link Player} created.
	 * @throws NullPointerException
	 *             if {@link Player} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link Player} already exists.
	 */
	public Player create(Player player) {
		requireNonNull(player, "Player can't be null");

		Player existentPlayer = em.find(Player.class, player.getPlayerId());
		if (existentPlayer != null) {
			throw new IllegalArgumentException("Player already exists");
		}

		User user = userEJB.findByLogin(currentUser.getName()).get(0);
		player.setPlayerManagedByUser(user);
		user.getUserPlayers().add(player);
		
		Player newPlayer = em.merge(player);
		em.merge(user);
		return newPlayer;
	}

	/**
	 * Creates a new {@link Player}.
	 * 
	 * @param player
	 *            A new user player to be stored.
	 * @return The persistent version of the {@link Player} created.
	 * @throws NullPointerException
	 *             if {@link Player} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link Player} already exists.
	 */
	public Player createUserPlayer(Player player) {
		requireNonNull(player, "Player can't be null");

		Player existentPlayer = em.find(Player.class, player.getPlayerId());
		if (existentPlayer != null) {
			throw new IllegalArgumentException("Player already exists");
		}

		User user = userEJB.findByLogin(currentUser.getName()).get(0);
		player.setPlayerManagedByUser(user);
		player.setPlayerUser(user);
		
		user.getUserPlayers().add(player);
		user.setUserPlayer(player);
		
		Player newPlayer = em.merge(player);
		em.merge(user);
		return newPlayer;
	}

	/**
	 * Returns the {@link Player} identified by id. If there is no {@link Player}
	 * with the specified id, <code>null</code> will be returned.
	 * 
	 * @param playerID.
	 *            The id of the {@link Player} to be returned.
	 * @return The {@link Player} with the given id.
	 */
	public Player find(long playerID) {
		return em.find(Player.class, playerID);
	}

	/**
	 * Updates a player.
	 * 
	 * @param player
	 *            A modified {@link Player} to be persisted.
	 * @return The persistent version of the {@link Player} modified.
	 * @throws NullPointerException
	 *             if the {@link Player} is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if {@link Player} doesn't exist.
	 * @throws EJBAccessException
	 *             if player's user isn't the current principal.
	 */
	public Player update(Player player) {
		requireNonNull(player, "Player can't be null");

		Player existentPlayer = em.find(Player.class, player.getPlayerId());
		if (existentPlayer == null) {
			throw new IllegalArgumentException("Player does not exist");
		}

		User playerUser = userEJB.findByLogin(currentUser.getName()).get(0);
		if (player.getPlayerManagedByUser().equals(playerUser)) {
			return em.merge(player);
		} else {
			throw new EJBAccessException("The current user can't manage this player");
		}
	}

	/**
	 * Deletes a {@link Player}.
	 * 
	 * @param playerId.
	 *            The id of the {@link Player} to be deleted.
	 * @throws NullPointerException
	 *             if the {@link Player} does not exist.
	 * @throws EJBAccessException
	 *             if player's user isn't the current principal.
	 * 
	 */
	public void delete(long playerId) {
		Player toDelete = this.find(playerId);
		requireNonNull(toDelete, "Player can't be null");
		em.remove(toDelete);
	}

	/**
	 * Returns the List of {@link Player} managed by the authorized user
	 * {@link User}.
	 * 
	 * @return The list of {@link Player} managed by the {@link User}.
	 */
	public List<Player> findByUser() {
		String userLogin = ctx.getCallerPrincipal().getName();
		final String query = "SELECT p FROM Player p LEFT JOIN p.playerManagedByUser u LEFT JOIN FETCH p.playerTeams WHERE u.userLogin LIKE :userLogin";
		return em.createQuery(query, Player.class).setParameter("userLogin", userLogin).getResultList();
	}

	/**
	 * Returns the List of {@link Player} that plays in a {@link Team}.
	 * 
	 * @return The list of {@link Player} that plays in a {@link Team}.
	 */
	@PermitAll
	public List<Player> findTeamPlayers(Team team) {
		
		requireNonNull(team, "team can't be null");
		
		final String query = "SELECT p FROM Player p, IN(p.playerTeams) AS t WHERE t = :team";
		return em.createQuery(query, Player.class).setParameter("team", team).getResultList();
	}
	
	/**
	 * Returns the List of public {@link Player}.
	 * 
	 * @return The list of public {@link Player}.
	 */
	@PermitAll
	public List<Player> findPublicPlayers() {
		final String query = "SELECT p FROM Player p WHERE p.playerPrivacity = 0";
		return em.createQuery(query, Player.class).getResultList();
	}

	/**
	 * Returns the List of {@link Player} given its nickname.
	 * 
	 * @param nickname
	 *            The nickname of the players
	 * @return the List of {@link Player} that have that nickname.
	 */
	public List<Player> findPlayersByNickname(String nickname) {

		requireNonNull(nickname, "nickname can't be null");
		
		final String query = "SELECT p FROM Player p WHERE p.playerNickName LIKE :nickname";
		List<Player> listPlayers = em.createQuery(query, Player.class).setParameter("nickname", nickname)
				.getResultList();
		return listPlayers;
	}

	/**
	 * Returns the List of {@link Player} given its locality.
	 * 
	 * @param locality
	 *            The locality of the players
	 * @return the List of {@link Player} that have that locality.
	 */
	public List<Player> findPlayersByLocality(String locality) {

		requireNonNull(locality, "locality can't be null");
		
		final String query = "SELECT p FROM Player p WHERE p.playerLocation LIKE :locality";
		List<Player> listPlayers = em.createQuery(query, Player.class).setParameter("locality", locality)
				.getResultList();
		return listPlayers;
	}
}
