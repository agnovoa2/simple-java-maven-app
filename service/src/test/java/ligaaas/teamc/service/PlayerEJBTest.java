package ligaaas.teamc.service;

import static ligaaas.teamc.domain.entities.IsEqualToPlayer.containsPlayersInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToPlayer.equalToPlayer;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import es.uvigo.esei.dgss.teamc.ligaaas.service.util.security.RoleCaller;
import es.uvigo.esei.dgss.teamc.ligaaas.service.util.security.TestPrincipal;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.entities.IsEqualToPlayer;
import ligaaas.teamc.domain.entities.PlayersDataset;

@RunWith(Arquillian.class)
@UsingDataSet("players.xml")
@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
public class PlayerEJBTest {

	@Inject
	private PlayerEJB facade;

	@EJB(beanName = "registered-caller")
	private RoleCaller asUser;

	@Inject
	private TestPrincipal principal;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test2.war")
				.addClasses(PlayerEJB.class, UserEJB.class, TestPrincipal.class, IsEqualToEntity.class,
						PlayersDataset.class)
				.addPackage(RoleCaller.class.getPackage()).addPackage(Player.class.getPackage())
				.addPackage(IsEqualToPlayer.class.getPackage()).addPackage(TestPrincipal.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jboss-web.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties")
				.addAsWebInfResource("beans.xml", "beans.xml");
	}

	@Test
	@ShouldMatchDataSet({ "players.xml", "playersCreate.xml" })
	public void testCreatePlayer() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final Player createdPlayer = PlayersDataset.createdPlayer();
		final Player actualPlayer = asUser.call(() -> facade.create(createdPlayer));
		assertThat(actualPlayer, is(equalToPlayer(createdPlayer)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("players.xml")
	public void testCreatePlayerWithNull() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.create(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("players.xml")
	public void testCreatePlayerThatAlreadyExists() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.create(PlayersDataset.predefinedPlayer()));
	}
	
	@Test
	@ShouldMatchDataSet("players.xml")
	public void testFindPlayer() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final Player predefinedPlayer = PlayersDataset.predefinedPlayer();
		final Player actualPlayer = asUser.call(() -> facade.find(predefinedPlayer.getPlayerId()));
		assertThat(actualPlayer, is(equalToPlayer(predefinedPlayer)));
	}

	@Test
	@ShouldMatchDataSet("players.xml")
	public void testUpdatePlayer() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final Player predefinedPlayer = PlayersDataset.predefinedPlayer();
		final Player modifiedPlayer = PlayersDataset.modifiedPlayer();

		modifiedPlayer.setPlayerNickName(predefinedPlayer.getPlayerNickName());

		final Player actualPlayer = asUser.call(() -> facade.update(modifiedPlayer));
		assertThat(actualPlayer, is(equalToPlayer(predefinedPlayer)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("players.xml")
	public void testUpdatePlayerWithNull() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.update(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("players.xml")
	public void testUpdatePlayerWithNonExistent() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final Player createdPlayer = PlayersDataset.createdUserPlayer();
		asUser.call(() -> facade.update(createdPlayer));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("players.xml")
	public void testUpdatePlayerWithAnotherUser() {
		this.principal.setName(PlayersDataset.anotherUser().getUserLogin());
		final Player predefinedPlayer = PlayersDataset.predefinedPlayer();
		asUser.call(() -> facade.update(predefinedPlayer));
	}

	@Test
	@ShouldMatchDataSet("playersDelete.xml")
	public void testDeletePlayer() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final Player predefinedPlayer = PlayersDataset.predefinedPlayer();
		asUser.run(() -> facade.delete(predefinedPlayer.getPlayerId()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("playersDelete.xml")
	public void testDeletePlayerWithNonExistent() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final Player nonExistentPlayer = PlayersDataset.createdUserPlayer();
		asUser.run(() -> facade.delete(nonExistentPlayer.getPlayerId()));
	}
	
	@Test
	@UsingDataSet("playersFind.xml")
	@ShouldMatchDataSet("playersFind.xml")
	public void testFindPlayersByUser() {
		this.principal.setName(PlayersDataset.existentUserPlayer().getUserLogin());
		final List<Player> predefinedPlayer = PlayersDataset.playersManagedByUser();
		final List<Player> actualPlayer = asUser.call(() -> facade.findByUser());
		assertThat(actualPlayer, containsPlayersInAnyOrder(predefinedPlayer));	
	}
	
	@Test
	@UsingDataSet("playersPublic.xml")
	@ShouldMatchDataSet("playersPublic.xml")
	public void testFindPublicPlayers() {
		final List<Player> predefinedPlayer = PlayersDataset.publicPlayers();
		final List<Player> actualPlayer = asUser.call(() -> facade.findPublicPlayers());
		assertThat(actualPlayer, containsPlayersInAnyOrder(predefinedPlayer));	
	}
	
	@ShouldMatchDataSet("playersFind.xml")
	public void testGetTeamPlayers() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final List<Player> predefinedPlayer = PlayersDataset.playersManagedByUser();
		final List<Player> actualPlayer = asUser.call(() -> facade.findByUser());
		asUser.run(() -> facade.findTeamPlayers(PlayersDataset.predefinedTeam()));
		assertThat(actualPlayer, containsPlayersInAnyOrder(predefinedPlayer));	
	}
	
	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("playersFind.xml")
	public void testGetTeamPlayersNullTeam() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		asUser.run(() -> facade.findTeamPlayers(null));
	}

	@ShouldMatchDataSet("playersFind.xml")
	public void testFindPlayersByNickname() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final Player predefinedPlayer = PlayersDataset.predefinedFindPlayer();
		final List<Player> actualPlayer = asUser.call(() -> facade.findByUser());
		asUser.run(() -> facade.findPlayersByNickname(predefinedPlayer.getPlayerNickName()));
		assertThat(actualPlayer, containsPlayersInAnyOrder(predefinedPlayer));	
	}
	
	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("playersFind.xml")
	public void testFindPlayersByNicknameNullNickamne() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		asUser.run(() -> facade.findPlayersByNickname(null));
	}
	
	@ShouldMatchDataSet("playersFind.xml")
	public void testFindPlayersByLocality() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		final List<Player> predefinedPlayer = PlayersDataset.playersManagedByUser();
		final List<Player> actualPlayer = asUser.call(() -> facade.findByUser());
		asUser.run(() -> facade.findPlayersByLocality(PlayersDataset.getLocality()));
		assertThat(actualPlayer, containsPlayersInAnyOrder(predefinedPlayer));	
	}
	
	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("playersFind.xml")
	public void testFindPlayersByLocalityNullNickamne() {
		this.principal.setName(PlayersDataset.existentUser().getUserLogin());
		asUser.run(() -> facade.findPlayersByLocality(null));
	}
}
