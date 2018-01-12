package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.domain.Competition;

/**
 * Hamcrest matcher for the class {@link Competition}
 *
 * @author teamC
 *
 */
public class IsEqualToCompetition extends IsEqualToEntity<Competition> {

	/**
	 * Constructor for the matcher
	 * 
	 * @param entity.
	 *            The entity to match.
	 */
	public IsEqualToCompetition(Competition entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(Competition actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			// TODO: Relation collections need to be added in the future.
			return checkAttribute("competitionName", Competition::getCompetitionName, actual)
					&& checkAttribute("competitionShortName", Competition::getCompetitionShortName, actual)
					&& checkAttribute("competitionDescription", Competition::getCompetitionDescription, actual)
					&& checkAttribute("competitionSportType", Competition::getCompetitionSportType, actual)
					&& checkAttribute("competitionType", Competition::getCompetitionType, actual)
					&& checkAttribute("competitionOpen", Competition::getCompetitionOpen, actual)
					&& checkAttribute("competitionMinTeams", Competition::getCompetitionMinTeams, actual)
					&& checkAttribute("competitionMaxTeams", Competition::getCompetitionMaxTeams, actual)
					&& checkAttribute("competitionPublic", Competition::getCompetitionPublic, actual)
					&& checkAttribute("competitionDeleted", Competition::getCompetitionDeleted, actual);
		}
	}

	/**
	 * Factory method to match an equal {@link Competition}
	 * 
	 * @param competition.
	 *            The {@link Competition} to be matched.
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static IsEqualToCompetition equalToCompetition(Competition competition) {
		return new IsEqualToCompetition(competition);
	}

	/**
	 * Factory method to match an equal {@link Competition} with a list. No order
	 * required.
	 * 
	 * @param competitions.
	 *            An array of {@link Competition} to be matched
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static Matcher<Iterable<? extends Competition>> containsCompetitionsInAnyOrder(Competition... competitions) {
		return containsEntityInAnyOrder(IsEqualToCompetition::equalToCompetition, competitions);
	}
	
	@Factory
	public static Matcher<Iterable<? extends Competition>> containsCompetitionsInAnyOrder(Iterable<Competition> competitions) {
		return containsEntityInAnyOrder(IsEqualToCompetition::equalToCompetition, competitions);
	}
}
