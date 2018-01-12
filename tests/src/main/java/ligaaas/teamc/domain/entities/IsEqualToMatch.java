package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.domain.Match;

/**
 * Hamcrest matcher for the class {@link Match}
 *
 * @author teamC
 *
 */
public class IsEqualToMatch extends IsEqualToEntity<Match> {

	/**
	 * Constructor for the matcher
	 * 
	 * @param entity.
	 *            The entity to match.
	 */
	public IsEqualToMatch(Match entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(Match actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("matchDate", Match::getMatchDate, actual)
					&& checkAttribute("matchState", Match::getMatchState, actual)
					&& checkAttribute("matchDuration", Match::getMatchDuration, actual)
					&& checkAttribute("matchLocalPoints", Match::getMatchLocalPoints, actual)
					&& checkAttribute("matchVisitingPoints", Match::getMatchVisitingPoints, actual)
					&& checkAttribute("matchDescription", Match::getMatchDescription, actual)
					&& checkAttribute("matchComments", Match::getMatchComments, actual);
		}
	}

	/**
	 * Factory method to match an equal {@link Match}
	 * 
	 * @param match.
	 *            The {@link Match} to be matched.
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static IsEqualToMatch equalToMatch(Match match) {
		return new IsEqualToMatch(match);
	}

	/**
	 * Factory method to match an equal {@link Match} with a list. No order
	 * required.
	 * 
	 * @param matches.
	 *            An array of {@link Match} to be matched
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static Matcher<Iterable<? extends Match>> containsMatchesInAnyOrder(Match... matches) {
		return containsEntityInAnyOrder(IsEqualToMatch::equalToMatch, matches);
	}
}
