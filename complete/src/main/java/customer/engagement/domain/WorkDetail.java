package customer.engagement.domain;

/**
 * REpresent the work location of a user
 * 
 * @author abdul
 *
 */
public class WorkDetail {

	private String employerName;

	/**
	 * @return the employerName
	 */
	public String getEmployerName() {
		return employerName;
	}

	/**
	 * @param employerName
	 *            the employerName to set
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorkDetail [employerName=" + employerName + "]";
	}

}
