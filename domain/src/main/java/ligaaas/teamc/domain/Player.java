package ligaaas.teamc.domain;

import static java.util.Objects.requireNonNull;
import static ligaaas.teamc.domain.RegexpTemplates.EMAIL;
import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.matchesPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Player {
	@Id
	@Column(name = "playerId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long playerId;

	@NotNull
	@Size(min = 2, max = 30)
	private String playerNickName;

	private byte[] playerPicture;

	@Size(max = 1000)
	private String playerInterests;

	@NotNull
	@Pattern(regexp = RegexpTemplates.EMAIL)
	@Size(min = 5, max = 40)
	private String playerEmail;

	@NotNull
	@Size(min = 2, max = 100)
	private String playerLocation;

	@NotNull
	@Size(min = 2, max = 50)
	private String playerProvince;

	private String playerFavouriteSportsList;

	private String playerFavouriteTeamList;

	@NotNull
	private Boolean playerPrivacity;

	@ManyToMany(mappedBy = "teamPlayers")
	private List<Team> playerTeams = new ArrayList<Team>();

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userPlayer")
	private User playerUser;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "user_UserId", referencedColumnName = "userId")
	private User playerManagedByUser;

	/**
	 * Creates a new empty instance of {@code Player}.
	 */
	public Player() {

	}

	/**
	 * Creates a new instance of {@code Player} without owner.
	 * 
	 * @param playerId
	 *            id of the Player. This parameter must be a non empty and non
	 *            {@code null} long.
	 * @param playerNickName
	 *            the nickname of Player. This parameter can not be {@code null}.
	 * @param playerPicture
	 *            the description of Player.
	 * @param playerInterests
	 *            the interests of Player.
	 * @param playerEmail
	 *            the email Player. This parameter can not be {@code null}.
	 * @param playerLocation
	 *            the location of Player. This parameter can not be {@code null}.
	 * @param playerProvince
	 *            the province of Player. This parameter can not be {@code null}.
	 * @param playerFavouriteSportsList
	 *            the list of favourite sports of Player.
	 * @param playerFavouriteTeamList
	 *            the list of favourite teams of Player.
	 * @param playerPrivacity
	 *            the privacity of Player.
	 *
	 * @throws NullPointerException
	 *             if a {@code null} value is passed as the value for any parameter.
	 * @throws IllegalArgumentException
	 *             if value provided for any parameter is not valid according to its
	 *             description.
	 */
	public Player(long playerId, String playerNickName, byte[] playerPicture, String playerInterests,
			String playerEmail, String playerLocation, String playerProvince, String playerFavouriteSportsList,
			String playerFavouriteTeamList, Boolean playerPrivacity) {

		setPlayerId(playerId);
		setPlayerNickName(playerNickName);
		setPlayerPicture(playerPicture);
		setPlayerInterests(playerInterests);
		setPlayerEmail(playerEmail);
		setPlayerLocation(playerLocation);
		setPlayerProvince(playerProvince);
		setPlayerFavouriteSportsList(playerFavouriteSportsList);
		setPlayerFavouriteTeamList(playerFavouriteTeamList);
		setPlayerPrivacity(playerPrivacity);
	}

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
	 *            the id of the Player. This parameter must be a non empty and non
	 *            {@code null} long.
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
	 *            the nickname of the Player. This parameter must be a non empty and
	 *            non {@code null} long.
	 *
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setPlayerNickName(String playerNickName) {
		requireNonNull(playerNickName, "Nickname cannot be null.");
		inclusiveBetween(2, 30, playerNickName.length(), "Nickname must be between 2 and 30 characters long.");

		this.playerNickName = playerNickName;
	}

	/**
	 * Returns the picture of the Player.
	 * 
	 * @return the picture of the Player.
	 */
	public byte[] getPlayerPicture() {
		return playerPicture;
	}

	/**
	 * Sets the picture of the Player.
	 * 
	 * @param playerPicture
	 *            the picture of the Player.
	 */
	public void setPlayerPicture(byte[] playerPicture) {
		this.playerPicture = playerPicture;
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
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setPlayerInterests(String playerInterests) {
		if (playerInterests != null) {
			isTrue(playerInterests.length() < 1001, "Interests must be less than 1000 characters long.");
		}

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
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid or the format is
	 *             not valid.
	 */
	public void setPlayerEmail(String playerEmail) {
		requireNonNull(playerEmail, "Player email cannot be null.");
		inclusiveBetween(5, 40, playerEmail.length(), "Player email must be between 5 and 40 characters long.");
		matchesPattern(playerEmail, EMAIL);

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
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setPlayerLocation(String playerLocation) {
		requireNonNull(playerLocation, "Location cannot be null.");
		inclusiveBetween(2, 100, playerLocation.length(), "Location must be between 2 and 100 characters long.");

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
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setPlayerProvince(String playerProvince) {
		requireNonNull(playerProvince, "Province cannot be null.");
		inclusiveBetween(2, 50, playerProvince.length(), "Province must be between 2 and 50 characters long.");

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
	 *            if player is private. This parameter must be a non empty and non
	 *            {@code null} boolean
	 *
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setPlayerPrivacity(Boolean privacity) {
		requireNonNull(privacity, "Privacity cannot be null.");

		this.playerPrivacity = privacity;
	}

	/**
	 * Returns the player {@code Team}s.
	 * 
	 * @return the player {@code Team}s.
	 */
	public List<Team> getPlayerTeams() {
		return playerTeams;
	}

	/**
	 * Sets the {@code Team}s of the player.
	 * 
	 * @param playerTeams
	 *            {@code List} of {@code Team}s of a Player. This parameter must be
	 *            a non {@code null} {@code List} of {@code Team}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setPlayerTeams(List<Team> playerTeams) {
		requireNonNull(playerTeams, "player teams can't be null");
		this.playerTeams = playerTeams;
	}

	/**
	 * Returns the managed players {@code User}s.
	 * 
	 * @return the managed players {@code User}s.
	 */
	public User getPlayerUser() {
		return playerUser;
	}

	/**
	 * Sets the manager User of the player.
	 * 
	 * @param playerUser
	 *            {@code User}s that manages the player.
	 */
	public void setPlayerUser(User playerUser) {
		this.playerUser = playerUser;
	}

	/**
	 * Returns the user {@code User}.
	 * 
	 * @return the playerManagedByUser {@code User}.
	 */
	public User getPlayerManagedByUser() {
		return playerManagedByUser;
	}

	/**
	 * Sets the manager user of the player.
	 * 
	 * @param playerManagedByUser
	 *            The manager {@code User} of the player.
	 */
	public void setPlayerManagedByUser(User playerManagedByUser) {
		this.playerManagedByUser = playerManagedByUser;
	}

	/**
	 * Returns the hashCode of Player.
	 * 
	 * @return the hashCode of Player.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerEmail == null) ? 0 : playerEmail.hashCode());
		result = prime * result + ((playerFavouriteSportsList == null) ? 0 : playerFavouriteSportsList.hashCode());
		result = prime * result + ((playerFavouriteTeamList == null) ? 0 : playerFavouriteTeamList.hashCode());
		result = prime * result + (int) (playerId ^ (playerId >>> 32));
		result = prime * result + ((playerInterests == null) ? 0 : playerInterests.hashCode());
		result = prime * result + ((playerLocation == null) ? 0 : playerLocation.hashCode());
		result = prime * result + ((playerNickName == null) ? 0 : playerNickName.hashCode());
		result = prime * result + Arrays.hashCode(playerPicture);
		result = prime * result + ((playerPrivacity == null) ? 0 : playerPrivacity.hashCode());
		result = prime * result + ((playerProvince == null) ? 0 : playerProvince.hashCode());
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
		Player other = (Player) obj;
		if (playerEmail == null) {
			if (other.playerEmail != null)
				return false;
		} else if (!playerEmail.equals(other.playerEmail))
			return false;
		if (playerFavouriteSportsList == null) {
			if (other.playerFavouriteSportsList != null)
				return false;
		} else if (!playerFavouriteSportsList.equals(other.playerFavouriteSportsList))
			return false;
		if (playerFavouriteTeamList == null) {
			if (other.playerFavouriteTeamList != null)
				return false;
		} else if (!playerFavouriteTeamList.equals(other.playerFavouriteTeamList))
			return false;
		if (playerId != other.playerId)
			return false;
		if (playerInterests == null) {
			if (other.playerInterests != null)
				return false;
		} else if (!playerInterests.equals(other.playerInterests))
			return false;
		if (playerLocation == null) {
			if (other.playerLocation != null)
				return false;
		} else if (!playerLocation.equals(other.playerLocation))
			return false;
		if (playerNickName == null) {
			if (other.playerNickName != null)
				return false;
		} else if (!playerNickName.equals(other.playerNickName))
			return false;
		if (!Arrays.equals(playerPicture, other.playerPicture))
			return false;
		if (playerPrivacity == null) {
			if (other.playerPrivacity != null)
				return false;
		} else if (!playerPrivacity.equals(other.playerPrivacity))
			return false;
		if (playerProvince == null) {
			if (other.playerProvince != null)
				return false;
		} else if (!playerProvince.equals(other.playerProvince))
			return false;
		return true;
	}

}
