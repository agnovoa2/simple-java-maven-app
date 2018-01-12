package ligaaas.teamc.restricted;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ligaaas.teamc.DTO.PlayerDTO;
import ligaaas.teamc.converter.PlayerConverter;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.service.PlayerEJB;

/**
 * Resource that represents the user in the application.
 * 
 * @author teamC
 *
 */
@Path("private/player")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

	@EJB
	private PlayerEJB playerEJB;
	
	@Context
	private UriInfo uriInfo;
	
	
	/**
	 * Returns a {@link List} of the {@link Player}s managed by the User.
	 * @return a {@link List} of {@link Player}s managed by the User
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPlayers() {
		
		List<Player> listPlayers = playerEJB.findByUser();
		List<PlayerDTO> listDTOs = PlayerConverter.toPlayerDTO(listPlayers);
		return Response.status(200).entity(listDTOs).build();
	}	
}
