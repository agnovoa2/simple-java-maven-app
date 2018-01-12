package ligaaas.teamc.rest;

import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasOkStatus;
import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasBadRequestStatus;
import static org.junit.Assert.assertThat;
import static ligaaas.teamc.domain.entities.IsEqualToTeamDTO.containsTeamsInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToPlayerDTO.containsPlayersInAnyOrder;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
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

import ligaaas.teamc.DTO.PlayerDTO;
import ligaaas.teamc.DTO.TeamDTO;
import ligaaas.teamc.converter.PlayerConverter;
import ligaaas.teamc.converter.TeamConverter;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.entities.PlayersDataset;
import ligaaas.teamc.domain.entities.TeamsDataset;
import ligaaas.teamc.service.CompetitionEJB;
import ligaaas.teamc.service.PlayerEJB;
import ligaaas.teamc.service.TeamEJB;
import ligaaas.teamc.service.UserEJB;

@RunWith(Arquillian.class)
public class TeamResourcePublicRestTest {
	private final static String BASE_PATH = "api/team";
	private final static String PLAYERS_PATH = "api/team/998/players";
	private final static String SPORT_TYPE_PATH = BASE_PATH + "?sportType=FOOTBALL11";
	private final static String WRONG_SPORT_TYPE_PATH = BASE_PATH + "?sportType=BSAKETBALL";
	private final static String LOCALITY_PATH = BASE_PATH + "?locality=Ourense";
	private final static String SPORT_TYPE_LOCALITY_PATH = BASE_PATH + "?sportType=FOOTBALL11&locality=Ourense";

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test1.war")
				.addClasses(TeamEJB.class, UserEJB.class, CompetitionEJB.class, PlayerEJB.class)
				.addPackage(Team.class.getPackage()).addPackage(TeamDTO.class.getPackage())
				.addPackage(TeamResource.class.getPackage()).addPackage(TeamConverter.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties").addAsWebInfResource("web.xml")
				.addAsWebInfResource("jboss-web.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@InSequence(1)
	@UsingDataSet("teamsPublicREST.xml")
	@Cleanup(phase = TestExecutionPhase.NONE)
	public void beforeGetPublicTeams() {
	}

	@Test
	@InSequence(2)
	@RunAsClient
	public void testGetPublicTeams(@ArquillianResteasyResource(BASE_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<TeamDTO> expectedTeams = TeamConverter.toTeamDTO(TeamsDataset.listTeams());
		List<TeamDTO> teams = response.readEntity(new GenericType<List<TeamDTO>>() {
		});

		assertThat(teams, is(containsTeamsInAnyOrder(expectedTeams)));
	}

	@Test
	@InSequence(3)
	@RunAsClient
	public void testGetPublicBySportTypeTeams(@ArquillianResteasyResource(SPORT_TYPE_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		TeamDTO expectedTeams = TeamConverter.toTeamDTO(TeamsDataset.otherTeam());
		List<TeamDTO> teams = response.readEntity(new GenericType<List<TeamDTO>>() {
		});

		assertThat(teams, is(containsTeamsInAnyOrder(expectedTeams)));
	}

	@Test
	@InSequence(4)
	@RunAsClient
	public void testGetPublicByWrongSportTypeTeams(
			@ArquillianResteasyResource(WRONG_SPORT_TYPE_PATH) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasBadRequestStatus());
	}

	@Test
	@InSequence(5)
	@RunAsClient
	public void testGetByLocalityPublicTeams(@ArquillianResteasyResource(LOCALITY_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		TeamDTO expectedTeams = TeamConverter.toTeamDTO(TeamsDataset.otherTeam());
		List<TeamDTO> teams = response.readEntity(new GenericType<List<TeamDTO>>() {
		});

		assertThat(teams, is(containsTeamsInAnyOrder(expectedTeams)));
	}

	@Test
	@InSequence(6)
	@RunAsClient
	public void testGetByLocalityAndSporTypePublicTeams(
			@ArquillianResteasyResource(SPORT_TYPE_LOCALITY_PATH) ResteasyWebTarget webTarget) throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasBadRequestStatus());
	}

	@Test
	@InSequence(7)
	@RunAsClient
	public void testGetListOfPlayers(@ArquillianResteasyResource(PLAYERS_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<PlayerDTO> expectedPlayers = PlayerConverter.toPlayerDTO(PlayersDataset.playerListFromTeam());
		List<PlayerDTO> players = response.readEntity(new GenericType<List<PlayerDTO>>() {
		});
		
		assertThat(players, is(containsPlayersInAnyOrder(expectedPlayers)));
	}
	
	@Test
	@InSequence(8)
	@ShouldMatchDataSet("teamsPublicREST.xml")
	@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
	public void afterGetSportType() {
	}
	

	

}
