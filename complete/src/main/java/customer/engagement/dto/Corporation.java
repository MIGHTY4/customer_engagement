package customer.engagement.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CORPORATION")
public class Corporation {

	@Id
	private String corporationId;
	private String corporationName;
	private String functionalDomain;
	private String imagePath;
	private String URL;
	private String address;
	private String areaCode;

	/**
	 * @return the corporationId
	 */
	public String getCorporationId() {
		return corporationId;
	}

	/**
	 * @param corporationId
	 *            the corporationId to set
	 */
	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	/**
	 * @return the corporationName
	 */
	public String getCorporationName() {
		return corporationName;
	}

	/**
	 * @param corporationName
	 *            the corporationName to set
	 */
	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	/**
	 * @return the functionalDomain
	 */
	public String getFunctionalDomain() {
		return functionalDomain;
	}

	/**
	 * @param functionalDomain the functionalDomain to set
	 */
	public void setFunctionalDomain(String functionalDomain) {
		this.functionalDomain = functionalDomain;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param uRL
	 *            the uRL to set
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Corporation [corporationId=" + corporationId + ", corporationName=" + corporationName
				+ ", functionalDomain=" + functionalDomain + ", imagePath=" + imagePath + ", URL=" + URL + ", address="
				+ address + ", areaCode=" + areaCode + "]";
	}

}
