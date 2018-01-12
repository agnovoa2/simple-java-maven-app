package ligaaas.teamc.domain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.CompetitionType;
import ligaaas.teamc.domain.EventState;
import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.Match;
import ligaaas.teamc.domain.Round;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.Team;
import ligaaas.teamc.domain.User;

public class MatchesDataset {
	public static Match createdMatch() {
		HeadQuarter h = new HeadQuarter(700, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Bouzas", "Pontevedra", false);
		Team t = new Team(900, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true, 18, 27, false, false, h, null, null);
		Team t2 = new Team(800, "Rapido de Bouzas 2 F.C.", "RdB 2 FC", "Rapido de Bouzas 2 F.C.", SportType.FOOTBALL11, true, 18, 27, false, false, h, null, null);
		Competition c = new Competition(600, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		Round r = new Round(500, 5, new GregorianCalendar(1991, 07, 02).getTime(), EventState.CANCELED, "For testing",
				new ArrayList<String>(), h, c);
		return new Match(1, t, t2, new GregorianCalendar(1991, 07, 02).getTime(), h, EventState.CANCELED, 0, 0, 0, "A Match", new ArrayList<String>(), r);
	}
	
	public static Match predefinedMatch() {
		HeadQuarter h = new HeadQuarter(700, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Bouzas", "Pontevedra", false);
		Team t = new Team(900, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true, 18, 27, false, false, h, null, null);
		Team t2 = new Team(800, "Rapido de Bouzas 2 F.C.", "RdB 2 FC", "Rapido de Bouzas 2 F.C.", SportType.FOOTBALL11, true, 18, 27, false, false, h, null, null);
		Competition c = new Competition(600, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		Round r = new Round(500, 5, new GregorianCalendar(1991, 07, 02).getTime(), EventState.CANCELED, "For testing",
				new ArrayList<String>(), h, c);
		return new Match(400, t, t2, new GregorianCalendar(1991, 07, 02).getTime(), h, EventState.CANCELED, 0, 0, 0, "A Match", new ArrayList<String>(), r);
	}
	
	public static Match modifiedMatch() {
		HeadQuarter h = new HeadQuarter(700, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Bouzas", "Pontevedra", false);
		Team t = new Team(900, "Rapido de Bouzas F.C.", "RdB FC", "Rapido de Bouzas F.C.", SportType.FOOTBALL11, true, 18, 27, false, false, h, null, null);
		Team t2 = new Team(800, "Rapido de Bouzas 2 F.C.", "RdB 2 FC", "Rapido de Bouzas 2 F.C.", SportType.FOOTBALL11, true, 18, 27, false, false, h, null, null);
		Competition c = new Competition(600, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		Round r = new Round(500, 5, new GregorianCalendar(1991, 07, 02).getTime(), EventState.CANCELED, "For testing",
				new ArrayList<String>(), h, c);
		return new Match(400, t, t2, new GregorianCalendar(1991, 07, 02).getTime(), h, EventState.CANCELED, 0, 0, 0, "A Match 2", new ArrayList<String>(), r);
	}
	
	public static User existentUser() {
		return new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}
}
