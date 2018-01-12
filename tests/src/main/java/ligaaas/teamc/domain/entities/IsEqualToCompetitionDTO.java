package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.DTO.CompetitionDTO;

/**
 * Hamcrest matcher for the class {@link CompetitionDTO}
 *
 * @author teamC
 *
 */
public class IsEqualToCompetitionDTO extends IsEqualToEntity<CompetitionDTO> {

	/**
	 * Constructor for the matcher
	 * 
	 * @param entity.
	 *            The entity to match.
	 */
	public IsEqualToCompetitionDTO(CompetitionDTO entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(CompetitionDTO actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("competitionName", CompetitionDTO::getCompetitionName, actual)
					&& checkAttribute("competitionShortName", CompetitionDTO::getCompetitionShortName, actual)
					&& checkAttribute("competitionDescription", CompetitionDTO::getCompetitionDescription, actual)
					&& checkAttribute("competitionSportType", CompetitionDTO::getCompetitionSportType, actual)
					&& checkAttribute("competitionType", CompetitionDTO::getCompetitionType, actual)
					&& checkAttribute("competitionOpen", CompetitionDTO::getCompetitionOpen, actual)
					&& checkAttribute("competitionMinTeams", CompetitionDTO::getCompetitionMinTeams, actual)
					&& checkAttribute("competitionMaxTeams", CompetitionDTO::getCompetitionMaxTeams, actual);
		}
	}

	/**
	 * Factory method to match an equal {@link CompetitionDTO}
	 * 
	 * @param competition.
	 *            The {@link CompetitionDTO} to be matched.
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static IsEqualToCompetitionDTO equalToCompetition(CompetitionDTO competition) {
		return new IsEqualToCompetitionDTO(competition);
	}

	/**
	 * Factory method to match an equal {@link CompetitionDTO} with a list. No order
	 * required.
	 * 
	 * @param competitions.
	 *            An array of {@link CompetitionDTO} to be matched
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static Matcher<Iterable<? extends CompetitionDTO>> containsCompetitionsInAnyOrder(CompetitionDTO... competitions) {
		return containsEntityInAnyOrder(IsEqualToCompetitionDTO::equalToCompetition, competitions);
	}
	
	@Factory
	public static Matcher<Iterable<? extends CompetitionDTO>> containsCompetitionsInAnyOrder(Iterable<CompetitionDTO> competitions) {
		return containsEntityInAnyOrder(IsEqualToCompetitionDTO::equalToCompetition, competitions);
	}
}
