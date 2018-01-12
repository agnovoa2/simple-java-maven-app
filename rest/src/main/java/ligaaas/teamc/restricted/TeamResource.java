package ligaaas.teamc.restricted;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ligaaas.teamc.DTO.TeamDTO;
import ligaaas.teamc.converter.TeamConverter;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.service.PlayerEJB;
import ligaaas.teamc.service.TeamEJB;

/**
 * Resource that represents the user in the application.
 * 
 * @author teamC
 *
 */
@Path("private/team")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

	@EJB
	private TeamEJB teamEJB;
	
	@EJB
	private PlayerEJB playerEJB;
	
	@Context
	private UriInfo uriInfo;
	
	
	/**
	 * Returns a {@link List} of the {@link Team}s managed by the User.
	 * @return a {@link List} of {@link Team}s managed by the User
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listTeams() {
		
		List<Team> listTeams = teamEJB.findByUser();
		List<TeamDTO> listDTOs = TeamConverter.toTeamDTO(listTeams);
		return Response.status(200).entity(listDTOs).build();
	}	
	
	/**
	 * Adds a {@link Player} managed by the user to a {@link Team} managed by the User.
	 * @return a {@link Response} with the request status.
	 */
	@POST
	@Path("{TEAM_ID}/player/{PLAYER_ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPlayerToTeam(@PathParam("TEAM_ID") String teamId, @PathParam("PLAYER_ID") String playerId) {
		Team selectedTeam = teamEJB.find(Long.parseLong(teamId)); 
		
		if(selectedTeam == null) {
			throw new IllegalArgumentException("Team does not exist");
		}
		
		Player selectedPlayer = playerEJB.find(Long.parseLong(playerId)); 
		
		if(selectedPlayer == null) {
			throw new IllegalArgumentException("Player does not exist");
		}
		
		List<Player> players = new ArrayList<Player>();
		players.add(selectedPlayer);
		
		teamEJB.addPlayers(players, selectedTeam);
		
		return Response.ok().build();
	}	
}
