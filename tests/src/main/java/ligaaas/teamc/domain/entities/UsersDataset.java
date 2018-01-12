package ligaaas.teamc.domain.entities;

import java.util.Date;

import ligaaas.teamc.domain.User;

public class UsersDataset {
	public static User existentUser() {
		return new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}

	public static User modifiedUser() {
		return new User(999, "mmrodriguez", "Manolo", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}

	public static User registerUser() {
		return new User(1, "mmrodriguez2", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233B", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112232",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "dnfgalaxy@yopmail.com", false, false,
				"17123920472111775202122309700");
	}

	public static User registerUserWithExistingLogin() {
		return new User(1, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233B", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112232",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez2@gmail.es", false, false, "token");
	}

	public static User registerUserWithExistingEmail() {
		return new User(1, "mmrodriguez2", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233B", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112232",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", false, false, "token");
	}

	public static User registerUserError() {
		return new User(1, "mmrodriguez2", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez2@gmail.es", false, false, "token");
	}

	public static String existentUserHash() {
		return "3323331197229756452122309700";
	}

	public static User existentUserActivated() {
		return new User(999, "mmrodriguez", "Manuel", "Manso Rodriguez", "abcd1234..", new Date(946684861000L),
				"11122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988112233",
				"https://twitter.com/mmrodriguez", "https://facebook.com/mmrodriguez",
				"https://instagram.com/mmrodriguez", "mmrodriguez@gmail.es", true, false, "token");
	}

	public static User activatedUser() {
		return new User(1999, "pprodriguez", "Patricio", "Pastos Rodriguez", "abcd1234..", new Date(946684861000L),
				"91122233A", "Avda Pontevedra 8-1", "Ourense", "Ourense", "España", "+34988162233",
				"https://twitter.com/pprodriguez", "https://facebook.com/pprodriguez",
				"https://instagram.com/pprodriguez", "pprodriguez@gmail.es", true, false, "token");
	}
}
