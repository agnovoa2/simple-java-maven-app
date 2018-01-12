package ligaaas.teamc.converter;

import java.util.ArrayList;
import java.util.List;

import ligaaas.teamc.DTO.PlayerDTO;
import ligaaas.teamc.domain.Player;

/**
 * Converter for Player
 * 
 * @author teamC
 *
 */
public class PlayerConverter {

	/**
	 * Converts a List of {@link Player}s into a List of {@link PlayerDTO}s
	 * 
	 * @param listPlayer List of {@link Player}s.
	 * @return	List of {@link PlayerDTO}s.
	 */
	public static List<PlayerDTO> toPlayerDTO(List<Player> listPlayer){
		List<PlayerDTO> listDTO = new ArrayList<>();
		for(Player player : listPlayer) {
			listDTO.add(toPlayerDTO(player));
		}
		return listDTO;
	}

	/**
	 * Converts a {@link Player}s into a {@link PlayerDTO}s
	 * 
	 * @param player {@link Player}.
	 * @return	{@link PlayerDTO}.
	 */
	public static PlayerDTO toPlayerDTO(Player player){
		PlayerDTO playerDTO = new PlayerDTO();
		playerDTO.setPlayerId(player.getPlayerId());
		playerDTO.setPlayerNickName(player.getPlayerNickName());
		playerDTO.setPlayerInterests(player.getPlayerInterests());
		playerDTO.setPlayerEmail(player.getPlayerEmail());
		playerDTO.setPlayerLocation(player.getPlayerLocation());
		playerDTO.setPlayerProvince(player.getPlayerProvince());
		playerDTO.setPlayerFavouriteSportsList(player.getPlayerFavouriteSportsList());
		playerDTO.setPlayerFavouriteTeamList(player.getPlayerFavouriteTeamList());
		playerDTO.setPlayerPrivacity(player.getPlayerPrivacity());
		playerDTO.setPlayerTeams(TeamConverter.toTeamDTO(player.getPlayerTeams()));
		
		return playerDTO;
	}
}
