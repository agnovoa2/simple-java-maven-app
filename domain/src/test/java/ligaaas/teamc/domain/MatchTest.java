package ligaaas.teamc.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchTest {

	private long matchId;
	private Team matchHomeTeam;
	private Team matchVisitingTeam;
	private Date matchDate;
	private HeadQuarter matchPlace;
	private EventState matchState;
	private int matchDuration;
	private int matchLocalPoints;
	private int matchVisitingPoints;
	private String matchDescription;
	private List<String> matchComments;
	private Round matchRound;

	@Before
	public void setUp() throws Exception {
		matchPlace = new HeadQuarter(700, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Bouzas", "Pontevedra", false);
		matchHomeTeam = new Team(900, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11,
				true, 18, 27, false, false, matchPlace, null, null);
		matchVisitingTeam = new Team(800, "Rapido de Bouzas 2 F.C.", "RdB 2 FC", "Rapido de Bouzas 2 F.C.",
				SportType.FOOTBALL11, true, 18, 27, false, false, matchPlace, null, null);
		matchDate = new Date();
		matchState = EventState.PLANIFIED;
		matchDuration = 0;
		matchLocalPoints = 0;
		matchVisitingPoints = 0;
		matchDescription = "Rapido de Bouzas F.C. VS Rapido de Bouzas 2 F.C.";
		matchComments = Arrays.asList("comment1", "comment2", "comment3");

		Competition c = new Competition(888, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		matchRound = new Round(999, 5, new GregorianCalendar(1991, 07, 02).getTime(), EventState.CANCELED,
				"For testing", new ArrayList<String>(), matchPlace, c);
	}

	@Test
	public void testMatchConstructor() {
		Match matchTest = new Match(matchId, matchHomeTeam, matchVisitingTeam, matchDate, matchPlace, matchState,
				matchDuration, matchLocalPoints, matchVisitingPoints, matchDescription, matchComments, matchRound);
		assertThat(matchTest.getMatchId(), is(equalTo(matchId)));
		assertThat(matchTest.getMatchHomeTeam(), is(equalTo(matchHomeTeam)));
		assertThat(matchTest.getMatchVisitingTeam(), is(equalTo(matchVisitingTeam)));
		assertThat(matchTest.getMatchDate(), is(equalTo(matchDate)));
		assertThat(matchTest.getMatchPlace(), is(equalTo(matchPlace)));
		assertThat(matchTest.getMatchState(), is(equalTo(matchState)));
		assertThat(matchTest.getMatchDuration(), is(equalTo(matchDuration)));
		assertThat(matchTest.getMatchLocalPoints(), is(equalTo(matchLocalPoints)));
		assertThat(matchTest.getMatchVisitingPoints(), is(equalTo(matchVisitingPoints)));
		assertThat(matchTest.getMatchDescription(), is(equalTo(matchDescription)));
		assertThat(matchTest.getMatchComments(), is(equalTo(matchComments)));
		assertThat(matchTest.getMatchRound(), is(equalTo(matchRound)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetMatchHomeTeamNull() {
		Match matchTest = new Match();
		matchTest.setMatchHomeTeam(null);
	}

	@Test
	public void testSetMatchHomeTeam() {
		Match matchTest = new Match();
		matchTest.setMatchHomeTeam(matchHomeTeam);
		assertThat(matchTest.getMatchHomeTeam(), is(equalTo(matchHomeTeam)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetMatchVisitingTeamNull() {
		Match matchTest = new Match();
		matchTest.setMatchVisitingTeam(null);
	}

	@Test
	public void testSetMatchVisitingTeam() {
		Match matchTest = new Match();
		matchTest.setMatchVisitingTeam(matchVisitingTeam);
		assertThat(matchTest.getMatchVisitingTeam(), is(equalTo(matchVisitingTeam)));
	}

	@Test
	public void testSetMatchDate() {
		Match matchTest = new Match();
		Date date = new Date();
		matchTest.setMatchDate(date);
		assertThat(matchTest.getMatchDate(), is(equalTo(date)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetMatchDateNull() {
		Match matchTest = new Match();
		matchTest.setMatchDate(null);
	}

	@Test
	public void testSetMatchState() {
		Match matchTest = new Match();
		matchTest.setMatchState(matchState);
		assertThat(matchTest.getMatchState(), is(equalTo(matchState)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetMatchStateNull() {
		Match matchTest = new Match();
		matchTest.setMatchState(null);
	}

	@Test
	public void testSetMatchDescription() {
		Match matchTest = new Match();
		matchTest.setMatchDescription(matchDescription);
		assertThat(matchTest.getMatchDescription(), is(equalTo(matchDescription)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetMatchDescriptionNull() {
		Match matchTest = new Match();
		matchTest.setMatchDescription(null);
	}

	@Test
	public void testSetMatchComments() {
		Match matchTest = new Match();
		matchTest.setMatchComments(matchComments);
		assertThat(matchTest.getMatchComments(), is(equalTo(matchComments)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetMatchCommentsNull() {
		Match matchTest = new Match();
		matchTest.setMatchComments(null);
	}

	@Test
	public void testSetMatchRound() {
		Match matchTest = new Match();
		matchTest.setMatchRound(matchRound);
		assertThat(matchTest.getMatchRound(), is(equalTo(matchRound)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetMatchRoundNull() {
		Match matchTest = new Match();
		matchTest.setMatchRound(null);
	}

	@Test
	public void testSetMatchDuration() {
		Match matchTest = new Match();
		matchTest.setMatchDuration(matchDuration);
		assertThat(matchTest.getMatchDuration(), is(equalTo(matchDuration)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMatchDurationTooShort() {
		Match matchTest = new Match();
		matchTest.setMatchDuration(-1);
	}

	@Test
	public void testSetMatchLocalPoints() {
		Match matchTest = new Match();
		matchTest.setMatchLocalPoints(matchLocalPoints);
		assertThat(matchTest.getMatchLocalPoints(), is(equalTo(matchLocalPoints)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMatchLocalPointsTooShort() {
		Match matchTest = new Match();
		matchTest.setMatchLocalPoints(-1);
	}

	@Test
	public void testSetMatchVisitingPoints() {
		Match matchTest = new Match();
		matchTest.setMatchVisitingPoints(matchVisitingPoints);
		assertThat(matchTest.getMatchVisitingPoints(), is(equalTo(matchVisitingPoints)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMatchVisitingPointsTooShort() {
		Match matchTest = new Match();
		matchTest.setMatchVisitingPoints(-1);
	}

	@Test
	public void testSetMatchPlace() {
		Match matchTest = new Match();
		matchTest.setMatchPlace(matchPlace);
		;
		assertThat(matchTest.getMatchPlace(), is(equalTo(matchPlace)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMatchDescriptionTooLong() {
		Match matchTest = new Match();
		String testString = "";
		for (int i = 0; i < 250; testString += Integer.toString(i++))
			;
		matchTest.setMatchDescription(testString);
	}
}
