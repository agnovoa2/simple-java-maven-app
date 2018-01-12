package ligaaas.teamc.domain;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RoundTest {

	private long roundId;
	private int roundNumber;
	private Date roundDate;
	private EventState roundState;
	private String roundDescription;
	private List<String> roundComments = new ArrayList<>();;
	private HeadQuarter roundHeadQuarter;
	private Competition roundCompetition;

	private long newRoundId;
	private int newRoundNumber;
	private Date newRoundDate;
	private EventState newRoundState;
	private String newRoundDescription;
	private List<String> newRoundComments = new ArrayList<>();;
	private HeadQuarter newRoundHeadQuarter;
	private Competition newRoundCompetition;

	@Before
	public void setUp() throws Exception {
		roundId = 1;
		roundNumber = 1;
		roundDate = new Date();
		roundState = EventState.PLANIFIED;
		roundDescription = "Descripcion";
		roundComments.add("commentario");
		roundHeadQuarter = new HeadQuarter(1, "Liga gallega HQ", "Descripción de la sede de la liga gallega",
				"Calle mayor Bouzas 33", "Bouzas", "Pontevedra", false);
		roundCompetition = new Competition(1, "Liga gallega", "Ligal", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);

		newRoundId = 2;
		newRoundNumber = 2;
		newRoundDate = new SimpleDateFormat("dd/MM/YYYY").parse("24/09/1994");
		newRoundState = EventState.CANCELED;
		newRoundDescription = "nueva descripcion";
		newRoundComments.add("nuevo comentario");
		newRoundHeadQuarter = new HeadQuarter(2, "Liga asturiana HQ", "Descripción de la sede de la liga asturiana",
				"Calle menor Bouzas 33", "Bouzas", "Pontevedra", false);
		newRoundCompetition = new Competition(2, "Liga asturiana", "Ligar", "Descripción de la liga asturiana",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);

	}

	@Test
	public void testRoundConstructor() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		assertThat(roundTest.getRoundId(), is(equalTo(roundId)));
		assertThat(roundTest.getRoundNumber(), is(equalTo(roundNumber)));
		assertThat(roundTest.getRoundDate(), is(equalTo(roundDate)));
		assertThat(roundTest.getRoundState(), is(equalTo(roundState)));
		assertThat(roundTest.getRoundDescription(), is(equalTo(roundDescription)));
		assertThat(roundTest.getRoundComments(), is(equalTo(roundComments)));
		assertThat(roundTest.getRoundHeadQuarter(), is(equalTo(roundHeadQuarter)));
		assertThat(roundTest.getRoundCompetition(), is(equalTo(roundCompetition)));

	}

	@Test
	public void testSetRoundId() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		roundTest.setRoundId(newRoundId);
		assertThat(roundTest.getRoundId(), is(equalTo(newRoundId)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRoundNumberGreaterThanZero() {
		Round roundTest = new Round();
		roundTest.setRoundNumber(0);
	}

	@Test()
	public void testRoundNumber() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		roundTest.setRoundNumber(newRoundNumber);
		assertThat(roundTest.getRoundNumber(), is(equalTo(newRoundNumber)));
	}

	@Test(expected = NullPointerException.class)
	public void testRoundDateNull() {
		Round roundTest = new Round();
		roundTest.setRoundDate(null);
	}

	@Test
	public void testRoundDate() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		roundTest.setRoundDate(newRoundDate);
		assertThat(roundTest.getRoundDate(), is(equalTo(newRoundDate)));
	}

	@Test(expected = NullPointerException.class)
	public void testRoundStateNull() {
		Round roundTest = new Round();
		roundTest.setRoundState(null);
	}

	@Test
	public void testRoundState() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		roundTest.setRoundState(newRoundState);
		assertThat(roundTest.getRoundState(), is(equalTo(newRoundState)));
	}

	@Test(expected = NullPointerException.class)
	public void testRoundDescriptionNull() {
		Round roundTest = new Round();
		roundTest.setRoundDescription(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRoundStringRoundDescriptionTooLong() {
		Round roundTest = new Round();
		roundTest.setRoundDescription(repeat('A', 241));
	}

	@Test
	public void testSetRoundDescription() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		roundTest.setRoundDescription(newRoundDescription);
		assertThat(roundTest.getRoundDescription(), is(equalTo(newRoundDescription)));
	}

	@Test(expected = NullPointerException.class)
	public void testRoundCommentsNull() {
		Round roundTest = new Round();
		roundTest.setRoundComments(null);
	}

	@Test
	public void testRoundComments() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		roundTest.setRoundComments(newRoundComments);
		assertThat(roundTest.getRoundComments(), is(equalTo(newRoundComments)));
	}

	@Test
	public void testSetRoundHeadQuarter() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		roundTest.setRoundHeadQuarter(newRoundHeadQuarter);
		assertThat(roundTest.getRoundHeadQuarter(), is(equalTo(newRoundHeadQuarter)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetRoundCompetitionNull() {
		Round roundTest = new Round();
		roundTest.setRoundCompetition(null);
	}

	@Test
	public void testSetRoundCompetitions() {
		Round roundTest = new Round(roundId, roundNumber, roundDate, roundState, roundDescription, roundComments,
				roundHeadQuarter, roundCompetition);
		roundTest.setRoundCompetition(newRoundCompetition);
		assertThat(roundTest.getRoundCompetition(), is(equalTo(newRoundCompetition)));
	}

}
