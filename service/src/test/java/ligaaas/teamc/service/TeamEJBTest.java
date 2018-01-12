package ligaaas.teamc.service;

import static ligaaas.teamc.domain.entities.IsEqualToPlayer.containsPlayersInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToTeam.containsTeamsInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToTeam.equalToTeam;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
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
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.entities.IsEqualToTeam;
import ligaaas.teamc.domain.entities.TeamsDataset;

@RunWith(Arquillian.class)
@UsingDataSet("teams.xml")
@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
public class TeamEJBTest {

	@Inject
	private TeamEJB facade;

	@EJB(beanName = "registered-caller")
	private RoleCaller asUser;

	@Inject
	private TestPrincipal principal;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(TeamEJB.class, UserEJB.class, PlayerEJB.class, TeamsDataset.class, TestPrincipal.class,
						IsEqualToTeam.class, IsEqualToEntity.class)
				.addPackage(RoleCaller.class.getPackage()).addPackage(Team.class.getPackage())
				.addPackage(IsEqualToTeam.class.getPackage()).addPackage(TestPrincipal.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jboss-web.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties")
				.addAsWebInfResource("beans.xml", "beans.xml");
	}

	@Test
	@ShouldMatchDataSet({ "teams.xml", "teamsCreate.xml" })
	public void testCreateTeam() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team createdTeam = TeamsDataset.createdTeam();
		final Team actualTeam = asUser.call(() -> facade.create(createdTeam));
		assertThat(actualTeam, is(equalToTeam(createdTeam)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "teams.xml" })
	public void testCreateTeamWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.create(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "teams.xml" })
	public void testCreateTeamThatAlreadyExists() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		asUser.call(() -> facade.create(predefinedTeam));
	}

	@Test
	@ShouldMatchDataSet("teams.xml")
	public void testUpdateTeam() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		final Team modifiedTeam = TeamsDataset.modifiedTeam();

		modifiedTeam.setTeamName(predefinedTeam.getTeamName());
		modifiedTeam.setTeamShortName(predefinedTeam.getTeamShortName());
		modifiedTeam.setTeamSportType(predefinedTeam.getTeamSportType());

		final Team actualTeam = asUser.call(() -> facade.update(modifiedTeam));
		assertThat(actualTeam, is(equalToTeam(predefinedTeam)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testUpdateTeamWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.update(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testUpdateTeamWithNonExistent() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team nonExistantTeam = TeamsDataset.createdTeam();
		asUser.call(() -> facade.update(nonExistantTeam));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testUpdateTeamWithAnotherUser() {
		this.principal.setName(TeamsDataset.anotherUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		asUser.run(() -> facade.update(predefinedTeam));
	}

	@Test
	@ShouldMatchDataSet({ "teamsDelete.xml" })
	public void testDeleteTeam() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		asUser.run(() -> facade.delete(predefinedTeam.getTeamId()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testDeleteTeamWithNonExistent() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team nonExistantTeam = TeamsDataset.createdTeam();
		asUser.run(() -> facade.delete(nonExistantTeam.getTeamId()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testDeleteTeamWithAnotherUser() {
		this.principal.setName(TeamsDataset.anotherUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		asUser.run(() -> facade.delete(predefinedTeam.getTeamId()));
	}

	@Test
	@ShouldMatchDataSet("teams.xml")
	public void testFindTeam() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		final Team actualTeam = asUser.call(() -> facade.find(predefinedTeam.getTeamId()));
		assertThat(actualTeam, is(equalToTeam(predefinedTeam)));
	}
	
	@Test
	@UsingDataSet("teamsFind.xml")
	@ShouldMatchDataSet("teamsFind.xml")
	public void testFindTeamsByUser() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final List<Team> predefinedTeam = TeamsDataset.teamsManagedByUser();
		final List<Team> actualTeam = asUser.call(() -> facade.findByUser());
		assertThat(actualTeam, containsTeamsInAnyOrder(predefinedTeam));	
	}

	@Test
	@ShouldMatchDataSet("teams.xml")
	public void testFindByNameTeam() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		final List<Team> actualTeam = asUser.call(() -> facade.findByName(predefinedTeam.getTeamName()));
		assertThat(actualTeam, containsTeamsInAnyOrder(predefinedTeam));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindByNameTeamWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByName(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindByNameTeamWithShortName() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByName(repeat(' ', 3)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindByNameTeamWithLongName() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByName(repeat(' ', 61)));
	}

	@Test
	@ShouldMatchDataSet("teams.xml")
	public void testFindByDescriptionTeam() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		final List<Team> actualTeam = asUser.call(() -> facade.findByDescription(predefinedTeam.getTeamDescription()));
		assertThat(actualTeam, is(containsTeamsInAnyOrder(predefinedTeam)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindByDescriptionTeamWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDescription(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindByDescriptionTeamWithShortName() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDescription(repeat(' ', 9)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindByDescriptionTeamWithLongName() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDescription(repeat(' ', 241)));
	}

	@Test
	@ShouldMatchDataSet("teams.xml")
	public void testFindByUserTeam() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		final List<Team> actualTeam = asUser.call(() -> facade.findByUser(TeamsDataset.existentUser()));
		assertThat(actualTeam, is(containsTeamsInAnyOrder(predefinedTeam)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindByUserTeamWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByUser(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindByUserTeamWithAnotherUser() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByUser(TeamsDataset.anotherUser()));
	}

	@Test
	@ShouldMatchDataSet("teams.xml")
	public void testFindBySportTypeTeam() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Team predefinedTeam = TeamsDataset.predefinedTeam();
		final List<Team> actualTeam = asUser.call(() -> facade.findBySportType(predefinedTeam.getTeamSportType()));
		assertThat(actualTeam, containsTeamsInAnyOrder(predefinedTeam));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testFindBySportTypeTeamWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findBySportType(null));
	}

	@Test
	@ShouldMatchDataSet("teams.xml")
	public void testAddTeamPlayers() {
		this.principal.setName(TeamsDataset.teamWithoutPlayers().getTeamUser().getUserLogin());
		final Team teamWithoutPlayers = TeamsDataset.teamWithoutPlayers();
		final List<Player> playersWithoutTeam = TeamsDataset.playersWithoutTeam();
		final Team actualTeam = asUser.call(() -> facade.addPlayers(playersWithoutTeam, teamWithoutPlayers));
		assertThat(playersWithoutTeam, containsPlayersInAnyOrder(actualTeam.getTeamPlayers()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testAddTeamPlayersTeamNull() {
		this.principal.setName(TeamsDataset.teamWithoutPlayers().getTeamUser().getUserLogin());
		final List<Player> playersWithoutTeam = TeamsDataset.playersWithoutTeam();
		asUser.call(() -> facade.addPlayers(playersWithoutTeam, null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testAddTeamPlayersNull() {
		this.principal.setName(TeamsDataset.teamWithoutPlayers().getTeamUser().getUserLogin());
		final Team teamWithoutPlayers = TeamsDataset.teamWithoutPlayers();
		asUser.call(() -> facade.addPlayers(null, teamWithoutPlayers));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testAddTeamPlayersAnotherUser() {
		this.principal.setName(TeamsDataset.anotherUser().getUserLogin());
		final Team teamWithoutPlayers = TeamsDataset.teamWithoutPlayers();
		asUser.call(() -> facade.addPlayers(TeamsDataset.playersWithoutTeam(), teamWithoutPlayers));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testAddTeamPlayersAlreadyExisting() {
		this.principal.setName(TeamsDataset.teamWithoutPlayers().getTeamUser().getUserLogin());
		final Team teamWithoutPlayers = TeamsDataset.teamWithoutPlayers();
		final List<Player> playersWithoutTeam = TeamsDataset.playersWithoutTeam();
		asUser.call(() -> facade.addPlayers(playersWithoutTeam, teamWithoutPlayers));
		asUser.call(() -> facade.addPlayers(playersWithoutTeam, teamWithoutPlayers));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testRemoveTeamPlayersTeamNull() {
		this.principal.setName(TeamsDataset.teamWithoutPlayers().getTeamUser().getUserLogin());
		final List<Player> playersWithoutTeam = TeamsDataset.playersWithoutTeam();
		asUser.call(() -> facade.removePlayers(playersWithoutTeam, null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testAddTeamPlayersTeamDeleted() {
		this.principal.setName(TeamsDataset.deletedTeam().getTeamUser().getUserLogin());
		final Team teamWithoutPlayers = TeamsDataset.deletedTeam();
		final List<Player> playersWithoutTeam = TeamsDataset.playersWithoutTeam();
		asUser.call(() -> facade.addPlayers(playersWithoutTeam, teamWithoutPlayers));
	}

	@Test
	@UsingDataSet("teamsWithPlayers.xml")
	@ShouldMatchDataSet("teamsWithPlayersDelete.xml")
	public void testRemoveTeamPlayers() {
		this.principal.setName(TeamsDataset.teamWithPlayers().getTeamUser().getUserLogin());
		final Team teamWithPlayers = TeamsDataset.teamWithPlayers();
		final List<Player> playersWithTeam = TeamsDataset.playersWithTeam();
		final Team actualTeam = asUser.call(() -> facade.removePlayers(playersWithTeam, teamWithPlayers));
		assertThat(actualTeam.getTeamPlayers(), containsPlayersInAnyOrder(new ArrayList<Player>()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testRemoveTeamPlayersNull() {
		this.principal.setName(TeamsDataset.teamWithoutPlayers().getTeamUser().getUserLogin());
		final Team teamWithoutPlayers = TeamsDataset.teamWithoutPlayers();
		asUser.call(() -> facade.removePlayers(null, teamWithoutPlayers));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testRemoveTeamPlayersNoExisting() {
		this.principal.setName(TeamsDataset.teamWithoutPlayers().getTeamUser().getUserLogin());
		final Team teamWithoutPlayers = TeamsDataset.teamWithoutPlayers();
		asUser.call(() -> facade.removePlayers(TeamsDataset.playersWithoutTeam(), teamWithoutPlayers));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("teams.xml")
	public void testRemoveTeamPlayersAnotherUser() {
		this.principal.setName(TeamsDataset.teamWithoutPlayers().getTeamUser().getUserLogin());
		final Team teamWithoutPlayers = TeamsDataset.teamWithoutPlayers();
		final List<Player> playersWithoutTeam = TeamsDataset.playersWithoutTeam();
		final Team actualTeam = asUser.call(() -> facade.addPlayers(playersWithoutTeam, teamWithoutPlayers));

		this.principal.setName(TeamsDataset.anotherUser().getUserLogin());
		asUser.call(() -> facade.removePlayers(playersWithoutTeam, actualTeam));
	}

	@Test
	@UsingDataSet("teamsPublicREST.xml")
	public void testFindPublicTeam() {
		final List<Team> predefinedTeams = TeamsDataset.listTeams();
		final List<Team> actualTeams = facade.findPublicTeam();
		assertThat(actualTeams, containsTeamsInAnyOrder(predefinedTeams));
	}

	@Test
	@UsingDataSet("teamsPublicREST.xml")
	public void testFindPublicTeamBySportType() {
		final Team predefinedTeam = TeamsDataset.otherTeam();
		final List<Team> actualTeam = facade.findPublicTeamBySportType(predefinedTeam.getTeamSportType());
		assertThat(actualTeam, containsTeamsInAnyOrder(predefinedTeam));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	public void testFindPublicTeamBySportTypeNull() {
		final Team predefinedTeam = TeamsDataset.otherTeam();
		final List<Team> actualTeam = facade.findPublicTeamBySportType(null);
		assertThat(actualTeam, containsTeamsInAnyOrder(predefinedTeam));
	}

	@Test
	@UsingDataSet("teamsPublicREST.xml")
	public void testFindPublicTeamByLocality() {
		final Team predefinedTeam = TeamsDataset.otherTeam();
		final List<Team> actualTeam = facade
				.findPublicTeamByLocality(predefinedTeam.getTeamHeadQuarter().getHeadquarterLocality());
		assertThat(actualTeam, containsTeamsInAnyOrder(predefinedTeam));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	public void testFindPublicTeamByLocalityTypeNull() {
		final Team predefinedTeam = TeamsDataset.otherTeam();
		final List<Team> actualTeam = facade.findPublicTeamByLocality(null);
		assertThat(actualTeam, containsTeamsInAnyOrder(predefinedTeam));
	}

	@Test
	@UsingDataSet("teamsPublicREST.xml")
	public void testFindPublicTeamByCompetition() {
		final Team predefinedTeam = TeamsDataset.otherTeam();
		final List<Team> actualTeam = facade.findPublicTeamByCompetition(predefinedTeam.getTeamCompetitions().get(0));
		assertThat(actualTeam, containsTeamsInAnyOrder(predefinedTeam));
	}

}
