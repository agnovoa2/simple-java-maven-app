package ligaaas.teamc.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TeamTest.class, HeadQuarterTest.class, ContactTest.class, CompetitionTest.class, PlayerTest.class,
		UserTest.class, RoundTest.class, MatchTest.class })
public class EntitiesTestSuite {

}
