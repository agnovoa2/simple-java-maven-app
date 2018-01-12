package ligaaas.teamc.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CompetitionEJBTest.class, TeamEJBTest.class, UserEJBTest.class, RoundEJBTest.class, MatchEJBTest.class,
		HeadQuarterEJBTest.class, PlayerEJBTest.class, PlayerEJBTestCreate.class })
public class EJBIntegrationTestSuite {

}
