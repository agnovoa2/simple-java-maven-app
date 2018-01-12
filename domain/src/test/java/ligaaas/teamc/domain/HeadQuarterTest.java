package ligaaas.teamc.domain;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class HeadQuarterTest {

	private long headQuarterId;
	private String headquarterName;
	private String headquarterDescription;
	private String headquarterAddress;
	private String headquarterLocality;
	private String headquarterProvince;
	private boolean headquarterDeleted;

	private long newHeadQuarterId;
	private String newHeadquarterName;
	private String newHeadquarterDescription;
	private String newHeadquarterAddress;
	private String newHeadquarterLocality;
	private String newHeadquarterProvince;
	private boolean newHeadquarterDeleted;

	@Before
	public void setUp() throws Exception {

		this.headQuarterId = 1;
		this.headquarterName = "A Madroa";
		this.headquarterDescription = "Instalaciones del Rapido de Bouzas";
		this.headquarterAddress = "Avenida de la Coruña 12";
		this.headquarterLocality = "Bouzas";
		this.headquarterProvince = "Pontevedra";
		this.headquarterDeleted = false;

		this.newHeadQuarterId = 2;
		this.newHeadquarterName = "Instalacions Xose Garcia";
		this.newHeadquarterDescription = "Instalaciones del Coruxo C.F.";
		this.newHeadquarterAddress = "Rua de Ferrol 5";
		this.newHeadquarterLocality = "Coruxo";
		this.newHeadquarterProvince = "Lugo";
		this.newHeadquarterDeleted = true;
	}

	@Test
	public void testHeadQuarterConstructor() {

		HeadQuarter headquarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);

		assertThat(headquarterTest.getHeadQuarterId(), is(equalTo(headQuarterId)));
		assertThat(headquarterTest.getHeadquarterName(), is(equalTo(headquarterName)));
		assertThat(headquarterTest.getHeadquarterDescription(), is(equalTo(headquarterDescription)));
		assertThat(headquarterTest.getHeadquarterAddress(), is(equalTo(headquarterAddress)));
		assertThat(headquarterTest.getHeadquarterLocality(), is(equalTo(headquarterLocality)));
		assertThat(headquarterTest.getHeadquarterProvince(), is(equalTo(headquarterProvince)));
		assertThat(headquarterTest.getHeadQuarterDeleted(), is(equalTo(headquarterDeleted)));
	}

	@Test
	public void testSetHeadQuarterId() {
		HeadQuarter headquarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);
		headquarterTest.setHeadQuarterId(newHeadQuarterId);
		assertThat(headquarterTest.getHeadQuarterId(), is(equalTo(newHeadQuarterId)));
	}

	@Test(expected = NullPointerException.class)
	public void testHeadQuarterStringHeadQuarterNameNull() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterNameTooShort() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterNameTooLong() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterName(repeat('A', 61));
	}

	@Test
	public void testSetHeadQuarterName() {
		HeadQuarter headquarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);
		headquarterTest.setHeadquarterName(newHeadquarterName);
		assertThat(headquarterTest.getHeadquarterName(), is(equalTo(newHeadquarterName)));
	}

	@Test(expected = NullPointerException.class)
	public void testHeadQuarterStringHeadQuarterDescriptionNull() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterDescription(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterDescriptionTooShort() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterDescription("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterDescriptionTooLong() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterDescription(repeat('A', 241));
	}

	@Test
	public void testSetHeadQuarterDescription() {
		HeadQuarter headquarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);
		headquarterTest.setHeadquarterDescription(newHeadquarterDescription);
		assertThat(headquarterTest.getHeadquarterDescription(), is(equalTo(newHeadquarterDescription)));
	}

	@Test(expected = NullPointerException.class)
	public void testHeadQuarterStringHeadQuarterAddressNull() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterAddress(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterAddressTooShort() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterAddress("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterAddressTooLong() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterAddress(repeat('A', 101));
	}

	@Test
	public void testSetHeadQuarterAddress() {
		HeadQuarter headquarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);
		headquarterTest.setHeadquarterAddress(newHeadquarterAddress);
		assertThat(headquarterTest.getHeadquarterAddress(), is(equalTo(newHeadquarterAddress)));
	}

	@Test(expected = NullPointerException.class)
	public void testHeadQuarterStringHeadQuarterLocalityNull() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterLocality(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterLocalityTooShort() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterLocality("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterLocalityTooLong() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterLocality(repeat('A', 101));
	}

	@Test
	public void testSetHeadQuarterLocality() {
		HeadQuarter headquarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);
		headquarterTest.setHeadquarterLocality(newHeadquarterLocality);
		assertThat(headquarterTest.getHeadquarterLocality(), is(equalTo(newHeadquarterLocality)));
	}

	@Test(expected = NullPointerException.class)
	public void testHeadQuarterStringHeadQuarterProvinceNull() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterProvince(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterProvinceTooShort() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterProvince("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeadQuarterStringHeadQuarterProvinceTooLong() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadquarterProvince(repeat('A', 101));
	}

	@Test
	public void testSetHeadQuarterProvince() {
		HeadQuarter headquarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);
		headquarterTest.setHeadquarterProvince(newHeadquarterProvince);
		assertThat(headquarterTest.getHeadquarterProvince(), is(equalTo(newHeadquarterProvince)));
	}

	@Test(expected = NullPointerException.class)
	public void testHeadQuarterStringHeadQuarterManagerNull() {
		HeadQuarter headquarterTest = new HeadQuarter();
		headquarterTest.setHeadQuarterManagedByUser(null);
	}

	@Test
	public void testSetHeadQuarterManagedByUser() {
		User userTest = new User();
		HeadQuarter headquarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);
		headquarterTest.setHeadQuarterManagedByUser(userTest);
		assertThat(headquarterTest.getHeadQuarterManagedByUser(), is(equalTo(userTest)));
	}

	@Test
	public void testSetHeadQuarterDeleted() {
		HeadQuarter headQuarterTest = new HeadQuarter(headQuarterId, headquarterName, headquarterDescription,
				headquarterAddress, headquarterLocality, headquarterProvince, headquarterDeleted);
		headQuarterTest.setHeadQuarterDeleted(newHeadquarterDeleted);
		assertThat(headQuarterTest.getHeadQuarterDeleted(), is(equalTo(newHeadquarterDeleted)));
	}
}
