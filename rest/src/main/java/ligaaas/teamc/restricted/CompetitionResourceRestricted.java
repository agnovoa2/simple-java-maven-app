package ligaaas.teamc.restricted;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ligaaas.teamc.DTO.CompetitionDTO;
import ligaaas.teamc.converter.CompetitionConverter;
import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.service.CompetitionEJB;

/**
 * Resource that represents the user in the application.
 * 
 * @author teamC
 *
 */
@Path("private/competition")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompetitionResourceRestricted {

	@EJB
	private CompetitionEJB competitionEJB;
	
	@Context
	private UriInfo uriInfo;
	
	
	/**
	 * Returns a {@link List} of the {@link Competition}s managed by the User.
	 * @return a {@link List} of {@link Competition}s managed by the User
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listCompetitions() {
		
		List<Competition> listCompetitions = competitionEJB.findByUser();
		List<CompetitionDTO> listDTOs = CompetitionConverter.toCompetitionDTO(listCompetitions);
		return Response.status(200).entity(listDTOs).build();
	}	
}
