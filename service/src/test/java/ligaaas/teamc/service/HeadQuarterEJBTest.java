package ligaaas.teamc.service;

import static ligaaas.teamc.domain.entities.IsEqualToHeadQuarter.containsHeadQuartersInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToHeadQuarter.equalToHeadQuarter;
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
import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.entities.HeadQuartersDataset;
import ligaaas.teamc.domain.entities.IsEqualToHeadQuarter;

@RunWith(Arquillian.class)
@UsingDataSet("headquarters.xml")
@CleanupUsingScript({ "cleanup.sql", "cleanup-autoincrement.sql" })
public class HeadQuarterEJBTest {

	@Inject
	private HeadQuarterEJB facade;

	@EJB(beanName = "registered-caller")
	private RoleCaller asUser;

	@Inject
	private TestPrincipal principal;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(HeadQuarterEJB.class, UserEJB.class, TestPrincipal.class, IsEqualToHeadQuarter.class,
						IsEqualToEntity.class)
				.addPackage(RoleCaller.class.getPackage()).addPackage(HeadQuarter.class.getPackage())
				.addPackage(IsEqualToHeadQuarter.class.getPackage()).addPackage(TestPrincipal.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jboss-web.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties")
				.addAsWebInfResource("beans.xml", "beans.xml");
	}

	@Test
	@ShouldMatchDataSet({ "headquarters.xml", "headquartersCreate.xml" })
	public void testCreateHeadQuarter() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		final HeadQuarter createdHeadQuarter = HeadQuartersDataset.createdHeadQuarter();
		final HeadQuarter actualHeadQuarter = asUser.call(() -> facade.create(createdHeadQuarter));
		assertThat(actualHeadQuarter, is(equalToHeadQuarter(createdHeadQuarter)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "headquarters.xml" })
	public void testCreateHeadQuarterWithNull() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		asUser.call(() -> facade.create(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "headquarters.xml" })
	public void testCreateHeadQuarterThatAlreadyExists() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		final HeadQuarter predefinedHeadQuarter = HeadQuartersDataset.predefinedHeadQuarter();
		asUser.call(() -> facade.create(predefinedHeadQuarter));
	}

	@Test
	@ShouldMatchDataSet({ "headquarters.xml" })
	public void testUpdateHeadQuarter() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		final HeadQuarter predefinedHeadQuarter = HeadQuartersDataset.predefinedHeadQuarter();
		final HeadQuarter modifiedHeadQuarter = HeadQuartersDataset.modifiedHeadQuarter();

		modifiedHeadQuarter.setHeadquarterProvince(predefinedHeadQuarter.getHeadquarterProvince());
		modifiedHeadQuarter.setHeadQuarterManagedByUser(HeadQuartersDataset.userWithHeadQuarter());

		final HeadQuarter actualHeadQuarter = asUser.call(() -> facade.update(modifiedHeadQuarter));
		assertThat(actualHeadQuarter, is(equalToHeadQuarter(predefinedHeadQuarter)));

	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("headquarters.xml")
	public void testUpdateHeadQuarterWithNull() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		asUser.call(() -> facade.update(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("headquarters.xml")
	public void testUpdateHeadQuarterWithNonExistent() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		final HeadQuarter nonExistantHeadQuarter = HeadQuartersDataset.createdHeadQuarter();
		asUser.call(() -> facade.update(nonExistantHeadQuarter));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("headquarters.xml")
	public void testUpdateHeadQuarterWithAnotherUser() {
		this.principal.setName(HeadQuartersDataset.anotherUser().getUserLogin());
		final HeadQuarter predefinedHeadQuarter = HeadQuartersDataset.predefinedHeadQuarter();
		asUser.call(() -> facade.update(predefinedHeadQuarter));
	}

	@Test
	@ShouldMatchDataSet({ "headquartersDelete.xml" })
	public void testDeleteHeadQuarter() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		final HeadQuarter predefinedHeadquarter = HeadQuartersDataset.predefinedHeadQuarter();
		predefinedHeadquarter.setHeadQuarterManagedByUser(HeadQuartersDataset.userWithHeadQuarter());

		asUser.run(() -> facade.delete(predefinedHeadquarter.getHeadQuarterId()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("headquarters.xml")
	public void testDeleteHeadQuarterWithNonExistent() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		final HeadQuarter nonExistantHeadQuarter = HeadQuartersDataset.createdHeadQuarter();
		asUser.run(() -> facade.delete(nonExistantHeadQuarter.getHeadQuarterId()));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("headquarters.xml")
	public void testDeleteHeadQuarterWithAnotherUser() {
		this.principal.setName(HeadQuartersDataset.anotherUser().getUserLogin());
		final HeadQuarter predefinedHeadQuarter = HeadQuartersDataset.predefinedHeadQuarter();
		asUser.run(() -> facade.delete(predefinedHeadQuarter.getHeadQuarterId()));
	}

	@Test
	@ShouldMatchDataSet("headquarters.xml")
	public void testFindByUserHeadQuarter() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		final HeadQuarter predefinedHeadQuarter = HeadQuartersDataset.predefinedHeadQuarter();
		final List<HeadQuarter> actualHeadQuarter = asUser
				.call(() -> facade.findByUser(HeadQuartersDataset.userWithHeadQuarter()));
		assertThat(actualHeadQuarter, is(containsHeadQuartersInAnyOrder(predefinedHeadQuarter)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("headquarters.xml")
	public void testFindByUserHeadQuarterWithNull() {
		this.principal.setName(HeadQuartersDataset.userWithHeadQuarter().getUserLogin());
		asUser.call(() -> facade.findByUser(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("headquarters.xml")
	public void testFindByUserHeadQuarterWithAnotherUser() {
		this.principal.setName(HeadQuartersDataset.anotherUser().getUserLogin());
		asUser.call(() -> facade.findByUser(HeadQuartersDataset.userWithHeadQuarter()));
	}

}
