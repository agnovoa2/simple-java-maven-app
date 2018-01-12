package ligaaas.teamc.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	protected Player player;

	protected long id;

	protected String nickname = "Pepe";
	protected String shortNickname = "a";
	protected String longNickname = repeatString(20, "ni");

	protected byte[] picture = new byte[] {};

	protected String interests = "Interest 1, interest 2";
	protected String longIterests = repeatString(120, "Interests");

	protected String email = "mail@mail.com";
	protected String longEmail = repeatString(20, "em") + "@mail.com";
	protected String shortEmail = "a";
	protected String errorFormatEmail = "mail.com";

	protected String location = "Location";
	protected String shortLocation = "a";
	protected String longLocation = repeatString(15, "location");

	protected String province = "province";
	protected String shortProvince = "a";
	protected String longProvince = repeatString(7, "province");

	protected String favouriteSportsList = "Football";

	protected String favouriteTeamList = "Celta de Vigo";

	protected boolean privacity = true;
	protected Team playerTeam = new Team(1, "Rápido de Bouzas FC", "RB-FC", "Descripción del equipo rápido de Bouzas",
			SportType.FOOTBALL11, true, 18, 27, false, false, null, null, null);;
	protected List<Team> playerTeams = new ArrayList<>();

	@Before
	public void setUpPlayer() throws Exception {
		this.player = new Player();
	}

	@Test
	public void testPlayerConstructor() {
		Player playerTest = new Player(id, nickname, picture, interests, email, location, province, favouriteSportsList,
				favouriteTeamList, privacity);

		assertThat(playerTest.getPlayerId(), equalTo(id));
		assertThat(playerTest.getPlayerNickName(), equalTo(nickname));
		assertThat(playerTest.getPlayerPicture(), equalTo(picture));
		assertThat(playerTest.getPlayerInterests(), equalTo(interests));
		assertThat(playerTest.getPlayerEmail(), equalTo(email));
		assertThat(playerTest.getPlayerLocation(), equalTo(location));
		assertThat(playerTest.getPlayerProvince(), equalTo(province));
		assertThat(playerTest.getPlayerFavouriteSportsList(), equalTo(favouriteSportsList));
		assertThat(playerTest.getPlayerFavouriteTeamList(), equalTo(favouriteTeamList));
		assertThat(playerTest.getPlayerPrivacity(), equalTo(privacity));

	}

	@Test
	public void testSetNickName() {
		this.player.setPlayerNickName(nickname);
		assertThat(this.player.getPlayerNickName(), equalTo(nickname));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerNickNameTooShort() {
		this.player.setPlayerNickName(shortNickname);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerNickNameTooLong() {
		this.player.setPlayerNickName(longNickname);
	}

	@Test(expected = NullPointerException.class)
	public void testSetPlayerNickNameWithNull() {
		this.player.setPlayerNickName(null);
	}

	@Test
	public void testSetPlayerPicture() {
		this.player.setPlayerPicture(new byte[] {});
		assertThat(this.player.getPlayerPicture(), notNullValue());
	}

	@Test
	public void testSetPlayerInterests() {
		this.player.setPlayerInterests(interests);
		assertThat(this.player.getPlayerInterests(), equalTo(interests));
	}
	
	@Test
	public void testSetPlayerInterestsNull() {
		this.player.setPlayerInterests(null);
		assertThat(this.player.getPlayerInterests(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerTooLong() {
		this.player.setPlayerInterests(longIterests);
	}

	@Test
	public void testSetPlayerEmail() {
		this.player.setPlayerEmail(email);
		assertThat(this.player.getPlayerEmail(), equalTo(email));
	}

	@Test(expected = NullPointerException.class)
	public void testSetPlayerEmailWithNull() {
		this.player.setPlayerEmail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerEmailTooShort() {
		this.player.setPlayerEmail(shortEmail);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerEmailTooLong() {
		this.player.setPlayerEmail(longEmail);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerEmailErrorFormat() {
		this.player.setPlayerEmail(errorFormatEmail);
	}

	@Test
	public void testSetPlayerLocation() {
		this.player.setPlayerLocation(location);
		assertThat(this.player.getPlayerLocation(), equalTo(location));
	}

	@Test(expected = NullPointerException.class)
	public void testSetPlayerLocationWithNull() {
		this.player.setPlayerLocation(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerLocationTooShort() {
		this.player.setPlayerLocation(shortLocation);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerLocationTooLong() {
		this.player.setPlayerLocation(longLocation);
	}

	@Test
	public void testSetPlayerProvince() {
		this.player.setPlayerProvince(province);
		assertThat(this.player.getPlayerProvince(), equalTo(province));
	}

	@Test(expected = NullPointerException.class)
	public void testSetPlayerProvinceWithNull() {
		this.player.setPlayerProvince(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerProvinceTooShort() {
		this.player.setPlayerProvince(shortProvince);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerProvinceTooLong() {
		this.player.setPlayerProvince(longProvince);
	}

	@Test
	public void testSetPlayerFavouriteSportsList() {
		this.player.setPlayerFavouriteSportsList(favouriteSportsList);
		assertThat(this.player.getPlayerFavouriteSportsList(), equalTo(favouriteSportsList));
	}

	@Test
	public void testSetPlayerFavouriteTeamList() {
		this.player.setPlayerFavouriteTeamList(favouriteTeamList);
		assertThat(this.player.getPlayerFavouriteTeamList(), equalTo(favouriteTeamList));
	}

	@Test
	public void testSetPlayerPrivacity() {
		this.player.setPlayerPrivacity(privacity);
		assertThat(this.player.getPlayerPrivacity(), equalTo(privacity));
	}

	@Test(expected = NullPointerException.class)
	public void testSetPlayerPrivacityWithNull() {
		this.player.setPlayerPrivacity(null);
	}

	@Test(expected = NullPointerException.class)
	public void testSetPlayerTeamsNull() {
		this.player.setPlayerTeams(null);
	}

	@Test
	public void testSetPlayerTeams() {
		playerTeams.add(playerTeam);
		this.player.setPlayerTeams(playerTeams);
		assertThat(this.player.getPlayerTeams(), is(equalTo(playerTeams)));
	}

	@Test
	public void testSetPlayerManagedByUser() {
		User userTest = new User();
		player.setPlayerManagedByUser(userTest);
		assertThat(player.getPlayerManagedByUser(), is(equalTo(userTest)));
	}

	@Test
	public void testSetPlayerUser() {
		User userTest = new User();
		player.setPlayerUser(userTest);
		assertThat(player.getPlayerUser(), is(equalTo(userTest)));
	}
	
	private String repeatString(int numberLoops, String string) {
		String returnString = "";
		for (int i = 0; i < numberLoops; i++) {
			returnString += string;
		}
		return returnString;
	}

}
