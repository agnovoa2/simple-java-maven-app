package ligaaas.teamc.rest;

import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasOkStatus;
import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasBadRequestStatus;
import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasUnauthorizedRequestStatus;
import static ligaaas.teamc.domain.entities.IsEqualToTeamDTO.containsTeamsInAnyOrder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
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

import ligaaas.teamc.DTO.TeamDTO;
import ligaaas.teamc.converter.TeamConverter;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.entities.TeamsDataset;
import ligaaas.teamc.restricted.TeamResource;
import ligaaas.teamc.service.TeamEJB;

@RunWith(Arquillian.class)
public class TeamResourceRestTest {
	private final static String BASE_PATH = "api/private/team";
	private final static String POST_PATH = "api/private/team/1000/player/500";
	private final static String POST_PATH_NON_EXISTENT_PLAYER = "api/private/team/1000/player/501";
	private final static String POST_PATH_NON_EXISTENT_TEAM = "api/private/team/1005/player/500";
	private final static String BASIC_AUTHORIZATION = "Basic bW1yb2RyaWd1ZXo6YXNkMTIzLiw=";
	private final static String BASIC_AUTHORIZATION_WITHOUT_TEAMS = "Basic cHByb2RyaWd1ZXo6YXNkMTIzLiw=";
	private final static String BASIC_UNAUTHORIZATION = "Basic bW1yb2R=";

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(CORSFilter.class, IllegalArgumentExceptionMapper.class, SecurityExceptionMapper.class)
				.addPackage(TeamEJB.class.getPackage()).addPackage(Team.class.getPackage())
				.addPackage(TeamDTO.class.getPackage()).addPackage(TeamEJB.class.getPackage())
				.addPackage(Player.class.getPackage())
				.addPackage(TeamResource.class.getPackage()).addPackage(TeamConverter.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties").addAsWebInfResource("web.xml")
				.addAsWebInfResource("jboss-web.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@InSequence(1)
	@UsingDataSet("teamsREST.xml")
	@Cleanup(phase = TestExecutionPhase.NONE)
	public void beforeGet() {
	}

	@Test
	@InSequence(2)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION)
	public void testGet(@ArquillianResteasyResource(BASE_PATH) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<TeamDTO> expectedTeams = TeamConverter.toTeamDTO(TeamsDataset.teamsManagedByUser());
		List<TeamDTO> teams = response.readEntity(new GenericType<List<TeamDTO>>() {
		});

		assertThat(teams, is(containsTeamsInAnyOrder(expectedTeams)));

	}

	@Test
	@InSequence(3)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_UNAUTHORIZATION)
	public void testGetUnauthorized(@ArquillianResteasyResource(BASE_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasUnauthorizedRequestStatus());

	}

	@Test
	@InSequence(2)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION_WITHOUT_TEAMS)
	public void testGetWithoutTeams(@ArquillianResteasyResource(BASE_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<TeamDTO> expectedTeams = new ArrayList<TeamDTO>();
		List<TeamDTO> teams = response.readEntity(new GenericType<List<TeamDTO>>() {
		});

		assertThat(teams, is(containsTeamsInAnyOrder(expectedTeams)));

	}
	
	@Test
	@InSequence(6)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION)
	public void testPost(@ArquillianResteasyResource(POST_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().post(Entity.json(""));

		assertThat(response, hasOkStatus());
	}
	
	@Test
	@InSequence(7)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION)
	public void testPostAlreadyAddedPlayer(@ArquillianResteasyResource(POST_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().post(Entity.json(""));

		assertThat(response, hasBadRequestStatus());
	}
	
	@Test
	@InSequence(8)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION)
	public void testPostNonExistentTeam(@ArquillianResteasyResource(POST_PATH_NON_EXISTENT_TEAM) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().post(Entity.json(""));

		assertThat(response, hasBadRequestStatus());
	}
	
	@Test
	@InSequence(9)
	@RunAsClient
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION)
	public void testPostNonExistentPlayer(@ArquillianResteasyResource(POST_PATH_NON_EXISTENT_PLAYER) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().post(Entity.json(""));

		assertThat(response, hasBadRequestStatus());
	}

	@Test
	@InSequence(10)
	@ShouldMatchDataSet("teamsREST.xml")
	@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
	public void afterGet() {
	}
}
