package ligaaas.teamc.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for player
 * 
 * @author teamC
 *
 */
public class PlayerDTO {

	private long playerId;
	private String playerNickName;
	private String playerInterests;
	private String playerEmail;
	private String playerLocation;
	private String playerProvince;
	private String playerFavouriteSportsList;
	private String playerFavouriteTeamList;
	private Boolean playerPrivacity;
	private List<TeamDTO> playerTeams = new ArrayList<TeamDTO>();

	/**
	 * Returns the id of the Player.
	 * 
	 * @return the id of the Player.
	 */
	public long getPlayerId() {
		return playerId;
	}

	/**
	 * Sets the id of the Player.
	 * 
	 * @param playerId
	 *            the id of the Player.
	 * 
	 */
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	/**
	 * Returns the nickname of the Player.
	 * 
	 * @return the nickname of the Player.
	 */
	public String getPlayerNickName() {
		return playerNickName;
	}

	/**
	 * Sets the playerNickname of the Player.
	 * 
	 * @param playerNickName
	 *            the nickname of the Player.
	 */
	public void setPlayerNickName(String playerNickName) {
		this.playerNickName = playerNickName;
	}

	/**
	 * Returns the interests of the Player.
	 * 
	 * @return the interests of the Player.
	 */
	public String getPlayerInterests() {
		return playerInterests;
	}

	/**
	 * Sets the interests of the Player.
	 * 
	 * @param playerInterests
	 *            the interests of the Player.
	 */
	public void setPlayerInterests(String playerInterests) {
		this.playerInterests = playerInterests;
	}

	/**
	 * Returns the email of the Player.
	 * 
	 * @return the email of the Player.
	 */
	public String getPlayerEmail() {
		return playerEmail;
	}

	/**
	 * Sets the email of the Player.
	 * 
	 * @param playerEmail
	 *            the email of the Player.
	 */
	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	/**
	 * Returns the location where the Player is living.
	 * 
	 * @return the location where the Player is living.
	 */
	public String getPlayerLocation() {
		return playerLocation;
	}

	/**
	 * Sets the location where the Player is living.
	 * 
	 * @param playerLocation
	 *            the location where the Player is living.
	 *
	 */
	public void setPlayerLocation(String playerLocation) {
		this.playerLocation = playerLocation;
	}

	/**
	 * Returns the province where the Player is living.
	 * 
	 * @return the province where the Player is living.
	 */
	public String getPlayerProvince() {
		return playerProvince;
	}

	/**
	 * Sets the province where the Player is living.
	 * 
	 * @param playerProvince
	 *            the province Player where the player is living.
	 *
	 */
	public void setPlayerProvince(String playerProvince) {
		this.playerProvince = playerProvince;
	}

	/**
	 * Returns the list of favourite sports of Player.
	 * 
	 * @return the list of favourite sports of Player.
	 */
	public String getPlayerFavouriteSportsList() {
		return playerFavouriteSportsList;
	}

	/**
	 * Sets the list of favourite sports of the Player.
	 * 
	 * @param playerFavouriteSportsList
	 *            the list of favourite sports of the Player.
	 */
	public void setPlayerFavouriteSportsList(String playerFavouriteSportsList) {
		this.playerFavouriteSportsList = playerFavouriteSportsList;
	}

	/**
	 * Returns the list of favourite teams of Player.
	 * 
	 * @return the list of favourite teams of Player.
	 */
	public String getPlayerFavouriteTeamList() {
		return playerFavouriteTeamList;
	}

	/**
	 * Sets the list of favourite teams of Player
	 *
	 * @param playerFavouriteTeamList
	 *            the list of favourite teams of the Player.
	 */
	public void setPlayerFavouriteTeamList(String playerFavouriteTeamList) {
		this.playerFavouriteTeamList = playerFavouriteTeamList;
	}

	/**
	 * Returns the account privacity of the Player
	 * 
	 * @return the account privacity of the Player
	 */
	public Boolean getPlayerPrivacity() {
		return playerPrivacity;
	}

	/**
	 * Sets the privacity for the account of Player
	 *
	 * @param privacity
	 *            if player is private.
	 */
	public void setPlayerPrivacity(Boolean privacity) {
		this.playerPrivacity = privacity;
	}

	/**
	 * Returns the player {@code TeamDTO}s.
	 * 
	 * @return the player {@code TeamDTO}s.
	 */
	public List<TeamDTO> getPlayerTeams() {
		return playerTeams;
	}

	/**
	 * Sets the {@code Team}s of the player.
	 * 
	 * @param playerTeams
	 *            {@code List} of {@code TeamDTO}s of a Player. This parameter must
	 *            be a non {@code null} {@code List} of {@code TeamDTO}
	 */
	public void setPlayerTeams(List<TeamDTO> playerTeams) {
		this.playerTeams = playerTeams;
	}

}
