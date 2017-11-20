package customer.engagement.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Customer {

	private enum FinancialCondition {
		POOR, LOWER_MIDDLE_CLASS, UPPER_MIDDLE_CLASS, RICH
	}

	@Id
	private String id;
	private String name;
	private String state;
	private String city;
	private String religion;
	private String gender;
	private FinancialCondition financialCondition;
	private List<String> domains;
	private List<String> pages;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the financialCondition
	 */
	public FinancialCondition getFinancialCondition() {
		return financialCondition;
	}
	/**
	 * @param financialCondition the financialCondition to set
	 */
	public void setFinancialCondition(FinancialCondition financialCondition) {
		this.financialCondition = financialCondition;
	}
	/**
	 * @return the domains
	 */
	public List<String> getDomains() {
		return domains;
	}
	/**
	 * @param domains the domains to set
	 */
	public void setDomains(List<String> domains) {
		this.domains = domains;
	}
	/**
	 * @return the pages
	 */
	public List<String> getPages() {
		return pages;
	}
	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<String> pages) {
		this.pages = pages;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", state=" + state + ", city=" + city + ", religion="
				+ religion + ", gender=" + gender + ", financialCondition=" + financialCondition + ", domains="
				+ domains + ", pages=" + pages + "]";
	}

}
