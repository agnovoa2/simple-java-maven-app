package ligaaas.teamc.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@SuiteClasses({	CompetitionResourceRestTest.class,PlayerResourceRestTest.class,TeamResourceRestTest.class, TeamResourcePublicRestTest.class})
@RunWith(Suite.class)
public class ResourceIntegrationTestSuite {
	
}
