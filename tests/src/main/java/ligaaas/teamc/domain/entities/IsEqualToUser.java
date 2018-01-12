package ligaaas.teamc.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.dgss.teamc.ligaaas.entities.IsEqualToEntity;
import ligaaas.teamc.domain.User;

public class IsEqualToUser extends IsEqualToEntity<User> {
	private final boolean checkRelations;

	public IsEqualToUser(User user, boolean checkRelations) {
		super(user);
		this.checkRelations = checkRelations;
	}

	@Override
	protected boolean matchesSafely(User actual) {
		this.clearDescribeTo();

		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("userLogin", User::getUserLogin, actual)
					&& checkAttribute("userName", User::getUserName, actual)
					&& checkAttribute("userSurname", User::getUserSurname, actual)
					&& checkAttribute("userPassword", User::getUserPassword, actual)
					&& checkAttribute("userBirthdate", User::getUserBirthdate, actual)
					&& checkAttribute("userIdentificationDocument", User::getUserIdentificationDocument, actual)
					&& checkAttribute("userResidence", User::getUserResidence, actual)
					&& checkAttribute("userLocality", User::getUserLocality, actual)
					&& checkAttribute("userProvince", User::getUserProvince, actual)
					&& checkAttribute("userCountry", User::getUserCountry, actual)
					&& checkAttribute("userPhone", User::getUserPhone, actual)
					&& checkAttribute("userTwitter", User::getUserTwitter, actual)
					&& checkAttribute("userFacebook", User::getUserFacebook, actual)
					&& checkAttribute("userInstagram", User::getUserInstagram, actual)
					&& checkAttribute("userEmail", User::getUserEmail, actual)
					&& checkAttribute("userConfirmed", User::isUserConfirmed, actual)
					&& checkAttribute("userToken", User::getUserToken, actual);
					//&& checkAttribute("userPlayers", User::getUserPlayers, actual)
					//&& checkAttribute("userPlayer", User::getUserPlayer, actual);
			// && (!this.checkRelations || checkAttribute("owner", Pet::getOwner, actual,
			// IsEqualToOwner::equalToOwnerWithoutRelations));
		}
	}

	@Factory
	public static IsEqualToUser equalToUser(User user) {
		return new IsEqualToUser(user, true);
	}

	@Factory
	public static IsEqualToUser equalToUserWithoutRelations(User user) {
		return new IsEqualToUser(user, false);
	}

	@Factory
	public static Matcher<Iterable<? extends User>> containsUsersInAnyOrder(User... users) {
		return containsEntityInAnyOrder(IsEqualToUser::equalToUser, users);
	}

	@Factory
	public static Matcher<Iterable<? extends User>> containsUsersWithoutRelationsInAnyOrder(User... users) {
		return containsEntityInAnyOrder(IsEqualToUser::equalToUserWithoutRelations, users);
	}

	@Factory
	public static Matcher<Iterable<? extends User>> containsUsersInAnyOrder(Iterable<User> users) {
		return containsEntityInAnyOrder(IsEqualToUser::equalToUser, users);
	}

	@Factory
	public static Matcher<Iterable<? extends User>> containsUsersWithoutRelationsInAnyOrder(Iterable<User> users) {
		return containsEntityInAnyOrder(IsEqualToUser::equalToUserWithoutRelations, users);
	}
}
