package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.domain.Team;

public class IsEqualToTeam extends IsEqualToEntity<Team> {
	private final boolean checkRelations;

	public IsEqualToTeam(Team team, boolean checkRelations) {
		super(team);
		this.checkRelations = checkRelations;
	}

	@Override
	protected boolean matchesSafely(Team actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("teamId", Team::getTeamId, actual)
					&& checkAttribute("teamName", Team::getTeamName, actual)
					&& checkAttribute("teamShortName", Team::getTeamShortName, actual)
					&& checkAttribute("teamDescription", Team::getTeamDescription, actual)
					&& checkAttribute("teamSportType", Team::getTeamSportType, actual)
					&& checkAttribute("teamOpen", Team::getTeamOpen, actual)
					&& checkAttribute("teamMinPlayer", Team::getTeamMinPlayers, actual)
					&& checkAttribute("teamMaxPlayer", Team::getTeamMaxPlayers, actual)
					&& checkAttribute("teamPublic", Team::getTeamPublic, actual)
					&& checkAttribute("teamDeleted", Team::getTeamDeleted, actual);
		}
	}

	@Factory
	public static IsEqualToTeam equalToTeam(Team team) {
		return new IsEqualToTeam(team, true);
	}

	@Factory
	public static IsEqualToTeam equalToTeamWithoutRelations(Team team) {
		return new IsEqualToTeam(team, false);
	}

	@Factory
	public static Matcher<Iterable<? extends Team>> containsTeamsInAnyOrder(Team... teams) {
		return containsEntityInAnyOrder(IsEqualToTeam::equalToTeam, teams);
	}

	@Factory
	public static Matcher<Iterable<? extends Team>> containsTeamsWithoutRelationsInAnyOrder(Team... team) {
		return containsEntityInAnyOrder(IsEqualToTeam::equalToTeamWithoutRelations, team);
	}

	@Factory
	public static Matcher<Iterable<? extends Team>> containsTeamsInAnyOrder(Iterable<Team> teams) {
		return containsEntityInAnyOrder(IsEqualToTeam::equalToTeam, teams);
	}

	@Factory
	public static Matcher<Iterable<? extends Team>> containsTeamsWithoutRelationsInAnyOrder(Iterable<Team> teams) {
		return containsEntityInAnyOrder(IsEqualToTeam::equalToTeamWithoutRelations, teams);
	}
}
