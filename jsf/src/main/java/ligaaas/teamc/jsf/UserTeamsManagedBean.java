package ligaaas.teamc.jsf;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.User;
import ligaaas.teamc.service.PlayerEJB;
import ligaaas.teamc.service.TeamEJB;
import ligaaas.teamc.service.UserEJB;

/**
 * ManagedBean for userTeams
 * 
 * @author teamC
 *
 */

@Named("userTeams")
@RequestScoped
public class UserTeamsManagedBean {

	@Inject
	private TeamEJB teamEJB;

	@Inject
	private UserEJB userEJB;
	
	@Inject
	private PlayerEJB playerEJB;

	@Inject
	private Principal currentUserPrincipal;

	private List<Team> teams;

	private Team selectedTeam;

	private boolean showTable;

	private Team newTeam = new Team();

	private String teamNameSearch;

	private String teamDescriptionSearch;

	private SportType teamSportTypeSearch;

	private User currentUser;
	
	private List<Player> players;
	
	private List<Player> filteredPlayers;

	@PostConstruct
	public void init() {
		currentUser = null;
		List<User> dbUsers = userEJB.findByLogin(currentUserPrincipal.getName());
		if (dbUsers.size() > 0) {
			currentUser = dbUsers.get(0);
		}

		if (currentUser != null) {
			this.teams = teamEJB.findByUser(currentUser);
			showTable = this.teams.size() > 0;
		}
		this.selectedTeam = new Team();
		
		this.players = playerEJB.findPublicPlayers();
		this.showTable = players.size() > 0;
	}

	/**
	 * Returns the current list of {@link Team} of the user
	 * 
	 * @return the current list of {@link Team} of the user
	 */
	public List<Team> getTeams() {
		return teams;
	}

	/**
	 * Returns if the table can be show
	 * 
	 * @return if the table can be show
	 */
	public boolean getShowTable() {
		return showTable;
	}

	/**
	 * Register a new team for the current {@link User}
	 * 
	 * @return index redirect to the userTeams.xhtml page.
	 */
	public String doRegisterTeam() {
		teams.add(teamEJB.create(newTeam));
		RequestContext.getCurrentInstance().update("userTeamsDatatable");

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/views/private/userTeams.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}

	/**
	 * Redirects to the page to edit the team
	 * 
	 * @return index redirect to the userTeamEdit.xhtml page.
	 */
	public String doEditTeam() {
		String teamId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		selectedTeam = teamEJB.find(Long.valueOf(teamId));
		return "userTeamEdit";
	}

	/**
	 * Edit an existent team
	 * 
	 * @return index redirect to the userTeamEdit.xhtml page.
	 */
	public String doUpdateTeam() {
		Team toUpdate = teamEJB.find(selectedTeam.getTeamId());

		toUpdate.setTeamName(selectedTeam.getTeamName());
		toUpdate.setTeamShortName(selectedTeam.getTeamShortName());
		toUpdate.setTeamDescription(selectedTeam.getTeamDescription());
		toUpdate.setTeamSportType(selectedTeam.getTeamSportType());
		toUpdate.setTeamOpen(selectedTeam.getTeamOpen());
		toUpdate.setTeamMinPlayers(selectedTeam.getTeamMinPlayers());
		toUpdate.setTeamMaxPlayers(selectedTeam.getTeamMaxPlayers());
		toUpdate.setTeamPublic(selectedTeam.getTeamPublic());
		toUpdate.setTeamUser(currentUser);

		teamEJB.update(toUpdate);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/views/private/userTeams.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}

	/**
	 * Removes a team for the current User
	 * 
	 * @param team
	 *            the {@link Team} to be removed for the current {@link User}
	 *            
	 * @return index redirect to the userTeamEdit.xhtml page.           
	 */
	public String doRemoveTeam(Team team) {
		teams.remove(team);
		teamEJB.delete(team.getTeamId());
		RequestContext.getCurrentInstance().update("userTeamsDatatable");

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/views/private/userTeams.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	/**
	 * Adds a {@link Player} to the selected {@link Team}
	 * 
	 * @return index redirect to the userTeamEdit.xhtml page.
	 */
	public String doAddPlayer() {
		long playerId = Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("playerId"));
		long teamId = Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("teamId"));
		List<Player> playerList = new ArrayList<>();
		playerList.add(playerEJB.find(playerId));
		Team team = new Team();
		team.setTeamId(teamId);
		teamEJB.addPlayers(playerList, team);

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/views/private/userTeams.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	/**
	 * Return an array of String values of {@link SportType} to be used in
	 * comboxes in the view
	 * 
	 * @return an array of String values of {@link SportType} to be used in
	 *         comboxes in the view
	 */
	public SportType[] getSportTypes() {
		return SportType.values();
	}

	/**
	 * Performs a search of {@link Team} given its name
	 */
	public void doSearchByName() {
		teams = teamEJB.findByName(teamNameSearch);
	}

	/**
	 * Performs a search of {@link Team} given its description
	 */
	public void doSearchByDescription() {
		teams = teamEJB.findByDescription(teamDescriptionSearch);
	}

	/**
	 * Performs a search of {@link Team} given its {@link SportType}
	 */
	public void doSearchBySportType() {
		teams = teamEJB.findBySportType(teamSportTypeSearch);
	}

	/**
	 * Return the name of the {@link Team} that will be used in the search
	 * 
	 * @return the name of the {@link Team}
	 */
	public String getTeamNameSearch() {
		return teamNameSearch;
	}

	/**
	 * Sets the name that will be used in the search
	 * 
	 * @param teamNameSearch
	 *            the name that will be used in the search
	 */
	public void setTeamNameSearch(String teamNameSearch) {
		this.teamNameSearch = teamNameSearch;
	}

	/**
	 * Return the description of the {@link Team} that will be used in the
	 * search
	 * 
	 * @return the description of the {@link Team}
	 */
	public String getTeamDescriptionSearch() {
		return teamDescriptionSearch;
	}

	/**
	 * Sets the description that will be used in the search
	 * 
	 * @param teamDesciptionSearch
	 *            the description that will be used in the search
	 */
	public void setTeamDescriptionSearch(String teamDesciptionSearch) {
		this.teamDescriptionSearch = teamDesciptionSearch;
	}

	/**
	 * Return the {@link SportType} of the {@link Team} that will be used in the
	 * search
	 * 
	 * @return the {@link SportType} of the {@link Team}
	 */
	public SportType getTeamSportTypeSearch() {
		return teamSportTypeSearch;
	}

	/**
	 * Sets the {@link SportType} that will be used in the search
	 * 
	 * @param teamSportTypeSearch
	 *            the {@link SportType} that will be used in the search
	 */
	public void setTeamSportTypeSearch(SportType teamSportTypeSearch) {
		this.teamSportTypeSearch = teamSportTypeSearch;
	}

	/**
	 * Return the {@link Team} that will be persisted
	 * 
	 * @return the {@link Team} that will be persisted
	 */
	public Team getNewTeam() {
		return newTeam;
	}

	/**
	 * Sets the {@link Team} that will be persisted
	 * 
	 * @param newTeam
	 *            the {@link Team} that will be persisted
	 */
	public void setNewTeam(Team newTeam) {
		this.newTeam = newTeam;
	}

	/**
	 * Return the {@link Team} selected for edit
	 * 
	 * @return the {@link Team} selected
	 */
	public Team getSelectedTeam() {
		return selectedTeam;
	}

	/**
	 * Sets the {@link Team} that will be updated
	 * 
	 * @param selectedTeam
	 *            the {@link Team} that will be updated
	 */
	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	/**
	 * Returns the current list of {@link Player}s
	 * 
	 * @return the current list of {@link Player}s
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Sets the list of {@link Player}s.
	 * 
	 * @param players
	 *            the list of {@link Player}s.
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	/**
	 * Returns the current filtered list of {@link Player}s
	 * 
	 * @return the current filtered list of {@link Player}s
	 */
	public List<Player> getFilteredPlayers() {
		return filteredPlayers;
	}

	/**
	 * Sets the filtered list of {@link Player}s.
	 * 
	 * @param filteredPlayers
	 *            the filtered list of {@link Player}s.
	 */
	public void setFilteredPlayers(List<Player> filteredPlayers) {
		this.filteredPlayers = filteredPlayers;
	}
}
