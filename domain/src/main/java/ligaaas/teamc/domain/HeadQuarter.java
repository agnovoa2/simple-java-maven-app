package ligaaas.teamc.domain;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.inclusiveBetween;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * A headquarter
 */
@Entity
public class HeadQuarter {
	@Id
	@Column(name = "headQuarterId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long headQuarterId;
	@NotNull
	@Size(min = 5, max = 60)
	private String headquarterName;
	@NotNull
	@Size(min = 10, max = 240)
	private String headquarterDescription;
	@NotNull
	@Size(min = 5, max = 100)
	private String headquarterAddress;
	@NotNull
	@Size(min = 3, max = 100)
	private String headquarterLocality;
	@NotNull
	@Size(min = 3, max = 100)
	private String headquarterProvince;
	@NotNull
	private Boolean headquarterDeleted;
	@NotNull
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "user_UserId", referencedColumnName = "userId")
	private User headQuarterManagedByUser;

	/**
	 * Creates a new empty instance of {@code HeadQuarter}.
	 */
	public HeadQuarter() {
	}

	/**
	 * Creates a new instance of {@code HeadQuarter} without owner.
	 * 
	 * @param headQuarterId
	 *            id of the HeadQuarter. This parameter must be a non empty and non
	 *            {@code null} long.
	 * @param headquarterName
	 *            the name HeadQuarter. This parameter can not be {@code null}.
	 * @param headquarterDescription
	 *            the description HeadQuarter. This parameter can not be
	 *            {@code null}.
	 * @param headquarterAddress
	 *            the address HeadQuarter. This parameter can not be {@code null}.
	 * @param headquarterLocality
	 *            the locality HeadQuarter. This parameter can not be {@code null}.
	 * @param headquarterProvince
	 *            the province HeadQuarter. This parameter can not be {@code null}.
	 * @param headquarterDeleted
	 *            if the headQuarter is logically deleted. This parameter must be
	 *            anon {@code null} boolean
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed as the value for any parameter.
	 * @throws IllegalArgumentException
	 *             if value provided for any parameter is not valid according to its
	 *             description.
	 */
	public HeadQuarter(long headQuarterId, String headquarterName, String headquarterDescription,
			String headquarterAddress, String headquarterLocality, String headquarterProvince,
			boolean headquarterDeleted) {
		setHeadQuarterId(headQuarterId);
		setHeadquarterName(headquarterName);
		setHeadquarterDescription(headquarterDescription);
		setHeadquarterAddress(headquarterAddress);
		setHeadquarterLocality(headquarterLocality);
		setHeadquarterProvince(headquarterProvince);
		setHeadQuarterDeleted(headquarterDeleted);
	}

	/**
	 * Returns the id of the HeadQuarter.
	 * 
	 * @return the id of the HeadQuarter.
	 */
	public long getHeadQuarterId() {
		return headQuarterId;
	}

	/**
	 * Sets the id of the HeadQuarter.
	 * 
	 * @param headQuarterId
	 *            the new id of the HeadQuarter. This parameter must be a non empty
	 *            and non {@code null} long.
	 * 
	 */
	public void setHeadQuarterId(long headQuarterId) {
		this.headQuarterId = headQuarterId;
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
	 *            the new name of the HeadQuarter. This parameter must be a non
	 *            empty and non {@code null} string with a length between 5 and 60
	 *            chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setHeadquarterName(String headquarterName) {
		requireNonNull(headquarterName, "headquarter name can't be null");
		inclusiveBetween(5, 60, headquarterName.length(), "headquarter name must have a length between 5 and 60");
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
	 *            the new description of the HeadQuarter. This parameter must be a
	 *            non empty and non {@code null} string with a length between 10 and
	 *            240 chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setHeadquarterDescription(String headquarterDescription) {
		requireNonNull(headquarterDescription, "headquarter description can't be null");
		inclusiveBetween(10, 240, headquarterDescription.length(),
				"headquarter description must have a length between 10 and 240");
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
	 *            the new address of the HeadQuarter. This parameter must be a non
	 *            empty and non {@code null} string with a length between 5 and 100
	 *            chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setHeadquarterAddress(String headquarterAddress) {
		requireNonNull(headquarterAddress, "headquarter address can't be null");
		inclusiveBetween(5, 100, headquarterAddress.length(),
				"headquarter address must have a length between 5 and 100");
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
	 *            the new locality of the HeadQuarter. This parameter must be a non
	 *            empty and non {@code null} string with a length between 5 and 100
	 *            chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setHeadquarterLocality(String headquarterLocality) {
		requireNonNull(headquarterLocality, "headquarter locality can't be null");
		inclusiveBetween(3, 100, headquarterLocality.length(),
				"headquarter locality must have a length between 3 and 100");
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
	 *            the new province of the HeadQuarter. This parameter must be a non
	 *            empty and non {@code null} string with a length between 5 and 100
	 *            chars.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 * @throws IllegalArgumentException
	 *             if the length of the string passed is not valid.
	 */
	public void setHeadquarterProvince(String headquarterProvince) {
		requireNonNull(headquarterProvince, "headquarter province can't be null");
		inclusiveBetween(3, 100, headquarterProvince.length(),
				"headquarter province must have a length between 3 and 100");
		this.headquarterProvince = headquarterProvince;
	}

	/**
	 * Returns the hascCode of the HeadQuarter.
	 * 
	 * @return the hashCode of the HeadQuarter.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (headQuarterId ^ (headQuarterId >>> 32));
		result = prime * result + ((headquarterAddress == null) ? 0 : headquarterAddress.hashCode());
		result = prime * result + ((headquarterDescription == null) ? 0 : headquarterDescription.hashCode());
		result = prime * result + ((headquarterLocality == null) ? 0 : headquarterLocality.hashCode());
		result = prime * result + ((headquarterName == null) ? 0 : headquarterName.hashCode());
		result = prime * result + ((headquarterProvince == null) ? 0 : headquarterProvince.hashCode());
		return result;
	}

	/**
	 * Returns the user {@link User} manager.
	 * 
	 * @return the playerManagedByUser {@link User}.
	 */
	public User getHeadQuarterManagedByUser() {
		return headQuarterManagedByUser;
	}

	/**
	 * Sets the manager {@link User} of the {@link HeadQuarter}.
	 * 
	 * @param headQuarterManagedByUser
	 *            The manager {@link User} of the {@link HeadQuarter}.
	 */
	public void setHeadQuarterManagedByUser(User headQuarterManagedByUser) {
		requireNonNull(headquarterProvince, "headquarter manager can't be null");
		this.headQuarterManagedByUser = headQuarterManagedByUser;
	}

	/**
	 * Returns if the headQuarter is logically deleted.
	 * 
	 * @return if the headQuarter is logically deleted.
	 */
	public Boolean getHeadQuarterDeleted() {
		return headquarterDeleted;
	}

	/**
	 * Sets if headQuarter is deleted
	 * 
	 * @param headquarterDeleted
	 *            if the headQuarter is logically deleted. This parameter must be a
	 *            non empty and non {@code null} boolean.
	 * 
	 * @throws NullPointerException
	 *             if a {@code null} value is passed.
	 */
	public void setHeadQuarterDeleted(Boolean headquarterDeleted) {
		requireNonNull(headquarterDeleted, "headQuarter deleted can't be null");
		this.headquarterDeleted = headquarterDeleted;
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
		HeadQuarter other = (HeadQuarter) obj;
		if (headQuarterId != other.headQuarterId)
			return false;
		if (headquarterAddress == null) {
			if (other.headquarterAddress != null)
				return false;
		} else if (!headquarterAddress.equals(other.headquarterAddress))
			return false;
		if (headquarterDescription == null) {
			if (other.headquarterDescription != null)
				return false;
		} else if (!headquarterDescription.equals(other.headquarterDescription))
			return false;
		if (headquarterLocality == null) {
			if (other.headquarterLocality != null)
				return false;
		} else if (!headquarterLocality.equals(other.headquarterLocality))
			return false;
		if (headquarterName == null) {
			if (other.headquarterName != null)
				return false;
		} else if (!headquarterName.equals(other.headquarterName))
			return false;
		if (headquarterProvince == null) {
			if (other.headquarterProvince != null)
				return false;
		} else if (!headquarterProvince.equals(other.headquarterProvince))
			return false;
		return true;
	}
}
