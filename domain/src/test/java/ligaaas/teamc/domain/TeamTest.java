package ligaaas.teamc.domain;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TeamTest {
	private long teamId;
	private String teamName;
	private String teamShortName;
	private String teamDescription;
	private SportType teamSportType;
	private Boolean teamOpen;
	private int teamMinPlayers;
	private int teamMaxPlayers;
	private Boolean teamPublic;
	private Boolean teamDeleted;
	private HeadQuarter teamHeadQuarter;
	private Contact teamContact;
	private Competition teamCompetition;
	private List<Competition> teamCompetitions = new ArrayList<>();
	private Player teamPlayer;
	private List<Player> teamPlayers = new ArrayList<>();
	private User teamUser;

	private long newTeamId;
	private String newTeamName;
	private String newTeamShortName;
	private String newTeamDescription;
	private SportType newTeamSportType;
	private Boolean newTeamOpen;
	private int newTeamMinPlayers;
	private int newTeamMaxPlayers;
	private Boolean newTeamPublic;
	private Boolean newTeamDeleted;
	private HeadQuarter newTeamHeadQuarter;
	private Contact newTeamContact;
	private Competition newTeamCompetition;
	private List<Competition> newTeamCompetitions = new ArrayList<>();
	private Player newTeamPlayer;
	private List<Player> newTeamPlayers = new ArrayList<>();
	private User newTeamUser;

	@Before
	public void setUp() throws Exception {
		teamId = 1;
		teamName = "Rápido de Bouzas FC";
		teamShortName = "RB-FC";
		teamDescription = "Descripción del equipo rápido de Bouzas";
		teamSportType = SportType.FOOTBALL11;
		teamOpen = true;
		teamMinPlayers = 18;
		teamMaxPlayers = 27;
		teamPublic = false;
		teamDeleted = false;
		teamHeadQuarter = new HeadQuarter(1, "Rápido de Bouzas HQ",
				"Descripción de la sede del equipo Rápido de Bouzas", "Calle mayor Bouzas 33", "Bouzas", "Pontevedra",
				false);
		teamContact = new Contact(1, "rbfc@gmail.com", "htpp://www.rbfc.gal", "+34666666666", null, null, null);
		teamCompetition = new Competition(1, "Liga gallega", "Ligal", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		teamCompetitions.add(teamCompetition);
		teamPlayer = new Player(1, "Pepe", new byte[] {}, "Interest 1, interest 2", "pepe@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", true);
		teamPlayers.add(teamPlayer);
		teamUser = new User(1, "jvsantamarina", "Javier", "Villalobos Santamarina", "passwordJavier1",
				new SimpleDateFormat("dd/MM/YYYY").parse("24/09/1994"), "34631717L", "Rua Esgos", "Ourense", "Ourense",
				"España", "+34659797958", "https://twitter.com/jvsantamarina", "https://www.facebook.com/jvsantamarina",
				"https://www.instagram.com/jvsantamarina", "jvsantamarina@gmail.es", true, false, "token");

		newTeamId = 5;
		newTeamName = "Lento de Bouzas FC";
		newTeamShortName = "LB-FC";
		newTeamDescription = "Descripción del equipo lento de Bouzas";
		newTeamSportType = SportType.FOOTBALL11;
		newTeamOpen = false;
		newTeamMinPlayers = 17;
		newTeamMaxPlayers = 28;
		newTeamPublic = true;
		newTeamDeleted = true;
		newTeamHeadQuarter = new HeadQuarter(2, "Lento de Bouzas HQ",
				"Descripción de la sede del equipo Lento de Bouzas", "Calle menor Bouzas 33", "Bouzas", "Pontevedra",
				false);
		newTeamContact = new Contact(2, "lbfc@gmail.com", "htpp://www.lbfc.gal", "+34666666667", null, null, null);
		newTeamCompetition = new Competition(2, "Liga asturiana", "Ligar", "Descripción de la liga asturiana",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		newTeamCompetitions.add(newTeamCompetition);
		newTeamPlayer = new Player(2, "Antonio", new byte[] {}, "Interest 1, interest 2", "antonio@gmail.com",
				"Location", "Province", "FavouriteList", "FavouriteTeamList", true);
		newTeamPlayers.add(newTeamPlayer);
		newTeamUser = new User(2, "dvvilar", "Diego", "Villanueva Vilar", "passwordDiego1",
				new SimpleDateFormat("dd/MM/YYYY").parse("12/08/1992"), "34631717D", "Avd. de Portugal", "Lugo", "Lugo",
				"España", "+35658787859", "https://twitter.com/dvvilar", "https://www.facebook.com/dvvilar",
				"https://www.instagram.com/dvvilar", "dvvilar@gmail.es", false, false, "token");

	}

	@Test
	public void testTeamConstructor() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		assertThat(teamTest.getTeamId(), is(equalTo(teamId)));
		assertThat(teamTest.getTeamName(), is(equalTo(teamName)));
		assertThat(teamTest.getTeamShortName(), is(equalTo(teamShortName)));
		assertThat(teamTest.getTeamDescription(), is(equalTo(teamDescription)));
		assertThat(teamTest.getTeamSportType(), is(equalTo(teamSportType)));
		assertThat(teamTest.getTeamOpen(), is(equalTo(teamOpen)));
		assertThat(teamTest.getTeamMinPlayers(), is(equalTo(teamMinPlayers)));
		assertThat(teamTest.getTeamMaxPlayers(), is(equalTo(teamMaxPlayers)));
		assertThat(teamTest.getTeamPublic(), is(equalTo(teamPublic)));
		assertThat(teamTest.getTeamDeleted(), is(equalTo(teamDeleted)));
		assertThat(teamTest.getTeamHeadQuarter(), is(equalTo(teamHeadQuarter)));
		assertThat(teamTest.getTeamContact(), is(equalTo(teamContact)));
	}

	@Test
	public void testSetTeamId() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamId(newTeamId);
		assertThat(teamTest.getTeamId(), is(equalTo(newTeamId)));
	}

	@Test(expected = NullPointerException.class)
	public void testTeamStringTeamNameNull() {
		Team teamTest = new Team();
		teamTest.setTeamName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeamStringTeamNameTooShort() {
		Team teamTest = new Team();
		teamTest.setTeamName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeamStringTeamNameTooLong() {
		Team teamTest = new Team();
		teamTest.setTeamName(repeat('A', 61));
	}

	@Test
	public void testSetTeamName() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamName(newTeamName);
		assertThat(teamTest.getTeamName(), is(equalTo(newTeamName)));
	}

	@Test(expected = NullPointerException.class)
	public void testTeamStringTeamShortNameNull() {
		Team teamTest = new Team();
		teamTest.setTeamShortName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeamStringTeamShortNameTooShort() {
		Team teamTest = new Team();
		teamTest.setTeamShortName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeamStringTeamShortNameTooLong() {
		Team teamTest = new Team();
		teamTest.setTeamShortName(repeat('A', 11));
	}

	@Test
	public void testSetTeamShortName() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamShortName(newTeamShortName);
		assertThat(teamTest.getTeamShortName(), is(equalTo(newTeamShortName)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeamStringTeamDescriptionTooShort() {
		Team teamTest = new Team();
		teamTest.setTeamDescription("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeamStringTeamDescriptionTooLong() {
		Team teamTest = new Team();
		teamTest.setTeamDescription(repeat('A', 241));
	}

	@Test
	public void testSetTeamDescription() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamDescription(newTeamDescription);
		assertThat(teamTest.getTeamDescription(), is(equalTo(newTeamDescription)));
	}

	@Test
	public void testSetTeamDescriptionNull() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamDescription(null);
		assertThat(teamTest.getTeamDescription(), is(nullValue()));
	}

	@Test(expected = NullPointerException.class)
	public void testTeamEnumTeamSportTypeNull() {
		Team teamTest = new Team();
		teamTest.setTeamSportType(null);
	}

	@Test
	public void testSetTeamSportType() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamSportType(newTeamSportType);
		assertThat(teamTest.getTeamSportType(), is(equalTo(newTeamSportType)));
	}

	@Test(expected = NullPointerException.class)
	public void testTeamBooleanTeamOpenNull() {
		Team teamTest = new Team();
		teamTest.setTeamOpen(null);
	}

	@Test
	public void testSetTeamOpen() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamOpen(newTeamOpen);
		assertThat(teamTest.getTeamOpen(), is(equalTo(newTeamOpen)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeamIntTeamMinPlayersGreaterThanZero() {
		Team teamTest = new Team();
		teamTest.setTeamMinPlayers(0);
	}

	@Test
	public void testSetTeamMinPlayers() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamMinPlayers(newTeamMinPlayers);
		assertThat(teamTest.getTeamMinPlayers(), is(equalTo(newTeamMinPlayers)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTeamIntTeamMaxPlayersGreaterThanZero() {
		Team teamTest = new Team();
		teamTest.setTeamMaxPlayers(0);
	}

	@Test
	public void testSetTeamMaxPlayers() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamMaxPlayers(newTeamMaxPlayers);
		assertThat(teamTest.getTeamMaxPlayers(), is(equalTo(newTeamMaxPlayers)));
	}

	@Test(expected = NullPointerException.class)
	public void testTeamBooleanTeamPublicNull() {
		Team teamTest = new Team();
		teamTest.setTeamPublic(null);
	}

	@Test
	public void testSetTeamPublic() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamPublic(newTeamPublic);
		assertThat(teamTest.getTeamPublic(), is(equalTo(newTeamPublic)));
	}

	@Test(expected = NullPointerException.class)
	public void testTeamBooleanTeamDeletedNull() {
		Team teamTest = new Team();
		teamTest.setTeamDeleted(null);
	}

	@Test
	public void testSetTeamDeleted() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamDeleted(newTeamDeleted);
		assertThat(teamTest.getTeamDeleted(), is(equalTo(newTeamDeleted)));
	}

	@Test
	public void testSetTeamContact() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamContact(newTeamContact);
		assertThat(teamTest.getTeamContact(), is(equalTo(newTeamContact)));
	}

	@Test
	public void testSetTeamHeadQuarter() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamHeadQuarter(newTeamHeadQuarter);
		assertThat(teamTest.getTeamHeadQuarter(), is(equalTo(newTeamHeadQuarter)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetTeamCompetitionsNull() {
		Team teamTest = new Team();
		teamTest.setTeamCompetitions(null);
	}

	@Test
	public void testSetTeamCompetitions() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamCompetitions(teamCompetitions);
		assertThat(teamTest.getTeamCompetitions(), is(equalTo(teamCompetitions)));
		teamTest.setTeamCompetitions(newTeamCompetitions);
		assertThat(teamTest.getTeamCompetitions(), is(equalTo(newTeamCompetitions)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetTeamPlayersNull() {
		Team teamTest = new Team();
		teamTest.setTeamPlayers(null);
	}

	@Test
	public void testSetTeamPlayers() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamPlayers(teamPlayers);
		assertThat(teamTest.getTeamPlayers(), is(equalTo(teamPlayers)));
		teamTest.setTeamPlayers(newTeamPlayers);
		assertThat(teamTest.getTeamPlayers(), is(equalTo(newTeamPlayers)));
	}

	@Test
	public void testSetTeamUser() {
		Team teamTest = new Team(teamId, teamName, teamShortName, teamDescription, teamSportType, teamOpen,
				teamMinPlayers, teamMaxPlayers, teamPublic, teamDeleted, teamHeadQuarter, teamContact, teamUser);
		teamTest.setTeamUser(teamUser);
		assertThat(teamTest.getTeamUser(), is(equalTo(teamUser)));
		teamTest.setTeamUser(newTeamUser);
		assertThat(teamTest.getTeamUser(), is(equalTo(newTeamUser)));
	}
}
