package ligaaas.teamc.domain.entities;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.CompetitionType;
import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.User;

public class TeamsDataset {
	public static Team createdTeam() {
		return new Team(1, "Inter Movistar", "Inter", "Inter Movistar", SportType.FOOTBALL7, false, 10, 17, false,
				false, null, null, existentUser());
	}

	public static Team predefinedTeam() {
		return new Team(999, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true, 18,
				27, false, false, null, null, existentUser());
	}

	public static Team otherTeam() {
		List<Competition> teamCompetitions = new ArrayList<>();
		teamCompetitions.add(new Competition(999, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, true, false, null, anotherUser(), null));

		Team t = new Team(997, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true,
				18, 27, true, false, headQuarter(), null, anotherUser());
		t.setTeamCompetitions(teamCompetitions);

		return t;
	}

	public static Team deletedTeam() {
		return new Team(998, "Rapido de Bouzas F.C.D", "RdB FC", "Rapido de Bouzas F.C.D", SportType.FOOTBALL11, true,
				18, 27, false, true, null, null, existentUser());
	}

	public static Team modifiedTeam() {
		return new Team(999, "Rapido de Bouzas F.C. 2", "RdB FC 2", "Rapido de Bouzas F.C.", SportType.FOOTBALL7, true,
				18, 27, false, false, null, null, existentUser());
	}

	public static HeadQuarter headQuarter() {
		HeadQuarter h1 = new HeadQuarter(1, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Ourense", "Pontevedra", false);
		h1.setHeadQuarterManagedByUser(existentUser());
		return h1;
	}

	public static SportType modifiedSportType() {
		return SportType.FOOTBALL11;
	}

	public static User existentUser() {
		return new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}

	public static User anotherUser() {
		return new User(998, "mrrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233B", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mrrodriguez@gmail.es", false, false, "token");
	}

	public static Team teamWithoutPlayers() {
		return new Team(999, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL7, true, 18,
				27, false, false, null, null, existentUser());
	}

	private static Player[] arrayPlayersWithoutTeam() {
		return new Player[] {
				new Player(1, "Pepe", null, "Interest 1, interest 2", "pepe@gmail.com", "Location", "Province",
						"FavouriteList", "FavouriteTeamList", true),
				new Player(2, "Antonio", null, "Interest 1, interest 2", "antonio@gmail.com", "Location", "Province",
						"FavouriteList", "FavouriteTeamList", true) };
	}

	public static List<Player> playersWithoutTeam() {
		return new ArrayList<>(asList(arrayPlayersWithoutTeam()));
	}

	public static Team teamWithPlayers() {
		Team team = predefinedTeam();
		team.getTeamPlayers().addAll(asList(arrayPlayersWithTeam()));
		return team;
	}

	private static Player[] arrayPlayersWithTeam() {
		return new Player[] {
				new Player(1, "Pepe", null, "Interest 1, interest 2", "pepe@gmail.com", "Location", "Province",
						"FavouriteList", "FavouriteTeamList", true),
				new Player(2, "Antonio", null, "Interest 1, interest 2", "antonio@gmail.com", "Location", "Province",
						"FavouriteList", "FavouriteTeamList", true) };
	}

	public static List<Player> playersWithTeam() {
		return new ArrayList<>(asList(arrayPlayersWithTeam()));
	}

	public static List<Team> teamsManagedByUser() {
		User user = new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "d23f11aa47a86278435e2f853fed8d3d",
				new Date(946684861000L), "11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España",
				"+34988112233", "https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");

		List<Team> teams = new ArrayList<Team>();
		teams.add(new Team(1000, "Inter Movistar", "Inter", "Inter Movistar", SportType.FOOTBALL7, false, 10, 17, false,
				false, null, null, user));

		teams.add(new Team(1001, "Rapido de Bouzas F.C.D", "RdB FC", "Rapido de Bouzas F.C.D", SportType.FOOTBALL7,
				true, 18, 27, false, false, null, null, user));

		return teams;
	}

	public static List<Team> listTeams() {
		List<Team> teams = new ArrayList<>();
		teams.add(otherTeam());
		return teams;
	}
}
