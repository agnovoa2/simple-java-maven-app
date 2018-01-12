package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.DTO.PlayerDTO;

/**
 * Hamcrest matcher for the class {@link PlayerDTO}
 *
 * @author teamC
 *
 */
public class IsEqualToPlayerDTO extends IsEqualToEntity<PlayerDTO> {

	/**
	 * Constructor for the matcher
	 * 
	 * @param entity.
	 *            The entity to match.
	 */
	public IsEqualToPlayerDTO(PlayerDTO entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(PlayerDTO actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("playerEmail", PlayerDTO::getPlayerEmail, actual)
					&& checkAttribute("playerFavouriteSportsList", PlayerDTO::getPlayerFavouriteSportsList, actual)
					&& checkAttribute("playerFavouriteTeamList", PlayerDTO::getPlayerFavouriteTeamList, actual)
					&& checkAttribute("playerInterests", PlayerDTO::getPlayerInterests, actual)
					&& checkAttribute("playerLocation", PlayerDTO::getPlayerLocation, actual)
					&& checkAttribute("playerNickName", PlayerDTO::getPlayerNickName, actual)
					&& checkAttribute("playerPrivacity", PlayerDTO::getPlayerPrivacity, actual)
					&& checkAttribute("playerProvince", PlayerDTO::getPlayerProvince, actual);
		}
	}

	/**
	 * Factory method to match an equal {@link PlayerDTO}
	 * 
	 * @param player.
	 *            The {@link PlayerDTO} to be matched.
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static IsEqualToPlayerDTO equalToPlayer(PlayerDTO player) {
		return new IsEqualToPlayerDTO(player);
	}

	/**
	 * Factory method to match an equal {@link PlayerDTO} with a list. No order
	 * required.
	 * 
	 * @param players.
	 *            An array of {@link PlayerDTO} to be matched
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static Matcher<Iterable<? extends PlayerDTO>> containsPlayersInAnyOrder(PlayerDTO... players) {
		return containsEntityInAnyOrder(IsEqualToPlayerDTO::equalToPlayer, players);
	}
	
	@Factory
	public static Matcher<Iterable<? extends PlayerDTO>> containsPlayersInAnyOrder(Iterable<PlayerDTO> players) {
		return containsEntityInAnyOrder(IsEqualToPlayerDTO::equalToPlayer, players);
	}
}

