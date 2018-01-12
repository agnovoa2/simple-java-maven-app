package ligaaas.teamc.rest;

import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasOkStatus;
import static es.uvigo.esei.dgss.teamc.ligaaas.http.util.HasHttpStatus.hasUnauthorizedRequestStatus;
import static ligaaas.teamc.domain.entities.IsEqualToPlayerDTO.containsPlayersInAnyOrder;
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

import ligaaas.teamc.DTO.PlayerDTO;
import ligaaas.teamc.converter.PlayerConverter;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.entities.PlayersDataset;
import ligaaas.teamc.restricted.PlayerResource;
import ligaaas.teamc.service.PlayerEJB;

@RunWith(Arquillian.class)
public class PlayerResourceRestTest {
	private final static String BASE_PATH = "api/private/player";
	private final static String BASIC_AUTHORIZATION = "Basic bW1yb2RyaWd1ZXo6YXNkMTIzLiw=";
	private final static String BASIC_AUTHORIZATION_WITHOUT_PLAYERS = "Basic cHByb2RyaWd1ZXo6YXNkMTIzLiw=";
	private final static String BASIC_UNAUTHORIZATION = "Basic bW1yb2R=";

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(CORSFilter.class, IllegalArgumentExceptionMapper.class, SecurityExceptionMapper.class)
				.addPackage(PlayerEJB.class.getPackage()).addPackage(Player.class.getPackage())
				.addPackage(PlayerDTO.class.getPackage()).addPackage(PlayerResource.class.getPackage())
				.addPackage(PlayerConverter.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties").addAsWebInfResource("web.xml")
				.addAsWebInfResource("jboss-web.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@InSequence(1)
	@UsingDataSet("playersREST.xml")
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

		List<PlayerDTO> expectedPlayers = PlayerConverter.toPlayerDTO(PlayersDataset.playersManagedByUser());
		List<PlayerDTO> players = response.readEntity(new GenericType<List<PlayerDTO>>() {
		});

		assertThat(players, is(containsPlayersInAnyOrder(expectedPlayers)));

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
	@Header(name = "Authorization", value = BASIC_AUTHORIZATION_WITHOUT_PLAYERS)
	public void testGetWithoutPlayers(@ArquillianResteasyResource(BASE_PATH) ResteasyWebTarget webTarget)
			throws Exception {
		final Response response = webTarget.request().get();

		assertThat(response, hasOkStatus());

		List<PlayerDTO> expectedPlayers = new ArrayList<PlayerDTO>();
		List<PlayerDTO> players = response.readEntity(new GenericType<List<PlayerDTO>>() {
		});

		assertThat(players, is(containsPlayersInAnyOrder(expectedPlayers)));

	}

	@Test
	@InSequence(5)
	@ShouldMatchDataSet("playersREST.xml")
	@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
	public void afterGet() {
	}
}
