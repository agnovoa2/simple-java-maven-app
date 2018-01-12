package ligaaas.teamc.DTO;

/**
 * DTO of a HeadQuarter
 *
 * @author teamC
 *
 */
public class HeadQuarterDTO {
	private String headquarterName;
	private String headquarterDescription;
	private String headquarterAddress;
	private String headquarterLocality;
	private String headquarterProvince;

	public HeadQuarterDTO() {
	}

	/**
	 * Returns the name of the HeadQuarter.
	 * 
	 * @return the name of the HeadQuarter.
	 */
	public String getHeadquarterName() {
		return headquarterName;
	}

	/**
	 * Sets the name of the HeadQuarter.
	 * 
	 * @param headquarterName
	 *            the new name of the HeadQuarter.
	 */
	public void setHeadquarterName(String headquarterName) {
		this.headquarterName = headquarterName;
	}

	/**
	 * Returns the description of the HeadQuarter.
	 * 
	 * @return the description of the HeadQuarter.
	 */
	public String getHeadquarterDescription() {
		return headquarterDescription;
	}

	/**
	 * Sets the description of the HeadQuarter.
	 * 
	 * @param headquarterDescription
	 *            the new description of the HeadQuarter.
	 */
	public void setHeadquarterDescription(String headquarterDescription) {
		this.headquarterDescription = headquarterDescription;
	}

	/**
	 * Returns the address of the HeadQuarter.
	 * 
	 * @return the address of the HeadQuarter.
	 */
	public String getHeadquarterAddress() {
		return headquarterAddress;
	}

	/**
	 * Sets the address of the HeadQuarter.
	 * 
	 * @param headquarterAddress
	 *            the new address of the HeadQuarter.
	 */
	public void setHeadquarterAddress(String headquarterAddress) {
		this.headquarterAddress = headquarterAddress;
	}

	/**
	 * Returns the locality of the HeadQuarter.
	 * 
	 * @return the locality of the HeadQuarter.
	 */
	public String getHeadquarterLocality() {
		return headquarterLocality;
	}

	/**
	 * Sets the locality of the HeadQuarter.
	 * 
	 * @param headquarterLocality
	 *            the new locality of the HeadQuarter.
	 */
	public void setHeadquarterLocality(String headquarterLocality) {
		this.headquarterLocality = headquarterLocality;
	}

	/**
	 * Returns the province of the HeadQuarter.
	 * 
	 * @return the province of the HeadQuarter.
	 */
	public String getHeadquarterProvince() {
		return headquarterProvince;
	}

	/**
	 * Sets the province of the HeadQuarter.
	 * 
	 * @param headquarterProvince
	 *            the new province of the HeadQuarter.
	 */
	public void setHeadquarterProvince(String headquarterProvince) {
		this.headquarterProvince = headquarterProvince;
	}

}
