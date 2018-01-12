package ligaaas.teamc.converter;

import static ligaaas.teamc.converter.ContactConverter.toContactDTO;
import static ligaaas.teamc.converter.HeadQuarterConverter.toHeadQuarterDTO;

import java.util.ArrayList;
import java.util.List;

import ligaaas.teamc.DTO.TeamDTO;
import ligaaas.teamc.domain.Team;

/**
 * Converter for Team
 * 
 * @author teamC
 *
 */
public class TeamConverter {

	/**
	 * Converts a List of {@link Team}s into a List of {@link TeamDTO}s
	 * 
	 * @param listTeam
	 *            List of {@link Team}s.
	 * @return List of {@link TeamDTO}s.
	 */
	public static List<TeamDTO> toTeamDTO(List<Team> listTeam) {
		List<TeamDTO> listDTO = new ArrayList<>();
		for (Team team : listTeam) {
			listDTO.add(toTeamDTO(team));
		}
		return listDTO;
	}

	/**
	 * Converts a {@link Team}s into a {@link TeamDTO}s
	 * 
	 * @param team
	 *            {@link Team}.
	 * @return {@link TeamDTO}.
	 */
	public static TeamDTO toTeamDTO(Team team) {
		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setTeamId(team.getTeamId());
		teamDTO.setTeamName(team.getTeamName());
		teamDTO.setTeamShortName(team.getTeamShortName());
		teamDTO.setTeamDescription(team.getTeamDescription());
		teamDTO.setTeamSportType(team.getTeamSportType());
		teamDTO.setTeamOpen(team.getTeamOpen());
		teamDTO.setTeamMinPlayers(team.getTeamMinPlayers());
		teamDTO.setTeamMaxPlayers(team.getTeamMaxPlayers());
		teamDTO.setTeamPublic(team.getTeamPublic());
		if (team.getTeamContact() != null)
			teamDTO.setTeamContact(toContactDTO(team.getTeamContact()));
		if (team.getTeamHeadQuarter() != null) {
			teamDTO.setTeamHeadQuarter(toHeadQuarterDTO(team.getTeamHeadQuarter()));
		}
		return teamDTO;
	}
}
