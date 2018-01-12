package ligaaas.teamc.service;

import static ligaaas.teamc.domain.entities.IsEqualToMatch.containsMatchesInAnyOrder;
import static ligaaas.teamc.domain.entities.IsEqualToMatch.equalToMatch;
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
import ligaaas.teamc.domain.Match;
import ligaaas.teamc.domain.entities.IsEqualToMatch;
import ligaaas.teamc.domain.entities.MatchesDataset;

@RunWith(Arquillian.class)
@UsingDataSet("matches.xml")
@CleanupUsingScript({ "cleanup-match.sql", "cleanup-autoincrement.sql" })
public class MatchEJBTest {

	@Inject
	private MatchEJB facade;

	@EJB(beanName = "registered-caller")
	private RoleCaller asUser;

	@Inject
	private TestPrincipal principal;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(MatchEJB.class, UserEJB.class, PlayerEJB.class, MatchesDataset.class, TestPrincipal.class,
						IsEqualToMatch.class, IsEqualToEntity.class)
				.addPackage(RoleCaller.class.getPackage()).addPackage(Match.class.getPackage())
				.addPackage(IsEqualToMatch.class.getPackage()).addPackage(TestPrincipal.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jboss-web.xml")
				.addAsResource("arquillian.extension.persistence.properties")
				.addAsResource("arquillian.extension.persistence.dbunit.properties")
				.addAsWebInfResource("beans.xml", "beans.xml");
	}

	@Test
	@ShouldMatchDataSet({ "matches.xml", "matchesCreate.xml" })
	public void testCreateMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match createdMatch = MatchesDataset.createdMatch();
		final Match actualMatch = asUser.call(() -> facade.create(createdMatch));
		assertThat(actualMatch, is(equalToMatch(createdMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet({ "matches.xml", "matchesCreate.xml" })
	public void testCreateMatchWithNonExistentUser() {
		final Match createdMatch = MatchesDataset.createdMatch();
		final Match actualMatch = facade.create(createdMatch);
		assertThat(actualMatch, is(equalToMatch(createdMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "matches.xml" })
	public void testCreateMatchWithNull() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.create(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet({ "matches.xml" })
	public void testCreateMatchWithAlreadyExists() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		asUser.call(() -> facade.create(predefinedMatch));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testUpdateMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final Match modifiedMatch = MatchesDataset.modifiedMatch();

		modifiedMatch.setMatchDescription(predefinedMatch.getMatchDescription());

		final Match actualMatch = asUser.call(() -> facade.update(modifiedMatch));
		assertThat(actualMatch, is(equalToMatch(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testUpdateMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final Match modifiedMatch = MatchesDataset.modifiedMatch();

		modifiedMatch.setMatchDescription(predefinedMatch.getMatchDescription());

		final Match actualMatch = facade.update(modifiedMatch);
		assertThat(actualMatch, is(equalToMatch(predefinedMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testUpdateMatchWithNull() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.update(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testUpdateMatchWithNonExistent() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match nonExistantMatch = MatchesDataset.createdMatch();
		asUser.call(() -> facade.update(nonExistantMatch));
	}

	@Test
	@ShouldMatchDataSet({ "matchesDelete.xml" })
	public void testDeleteMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		asUser.run(() -> facade.delete(predefinedMatch.getMatchId()));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet({ "matchesDelete.xml" })
	public void testDeleteMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		facade.delete(predefinedMatch.getMatchId());
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testDeleteMatchWithNonExistent() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match nonExistantMatch = MatchesDataset.createdMatch();
		asUser.run(() -> facade.delete(nonExistantMatch.getMatchId()));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final Match actualMatch = asUser.call(() -> facade.find(predefinedMatch.getMatchId()));
		assertThat(actualMatch, is(equalToMatch(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final Match actualMatch = facade.find(predefinedMatch.getMatchId());
		assertThat(actualMatch, is(equalToMatch(predefinedMatch)));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByDateMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser.call(() -> facade.findByDate(predefinedMatch.getMatchDate()));
		assertThat(actualMatch, containsMatchesInAnyOrder(predefinedMatch));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByDateMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByDate(predefinedMatch.getMatchDate());
		assertThat(actualMatch, containsMatchesInAnyOrder(predefinedMatch));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByDateMatchWithNull() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDate(null));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByStateMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser.call(() -> facade.findByState(predefinedMatch.getMatchState()));
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByStateMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByState(predefinedMatch.getMatchState());
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByStateMatchWithNull() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByState(null));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByDescriptionMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser
				.call(() -> facade.findByDescription(predefinedMatch.getMatchDescription()));
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByDescriptionMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByDescription(predefinedMatch.getMatchDescription());
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByDescriptionMatchWithNull() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDescription(null));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByDescriptionMatchWithLongName() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByDescription(repeat(' ', 241)));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByLocalPointsMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser
				.call(() -> facade.findByLocalPoints(predefinedMatch.getMatchLocalPoints()));
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByLocalPointsMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByLocalPoints(predefinedMatch.getMatchLocalPoints());
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByLocalPointsMatchLowerThanZero() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByLocalPoints(-1));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByVisitingPointsMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser
				.call(() -> facade.findByVisitingPoints(predefinedMatch.getMatchVisitingPoints()));
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByVisitingPointsMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByVisitingPoints(predefinedMatch.getMatchVisitingPoints());
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByVisitingPointsMatchLowerThanZero() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByVisitingPoints(-1));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByHeadQuarterMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser.call(() -> facade.findByHeadQuarter(predefinedMatch.getMatchPlace()));
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByHeadQuarterMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByHeadQuarter(predefinedMatch.getMatchPlace());
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByRoundMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser.call(() -> facade.findByRound(predefinedMatch.getMatchRound()));
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByRoundMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByRound(predefinedMatch.getMatchRound());
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByRoundMatchWithNull() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByRound(null));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByHomeTeamMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser.call(() -> facade.findByHomeTeam(predefinedMatch.getMatchHomeTeam()));
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByHomeTeamMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByHomeTeam(predefinedMatch.getMatchHomeTeam());
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByHomeTeamMatchWithNull() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByHomeTeam(null));
	}

	@Test
	@ShouldMatchDataSet("matches.xml")
	public void testFindByVisitingTeamMatch() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = asUser
				.call(() -> facade.findByVisitingTeam(predefinedMatch.getMatchVisitingTeam()));
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBAccessException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByVisitingTeamMatchWithNonExistentUser() {
		final Match predefinedMatch = MatchesDataset.predefinedMatch();
		final List<Match> actualMatch = facade.findByVisitingTeam(predefinedMatch.getMatchVisitingTeam());
		assertThat(actualMatch, is(containsMatchesInAnyOrder(predefinedMatch)));
	}

	@Test(expected = EJBTransactionRolledbackException.class)
	@ShouldMatchDataSet("matches.xml")
	public void testFindByVisitingTeamMatchWithNull() {
		this.principal.setName(MatchesDataset.existentUser().getUserLogin());
		asUser.call(() -> facade.findByVisitingTeam(null));
	}
}
