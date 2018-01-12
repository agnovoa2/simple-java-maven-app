package ligaaas.teamc.domain.entities;

import java.util.Date;

import ligaaas.teamc.domain.HeadQuarter;
import ligaaas.teamc.domain.User;

public class HeadQuartersDataset {

	public static User userWithHeadQuarter() {
		return new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}

	public static User anotherUser() {
		return new User(998, "mmrodriguez2", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}

	public static HeadQuarter predefinedHeadQuarter() {
		return new HeadQuarter(700, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Bouzas", "Pontevedra", false);
	}

	public static HeadQuarter createdHeadQuarter() {
		return new HeadQuarter(1, "Almendroa", "Instalaciones del Rapido de torremolinos", "Avenida de la Ciudad",
				"Couto", "Ourense", false);
	}

	public static HeadQuarter modifiedHeadQuarter() {
		return new HeadQuarter(700, "A Madroa", "Instalaciones del Rapido de Bouzas", "Avenida de la Coruña 12",
				"Bouzas", "Ourense", false);
	}
}
