package ligaaas.teamc.service;

import static ligaaas.teamc.domain.entities.IsEqualToRound.containsRoundsInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToRound.equalToRound;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
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
import ligaaas.teamc.domain.Round;
import ligaaas.teamc.domain.entities.IsEqualToRound;
import ligaaas.teamc.domain.entities.RoundsDataset;
import ligaaas.teamc.domain.entities.TeamsDataset;

@RunWith(Arquillian.class)
@UsingDataSet("rounds.xml")
@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
public class RoundEJBTest {

	@Inject
	private RoundEJB facade;

	@EJB(beanName = "registered-caller")
	private RoleCaller asUser;

	@Inject
	private TestPrincipal principal;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(RoundEJB.class, UserEJB.class, PlayerEJB.class, RoundsDataset.class, TestPrincipal.class,
						IsEqualToRound.class, IsEqualToEntity.class)
				.addPackage(RoleCaller.class.getPackage()).addPackage(Round.class.getPackage())
				.addPackage(IsEqualToRound.class.getPackage()).addPackage(TestPrincipal.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jboss-web.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties")
				.addAsWebInfResource("beans.xml", "beans.xml");
	}

	@Test
	@ShouldMatchDataSet({ "rounds.xml", "roundsCreate.xml" })
	public void testCreateRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round createdRound = RoundsDataset.createdRound();
		final Round actualRound = asUser.call(() -> facade.create(createdRound));
		assertThat(actualRound, is(equalToRound(createdRound)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testCreateRoundWithNonRegisteredUser() {
		final Round createdRound = RoundsDataset.createdRound();
		final Round actualRound = facade.create(createdRound);
		assertThat(actualRound, is(equalToRound(createdRound)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "rounds.xml" })
	public void testCreateRoundWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.create(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "rounds.xml" })
	public void testCreateRoundWithAlreadyExists() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		asUser.call(() -> facade.create(predefinedRound));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testUpdateRoundWithNonRegisteredUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final Round modifiedRound = RoundsDataset.modifiedRound();

		modifiedRound.setRoundNumber(predefinedRound.getRoundNumber());
		modifiedRound.setRoundDescription(predefinedRound.getRoundDescription());

		final Round actualRound = facade.update(modifiedRound);
		assertThat(actualRound, is(equalToRound(predefinedRound)));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testUpdateRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final Round modifiedRound = RoundsDataset.modifiedRound();

		modifiedRound.setRoundNumber(predefinedRound.getRoundNumber());
		modifiedRound.setRoundDescription(predefinedRound.getRoundDescription());

		final Round actualRound = asUser.call(() -> facade.update(modifiedRound));
		assertThat(actualRound, is(equalToRound(predefinedRound)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testUpdateRoundWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.update(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testUpdateRoundWithNonExistent() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round nonExistantRound = RoundsDataset.createdRound();
		asUser.call(() -> facade.update(nonExistantRound));
	}

	@Test
	@ShouldMatchDataSet({ "roundsDelete.xml" })
	public void testDeleteRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		asUser.run(() -> facade.delete(predefinedRound.getRoundId()));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet({ "roundsDelete.xml" })
	public void testDeleteRoundWithNonRegisteredUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		facade.delete(predefinedRound.getRoundId());
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testDeleteRoundWithNonExistent() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round nonExistantRound = RoundsDataset.createdRound();
		asUser.run(() -> facade.delete(nonExistantRound.getRoundId()));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testFindRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final Round actualRound = asUser.call(() -> facade.find(predefinedRound.getRoundId()));
		assertThat(actualRound, is(equalToRound(predefinedRound)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindRoundWithNonRegisteredUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final Round actualRound = facade.find(predefinedRound.getRoundId());
		assertThat(actualRound, is(equalToRound(predefinedRound)));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByNumberRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = asUser.call(() -> facade.findByNumber(predefinedRound.getRoundNumber()));
		assertThat(actualRound, containsRoundsInAnyOrder(predefinedRound));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByNumberRoundWithNonRegisteredUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = facade.findByNumber(predefinedRound.getRoundNumber());
		assertThat(actualRound, containsRoundsInAnyOrder(predefinedRound));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByNumberRoundWithNumberLowerThanOne() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByNumber(0));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByDateRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = asUser.call(() -> facade.findByDate(predefinedRound.getRoundDate()));
		assertThat(actualRound, is(containsRoundsInAnyOrder(predefinedRound)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByDateRoundWithNonRegisteredUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = facade.findByDate(predefinedRound.getRoundDate());
		assertThat(actualRound, is(containsRoundsInAnyOrder(predefinedRound)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByDateRoundWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDate(null));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByStateRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = asUser.call(() -> facade.findByState(predefinedRound.getRoundState()));
		assertThat(actualRound, is(containsRoundsInAnyOrder(predefinedRound)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByStateRoundWithNonExistentUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = facade.findByState(predefinedRound.getRoundState());
		assertThat(actualRound, is(containsRoundsInAnyOrder(predefinedRound)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByStateRoundWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByState(null));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByDescriptionRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = asUser
				.call(() -> facade.findByDescription(predefinedRound.getRoundDescription()));
		assertThat(actualRound, is(containsRoundsInAnyOrder(predefinedRound)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByDescriptionRoundWithNonExistentUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = facade.findByDescription(predefinedRound.getRoundDescription());
		assertThat(actualRound, is(containsRoundsInAnyOrder(predefinedRound)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByDescriptionRoundWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDescription(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByDescriptionRoundWithLongName() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDescription(repeat(' ', 241)));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByHeadQuarterRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = asUser
				.call(() -> facade.findByHeadQuarter(predefinedRound.getRoundHeadQuarter()));
		assertThat(actualRound, containsRoundsInAnyOrder(predefinedRound));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByHeadQuarterRoundWithNonExistentUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = facade.findByHeadQuarter(predefinedRound.getRoundHeadQuarter());
		assertThat(actualRound, containsRoundsInAnyOrder(predefinedRound));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByHeadQuarterRoundWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByHeadQuarter(null));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByCompetitionRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = asUser
				.call(() -> facade.findByCompetition(predefinedRound.getRoundCompetition()));
		assertThat(actualRound, containsRoundsInAnyOrder(predefinedRound));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByCompetitionRoundWithNonExistentUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = facade.findByCompetition(predefinedRound.getRoundCompetition());
		assertThat(actualRound, containsRoundsInAnyOrder(predefinedRound));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testFindByCompetitionRoundWithNull() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByCompetition(null));
	}

	@Test
	@ShouldMatchDataSet("rounds.xml")
	public void testAddMatchToRound() {
		this.principal.setName(TeamsDataset.existentUser().getUserLogin());
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = asUser
				.call(() -> facade.findByCompetition(predefinedRound.getRoundCompetition()));
		assertThat(actualRound, containsRoundsInAnyOrder(predefinedRound));
	}
	
	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("rounds.xml")
	public void testAddMatchToRoundWithNonExistentUser() {
		final Round predefinedRound = RoundsDataset.predefinedRound();
		final List<Round> actualRound = facade.findByCompetition(predefinedRound.getRoundCompetition());
		assertThat(actualRound, containsRoundsInAnyOrder(predefinedRound));
	}
}
