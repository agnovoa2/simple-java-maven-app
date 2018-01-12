package ligaaas.teamc.domain;

import static java.util.Objects.requireNonNull;
import static ligaaas.teamc.domain.RegexpTemplates.EMAIL;
import static ligaaas.teamc.domain.RegexpTemplates.PASSWORD;
import static ligaaas.teamc.domain.RegexpTemplates.PHONE;
import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.matchesPattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A User
 * 
 * @author teamC
 *
 */
@Entity
public class User {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@NotNull
	@Size(min = 5, max = 15)
	@Column(unique = true)
	private String userLogin;

	@NotNull
	@Size(min = 5, max = 60)
	private String userName;

	@NotNull
	@Size(min = 5, max = 60)
	private String userSurname;

	@NotNull
	@Size(min = 8, max = 60)
	@Pattern(regexp = RegexpTemplates.PASSWORD)
	private String userPassword;

	@Past
	private Date userBirthdate;

	@Size(min = 9, max = 10)
	@Column(unique = true)
	private String userIdentificationDocument;

	@Size(max = 100)
	private String userResidence;

	@Size(max = 100)
	private String userLocality;

	@Size(max = 100)
	private String userProvince;

	@Size(max = 100)
	private String userCountry;

	@Pattern(regexp = RegexpTemplates.PHONE)
	private String userPhone;

	@Size(min = 10, max = 100)
	private String userTwitter;

	@Size(min = 10, max = 100)
	private String userFacebook;

	@Size(min = 10, max = 100)
	private String userInstagram;

	@NotNull
	@Size(min = 10, max = 100)
	@Pattern(regexp = RegexpTemplates.EMAIL)
	@Column(unique = true)
	private String userEmail;

	@NotNull
	private boolean userConfirmed;

	@NotNull
	private boolean userDeleted;

	private String userToken;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "playerManagedByUser")
	private List<Player> userPlayers = new ArrayList<Player>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "headQuarterManagedByUser")
	private List<HeadQuarter> userHeadQuarters = new ArrayList<HeadQuarter>();

	@OneToOne
	private Player userPlayer;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new {@code User}.
	 *
	 * @param userId
	 *            the user id. This parameter must be a non empty and non
	 *            {@code null}
	 * @param userLogin
	 *            the user login. This parameter must be a non empty and non
	 *            {@code null}
	 * @param userName
	 *            the user name. This parameter cannot be {@code null} and have a
	 *            length between 5 and 60 characters.
	 * @param userSurname
	 *            the user surname. This parameter cannot be {@code null} and have a
	 *            length between 5 and 60 characters.
	 * @param userPassword
	 *            the user password. This parameter cannot be {@code null} and have
	 *            a length between 8 and 60 characters. It also has to comply with
	 *            {@code RegexTemplates.PASSWORD}
	 * @param userBirthdate
	 *            the user birthdate.
	 * @param userIdentificationDocument
	 *            the user identification document. This parameter has to have a
	 *            length between 9 and 10 characters.
	 * @param userResidence
	 *            the user residence. This parameter has a limit of 100 characters
	 * @param userLocality
	 *            the user locality. This parameter has a limit of 100 characters
	 * @param userProvince
	 *            the user province. This parameter has a limit of 100 characters
	 * @param userCountry
	 *            the user country. This parameter has a limit of 100 characters
	 * @param userPhone
	 *            the user phone. This parameter has to comply with
	 *            {@code RegexTemplates.PHONE}
	 * @param userTwitter
	 *            the user twitter. This parameter has to have between 10 and 100
	 *            characters
	 * @param userFacebook
	 *            the user facebook. This parameter has to have between 10 and 100
	 *            characters
	 * @param userInstagram
	 *            the user instagram. This parameter has to have between 10 and 100
	 *            characters
	 * @param userEmail
	 *            the user email. This parameter must be non {@code null}, to have
	 *            between 5 and 40 characters, and comply with
	 *            {@code RegexTemplates.EMAIL}
	 * @param userConfirmed
	 *            the user confirmed. This parameter must be non {@code null}
	 * @param userDeleted
	 *            if the user is logically deleted. This parameter must be a non
	 *            {@code null} boolean.
	 * @param userToken
	 *            the user token
	 * @throws NullPointerException
	 *             if a {@code null} value is passed as the value for any parameter.
	 * @throws IllegalArgumentException
	 *             if value provided for any parameter is not valid according to its
	 *             description.
	 */
	public User(long userId, String userLogin, String userName, String userSurname, String userPassword,
			Date userBirthdate, String userIdentificationDocument, String userResidence, String userLocality,
			String userProvince, String userCountry, String userPhone, String userTwitter, String userFacebook,
			String userInstagram, String userEmail, boolean userConfirmed, boolean userDeleted, String userToken) {
		setUserId(userId);
		setUserLogin(userLogin);
		setUserName(userName);
		setUserSurname(userSurname);
		setUserPassword(userPassword);
		setUserBirthdate(userBirthdate);
		setUserIdentificationDocument(userIdentificationDocument);
		setUserResidence(userResidence);
		setUserLocality(userLocality);
		setUserProvince(userProvince);
		setUserCountry(userCountry);
		setUserPhone(userPhone);
		setUserTwitter(userTwitter);
		setUserFacebook(userFacebook);
		setUserInstagram(userInstagram);
		setUserEmail(userEmail);
		setUserConfirmed(userConfirmed);
		setUserDeleted(userDeleted);
		setUserToken(userToken);
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the user login.
	 *
	 * @return the user login
	 */
	public String getUserLogin() {
		return userLogin;
	}

	/**
	 * Sets the user login.
	 *
	 * @param userLogin
	 *            the new user login
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserLogin(String userLogin) {
		requireNonNull(userLogin, "User Login cannot be null");
		inclusiveBetween(5, 15, userLogin.length(), "User Login must be between 5 and 15 characters long.");

		this.userLogin = userLogin;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the new user name
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserName(String userName) {
		requireNonNull(userName, "User Name cannot be null");
		inclusiveBetween(5, 60, userName.length(), "User Name must be between 5 and 60 characters long.");

		this.userName = userName;
	}

	/**
	 * Gets the user surname.
	 *
	 * @return the user surname
	 */
	public String getUserSurname() {
		return userSurname;
	}

	/**
	 * Sets the user surname.
	 *
	 * @param userSurname
	 *            the new user surname
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserSurname(String userSurname) {
		requireNonNull(userSurname, "User Surname cannot be null");
		inclusiveBetween(5, 60, userSurname.length(), "User Surname must be between 5 and 60 characters long.");

		this.userSurname = userSurname;
	}

	/**
	 * Gets the user password.
	 *
	 * @return the user password
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Sets the user password.
	 *
	 * @param userPassword
	 *            the new user password
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserPassword(String userPassword) {
		requireNonNull(userPassword, "User Password cannot be null");
		inclusiveBetween(8, 60, userPassword.length(), "User Password must be between 8 and 60 characters long.");
		matchesPattern(userPassword, PASSWORD);

		this.userPassword = userPassword;
	}

	/**
	 * Gets the user email.
	 *
	 * @return the user email
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the user email.
	 *
	 * @param userEmail
	 *            the new user email. This parameter must be a String with length
	 *            between 10 and 100
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserEmail(String userEmail) {
		requireNonNull(userEmail, "User Email cannot be null");
		inclusiveBetween(10, 100, userEmail.length(), "User Email must be between 10 and 100 characters long.");
		matchesPattern(userEmail, EMAIL);

		this.userEmail = userEmail;
	}

	/**
	 * Gets the user birthdate.
	 *
	 * @return the user birthdate
	 */
	public Date getUserBirthdate() {
		return userBirthdate;
	}

	/**
	 * Sets the user birthdate.
	 *
	 * @param userBirthdate
	 *            the new user birthdate
	 */
	public void setUserBirthdate(Date userBirthdate) {
		if (userBirthdate != null) {
			Date actualDate = new Date();
			isTrue(userBirthdate.before(actualDate), "Birthdate can't be a future date");
		}

		this.userBirthdate = userBirthdate;
	}

	/**
	 * Gets the user identification document.
	 *
	 * @return the user identification document
	 */
	public String getUserIdentificationDocument() {
		return userIdentificationDocument;
	}

	/**
	 * Sets the user identification document.
	 *
	 * @param userIdentificationDocument
	 *            the new user identification document
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserIdentificationDocument(String userIdentificationDocument) {
		if (userIdentificationDocument != null) {
			inclusiveBetween(9, 10, userIdentificationDocument.length(),
					"User IdentificationDocument must be between 9 and 10 characters long.");
		}

		this.userIdentificationDocument = userIdentificationDocument;
	}

	/**
	 * Gets the user residence.
	 *
	 * @return the user residence
	 */
	public String getUserResidence() {
		return userResidence;
	}

	/**
	 * Sets the user residence.
	 *
	 * @param userResidence
	 *            the new user residence
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserResidence(String userResidence) {
		if (userResidence != null) {
			isTrue(userResidence.length() < 101, "Residence length must be less than 100 characters long");
		}

		this.userResidence = userResidence;
	}

	/**
	 * Gets the user locality.
	 *
	 * @return the user locality
	 */
	public String getUserLocality() {
		return userLocality;
	}

	/**
	 * Sets the user locality.
	 *
	 * @param userLocality
	 *            the new user locality
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserLocality(String userLocality) {
		if (userLocality != null) {
			isTrue(userLocality.length() < 101, "Locality length must be less than 100 characters long");
		}
		this.userLocality = userLocality;
	}

	/**
	 * Gets the user province.
	 *
	 * @return the user province
	 */
	public String getUserProvince() {
		return userProvince;
	}

	/**
	 * Sets the user province.
	 *
	 * @param userProvince
	 *            the new user province
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserProvince(String userProvince) {
		if (userProvince != null) {
			isTrue(userProvince.length() < 101, "Province length must be less than 100 characters long");
		}
		this.userProvince = userProvince;
	}

	/**
	 * Gets the user country.
	 *
	 * @return the user country
	 */
	public String getUserCountry() {
		return userCountry;
	}

	/**
	 * Sets the user country.
	 *
	 * @param userCountry
	 *            the new user country
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserCountry(String userCountry) {
		if (userCountry != null) {
			isTrue(userCountry.length() < 101, "Province length must be less than 100 characters long");
		}
		this.userCountry = userCountry;
	}

	/**
	 * Gets the user phone.
	 *
	 * @return the user phone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * Sets the user phone.
	 *
	 * @param userPhone
	 *            the new user phone
	 * 
	 * @throws IllegalArgumentException
	 *             if the string is not a valid phone.
	 */
	public void setUserPhone(String userPhone) {
		if (userPhone != null) {
			matchesPattern(userPhone, PHONE);
		}

		this.userPhone = userPhone;
	}

	/**
	 * Gets the user twitter.
	 *
	 * @return the user twitter
	 */
	public String getUserTwitter() {
		return userTwitter;
	}

	/**
	 * Sets the user twitter.
	 *
	 * @param userTwitter
	 *            the new user twitter
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserTwitter(String userTwitter) {
		if (userTwitter != null) {
			inclusiveBetween(10, 100, userTwitter.length(), "User Twitter must be between 10 and 100 characters long.");
		}

		this.userTwitter = userTwitter;
	}

	/**
	 * Gets the user facebook.
	 *
	 * @return the user facebook
	 */
	public String getUserFacebook() {
		return userFacebook;
	}

	/**
	 * Sets the user facebook.
	 *
	 * @param userFacebook
	 *            the new user facebook
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserFacebook(String userFacebook) {
		if (userFacebook != null) {
			inclusiveBetween(10, 100, userFacebook.length(),
					"User Facebook must be between 10 and 100 characters long.");
		}
		this.userFacebook = userFacebook;
	}

	/**
	 * Gets the user instagram.
	 *
	 * @return the user instagram
	 */
	public String getUserInstagram() {
		return userInstagram;
	}

	/**
	 * Sets the user instagram.
	 *
	 * @param userInstagram
	 *            the new user instagram
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setUserInstagram(String userInstagram) {
		if (userInstagram != null) {
			inclusiveBetween(10, 100, userInstagram.length(),
					"User Instagram must be between 10 and 100 characters long.");
		}
		this.userInstagram = userInstagram;
	}

	/**
	 * Checks if is user confirmed.
	 *
	 * @return true, if is user confirmed
	 */
	public boolean isUserConfirmed() {
		return userConfirmed;
	}

	/**
	 * Sets the user confirmed.
	 *
	 * @param userConfirmed
	 *            the new user confirmed
	 */
	public void setUserConfirmed(boolean userConfirmed) {
		this.userConfirmed = userConfirmed;
	}

	/**
	 * Returns if the user is logically deleted.
	 * 
	 * @return if the user is logically deleted.
	 */
	public Boolean getUserDeleted() {
		return userDeleted;
	}

	/**
	 * Sets if user is deleted.
	 * 
	 * @param userDeleted
	 *            if user is deleted. This parameter must be a non empty and non
	 *            {@code null} boolean.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setUserDeleted(Boolean userDeleted) {
		requireNonNull(userDeleted, "userDeleted can't be null");
		this.userDeleted = userDeleted;
	}

	/**
	 * Gets the user token.
	 * 
	 * @return the user token
	 */
	public String getUserToken() {
		return userToken;
	}

	/**
	 * Sets the user taken.
	 * 
	 * @param userToken
	 *            the user token
	 */
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	/**
	 * Returns the user managed {@code Player}s.
	 * 
	 * @return the user managed {@code Player}s.
	 */
	public List<Player> getUserPlayers() {
		return userPlayers;
	}

	/**
	 * Sets the players managed of the user.
	 * 
	 * @param userPlayers
	 *            {@code List} of {@code Player}s that a user manages. This
	 *            parameter must be a non {@code null} {@code List} of
	 *            {@code Player}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setUserPlayers(List<Player> userPlayers) {
		requireNonNull(userPlayers, "User Players cannot be null");
		this.userPlayers = userPlayers;
	}

	/**
	 * Returns the player {@code Player}.
	 * 
	 * @return the userPlayer {@code Player}.
	 */
	public Player getUserPlayer() {
		return userPlayer;
	}

	/**
	 * Sets the player of the user.
	 * 
	 * @param userPlayer
	 *            The {@code Player} of the user.
	 */
	public void setUserPlayer(Player userPlayer) {
		this.userPlayer = userPlayer;
	}

	/**
	 * Returns the user managed list of {@link HeadQuarter}.
	 * 
	 * @return the user managed list of {@link HeadQuarter}.
	 */
	public List<HeadQuarter> getUserHeadQuarters() {
		return userHeadQuarters;
	}

	/**
	 * Sets the {@link HeadQuarter}s managed of the user.
	 * 
	 * @param userHeadQuarters
	 *            {@code List} of {@link HeadQuarter}s that a user manages. This
	 *            parameter must be a non {@code null} {@code List} of
	 *            {@link HeadQuarter}
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setUserHeadQuarters(List<HeadQuarter> userHeadQuarters) {
		requireNonNull(userHeadQuarters, "User HeadQuarters cannot be null");
		this.userHeadQuarters = userHeadQuarters;
	}

	/**
	 * Returns the hashCode of the User.
	 * 
	 * @return the hashCode of the User.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userBirthdate == null) ? 0 : userBirthdate.hashCode());
		result = prime * result + (userConfirmed ? 1231 : 1237);
		result = prime * result + ((userCountry == null) ? 0 : userCountry.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userFacebook == null) ? 0 : userFacebook.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((userIdentificationDocument == null) ? 0 : userIdentificationDocument.hashCode());
		result = prime * result + ((userInstagram == null) ? 0 : userInstagram.hashCode());
		result = prime * result + ((userLocality == null) ? 0 : userLocality.hashCode());
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result + ((userPhone == null) ? 0 : userPhone.hashCode());
		result = prime * result + ((userProvince == null) ? 0 : userProvince.hashCode());
		result = prime * result + ((userResidence == null) ? 0 : userResidence.hashCode());
		result = prime * result + ((userSurname == null) ? 0 : userSurname.hashCode());
		result = prime * result + ((userTwitter == null) ? 0 : userTwitter.hashCode());
		return result;
	}

	/**
	 *
	 * Returns if two classes are equals
	 * 
	 * @param obj
	 *            The object to compare to
	 * 
	 * @return if object and {@code this} are equals.
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userBirthdate == null) {
			if (other.userBirthdate != null)
				return false;
		} else if (!userBirthdate.equals(other.userBirthdate))
			return false;
		if (userConfirmed != other.userConfirmed)
			return false;
		if (userCountry == null) {
			if (other.userCountry != null)
				return false;
		} else if (!userCountry.equals(other.userCountry))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userFacebook == null) {
			if (other.userFacebook != null)
				return false;
		} else if (!userFacebook.equals(other.userFacebook))
			return false;
		if (userId != other.userId)
			return false;
		if (userIdentificationDocument == null) {
			if (other.userIdentificationDocument != null)
				return false;
		} else if (!userIdentificationDocument.equals(other.userIdentificationDocument))
			return false;
		if (userInstagram == null) {
			if (other.userInstagram != null)
				return false;
		} else if (!userInstagram.equals(other.userInstagram))
			return false;
		if (userLocality == null) {
			if (other.userLocality != null)
				return false;
		} else if (!userLocality.equals(other.userLocality))
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userPhone == null) {
			if (other.userPhone != null)
				return false;
		} else if (!userPhone.equals(other.userPhone))
			return false;
		if (userProvince == null) {
			if (other.userProvince != null)
				return false;
		} else if (!userProvince.equals(other.userProvince))
			return false;
		if (userResidence == null) {
			if (other.userResidence != null)
				return false;
		} else if (!userResidence.equals(other.userResidence))
			return false;
		if (userSurname == null) {
			if (other.userSurname != null)
				return false;
		} else if (!userSurname.equals(other.userSurname))
			return false;
		if (userTwitter == null) {
			if (other.userTwitter != null)
				return false;
		} else if (!userTwitter.equals(other.userTwitter))
			return false;
		return true;
	}

}
