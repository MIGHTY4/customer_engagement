package customer.engagement.domain;

/**
 * Represent from where the education has been done by User.
 * 
 * @author abdul
 *
 */
public class EducationDetails {

	private String instituteName;
	private String type;

	/**
	 * @return the instituteName
	 */
	public String getInstituteName() {
		return instituteName;
	}

	/**
	 * @param instituteName
	 *            the instituteName to set
	 */
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EducationDetails [instituteName=" + instituteName + ", type=" + type + "]";
	}

}
