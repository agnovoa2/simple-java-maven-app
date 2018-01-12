package ligaaas.teamc.domain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import ligaaas.teamc.domain.Competition;
import ligaaas.teamc.domain.CompetitionType;
import ligaaas.teamc.domain.EventState;
import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.Round;
import ligaaas.teamc.domain.SportType;
import ligaaas.teamc.domain.User;

public class RoundsDataset {

	public static Round predefinedRound() {
		HeadQuarter h = new HeadQuarter(888, "A Madroa", "Instalaciones del Rapido de Bouzas",
				"Avenida de la Coruña 12", "Bouzas", "Pontevedra", false);
		Competition c = new Competition(888, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		return new Round(999, 5, new GregorianCalendar(1991, 07, 02).getTime(), EventState.CANCELED, "For testing",
				new ArrayList<String>(), h, c);
	}

	public static Round modifiedRound() {
		HeadQuarter h = new HeadQuarter(888, "A Madroa", "Instalaciones del Rapido de Bouzas",
				"Avenida de la Coruña 12", "Bouzas", "Pontevedra", false);
		Competition c = new Competition(888, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		return new Round(999, 6, new GregorianCalendar(1991, 07, 02).getTime(), EventState.CANCELED, "For testing 2",
				new ArrayList<String>(), h, c);
	}

	public static Round createdRound() {
		HeadQuarter h = new HeadQuarter(888, "A Madroa", "Instalaciones del Rapido de Bouzas",
				"Avenida de la Coruña 12", "Bouzas", "Pontevedra", false);
		Competition c = new Competition(888, "Liga gallega", "LigaG", "Descripción de la liga gallega",
				SportType.FOOTBALL11, CompetitionType.SIMPLE, true, 18, 27, false, false, null, null, null);
		return new Round(1, 5, new GregorianCalendar(1991, 07, 02).getTime(), EventState.CANCELED, "For testing",
				new ArrayList<String>(), h, c);
	}

	public static User existentUser() {
		return new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}
}
