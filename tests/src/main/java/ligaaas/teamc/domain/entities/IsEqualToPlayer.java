package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.domain.Player;

public class IsEqualToPlayer extends IsEqualToEntity<Player> {

	public IsEqualToPlayer(Player player, boolean checkRelations) {
		super(player);
	}

	@Override
	protected boolean matchesSafely(Player actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("playerNickName", Player::getPlayerNickName, actual)
					&& checkAttribute("playerPicture", Player::getPlayerPicture, actual)
					&& checkAttribute("playerInterests", Player::getPlayerInterests, actual)
					&& checkAttribute("playerEmail", Player::getPlayerEmail, actual)
					&& checkAttribute("playerLocation", Player::getPlayerLocation, actual)
					&& checkAttribute("playerProvince", Player::getPlayerProvince, actual)
					&& checkAttribute("playerFavouriteSportsList", Player::getPlayerFavouriteSportsList, actual)
					&& checkAttribute("playerFavouriteTeamList", Player::getPlayerFavouriteTeamList, actual)
					&& checkAttribute("playerPrivacity", Player::getPlayerPrivacity, actual);
		}
	}

	@Factory
	public static IsEqualToPlayer equalToPlayer(Player player) {
		return new IsEqualToPlayer(player, true);
	}

	@Factory
	public static IsEqualToPlayer equalToPlayerWithoutRelations(Player player) {
		return new IsEqualToPlayer(player, false);
	}

	@Factory
	public static Matcher<Iterable<? extends Player>> containsPlayersInAnyOrder(Player... players) {
		return containsEntityInAnyOrder(IsEqualToPlayer::equalToPlayer, players);
	}

	@Factory
	public static Matcher<Iterable<? extends Player>> containsPlayersWithoutRelationsInAnyOrder(Player... players) {
		return containsEntityInAnyOrder(IsEqualToPlayer::equalToPlayerWithoutRelations, players);
	}

	@Factory
	public static Matcher<Iterable<? extends Player>> containsPlayersInAnyOrder(Iterable<Player> players) {
		return containsEntityInAnyOrder(IsEqualToPlayer::equalToPlayer, players);
	}

	@Factory
	public static Matcher<Iterable<? extends Player>> containsPlayersWithoutRelationsInAnyOrder(Iterable<Player> players) {
		return containsEntityInAnyOrder(IsEqualToPlayer::equalToPlayerWithoutRelations, players);
	}
}
