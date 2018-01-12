package ligaaas.teamc.converter;

import static ligaaas.teamc.converter.ContactConverter.toContactDTO;
import static ligaaas.teamc.converter.HeadQuarterConverter.toHeadQuarterDTO;

import java.util.ArrayList;
import java.util.List;

import ligaaas.teamc.DTO.CompetitionDTO;
import ligaaas.teamc.domain.Competition;

/**
 * Converter of {@link Competition} into {@link CompetitionDTO}
 * @author teamC
 *
 */
public class CompetitionConverter {
	
	/**
	 * Converts a List of {@link Competition} into a list of {@link CompetitionDTO} to be used over REST API
	 * @param competitionList a List of {@link Competition}
	 * @return a List of {@link CompetitionDTO}
	 */
	public static List<CompetitionDTO> toCompetitionDTO(List<Competition> competitionList){
		List<CompetitionDTO> toRet = new ArrayList<>();
		
		for(Competition competition: competitionList) {
			toRet.add(toCompetitionDTO(competition));
		}
		
		return toRet;
	}
	
	/**
	 * Converts a {@link Competition} into a {@link CompetitionDTO} to be used over REST API
	 * @param competition a {@link Competition}
	 * @return a {@link CompetitionDTO}
	 */
	public static CompetitionDTO toCompetitionDTO(Competition competition) {
		CompetitionDTO competitionDTO = new CompetitionDTO();
		
		competitionDTO.setCompetitionDescription(competition.getCompetitionDescription());
		competitionDTO.setCompetitionMaxTeams(competition.getCompetitionMaxTeams());
		competitionDTO.setCompetitionMinTeams(competition.getCompetitionMinTeams());
		competitionDTO.setCompetitionName(competition.getCompetitionName());
		competitionDTO.setCompetitionOpen(competition.getCompetitionOpen());
		competitionDTO.setCompetitionShortName(competition.getCompetitionShortName());
		competitionDTO.setCompetitionSportType(competition.getCompetitionSportType().toString());
		competitionDTO.setCompetitionType(competition.getCompetitionType().toString());
		if(competition.getCompetitionContact() != null)
			competitionDTO.setContact(toContactDTO(competition.getCompetitionContact()));
		if(competition.getCompetitionHeadQuarter() != null) 
			competitionDTO.setHeadQuarter(toHeadQuarterDTO(competition.getCompetitionHeadQuarter()));
		
		return competitionDTO;
	}
}
