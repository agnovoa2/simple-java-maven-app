package ligaaas.teamc.domain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ligaaas.teamc.domain.Player;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.User;

public class PlayersDataset {

	public static Player predefinedPlayer() {
		Player p = new Player(999, "Pepe", null, "Interest 1, interest 2", "pepe@gmail.com", "Location", "Province",
				"FavouriteList", "FavouriteTeamList", true);
		p.setPlayerManagedByUser(existentUser());
		return p;
	}

	public static Player modifiedPlayer() {
		Player p = new Player(999, "Juan", null, "Interest 1, interest 2", "pepe@gmail.com", "Location", "Province",
				"FavouriteList", "FavouriteTeamList", true);
		p.setPlayerManagedByUser(existentUser());
		return p;
	}

	public static Player createdUserPlayer() {
		Player p = new Player(1, "Manolo", null, "Interest 1, interest 2", "manolo@gmail.com", "Location", "Province",
				"FavouriteList", "FavouriteTeamList", true);
		p.setPlayerManagedByUser(existentUserPlayer());
		p.setPlayerUser(existentUserPlayer());
		return p;
	}

	public static Player createdPlayer() {
		Player p = new Player(1, "Manolo", null, "Interest 1, interest 2", "manolo@gmail.com", "Location", "Province",
				"FavouriteList", "FavouriteTeamList", true);
		p.setPlayerManagedByUser(existentUser());
		return p;
	}

	public static User existentUser() {
		return new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}

	public static User existentUserPlayer() {
		User u = new User(998, "mrrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233B", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mrrodriguez@gmail.es", false, false, "token");
		Player p = new Player(1, "Manolo", null, "Interest 1, interest 2", "manolo@gmail.com", "Location", "Province",
				"FavouriteList", "FavouriteTeamList", true);

		u.setUserPlayer(p);
		return u;
	}

	public static User anotherUser() {
		return new User(998, "mrrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233B", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mrrodriguez@gmail.es", false, false, "token");
	}

	public static List<Player> playersManagedByUser() {
		List<Player> players = new ArrayList<Player>();

		players.add(new Player(1000, "Usuario 1000", null, "Interest 1, interest 2", "user1000@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", true));

		players.add(new Player(1001, "Usuario 1001", null, "Interest 1, interest 2", "user1001@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", true));

		players.add(new Player(1004, "Usuario 1004", null, "Interest 1, interest 2", "user1004@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", true));

		players.add(new Player(1005, "Usuario 1005", null, "Interest 1, interest 2", "user1005@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", true));

		return players;
	}
	
	public static List<Player> publicPlayers(){
		List<Player> players = new ArrayList<Player>();

		players.add(new Player(1000, "Usuario 1000", null, "Interest 1, interest 2", "user1000@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", false));

		players.add(new Player(1001, "Usuario 1001", null, "Interest 1, interest 2", "user1001@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", false));

		players.add(new Player(1004, "Usuario 1004", null, "Interest 1, interest 2", "user1004@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", false));

		players.add(new Player(1005, "Usuario 1005", null, "Interest 1, interest 2", "user1005@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", false));

		return players;
	}

	public static Player predefinedFindPlayer() {
		return new Player(999, "Pepe", null, "Interest 1, interest 2", "pepe@gmail.com", "Location", "Province",
				"FavouriteList", "FavouriteTeamList", true);
	}

	public static List<Player> playerListByUser() {
		List<Player> toRet = new ArrayList<Player>();
		Team t = new Team(999, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true,
				18, 27, false, false, null, null, existentUser());
		Player p1 = new Player(1, "Pepe", null, "Interest 1, interest 2", "pepe@gmail.com", "Location", "Province",
				"FavouriteList", "FavouriteTeamList", true);
		Player p2 = new Player(2, "Antonio", null, "Interest 1, interest 2", "antonio@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", true);
		List<Team> tl= new ArrayList<>();
		tl.add(t);
		p1.setPlayerTeams(tl);
		p2.setPlayerTeams(tl);
		p1.setPlayerManagedByUser(existentUser());
		p2.setPlayerManagedByUser(existentUser());
		toRet.add(p1);
		toRet.add(p2);
		t.setTeamPlayers(toRet);
		return toRet;
	}

	public static Team predefinedTeam() {
		return new Team(999, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true, 18,
				27, false, false, null, null, existentUser());
	}

	public static String getLocality() {
		return "Ourense";
	}
	
	public static List<Player> playerListFromTeam() {
		List<Player> toRet = new ArrayList<Player>();
		Team t = new Team(998, "Rapido de Bouzas F.C.D", "RdB FC", "Rapido de Bouzas F.C.D", SportType.FOOTBALL7, true,
				18, 27, false, false, null, null, existentUser());
		Player p1 = new Player(1, "Pepe", null, "Interest 1, interest 2", "pepe@gmail.com", "Location", "Province",
				"FavouriteList", "FavouriteTeamList", true);
		List<Team> tl= new ArrayList<>();
		tl.add(t);
		p1.setPlayerTeams(tl);
		p1.setPlayerManagedByUser(existentUser());
		toRet.add(p1);
		t.setTeamPlayers(toRet);
		return toRet;
	}
}
