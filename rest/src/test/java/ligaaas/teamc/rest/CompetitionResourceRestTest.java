package ligaaas.teamc.rest;

import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasBadRequestStatus;
import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasOkStatus;
import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasUnauthorizedRequestStatus;
import static ligaaas.teamc.converter.CompetitionConverter.toCompetitionDTO;
import static ligaaas.teamc.converter.TeamConverter.toTeamDTO;
import static ligaaas.teamc.domain.entities.CompetitionsDataset.findByLocalityTypeCompetition;
import static ligaaas.teamc.domain.entities.CompetitionsDataset.findBySportTypeCompetition;
import static ligaaas.teamc.domain.entities.CompetitionsDataset.restCompetitions;
import static ligaaas.teamc.domain.entities.IsEqualToCompetitionDTO.containsCompetitionsInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToTeamDTO.containsTeamsInAnyOrder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.extension.rest.client.Header;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import ligaaas.teamc.DTO.CompetitionDTO;
import ligaaas.teamc.DTO.TeamDTO;
import ligaaas.teamc.converter.CompetitionConverter;
import ligaaas.teamc.converter.TeamConverter;
import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.entities.CompetitionsDataset;
import ligaaas.teamc.restricted.CompetitionResourceRestricted;
import ligaaas.teamc.service.CompetitionEJB;
import ligaaas.teamc.service.TeamEJB;

@RunWith(Arquillian.class)
public class CompetitionResourceRestTest {
	private final static String BASE_PATH = "api/competition";
	private final static String SPORT_TYPE_PATH = BASE_PATH + "?sportType=FOOTBALL7";
	private final static String WRONG_SPORT_TYPE_PATH = BASE_PATH + "?sportType=BSAKETBALL";
	private final static String LOCALITY_PATH = BASE_PATH + "?competitionLocality=Bouzas";
	private final static String SPORT_TYPE_LOCALITY_PATH = BASE_PATH
			+ "?sportType=BASKETBALL&competitionLocality=Ourense";
	private final static String TEAM_PATH = "/team";
	private final static String TEAMS_OF_COMPETITION = BASE_PATH + "/1" + TEAM_PATH;
	private final static String TEAMS_OF_UNEXIST_COMPETITION = BASE_PATH + "/89" + TEAM_PATH;

	private final static String BASE_PRIVATE_PATH = "api/private/competition";
	private final static String BASIC_AUTHORIZATION = "Basic bW1yb2RyaWd1ZXo6YXNkMTIzLiw=";
	private final static String BASIC_AUTHORIZATION_WITHOUT_TEAMS = "Basic cHByb2RyaWd1ZXo6YXNkMTIzLiw=";
	private final static String BASIC_UNAUTHORIZATION = "Basic bW1yb2R=";

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test1.war")
				.addClasses(CORSFilter.class, IllegalArgumentExceptionMapper.class, SecurityExceptionMapper.class)
				.addPackage(Competition.class.getPackage()).addPackage(CompetitionDTO.class.getPackage())
				.addPackage(CompetitionEJB.class.getPackage()).addPackage(CompetitionResource.class.getPackage())
				.addPackage(CompetitionResourceRestricted.class.getPackage())
				.addPackage(CompetitionConverter.class.getPackage()).addPackage(Team.class.getPackage())
				.addPackage(TeamEJB.class.getPackage()).addPackage(TeamDTO.class.getPackage())
				.addPackage(TeamResource.class.getPackage()).addPackage(TeamConverter.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties").addAsWebInfResource("web.xml")
				.addAsWebInfResource("jboss-web.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@InSequence(1)
	@UsingDataSet("competitionsREST.xml")
	@Cleanup(phase = TestExecutionPhase.NONE)
	public void beforeGetPublicCompetitions() {
	}

	@Test
	@InSequence(2)
	@RunAsClient
	public void testGetPublicCompetitions(@ArquillianResteasyResource(BASE_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<CompetitionDTO> expectedCompetitions = toCompetitionDTO(restCompetitions());
		List<CompetitionDTO> competitions = response.readEntity(new GenericType<List<CompetitionDTO>>() {
		});

		assertThat(competitions, is(containsCompetitionsInAnyOrder(expectedCompetitions)));
	}

	@Test
	@InSequence(3)
	@RunAsClient
	public void testGetPublicBySportTypeCompetitions(
			@ArquillianResteasyResource(SPORT_TYPE_PATH) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		CompetitionDTO expectedCompetitions = toCompetitionDTO(findBySportTypeCompetition());
		List<CompetitionDTO> competitions = response.readEntity(new GenericType<List<CompetitionDTO>>() {
		});

		assertThat(competitions, is(containsCompetitionsInAnyOrder(expectedCompetitions)));
	}

	@Test
	@InSequence(4)
	@RunAsClient
	public void testGetPublicByWrongSportTypeCompetitions(
			@ArquillianResteasyResource(WRONG_SPORT_TYPE_PATH) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasBadRequestStatus());
	}

	@Test
	@InSequence(5)
	@RunAsClient
	public void testGetByLocalityPublicCompetitions(
			@ArquillianResteasyResource(LOCALITY_PATH) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		CompetitionDTO expectedCompetitions = toCompetitionDTO(findByLocalityTypeCompetition());
		List<CompetitionDTO> competitions = response.readEntity(new GenericType<List<CompetitionDTO>>() {
		});

		assertThat(competitions, is(containsCompetitionsInAnyOrder(expectedCompetitions)));
	}

	@Test
	@InSequence(6)
	@RunAsClient
	public void testGetByLocalityAndSporTypePublicCompetitions(
			@ArquillianResteasyResource(SPORT_TYPE_LOCALITY_PATH) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasBadRequestStatus());
	}

	@Test
	@InSequence(7)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION)
	public void testGet(@ArquillianResteasyResource(BASE_PRIVATE_PATH) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<CompetitionDTO> expectedCompetitions = CompetitionConverter
				.toCompetitionDTO(CompetitionsDataset.restCompetitions());
		List<CompetitionDTO> competitions = response.readEntity(new GenericType<List<CompetitionDTO>>() {
		});

		assertThat(competitions, is(containsCompetitionsInAnyOrder(expectedCompetitions)));
	}

	@Test
	@InSequence(8)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_UNAUTHORIZATION)
	public void testGetUnauthorized(@ArquillianResteasyResource(BASE_PRIVATE_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasUnauthorizedRequestStatus());
	}

	@Test
	@InSequence(9)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION_WITHOUT_TEAMS)
	public void testGetWithoutCompetitions(@ArquillianResteasyResource(BASE_PRIVATE_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<CompetitionDTO> expectedCompetitions = new ArrayList<CompetitionDTO>();
		List<CompetitionDTO> competitions = response.readEntity(new GenericType<List<CompetitionDTO>>() {
		});

		assertThat(competitions, is(containsCompetitionsInAnyOrder(expectedCompetitions)));
	}

	@Test
	@InSequence(10)
	@RunAsClient
	public void testGetPublicTeamsByCompetition(
			@ArquillianResteasyResource(TEAMS_OF_COMPETITION) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<TeamDTO> expectedTeams = toTeamDTO(CompetitionsDataset.restTeams());
		List<TeamDTO> teams = response.readEntity(new GenericType<List<TeamDTO>>() {
		});

		assertThat(teams, is(containsTeamsInAnyOrder(expectedTeams)));
	}

	@Test
	@InSequence(11)
	@RunAsClient
	public void testGetPublicTeamsByUnexistCompetition(
			@ArquillianResteasyResource(TEAMS_OF_UNEXIST_COMPETITION) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasBadRequestStatus());
	}

	@Test
	@InSequence(11)
	@ShouldMatchDataSet("competitionsREST.xml")
	@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
	public void afterGetSportType() {
	}
}
