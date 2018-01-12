package ligaaas.teamc.domain;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CompetitionTest {
	private long competitionId;
	private String competitionName;
	private String competitionShortName;
	private String competitionDescription;
	private SportType competitionSportType;
	private CompetitionType competitionType;
	private Boolean competitionOpen;
	private int competitionMinTeams;
	private int competitionMaxTeams;
	private Boolean competitionPublic;
	private Boolean competitionDeleted;
	private HeadQuarter competitionHeadQuarter;
	private User competitionUser;
	private Contact competitionContact;
	private Team competitionTeam;
	private List<Team> competitionTeams = new ArrayList<>();

	private long newCompetitionId;
	private String newCompetitionName;
	private String newCompetitionShortName;
	private String newCompetitionDescription;
	private SportType newCompetitionSportType;
	private CompetitionType newCompetitionType;
	private Boolean newCompetitionOpen;
	private int newCompetitionMinTeams;
	private int newCompetitionMaxTeams;
	private Boolean newCompetitionPublic;
	private Boolean newCompetitionDeleted;
	private HeadQuarter newCompetitionHeadQuarter;
	private User newCompetitionUser;
	private Contact newCompetitionContact;
	private Team newCompetitionTeam;
	private List<Team> newCompetitionTeams = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		competitionId = 1;
		competitionName = "Liga gallega";
		competitionShortName = "Ligal";
		competitionDescription = "Descripción de la liga gallega";
		competitionSportType = SportType.FOOTBALL11;
		competitionType = CompetitionType.SIMPLE;
		competitionOpen = true;
		competitionMinTeams = 18;
		competitionMaxTeams = 27;
		competitionPublic = false;
		competitionDeleted = false;
		competitionHeadQuarter = new HeadQuarter(1, "Liga gallega HQ", "Descripción de la sede de la liga gallega",
				"Calle mayor Bouzas 33", "Bouzas", "Pontevedra", false);
		competitionUser = new User(1, "jvsantamarina", "Javier", "Villalobos Santamarina", "passwordJavier1",
				new SimpleDateFormat("dd/MM/YYYY").parse("24/09/1994"), "34631717L", "Rua Esgos", "Ourense", "Ourense",
				"España", "+34659797958", "https://twitter.com/jvsantamarina", "https://www.facebook.com/jvsantamarina",
				"https://www.instagram.com/jvsantamarina", "jvsantamarina@gmail.es", true, false, "token");
		competitionContact = new Contact(1, "ligal@gmail.com", "htpp://www.ligal.gal", "+34666666666", null, null,
				null);
		competitionTeam = new Team(1, "Rápido de Bouzas FC", "RB-FC", "Descripción del equipo rápido de Bouzas",
				SportType.FOOTBALL11, true, 18, 27, false, false, null, null, null);
		competitionTeams.add(competitionTeam);

		newCompetitionId = 5;
		newCompetitionName = "Liga asturiana";
		newCompetitionShortName = "Ligar";
		newCompetitionDescription = "Descripción de la liga asturiana";
		newCompetitionSportType = SportType.FOOTBALL11;
		newCompetitionType = CompetitionType.DOUBLE;
		newCompetitionOpen = false;
		newCompetitionMinTeams = 17;
		newCompetitionMaxTeams = 28;
		newCompetitionPublic = true;
		newCompetitionDeleted = true;
		newCompetitionHeadQuarter = new HeadQuarter(2, "Liga asturiana HQ",
				"Descripción de la sede de la liga asturiana", "Calle menor Bouzas 33", "Bouzas", "Pontevedra", false);
		competitionUser = new User(2, "dvvilar", "Diego", "Villanueva Vilar", "passwordDiego1",
				new SimpleDateFormat("dd/MM/YYYY").parse("12/08/1992"), "34631717D", "Avd. de Portugal", "Lugo", "Lugo",
				"España", "+35658787859", "https://twitter.com/dvvilar", "https://www.facebook.com/dvvilar",
				"https://www.instagram.com/dvvilar", "dvvilar@gmail.es", false, false, "token");
		newCompetitionContact = new Contact(2, "ligar@gmail.com", "htpp://www.ligar.gal", "+34666666667", null, null,
				null);
		newCompetitionTeam = new Team(5, "Lento de Bouzas FC", "LB-FC", "Descripción del equipo lento de Bouzas",
				SportType.FOOTBALL11, true, 18, 27, false, false, null, null, null);
		newCompetitionTeams.add(newCompetitionTeam);
	}

	@Test
	public void testCompetitionConstructor() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		assertThat(competitionTest.getCompetitionId(), is(equalTo(competitionId)));
		assertThat(competitionTest.getCompetitionName(), is(equalTo(competitionName)));
		assertThat(competitionTest.getCompetitionShortName(), is(equalTo(competitionShortName)));
		assertThat(competitionTest.getCompetitionDescription(), is(equalTo(competitionDescription)));
		assertThat(competitionTest.getCompetitionSportType(), is(equalTo(competitionSportType)));
		assertThat(competitionTest.getCompetitionOpen(), is(equalTo(competitionOpen)));
		assertThat(competitionTest.getCompetitionMinTeams(), is(equalTo(competitionMinTeams)));
		assertThat(competitionTest.getCompetitionMaxTeams(), is(equalTo(competitionMaxTeams)));
		assertThat(competitionTest.getCompetitionPublic(), is(equalTo(competitionPublic)));
		assertThat(competitionTest.getCompetitionDeleted(), is(equalTo(competitionDeleted)));
		assertThat(competitionTest.getCompetitionHeadQuarter(), is(equalTo(competitionHeadQuarter)));
		assertThat(competitionTest.getCompetitionContact(), is(equalTo(competitionContact)));
	}

	@Test
	public void testSetCompetitionId() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionId(newCompetitionId);
		assertThat(competitionTest.getCompetitionId(), is(equalTo(newCompetitionId)));
	}

	@Test(expected = NullPointerException.class)
	public void testCompetitionStringCompetitionNameNull() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompetitionStringCompetitionNameTooShort() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompetitionStringCompetitionNameTooLong() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionName(repeat('A', 61));
	}

	@Test
	public void testSetCompetitionName() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionName(newCompetitionName);
		assertThat(competitionTest.getCompetitionName(), is(equalTo(newCompetitionName)));
	}

	@Test(expected = NullPointerException.class)
	public void testCompetitionStringCompetitionShortNameNull() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionShortName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompetitionStringCompetitionShortNameTooShort() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionShortName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompetitionStringCompetitionShortNameTooLong() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionShortName(repeat('A', 11));
	}

	@Test
	public void testSetCompetitionShortName() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionShortName(newCompetitionShortName);
		assertThat(competitionTest.getCompetitionShortName(), is(equalTo(newCompetitionShortName)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompetitionStringCompetitionDescriptionTooShort() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionDescription("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompetitionStringCompetitionDescriptionTooLong() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionDescription(repeat('A', 241));
	}

	@Test
	public void testSetCompetitionDescription() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionDescription(newCompetitionDescription);
		assertThat(competitionTest.getCompetitionDescription(), is(equalTo(newCompetitionDescription)));
	}

	@Test(expected = NullPointerException.class)
	public void testCompetitionEnumCompetitionSportTypeNull() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionSportType(null);
	}

	@Test
	public void testSetCompetitionSportType() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionSportType(newCompetitionSportType);
		assertThat(competitionTest.getCompetitionSportType(), is(equalTo(newCompetitionSportType)));
	}

	@Test(expected = NullPointerException.class)
	public void testCompetitionEnumCompetitionTypeNull() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionType(null);
	}

	@Test
	public void testSetCompetitionType() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionType(newCompetitionType);
		assertThat(competitionTest.getCompetitionType(), is(equalTo(newCompetitionType)));
	}

	@Test(expected = NullPointerException.class)
	public void testCompetitionBooleanCompetitionOpenNull() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionOpen(null);
	}

	@Test
	public void testSetCompetitionOpen() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionOpen(newCompetitionOpen);
		assertThat(competitionTest.getCompetitionOpen(), is(equalTo(newCompetitionOpen)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompetitionIntCompetitionMinTeamsGreaterThanZero() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionMinTeams(0);
	}

	@Test
	public void testSetCompetitionMinTeams() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionMinTeams(newCompetitionMinTeams);
		assertThat(competitionTest.getCompetitionMinTeams(), is(equalTo(newCompetitionMinTeams)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompetitionIntCompetitionMaxTeamsGreaterThanZero() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionMaxTeams(0);
	}

	@Test
	public void testSetCompetitionMaxTeams() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionMaxTeams(newCompetitionMaxTeams);
		assertThat(competitionTest.getCompetitionMaxTeams(), is(equalTo(newCompetitionMaxTeams)));
	}

	@Test(expected = NullPointerException.class)
	public void testCompetitionBooleanCompetitionPublicNull() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionPublic(null);
	}

	@Test
	public void testSetCompetitionPublic() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionPublic(newCompetitionPublic);
		assertThat(competitionTest.getCompetitionPublic(), is(equalTo(newCompetitionPublic)));
	}

	@Test(expected = NullPointerException.class)
	public void testCompetitionBooleanCompetitionDeletedNull() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionDeleted(null);
	}

	@Test
	public void testSetCompetitionDeleted() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionDeleted(newCompetitionDeleted);
		assertThat(competitionTest.getCompetitionDeleted(), is(equalTo(newCompetitionDeleted)));
	}

	@Test
	public void testSetCompetitionContact() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionContact(newCompetitionContact);
		assertThat(competitionTest.getCompetitionContact(), is(equalTo(newCompetitionContact)));
	}

	@Test
	public void testSetCompetitionUser() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionUser(competitionUser);
		assertThat(competitionTest.getCompetitionUser(), is(equalTo(competitionUser)));
		competitionTest.setCompetitionUser(newCompetitionUser);
		assertThat(competitionTest.getCompetitionUser(), is(equalTo(newCompetitionUser)));
	}

	@Test
	public void testSetCompetitionHeadQuarter() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionHeadQuarter(newCompetitionHeadQuarter);
		assertThat(competitionTest.getCompetitionHeadQuarter(), is(equalTo(newCompetitionHeadQuarter)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetCompetitionTeamsNull() {
		Competition competitionTest = new Competition();
		competitionTest.setCompetitionTeams(null);
	}

	@Test
	public void testSetCompetitionTeams() {
		Competition competitionTest = new Competition(competitionId, competitionName, competitionShortName,
				competitionDescription, competitionSportType, competitionType, competitionOpen, competitionMinTeams,
				competitionMaxTeams, competitionPublic, competitionDeleted, competitionHeadQuarter, competitionUser,
				competitionContact);
		competitionTest.setCompetitionTeams(competitionTeams);
		assertThat(competitionTest.getCompetitionTeams(), is(equalTo(competitionTeams)));
		competitionTest.setCompetitionTeams(newCompetitionTeams);
		assertThat(competitionTest.getCompetitionTeams(), is(equalTo(newCompetitionTeams)));
	}
}
