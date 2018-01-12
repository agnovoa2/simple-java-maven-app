package ligaaas.teamc.service;

import static ligaaas.teamc.domain.entities.IsEqualToPlayer.equalToPlayer;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
public class PlayerEJBTestCreate {

	@Inject
	private PlayerEJB facade;

	@EJB(beanName = "registered-caller")
	private RoleCaller asUser;

	@Inject
	private TestPrincipal principal;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
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
	@UsingDataSet("userPlayers.xml")
	@ShouldMatchDataSet({ "players.xml", "userPlayersCreate.xml" })
	public void testCreateUserPlayer() {
		this.principal.setName(PlayersDataset.existentUserPlayer().getUserLogin());
		final Player createdUserPlayer = PlayersDataset.createdUserPlayer();
		final Player actualPlayer = asUser.call(() -> facade.createUserPlayer(createdUserPlayer));
		assertThat(actualPlayer, is(equalToPlayer(createdUserPlayer)));
	}
	
	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("players.xml")
	public void testCreatePlayerWithNull() {
		this.principal.setName(PlayersDataset.existentUserPlayer().getUserLogin());
		asUser.call(() -> facade.createUserPlayer(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("players.xml")
	public void testCreatePlayerThatAlreadyExists() {
		this.principal.setName(PlayersDataset.existentUserPlayer().getUserLogin());
		asUser.call(() -> facade.createUserPlayer(PlayersDataset.predefinedPlayer()));
	}
}
