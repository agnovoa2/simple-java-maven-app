package ligaaas.teamc.domain;

import static java.util.Objects.requireNonNull;
import static ligaaas.teamc.domain.RegexpTemplates.EMAIL;
import static ligaaas.teamc.domain.RegexpTemplates.PHONE;
import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.matchesPattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Contact {

	@Id
	@Column(name = "contactId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long contactId;
	@Size(min = 10, max = 100)
	@Pattern(regexp = EMAIL)
	private String contactEmail;
	@Size(min = 10, max = 100)
	private String contactWeb;
	@NotNull
	@Pattern(regexp = PHONE)
	private String contactPhone;
	@Size(min = 10, max = 100)
	private String contactTwitter;
	@Size(min = 10, max = 100)
	private String contactFacebook;
	@Size(min = 10, max = 100)
	private String contactInstagram;

	/**
	 * Creates a new empty instance of {@code Contact}.
	 */
	public Contact() {
	}

	/**
	 * Creates a new instance of {@code Contact}.
	 * 
	 * @param contactId
	 *            the id of the contact. This parameter must be a non empty and non
	 *            {@code null} long .
	 * @param contactEmail
	 *            the email of the contact. This parameter must be a non empty and
	 *            valid email.
	 * @param contactWeb
	 *            the web url of the contact. This parameter must be a non empty
	 *            string.
	 * @param contactPhone
	 *            the phone of the contact. This parameter must be a non empty and
	 *            non {@code null} and valid phone.
	 * @param contactTwitter
	 *            the twitter url of the contact. This parameter must be a non empty
	 *            string.
	 * @param contactFacebook
	 *            the facebook url of the contact. This parameter must be a non
	 *            empty string.
	 * @param contactInstagram
	 *            the instagram url of the contact. This parameter must be a non
	 *            empty string.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed as the value for any parameter.
	 * @throws IllegalArgumentException
	 *             if value provided for any parameter is not valid according to its
	 *             description.
	 */
	public Contact(long contactId, String contactEmail, String contactWeb, String contactPhone, String contactTwitter,
			String contactFacebook, String contactInstagram) {
		setContactId(contactId);
		setContactEmail(contactEmail);
		setContactWeb(contactWeb);
		setContactPhone(contactPhone);
		setContactTwitter(contactTwitter);
		setContactFacebook(contactFacebook);
		setContactInstagram(contactInstagram);
	}

	/**
	 * Returns the id of the contact.
	 * 
	 * @return the id of the contact.
	 */
	public long getContactId() {
		return contactId;
	}

	/**
	 * Sets the id of the contact.
	 * 
	 * @param contactId
	 *            the id of the contact. This parameter must be a non empty and non
	 *            {@code null} long.
	 * 
	 */
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	/**
	 * Returns the email of the contact.
	 * 
	 * @return the email of the contact.
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * Sets the email of the contact.
	 * 
	 * @param contactEmail
	 *            the new email of the contact. This parameter must be a non empty
	 *            string with a maximum length of 100 chars and a minimun length of
	 *            10 chars and be a valid email.
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid or the email is
	 *             invalid.
	 */
	public void setContactEmail(String contactEmail) {
		inclusiveBetween(10, 100, contactEmail.length(), "contact email must have a length between 10 and 100");
		matchesPattern(contactEmail, EMAIL);
		this.contactEmail = contactEmail;
	}

	/**
	 * Returns the web of the contact.
	 * 
	 * @return the web of the contact.
	 */
	public String getContactWeb() {
		return contactWeb;
	}

	/**
	 * Sets the web of the contact.
	 * 
	 * @param contactWeb
	 *            the new web of the contact. This parameter must be a non empty
	 *            string with a maximum length of 100 chars and a minimun length of
	 *            10 chars.
	 * 
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setContactWeb(String contactWeb) {
		inclusiveBetween(10, 100, contactWeb.length(), "contact web must have a length between 10 and 100");
		this.contactWeb = contactWeb;
	}

	/**
	 * Returns the phone of the contact.
	 * 
	 * @return the phone of the contact.
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * Sets the phone of the contact.
	 * 
	 * @param contactPhone
	 *            the new phone of the contact. This parameter must be a non empty
	 *            and non {@code null} string and a valid phone.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setContactPhone(String contactPhone) {
		requireNonNull(contactPhone, "contact phone can't be null");
		matchesPattern(contactPhone, PHONE);
		this.contactPhone = contactPhone;
	}

	/**
	 * Returns the twitter account of the contact.
	 * 
	 * @return the twitter account of the contact.
	 */
	public String getContactTwitter() {
		return contactTwitter;
	}

	/**
	 * Sets the twitter account of the contact.
	 * 
	 * @param contactTwitter
	 *            the new twitter account of the contact. This parameter must be a
	 *            non empty string with a maximum length of 100 chars and a minimun
	 *            length of 10 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setContactTwitter(String contactTwitter) {
		if (contactTwitter != null) {
			inclusiveBetween(10, 100, contactTwitter.length(), "contact twitter must have a length between 10 and 100");
		}
		this.contactTwitter = contactTwitter;
	}

	/**
	 * Returns the facebook account of the contact.
	 * 
	 * @return the facebook account of the contact.
	 */
	public String getContactFacebook() {
		return contactFacebook;
	}

	/**
	 * Sets the facebook account of the contact.
	 * 
	 * @param contactFacebook
	 *            the new facebook account of the contact. This parameter must be a
	 *            non empty string with a maximum length of 100 chars and a minimun
	 *            length of 10 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setContactFacebook(String contactFacebook) {
		if (contactFacebook != null) {
			inclusiveBetween(10, 100, contactFacebook.length(),
					"contact facebook must have a length between 10 and 100");
		}
		this.contactFacebook = contactFacebook;
	}

	/**
	 * Returns the instagram account of the contact.
	 * 
	 * @return the instagram account of the contact.
	 */
	public String getContactInstagram() {
		return contactInstagram;
	}

	/**
	 * Sets the instagram account of the contact.
	 * 
	 * @param contactInstagram
	 *            the new instagram account of the contact. This parameter must be a
	 *            non empty string with a maximum length of 100 chars and a minimun
	 *            length of 10 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setContactInstagram(String contactInstagram) {
		if (contactInstagram != null) {
			inclusiveBetween(10, 100, contactInstagram.length(),
					"contact instagram must have a length between 10 and 100");
		}
		this.contactInstagram = contactInstagram;
	}

	/**
	 * Returns the hascCode of the contact.
	 * 
	 * @return the hashCode of the contact.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactEmail == null) ? 0 : contactEmail.hashCode());
		result = prime * result + ((contactFacebook == null) ? 0 : contactFacebook.hashCode());
		result = prime * result + (int) (contactId ^ (contactId >>> 32));
		result = prime * result + ((contactInstagram == null) ? 0 : contactInstagram.hashCode());
		result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
		result = prime * result + ((contactTwitter == null) ? 0 : contactTwitter.hashCode());
		result = prime * result + ((contactWeb == null) ? 0 : contactWeb.hashCode());
		return result;
	}

	/**
	 * Returns if two classes are equals
	 * 
	 * @param obj
	 *            the object to compare
	 * @return if object and this are equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (contactEmail == null) {
			if (other.contactEmail != null)
				return false;
		} else if (!contactEmail.equals(other.contactEmail))
			return false;
		if (contactFacebook == null) {
			if (other.contactFacebook != null)
				return false;
		} else if (!contactFacebook.equals(other.contactFacebook))
			return false;
		if (contactId != other.contactId)
			return false;
		if (contactInstagram == null) {
			if (other.contactInstagram != null)
				return false;
		} else if (!contactInstagram.equals(other.contactInstagram))
			return false;
		if (contactPhone == null) {
			if (other.contactPhone != null)
				return false;
		} else if (!contactPhone.equals(other.contactPhone))
			return false;
		if (contactTwitter == null) {
			if (other.contactTwitter != null)
				return false;
		} else if (!contactTwitter.equals(other.contactTwitter))
			return false;
		if (contactWeb == null) {
			if (other.contactWeb != null)
				return false;
		} else if (!contactWeb.equals(other.contactWeb))
			return false;
		return true;
	}
}
