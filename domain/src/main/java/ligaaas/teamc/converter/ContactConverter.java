package ligaaas.teamc.converter;

import ligaaas.teamc.DTO.ContactDTO;
import ligaaas.teamc.domain.Contact;

/**
 * Converter for Contact
 * 
 * @author teamC
 *
 */
public class ContactConverter {

	/**
	 * Converts a {@link Contact} into a {@link ContactDTO}
	 * 
	 * @param contact
	 *            a {@link Contact}
	 * @return {@link ContactDTO}
	 */
	public static ContactDTO toContactDTO(Contact contact) {
		ContactDTO contactDTO = new ContactDTO();

		contactDTO.setContactEmail(contact.getContactEmail());
		contactDTO.setContactFacebook(contact.getContactFacebook());
		contactDTO.setContactInstagram(contact.getContactInstagram());
		contactDTO.setContactPhone(contact.getContactPhone());
		contactDTO.setContactTwitter(contact.getContactTwitter());
		contactDTO.setContactWeb(contact.getContactWeb());

		return contactDTO;
	}
}
