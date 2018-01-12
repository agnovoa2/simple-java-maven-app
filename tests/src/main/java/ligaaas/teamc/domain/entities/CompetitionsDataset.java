package ligaaas.teamc.domain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.CompetitionType;
import ligaaas.teamc.domain.Contact;
import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.User;

public class CompetitionsDataset {

	public static Competition predefinedCompetition() {
		return new Competition(999, "Liga gallega", "LigaG", "Descripción de la liga gallega", SportType.FOOTBALL11,
				CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
	}

	public static Competition modifiedCompetition() {
		return new Competition(999, "Liga gallega 2", "LigaG 2", "Descripción de la liga gallega 2",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
	}

	public static Competition createdCompetition() {
		return new Competition(1, "Liga gallega 3", "LigaG 3", "Descripción de la liga gallega 3", SportType.FOOTBALL11,
				CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
	}

	public static Competition competitionWithUser() {
		User u = new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
		return new Competition(998, "Liga gallega 2", "LigaG 2", "Descripción de la liga gallega 2",
				SportType.FOOTBALL7, CompetitionType.DOUBLE, true, 18, 27, false, false, null, u, null);
	}

	public static Competition competitionWithTeams() {
		List<Team> teams = new ArrayList<Team>();
		Team t1 = new Team(999, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true,
				18, 27, false, false, null, null, null);
		Team t2 = new Team(998, "Rapido de Bouzas F.C. 2", "RdB FC 2", "Rapido de Bouzas F.C. 2", SportType.FOOTBALL11,
				true, 18, 27, false, false, null, null, null);
		teams.add(t1);
		teams.add(t2);

		Competition toRet = new Competition(997, "Liga gallega 3", "LigaG 3", "Descripción de la liga gallega 3",
				SportType.VIDEOGAMES, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		toRet.setCompetitionTeams(teams);
		return toRet;
	}

	public static List<Team> restTeams() {
		List<Team> teams = new ArrayList<Team>();
		Team t1 = new Team(997, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true,
				18, 27, true, false, null, null, null);
		teams.add(t1);

		return teams;
	}

	public static List<Competition> restCompetitions() {
		List<Competition> competitions = new ArrayList<>();
		User u = new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "d23f11aa47a86278435e2f853fed8d3d",
				new Date(946684861000L), "11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España",
				"+34988112233", "https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");

		Contact c = new Contact(1, "rapidodebouzas@gmail.gal", "http://www.rapidodebouzas.com/", "+34988012345",
				"https://twitter.com/rapidobouzas", "https://es-es.facebook.com/CF-RÁPIDO-DE-BOUZAS-155018914560146/",
				null);
		HeadQuarter h1 = new HeadQuarter(1, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Ourense", "Pontevedra", false);
		h1.setHeadQuarterManagedByUser(u);
		HeadQuarter h2 = new HeadQuarter(700, "A Madroa", "Instalaciones del Rapido de Bouzas",
				"Avenida de la Coruña 12", "Bouzas", "Pontevedra", false);
		h2.setHeadQuarterManagedByUser(u);
		Competition c1 = new Competition(1, "Liga gallega 2", "LigaG 2", "Descripción de la liga gallega 2",
				SportType.FOOTBALL7, CompetitionType.DOUBLE, true, 18, 27, true, false, h1, u, c);
		Competition c2 = new Competition(2, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, true, false, h1, u, null);
		Competition c3 = new Competition(3, "Liga gallega 3", "LigaG 3", "Descripción de la liga gallega 3",
				SportType.VIDEOGAMES, CompetitionType.SIMPLE, true, 18, 27, true, false, h2, u, c);
		competitions.add(c1);
		competitions.add(c2);
		competitions.add(c3);

		return competitions;
	}

	public static Competition findBySportTypeCompetition() {
		User u = new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
		Contact c = new Contact(1, "rapidodebouzas@gmail.gal", "http://www.rapidodebouzas.com/", "+34988012345",
				"https://twitter.com/rapidobouzas", "https://es-es.facebook.com/CF-RÁPIDO-DE-BOUZAS-155018914560146/",
				null);
		HeadQuarter h1 = new HeadQuarter(1, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Ourense", "Pontevedra", false);
		h1.setHeadQuarterManagedByUser(u);
		return new Competition(1, "Liga gallega 2", "LigaG 2", "Descripción de la liga gallega 2", SportType.FOOTBALL7,
				CompetitionType.DOUBLE, true, 18, 27, true, false, h1, u, c);
	}

	public static Competition findByLocalityTypeCompetition() {
		User u = new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
		HeadQuarter h = new HeadQuarter(700, "A Madroa", "Instalaciones del Rapido de Bouzas",
				"Avenida de la Coruña 12", "Bouzas", "Pontevedra", false);
		return new Competition(1, "Liga gallega 3", "LigaG 3", "Descripción de la liga gallega 3", SportType.VIDEOGAMES,
				CompetitionType.SIMPLE, true, 18, 27, true, false, h, u, null);
	}

}
