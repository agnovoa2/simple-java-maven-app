package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.HeadQuarter;

/**
 * Hamcrest matcher for the class {@link Competition}
 *
 * @author teamC
 *
 */
public class IsEqualToHeadQuarter extends IsEqualToEntity<HeadQuarter> {

	/**
	 * Constructor for the matcher
	 * 
	 * @param entity.
	 *            The entity to match.
	 */
	public IsEqualToHeadQuarter(HeadQuarter entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(HeadQuarter actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("headquarterName", HeadQuarter::getHeadquarterName, actual)
					&& checkAttribute("headquarterProvince", HeadQuarter::getHeadquarterProvince, actual)
					&& checkAttribute("headquarterAddress", HeadQuarter::getHeadquarterAddress, actual)
					&& checkAttribute("headquarterDescription", HeadQuarter::getHeadquarterDescription, actual)
					&& checkAttribute("headquarterLocality", HeadQuarter::getHeadquarterLocality, actual)
					&& checkAttribute("headquarterDeleted", HeadQuarter::getHeadQuarterDeleted, actual);
		}
	}

	/**
	 * Factory method to match an equal {@link HeadQuarter}
	 * 
	 * @param headQuarter.
	 *            The {@link HeadQuarter} to be matched.
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static IsEqualToHeadQuarter equalToHeadQuarter(HeadQuarter headQuarter) {
		return new IsEqualToHeadQuarter(headQuarter);
	}

	/**
	 * Factory method to match an equal {@link HeadQuarter} with a list. No order
	 * required.
	 * 
	 * @param headQuarters.
	 *            An array of {@link HeadQuarter} to be matched
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static Matcher<Iterable<? extends HeadQuarter>> containsHeadQuartersInAnyOrder(HeadQuarter... headQuarters) {
		return containsEntityInAnyOrder(IsEqualToHeadQuarter::equalToHeadQuarter, headQuarters);
	}
}
