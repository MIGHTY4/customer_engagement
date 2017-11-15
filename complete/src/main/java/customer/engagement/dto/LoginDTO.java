package customer.engagement.dto;

public class LoginDTO {
	private String name;
	private String email;
	private String coverPicsURL;
	private String profilePics;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the coverPicsURL
	 */
	public String getCoverPicsURL() {
		return coverPicsURL;
	}

	/**
	 * @param coverPicsURL
	 *            the coverPicsURL to set
	 */
	public void setCoverPicsURL(String coverPicsURL) {
		this.coverPicsURL = coverPicsURL;
	}

	/**
	 * @return the profilePics
	 */
	public String getProfilePics() {
		return profilePics;
	}

	/**
	 * @param profilePics the profilePics to set
	 */
	public void setProfilePics(String profilePics) {
		this.profilePics = profilePics;
	}

}
