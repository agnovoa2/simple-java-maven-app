package ligaaas.teamc.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.service.PlayerEJB;
import ligaaas.teamc.service.TeamEJB;

/**
 * ManagedBean for newPlayer
 * 
 * @author teamC
 *
 */
@Named("newPlayer")
@ViewScoped
public class NewPlayerManagedBean implements Serializable{

	private static final long serialVersionUID = -1306798838214394594L;

	@Inject
	private TeamEJB teamEJB;

	@Inject
	private PlayerEJB playerEJB;

	@Inject
	private HttpServletRequest request;

	private String newPlayerTeam;

	private Player newPlayer = new Player();

	/**
	 * Initializes the ManagedBean.
	 * 
	 */
	@PostConstruct
	public void init() {
		newPlayerTeam =  request.getParameter("newPlayerTeam");
	}

	/**
	 * Return the {@link Player} that will be persisted
	 * 
	 * @return the {@link Player} that will be persisted
	 */
	public Player getNewPlayer() {
		return newPlayer;
	}

	/**
	 * Sets the {@link Player} that will be persisted
	 * 
	 * @param newPlayer
	 *            the {@link Player} that will be persisted
	 */
	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}

	/**
	 * Returns the id of the {@link Team}.
	 * 
	 * @return the id of {@link Team}.
	 */
	public String getNewPlayerTeam() {
		return newPlayerTeam;
	}

	/**
	 * Sets the id of the {@link Team}.
	 * 
	 * @param newPlayerTeam
	 *            the id of {@link Team}.
	 */
	public void setNewPlayerTeam(String newPlayerTeam) {
		this.newPlayerTeam = newPlayerTeam;
	}

	/**
	 * Register a new {@link Player} for the {@link Team}
	 * 
	 * @return index redirect to the userTeams.xhtml page.
	 */
	public String doAddPlayer() {
		Team team = teamEJB.find(Long.parseLong(newPlayerTeam));
		
		Player playerSaved = playerEJB.create(newPlayer);
		List<Player> teamPlayers = new ArrayList<>();
		teamPlayers.add(playerSaved);
		teamEJB.addPlayers(teamPlayers, team);
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/views/private/userTeams.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
	
}
