package ligaaas.teamc.domain;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private long userId;
	private String userLogin;
	private String userName;
	private String userSurname;
	private String userPassword;
	private Date userBirthdate;
	private String userIdentificationDocument;
	private String userResidence;
	private String userLocality;
	private String userProvince;
	private String userCountry;
	private String userPhone;
	private String userTwitter;
	private String userFacebook;
	private String userInstagram;
	private String userEmail;
	private boolean userConfirmed;
	private boolean userDeleted;
	private String userToken;
	private Player userPlayer;
	private List<Player> userPlayers = new ArrayList<>();
	private HeadQuarter userHeadQuarter;
	private List<HeadQuarter> userHeadQuarters = new ArrayList<>();

	private long newUserId;
	private String newUserLogin;
	private String newUserName;
	private String newUserSurname;
	private String newUserPassword;
	private Date newUserBirthdate;
	private String newUserIdentificationDocument;
	private String newUserResidence;
	private String newUserLocality;
	private String newUserProvince;
	private String newUserCountry;
	private String newUserPhone;
	private String newUserTwitter;
	private String newUserFacebook;
	private String newUserInstagram;
	private String newUserEmail;
	private boolean newUserConfirmed;
	private boolean newUserDeleted;
	private String newUserToken;
	private Player newUserPlayer;
	private List<Player> newUserPlayers = new ArrayList<>();
	private HeadQuarter newUserHeadQuarter;
	private List<HeadQuarter> newUserHeadQuarters = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		userId = 1;
		userLogin = "jvsantamarina";
		userName = "Javier";
		userSurname = "Villalobos Santamarina";
		userPassword = "passwordJavier1";
		userBirthdate = new SimpleDateFormat("dd/MM/YYYY").parse("24/09/1994");
		userIdentificationDocument = "34631717L";
		userResidence = "Rua Esgos";
		userLocality = "Ourense";
		userProvince = "Ourense";
		userCountry = "España";
		userPhone = "+34659797958";
		userTwitter = "https://twitter.com/jvsantamarina";
		userFacebook = "https://www.facebook.com/jvsantamarina";
		userInstagram = "https://www.instagram.com/jvsantamarina";
		userEmail = "jvsantamarina@gmail.es";
		userConfirmed = true;
		userDeleted = false;
		userToken = "tokenTest";
		userPlayer = new Player(1, "Pepe", new byte[] {}, "Interest 1, interest 2", "pepe@gmail.com", "Location",
				"Province", "FavouriteList", "FavouriteTeamList", true);
		userPlayers.add(userPlayer);
		userHeadQuarter = new HeadQuarter(1, "A Madroa", "Instalaciones del Rapido de Bouzas",
				"Avenida de la Coruña 12", "Bouzas", "Pontevedra", false);
		userHeadQuarters.add(userHeadQuarter);

		newUserId = 5;
		newUserLogin = "dvvilar";
		newUserName = "Diego";
		newUserSurname = "Villanueva Vilar";
		newUserPassword = "passwordDiego1";
		newUserBirthdate = new SimpleDateFormat("dd/MM/YYYY").parse("12/08/1992");
		newUserIdentificationDocument = "34631717D";
		newUserResidence = "Avd. de Portugal";
		newUserLocality = "Lugo";
		newUserProvince = "Lugo";
		newUserCountry = "España";
		newUserPhone = "+35658787859";
		newUserTwitter = "https://twitter.com/dvvilar";
		newUserFacebook = "https://www.facebook.com/dvvilar";
		newUserInstagram = "https://www.instagram.com/dvvilar";
		newUserEmail = "dvvilar@gmail.es";
		newUserConfirmed = false;
		newUserDeleted = true;
		newUserToken = "newTokenTest";
		newUserPlayer = new Player(2, "Antonio", new byte[] {}, "Interest 1, interest 2", "antonio@gmail.com",
				"Location", "Province", "FavouriteList", "FavouriteTeamList", true);
		newUserPlayers.add(newUserPlayer);
		newUserHeadQuarter = new HeadQuarter(2, "A Madroa", "Instalaciones del Lento de Bouzas",
				"Avenida de la Coruña 12", "Bouzas", "Pontevedra", false);
		newUserHeadQuarters.add(newUserHeadQuarter);
	}

	@Test
	public void testUserConstructor() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		assertThat(userTest.getUserId(), is(equalTo(userId)));
		assertThat(userTest.getUserLogin(), is(equalTo(userLogin)));
		assertThat(userTest.getUserName(), is(equalTo(userName)));
		assertThat(userTest.getUserSurname(), is(equalTo(userSurname)));
		assertThat(userTest.getUserPassword(), is(equalTo(userPassword)));
		assertThat(userTest.getUserBirthdate(), is(equalTo(userBirthdate)));
		assertThat(userTest.getUserIdentificationDocument(), is(equalTo(userIdentificationDocument)));
		assertThat(userTest.getUserResidence(), is(equalTo(userResidence)));
		assertThat(userTest.getUserLocality(), is(equalTo(userLocality)));
		assertThat(userTest.getUserProvince(), is(equalTo(userProvince)));
		assertThat(userTest.getUserCountry(), is(equalTo(userCountry)));
		assertThat(userTest.getUserPhone(), is(equalTo(userPhone)));
		assertThat(userTest.getUserTwitter(), is(equalTo(userTwitter)));
		assertThat(userTest.getUserFacebook(), is(equalTo(userFacebook)));
		assertThat(userTest.getUserInstagram(), is(equalTo(userInstagram)));
		assertThat(userTest.getUserEmail(), is(equalTo(userEmail)));
		assertThat(userTest.isUserConfirmed(), is(equalTo(userConfirmed)));
		assertThat(userTest.getUserDeleted(), is(equalTo(userDeleted)));
		assertThat(userTest.getUserToken(), is(equalTo(userToken)));
	}

	@Test
	public void testSetUserId() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserId(newUserId);
		assertThat(userTest.getUserId(), is(equalTo(newUserId)));
	}

	@Test(expected = NullPointerException.class)
	public void testUserStringUserLoginNull() {
		User userTest = new User();
		userTest.setUserLogin(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserLoginTooShort() {
		User userTest = new User();
		userTest.setUserLogin("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserLoginTooLong() {
		User userTest = new User();
		userTest.setUserLogin(repeat('a', 16));
	}

	@Test
	public void testSetUserLogin() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserLogin(newUserLogin);
		assertThat(userTest.getUserLogin(), is(equalTo(newUserLogin)));
	}

	@Test(expected = NullPointerException.class)
	public void testUserStringUserNameNull() {
		User userTest = new User();
		userTest.setUserName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserNameTooShort() {
		User userTest = new User();
		userTest.setUserName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserNameTooLong() {
		User userTest = new User();
		userTest.setUserName(repeat('a', 61));
	}

	@Test(expected = NullPointerException.class)
	public void testUserStringUserSurnameNull() {
		User userTest = new User();
		userTest.setUserSurname(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserSurnameTooShort() {
		User userTest = new User();
		userTest.setUserSurname("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserSurnameTooLong() {
		User userTest = new User();
		userTest.setUserSurname(repeat('a', 61));
	}

	@Test
	public void testSetUserSurname() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserSurname(newUserSurname);
		assertThat(userTest.getUserSurname(), is(equalTo(newUserSurname)));
	}

	@Test(expected = NullPointerException.class)
	public void testUserStringUserPasswordNull() {
		User userTest = new User();
		userTest.setUserPassword(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserPasswordTooShort() {
		User userTest = new User();
		userTest.setUserPassword("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserPasswordTooLong() {
		User userTest = new User();
		userTest.setUserPassword(repeat('a', 61));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserPasswordWrongRegexp() {
		User userTest = new User();
		userTest.setUserPassword("aaaaaaaa");
	}

	@Test
	public void testSetUserPassword() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserPassword(newUserPassword);
		assertThat(userTest.getUserPassword(), is(equalTo(newUserPassword)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserDateUserBirthdateFuture() throws ParseException {
		User userTest = new User();
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();

		userTest.setUserBirthdate(date);
	}

	@Test
	public void testSetUserBirthdate() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserBirthdate(newUserBirthdate);
		assertThat(userTest.getUserBirthdate(), is(equalTo(newUserBirthdate)));
	}

	@Test
	public void testSetUserBirthdateNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserBirthdate(null);
		assertThat(userTest.getUserBirthdate(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserIdentificationDocumentTooShort() {
		User userTest = new User();
		userTest.setUserIdentificationDocument("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserIdentificationDocumentTooLong() {
		User userTest = new User();
		userTest.setUserIdentificationDocument(repeat('a', 11));
	}

	@Test
	public void testSetUserIdentificationDocument() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserIdentificationDocument(newUserIdentificationDocument);
		assertThat(userTest.getUserIdentificationDocument(), is(equalTo(newUserIdentificationDocument)));
	}

	@Test
	public void testSetUserIdentificationDocumentNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserIdentificationDocument(null);
		assertThat(userTest.getUserIdentificationDocument(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserResidenceTooLong() {
		User userTest = new User();
		userTest.setUserResidence(repeat('a', 101));
	}

	@Test
	public void testSetUserResidence() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserResidence(newUserResidence);
		assertThat(userTest.getUserResidence(), is(equalTo(newUserResidence)));
	}

	@Test
	public void testSetUserResidenceNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserResidence(null);
		assertThat(userTest.getUserResidence(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserLocalityTooLong() {
		User userTest = new User();
		userTest.setUserLocality(repeat('a', 101));
	}

	@Test
	public void testSetUserLocality() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserLocality(newUserLocality);
		assertThat(userTest.getUserLocality(), is(equalTo(newUserLocality)));
	}

	@Test
	public void testSetUserLocalityNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserLocality(null);
		assertThat(userTest.getUserLocality(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserProvinceTooLong() {
		User userTest = new User();
		userTest.setUserProvince(repeat('a', 101));
	}

	@Test
	public void testSetUserProvince() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserProvince(newUserProvince);
		assertThat(userTest.getUserProvince(), is(equalTo(newUserProvince)));
	}

	@Test
	public void testSetUserProvinceNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserProvince(null);
		assertThat(userTest.getUserProvince(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserCountryTooLong() {
		User userTest = new User();
		userTest.setUserCountry(repeat('a', 101));
	}

	@Test
	public void testSetUserCountry() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserCountry(newUserCountry);
		assertThat(userTest.getUserCountry(), is(equalTo(newUserCountry)));
	}

	@Test
	public void testSetUserCountryNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserCountry(null);
		assertThat(userTest.getUserCountry(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserPhoneWrongRegexp() {
		User userTest = new User();
		userTest.setUserPhone("34123121212");
	}

	@Test
	public void testSetUserPhone() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserPhone(newUserPhone);
		assertThat(userTest.getUserPhone(), is(equalTo(newUserPhone)));
	}

	@Test
	public void testSetUserPhoneNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserPhone(null);
		assertThat(userTest.getUserPhone(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserTwitterTooShort() {
		User userTest = new User();
		userTest.setUserTwitter("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserTwitterTooLong() {
		User userTest = new User();
		userTest.setUserTwitter(repeat('a', 101));
	}

	@Test
	public void testSetUserTwitter() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserTwitter(newUserTwitter);
		assertThat(userTest.getUserTwitter(), is(equalTo(newUserTwitter)));
	}

	@Test
	public void testSetUserTwitterNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserTwitter(null);
		assertThat(userTest.getUserTwitter(), is(nullValue()));
	}

	@Test
	public void testSetUserName() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserName(newUserName);
		assertThat(userTest.getUserName(), is(equalTo(newUserName)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserFacebookTooShort() {
		User userTest = new User();
		userTest.setUserFacebook("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserFacebookTooLong() {
		User userTest = new User();
		userTest.setUserFacebook(repeat('a', 101));
	}

	@Test
	public void testSetUserFacebook() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserFacebook(newUserFacebook);
		assertThat(userTest.getUserFacebook(), is(equalTo(newUserFacebook)));
	}

	@Test
	public void testSetUserFacebookNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserFacebook(null);
		assertThat(userTest.getUserFacebook(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserInstagramTooShort() {
		User userTest = new User();
		userTest.setUserInstagram("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserInstagramTooLong() {
		User userTest = new User();
		userTest.setUserInstagram(repeat('a', 101));
	}

	@Test
	public void testSetUserInstagram() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserInstagram(newUserInstagram);
		assertThat(userTest.getUserInstagram(), is(equalTo(newUserInstagram)));
	}

	@Test
	public void testSetUserInstagramNull() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserInstagram(null);
		assertThat(userTest.getUserInstagram(), is(nullValue()));
	}

	@Test(expected = NullPointerException.class)
	public void testUserStringUserEmailNull() {
		User userTest = new User();
		userTest.setUserEmail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserEmailTooShort() {
		User userTest = new User();
		userTest.setUserEmail("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserEmailTooLong() {
		User userTest = new User();
		userTest.setUserEmail(repeat('a', 41));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUserStringUserEmailWrongRegexp() {
		User userTest = new User();
		userTest.setUserEmail(repeat('a', 10));
	}

	@Test
	public void testSetUserEmail() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserEmail(newUserEmail);
		assertThat(userTest.getUserEmail(), is(equalTo(newUserEmail)));
	}

	@Test
	public void testSetUserConfirmed() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserConfirmed(newUserConfirmed);
		assertThat(userTest.isUserConfirmed(), is(equalTo(newUserConfirmed)));
	}

	@Test(expected = NullPointerException.class)
	public void testUserBooleanUserDeletedNull() {
		User userTest = new User();
		userTest.setUserDeleted(null);
	}

	@Test
	public void testSetUserDeleted() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserDeleted(newUserDeleted);
		assertThat(userTest.getUserDeleted(), is(equalTo(newUserDeleted)));
	}
	
	@Test
	public void testSetUserToken() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserToken(newUserToken);
		assertThat(userTest.getUserToken(), is(equalTo(newUserToken)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetUserManagedPlayersNull() {
		User userTest = new User();
		userTest.setUserPlayers(null);
	}

	@Test
	public void testSetUserPlayers() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserPlayers(userPlayers);
		assertThat(userTest.getUserPlayers(), is(equalTo(userPlayers)));
		userTest.setUserPlayers(newUserPlayers);
		assertThat(userTest.getUserPlayers(), is(equalTo(newUserPlayers)));
	}

	@Test
	public void testSetUserPlayer() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserPlayer(userPlayer);
		assertThat(userTest.getUserPlayer(), is(equalTo(userPlayer)));
		userTest.setUserPlayer(newUserPlayer);
		assertThat(userTest.getUserPlayer(), is(equalTo(newUserPlayer)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetUserManagedHeadQuartersNull() {
		User userTest = new User();
		userTest.setUserHeadQuarters(null);
	}

	@Test
	public void testSetUserHeadQuarters() {
		User userTest = new User(userId, userLogin, userName, userSurname, userPassword, userBirthdate,
				userIdentificationDocument, userResidence, userLocality, userProvince, userCountry, userPhone,
				userTwitter, userFacebook, userInstagram, userEmail, userConfirmed, userDeleted, userToken);
		userTest.setUserHeadQuarters(userHeadQuarters);
		assertThat(userTest.getUserHeadQuarters(), is(equalTo(userHeadQuarters)));
		userTest.setUserHeadQuarters(newUserHeadQuarters);
		assertThat(userTest.getUserHeadQuarters(), is(equalTo(newUserHeadQuarters)));
	}

}
