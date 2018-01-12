package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.DTO.TeamDTO;
import ligaaas.teamc.domain.Team;

/**
 * Hamcrest matcher for the class {@link TeamDTO}
 *
 * @author teamC
 *
 */
public class IsEqualToTeamDTO extends IsEqualToEntity<TeamDTO> {

	/**
	 * Constructor for the matcher
	 * 
	 * @param entity.
	 *            The entity to match.
	 */
	public IsEqualToTeamDTO(TeamDTO entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(TeamDTO actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("teamId", TeamDTO::getTeamId, actual)
					&& checkAttribute("teamName", TeamDTO::getTeamName, actual)
					&& checkAttribute("teamShortName", TeamDTO::getTeamShortName, actual)
					&& checkAttribute("teamDescription", TeamDTO::getTeamDescription, actual)
					&& checkAttribute("teamSportType", TeamDTO::getTeamSportType, actual)
					&& checkAttribute("teamOpen", TeamDTO::getTeamOpen, actual)
					&& checkAttribute("teamMinPlayer", TeamDTO::getTeamMinPlayers, actual)
					&& checkAttribute("teamMaxPlayer", TeamDTO::getTeamMaxPlayers, actual)
					&& checkAttribute("teamPublic", TeamDTO::getTeamPublic, actual)
					&& checkAttribute("teamContact", TeamDTO::getTeamContact, actual);
		}
	}

	/**
	 * Factory method to match an equal {@link TeamDTO}
	 * 
	 * @param team.
	 *            The {@link TeamDTO} to be matched.
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static IsEqualToTeamDTO equalToTeam(TeamDTO team) {
		return new IsEqualToTeamDTO(team);
	}

	/**
	 * Factory method to match an equal {@link TeamDTO} with a list. No order
	 * required.
	 * 
	 * @param teams.
	 *            An array of {@link TeamDTO} to be matched
	 * @return <code>true</code> if the value of the expected and actual entities
	 *         are equals and <code>false</code> otherwise.
	 */
	@Factory
	public static Matcher<Iterable<? extends TeamDTO>> containsTeamsInAnyOrder(TeamDTO... teams) {
		return containsEntityInAnyOrder(IsEqualToTeamDTO::equalToTeam, teams);
	}

	@Factory
	public static Matcher<Iterable<? extends TeamDTO>> containsTeamsInAnyOrder(Iterable<TeamDTO> teams) {
		return containsEntityInAnyOrder(IsEqualToTeamDTO::equalToTeam, teams);
	}
}
