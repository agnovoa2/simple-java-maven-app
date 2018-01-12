package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.domain.Round;

/**
 * Hamcrest matcher for the class {@link Round}
 *
 * @author teamC
 *
 */
public class IsEqualToRound extends IsEqualToEntity<Round> {

	/**
	 * Constructor for the matcher
	 * 
	 * @param entity.
	 *            The entity to match.
	 */
	public IsEqualToRound(Round entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(Round actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("roundNumber", Round::getRoundNumber, actual)
					&& checkAttribute("roundDate", Round::getRoundDate, actual)
					&& checkAttribute("roundState", Round::getRoundState, actual)
					&& checkAttribute("roundDescription", Round::getRoundDescription, actual)
					&& checkAttribute("roundComments", Round::getRoundComments, actual);
		}
	}

	/**
	 * Factory method to match an equal {@link Round}
	 * 
	 * @param round.
	 *            The {@link Round} to be matched.
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static IsEqualToRound equalToRound(Round round) {
		return new IsEqualToRound(round);
	}

	/**
	 * Factory method to match an equal {@link Round} with a list. No order
	 * required.
	 * 
	 * @param rounds.
	 *            An array of {@link Round} to be matched
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static Matcher<Iterable<? extends Round>> containsRoundsInAnyOrder(Round... rounds) {
		return containsEntityInAnyOrder(IsEqualToRound::equalToRound, rounds);
	}
}
