package ligaaas.teamc.service;

import static ligaaas.teamc.domain.entities.CompetitionsDataset.findByLocalityTypeCompetition;
import static ligaaas.teamc.domain.entities.CompetitionsDataset.findBySportTypeCompetition;
import static ligaaas.teamc.domain.entities.CompetitionsDataset.restCompetitions;
import static ligaaas.teamc.domain.entities.IsEqualToCompetition.containsCompetitionsInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToCompetition.equalToCompetition;
import static org.apache.commons.lang3.StringUtils.repeat;
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
import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.User;
import ligaaas.teamc.domain.entities.CompetitionsDataset;
import ligaaas.teamc.domain.entities.IsEqualToCompetition;

@RunWith(Arquillian.class)
@UsingDataSet("competitions.xml")
@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
public class CompetitionEJBTest {

	@Inject
	private CompetitionEJB facade;

	@EJB(beanName = "registered-caller")
	private RoleCaller asUser;
	
	@Inject
	private TestPrincipal principal;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(CompetitionEJB.class, UserEJB.class, User.class, TestPrincipal.class,
						IsEqualToCompetition.class, IsEqualToEntity.class)
				.addPackage(RoleCaller.class.getPackage()).addPackage(Competition.class.getPackage())
				.addPackage(IsEqualToCompetition.class.getPackage()).addPackage(TestPrincipal.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jboss-web.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties")
				.addAsWebInfResource("beans.xml", "beans.xml");
	}

	@Test
	@ShouldMatchDataSet({ "competitions.xml", "competitionsCreate.xml" })
	public void testCreateCompetition() {
		final Competition createdCompetition = CompetitionsDataset.createdCompetition();
		final Competition actualCompetition = asUser.call(() -> facade.create(createdCompetition));
		assertThat(actualCompetition, is(equalToCompetition(createdCompetition)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "competitions.xml" })
	public void testCreateCompetitionWithNull() {
		asUser.run(() -> facade.create(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "competitions.xml" })
	public void testCreateCompetitionWithAlreadyExists() {
		final Competition predefinedCompetition = CompetitionsDataset.predefinedCompetition();
		asUser.run(() -> facade.create(predefinedCompetition));
	}

	@Test
	@ShouldMatchDataSet("competitions.xml")
	public void testUpdateCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.predefinedCompetition();
		final Competition modifiedCompetition = CompetitionsDataset.modifiedCompetition();

		modifiedCompetition.setCompetitionName(predefinedCompetition.getCompetitionName());
		modifiedCompetition.setCompetitionShortName(predefinedCompetition.getCompetitionShortName());
		modifiedCompetition.setCompetitionDescription(predefinedCompetition.getCompetitionDescription());

		final Competition actualCompetition = asUser.call(() -> facade.update(modifiedCompetition));
		assertThat(actualCompetition, is(equalToCompetition(predefinedCompetition)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testUpdateCompetitionWithNull() {
		asUser.run(() -> facade.update(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testUpdateCompetitionWithNonExistent() {
		final Competition nonExistantCompetition = CompetitionsDataset.createdCompetition();
		asUser.run(() -> facade.update(nonExistantCompetition));
	}

	@Test
	@ShouldMatchDataSet({ "competitionsDelete.xml" })
	public void testDeleteCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.predefinedCompetition();
		asUser.run(() -> facade.delete(predefinedCompetition.getCompetitionId()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testDeleteCompetitionWithNonExistent() {
		final Competition nonExistantCompetition = CompetitionsDataset.createdCompetition();
		asUser.run(() -> facade.delete(nonExistantCompetition.getCompetitionId()));
	}

	@Test
	@ShouldMatchDataSet("competitions.xml")
	public void testFindCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.predefinedCompetition();
		final Competition actualCompetition = asUser.call(() -> facade.find(predefinedCompetition.getCompetitionId()));
		assertThat(actualCompetition, is(equalToCompetition(predefinedCompetition)));
	}
	
	@Test
	@UsingDataSet("competitionsFind.xml")
	@ShouldMatchDataSet("competitionsFind.xml")
	public void testFindCompetitionsByUser() {
		this.principal.setName(CompetitionsDataset.competitionWithUser().getCompetitionUser().getUserLogin());
		final List<Competition> predefinedCompetitions = CompetitionsDataset.restCompetitions();
		final List<Competition> actualCompetition = asUser.call(() -> facade.findByUser());
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetitions));	
	}

	@Test
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByNameCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.predefinedCompetition();
		final List<Competition> actualCompetition = asUser
				.call(() -> facade.findByName(predefinedCompetition.getCompetitionName()));
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByNameCompetitionWithNull() {
		asUser.run(() -> facade.findByName(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByNameCompetitionWithShortName() {
		asUser.run(() -> facade.findByName(repeat(' ', 3)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByNameCompetitionWithLongName() {
		asUser.run(() -> facade.findByName(repeat(' ', 61)));
	}

	@Test
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByShortNameCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.predefinedCompetition();
		final List<Competition> actualCompetition = asUser
				.call(() -> facade.findByShortName(predefinedCompetition.getCompetitionShortName()));
		assertThat(actualCompetition, is(containsCompetitionsInAnyOrder(predefinedCompetition)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByShortNameCompetitionWithNull() {
		asUser.run(() -> facade.findByShortName(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByShortNameCompetitionWithShortShortName() {
		asUser.run(() -> facade.findByShortName(repeat(' ', 1)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByShortNameCompetitionWithShortLongName() {
		asUser.run(() -> facade.findByShortName(repeat(' ', 11)));
	}

	@Test
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByDescriptionCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.predefinedCompetition();
		final List<Competition> actualCompetition = asUser
				.call(() -> facade.findByDescription(predefinedCompetition.getCompetitionDescription()));
		assertThat(actualCompetition, is(containsCompetitionsInAnyOrder(predefinedCompetition)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByDescriptionCompetitionWithNull() {
		asUser.run(() -> facade.findByDescription(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByDescriptionCompetitionWithShortName() {
		asUser.run(() -> facade.findByDescription(repeat(' ', 9)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByDescriptionCompetitionWithLongName() {
		asUser.run(() -> facade.findByDescription(repeat(' ', 241)));
	}

	@Test
	@ShouldMatchDataSet("competitions.xml")
	public void testFindBySportTypeCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.predefinedCompetition();
		final List<Competition> actualCompetition = asUser
				.call(() -> facade.findBySportType(predefinedCompetition.getCompetitionSportType()));
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindBySportTypeCompetitionWithNull() {
		asUser.run(() -> facade.findBySportType(null));
	}

	@Test
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByUserCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.competitionWithUser();
		final List<Competition> actualCompetition = asUser
				.call(() -> facade.findByUser(predefinedCompetition.getCompetitionUser()));
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByUserCompetitionWithNull() {
		asUser.run(() -> facade.findByUser(null));
	}

	@Test
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByTeamCompetition() {
		final Competition predefinedCompetition = CompetitionsDataset.competitionWithTeams();
		final List<Competition> actualCompetition = asUser
				.call(() -> facade.findByTeam(predefinedCompetition.getCompetitionTeams().get(0)));
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("competitions.xml")
	public void testFindByTeamCompetitionWithNull() {
		asUser.run(() -> facade.findByTeam(null));
	}

	@Test
	@UsingDataSet("competitionsREST.xml")
	public void testFindPublicCompetition() {
		final List<Competition> predefinedCompetitions = restCompetitions();
		final List<Competition> actualCompetition = facade.findPublicCompetition();
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetitions));
	}

	@Test
	@UsingDataSet("competitionsREST.xml")
	public void testFindPublicCompetitionBySportType() {
		final Competition predefinedCompetition = findBySportTypeCompetition();
		final List<Competition> actualCompetition = facade
				.findPublicCompetitionBySportType(predefinedCompetition.getCompetitionSportType());
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	public void testFindPublicCompetitionBySportTypeNull() {
		final Competition predefinedCompetition = findBySportTypeCompetition();
		final List<Competition> actualCompetition = facade.findPublicCompetitionBySportType(null);
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

	@Test
	@UsingDataSet("competitionsREST.xml")
	public void testFindPublicCompetitionByLocality() {
		final Competition predefinedCompetition = findByLocalityTypeCompetition();
		final List<Competition> actualCompetition = facade.findPublicCompetitionByLocality(
				predefinedCompetition.getCompetitionHeadQuarter().getHeadquarterLocality());
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	public void testFindPublicCompetitionByLocalityTypeNull() {
		final Competition predefinedCompetition = findBySportTypeCompetition();
		final List<Competition> actualCompetition = facade.findPublicCompetitionByLocality(null);
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

	@Test
	@UsingDataSet("competitionsREST.xml")
	public void testFindPublicCompetitionById() {
		final Competition predefinedCompetition = CompetitionsDataset.restCompetitions().get(0);
		final List<Competition> actualCompetition = facade
				.findPublicCompetitionById(predefinedCompetition.getCompetitionId());
		assertThat(actualCompetition, containsCompetitionsInAnyOrder(predefinedCompetition));
	}

}
