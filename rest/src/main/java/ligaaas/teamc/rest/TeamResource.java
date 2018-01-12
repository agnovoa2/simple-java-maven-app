package ligaaas.teamc.rest;

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

import ligaaas.teamc.DTO.PlayerDTO;
import ligaaas.teamc.DTO.TeamDTO;
import ligaaas.teamc.converter.PlayerConverter;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.service.PlayerEJB;
import ligaaas.teamc.service.TeamEJB;

@Path("team")
@Stateless
public class TeamResource {

	@EJB
	TeamEJB teamEJB;
	
	@EJB
	private PlayerEJB playerEJB;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Returns a list of public {@link Team} that can be filtered for sporType or
	 * locality
	 * 
	 * @param sportType
	 *            he {@link SportType} to filter
	 * @param locality
	 *            the locality of the team to filter
	 * @return a list of public {@link Team}
	 */
	public Response getPublicTeams(@QueryParam("sportType") String sportType, @QueryParam("locality") String locality) {
		List<Team> publicTeams = new ArrayList<>();

		if (sportType == null && locality == null) {
			publicTeams = teamEJB.findPublicTeam();
		} else if (sportType != null && locality == null) {
			try {
				publicTeams = teamEJB.findPublicTeamBySportType(SportType.valueOf(sportType));
			} catch (IllegalArgumentException e) {
				throw new BadRequestException("Invalid SportType");
			}
		} else if (sportType == null && locality != null) {
			publicTeams = teamEJB.findPublicTeamByLocality(locality);
		} else {
			throw new BadRequestException("Only one filter at a time");
		}
		List<TeamDTO> toRet = toTeamDTO(publicTeams);

		return Response.status(200).entity(toRet).build();
	}
	
	/**
	 * Returns a {@link List} of the {@link Player}s that belongs to a team.
	 * @return a {@link List} of {@link Player}s managed by the User
	 */
	@GET
	@Path("/{teamId}/players")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfPlayers(@PathParam("teamId") int teamId) {
		
		Team team = teamEJB.find(teamId);
		List<Player> listPlayers = playerEJB.findTeamPlayers(team);
		List<PlayerDTO> listDTOs = PlayerConverter.toPlayerDTO(listPlayers);
		return Response.status(200).entity(listDTOs).build();
	}

}
