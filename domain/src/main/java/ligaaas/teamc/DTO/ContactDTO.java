package ligaaas.teamc.DTO;

/**
 * DTO of a Contact
 *
 * @author teamC
 *
 */
public class ContactDTO {
	private String contactEmail;
	private String contactWeb;
	private String contactPhone;
	private String contactTwitter;
	private String contactFacebook;
	private String contactInstagram;

	public ContactDTO() {
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
	 *            the new email of the contact.
	 */
	public void setContactEmail(String contactEmail) {
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
	 *            the new web of the contact.
	 */
	public void setContactWeb(String contactWeb) {
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
	 *            the new phone of the contact.
	 */
	public void setContactPhone(String contactPhone) {
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
	 *            the new twitter account of the contact.
	 */
	public void setContactTwitter(String contactTwitter) {
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
	 *            the new facebook account of the contact.
	 */
	public void setContactFacebook(String contactFacebook) {
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
	 *            the new instagram account of the contact.
	 */
	public void setContactInstagram(String contactInstagram) {
		this.contactInstagram = contactInstagram;
	}

}
