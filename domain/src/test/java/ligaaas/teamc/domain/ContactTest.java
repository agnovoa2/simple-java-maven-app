package ligaaas.teamc.domain;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ContactTest {
	private long contactId;
	private String contactEmail;
	private String contactWeb;
	private String contactPhone;
	private String contactTwitter;
	private String contactFacebook;
	private String contactInstagram;

	private long newContactId;
	private String newContactEmail;
	private String newContactWeb;
	private String newContactPhone;
	private String newContactTwitter;
	private String newContactFacebook;
	private String newContactInstagram;

	@Before
	public void setUp() throws Exception {
		contactId = 1;
		contactEmail = "rbfc@gmail.com";
		contactWeb = "htpp://www.rbfc.gal";
		contactPhone = "+34666666666";
		contactTwitter = "https://twitter.com/rapidobouzas/";
		contactFacebook = "https://www.facebook.com/RapidoBouzas/";
		contactInstagram = "https://www.instagram.com/RapidoBouzas/";

		newContactId = 5;
		newContactEmail = "lbfc@gmail.com";
		newContactWeb = "htpp://www.lbfc.gal";
		newContactPhone = "+34666666667";
		newContactTwitter = "https://twitter.com/lentobouzas/";
		newContactFacebook = "https://www.facebook.com/LentoBouzas/";
		newContactInstagram = "https://www.instagram.com/LentoBouzas/";
	}

	@Test
	public void testContactConstructor() {
		Contact contactTest = new Contact(contactId, contactEmail, contactWeb, contactPhone, contactTwitter,
				contactFacebook, contactInstagram);
		assertThat(contactTest.getContactId(), is(equalTo(contactId)));
		assertThat(contactTest.getContactEmail(), is(equalTo(contactEmail)));
		assertThat(contactTest.getContactWeb(), is(equalTo(contactWeb)));
		assertThat(contactTest.getContactPhone(), is(equalTo(contactPhone)));
		assertThat(contactTest.getContactTwitter(), is(equalTo(contactTwitter)));
		assertThat(contactTest.getContactFacebook(), is(equalTo(contactFacebook)));
		assertThat(contactTest.getContactInstagram(), is(equalTo(contactInstagram)));
	}

	@Test
	public void testSetContactId() {
		Contact contactTest = new Contact(contactId, contactEmail, contactWeb, contactPhone, contactTwitter,
				contactFacebook, contactInstagram);
		contactTest.setContactId(newContactId);
		assertThat(contactTest.getContactId(), is(equalTo(newContactId)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactEmailTooShort() {
		Contact contactTest = new Contact();
		contactTest.setContactEmail("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactEmailTooLong() {
		Contact contactTest = new Contact();
		contactTest.setContactEmail(repeat('A', 99) + "@gmail.com");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactEmailIncorrect() {
		Contact contactTest = new Contact();
		contactTest.setContactEmail("RapidoBouzas@gmail");
	}

	@Test
	public void testSetContactEmail() {
		Contact contactTest = new Contact(contactId, contactEmail, contactWeb, contactPhone, contactTwitter,
				contactFacebook, contactInstagram);
		contactTest.setContactEmail(newContactEmail);
		assertThat(contactTest.getContactEmail(), is(equalTo(newContactEmail)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactWebTooShort() {
		Contact contactTest = new Contact();
		contactTest.setContactWeb("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactWebTooLong() {
		Contact contactTest = new Contact();
		contactTest.setContactWeb(repeat('A', 101));
	}

	@Test
	public void testSetContactWeb() {
		Contact contactTest = new Contact(contactId, contactEmail, contactWeb, contactPhone, contactTwitter,
				contactFacebook, contactInstagram);
		contactTest.setContactWeb(newContactWeb);
		assertThat(contactTest.getContactWeb(), is(equalTo(newContactWeb)));
	}

	@Test(expected = NullPointerException.class)
	public void testContactStringPhoneNull() {
		Contact contactTest = new Contact();
		contactTest.setContactPhone(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactPhoneTooShort() {
		Contact contactTest = new Contact();
		contactTest.setContactPhone("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactPhoneTooLong() {
		Contact contactTest = new Contact();
		contactTest.setContactPhone(repeat('1', 10));
	}

	@Test
	public void testSetContactPhone() {
		Contact contactTest = new Contact(contactId, contactEmail, contactWeb, contactPhone, contactTwitter,
				contactFacebook, contactInstagram);
		contactTest.setContactPhone(newContactPhone);
		assertThat(contactTest.getContactPhone(), is(equalTo(newContactPhone)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactTwitterTooShort() {
		Contact contactTest = new Contact();
		contactTest.setContactTwitter("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactTwitterTooLong() {
		Contact contactTest = new Contact();
		contactTest.setContactTwitter(repeat('A', 101));
	}

	@Test
	public void testSetContactTwitter() {
		Contact contactTest = new Contact(contactId, contactEmail, contactWeb, contactPhone, contactTwitter,
				contactFacebook, contactInstagram);
		contactTest.setContactTwitter(newContactTwitter);
		assertThat(contactTest.getContactTwitter(), is(equalTo(newContactTwitter)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactFacebookTooShort() {
		Contact contactTest = new Contact();
		contactTest.setContactFacebook("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactFacebookTooLong() {
		Contact contactTest = new Contact();
		contactTest.setContactFacebook(repeat('A', 101));
	}

	@Test
	public void testSetContactFacebook() {
		Contact contactTest = new Contact(contactId, contactEmail, contactWeb, contactPhone, contactTwitter,
				contactFacebook, contactInstagram);
		contactTest.setContactFacebook(newContactFacebook);
		assertThat(contactTest.getContactFacebook(), is(equalTo(newContactFacebook)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactInstagramTooShort() {
		Contact contactTest = new Contact();
		contactTest.setContactInstagram("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContactStringContactInstagramTooLong() {
		Contact contactTest = new Contact();
		contactTest.setContactInstagram(repeat('A', 101));
	}

	@Test
	public void testSetContactInstagram() {
		Contact contactTest = new Contact(contactId, contactEmail, contactWeb, contactPhone, contactTwitter,
				contactFacebook, contactInstagram);
		contactTest.setContactInstagram(newContactInstagram);
		assertThat(contactTest.getContactInstagram(), is(equalTo(newContactInstagram)));
	}
}
