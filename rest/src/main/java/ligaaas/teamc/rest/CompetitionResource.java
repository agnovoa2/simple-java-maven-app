package ligaaas.teamc.rest;

import static ligaaas.teamc.converter.CompetitionConverter.toCompetitionDTO;
import static ligaaas.teamc.converter.TeamConverter.toTeamDTO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ligaaas.teamc.DTO.CompetitionDTO;
import ligaaas.teamc.DTO.TeamDTO;
import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.service.CompetitionEJB;
import ligaaas.teamc.service.TeamEJB;

@Path("competition")
@Stateless
public class CompetitionResource {

	@EJB
	CompetitionEJB competitionEJB;

	@EJB
	TeamEJB teamEJB;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Returns a list of public {@link Competition} that can be filtered for
	 * sporType or competitionLocality
	 * 
	 * @param sportType
	 *            the {@link SportType} to filter
	 * @param competitionLocality
	 *            the locality of the competition to filter
	 * @return a list of public {@link Competition}
	 */
	public Response getPublicCompetitions(@QueryParam("sportType") String sportType,
			@QueryParam("competitionLocality") String competitionLocality) {
		List<Competition> publicCompetitions = new ArrayList<>();

		if (sportType == null && competitionLocality == null) {
			publicCompetitions = competitionEJB.findPublicCompetition();
		} else if (sportType != null && competitionLocality == null) {
			try {
				publicCompetitions = competitionEJB.findPublicCompetitionBySportType(SportType.valueOf(sportType));
			} catch (IllegalArgumentException e) {
				throw new BadRequestException("Invalid SportType");
			}
		} else if (sportType == null && competitionLocality != null) {
			publicCompetitions = competitionEJB.findPublicCompetitionByLocality(competitionLocality);
		} else {
			throw new BadRequestException("Only one filter at a time");
		}

		List<CompetitionDTO> toRet = toCompetitionDTO(publicCompetitions);

		return Response.status(200).entity(toRet).build();
	}

	@GET
	@Path("{COMPETITION_ID}/team")
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Returns a list of public {@link Team} associated to {@link Competition}
	 * 
	 * @param competitionId
	 *            the id of {@link Competition}
	 * @return a list of public {@link Team}
	 */
	public Response getPublicTeamsOfPublicCompetition(@PathParam("COMPETITION_ID") String competitionId) {
		List<Team> publicTeams = new ArrayList<>();
		List<Competition> publicCompetition = new ArrayList<>();

		publicCompetition = competitionEJB.findPublicCompetitionById(Long.valueOf(competitionId));
		if (publicCompetition.isEmpty()) {
			throw new IllegalArgumentException("Public competition not found with id: " + competitionId);
		}

		publicTeams = teamEJB.findPublicTeamByCompetition(publicCompetition.get(0));
		List<TeamDTO> toret = toTeamDTO(publicTeams);
		return Response.status(200).entity(toret).build();
	}
}
