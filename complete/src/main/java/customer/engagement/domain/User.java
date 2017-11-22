package customer.engagement.domain;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "USER")
public class User {

	@Id
	private String id;
	private String facebookId;
	private String name;
	private String email;
	private String gender;

	// TODO change to date format
	private String birthday;
	private String homeTownLocation;
	private String currentLocation;
	private String coverPicsURL;
	private String profilePics;
	private String website;

	private String religion;

	List<EducationDetails> details;
	List<WorkDetail> workDetails;
	List<UserGroup> userGroup;
	List<UserPage> userPage;
	List<Event> event;
	List<LikePages> likes;
	List<TaggedPlaces> taggedPlaces;
	List<EducationDetails> educationDetails;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the facebookId
	 */
	public String getFacebookId() {
		return facebookId;
	}

	/**
	 * @param facebookId
	 *            the facebookId to set
	 */
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the homeTownLocation
	 */
	public String getHomeTownLocation() {
		return homeTownLocation;
	}

	/**
	 * @param homeTownLocation
	 *            the homeTownLocation to set
	 */
	public void setHomeTownLocation(String homeTownLocation) {
		this.homeTownLocation = homeTownLocation;
	}

	/**
	 * @return the currentLocation
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation
	 *            the currentLocation to set
	 */
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
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
	 * @param profilePics
	 *            the profilePics to set
	 */
	public void setProfilePics(String profilePics) {
		this.profilePics = profilePics;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion
	 *            the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the details
	 */
	public List<EducationDetails> getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(List<EducationDetails> details) {
		this.details = details;
	}

	/**
	 * @return the workDetails
	 */
	public List<WorkDetail> getWorkDetails() {
		return workDetails;
	}

	/**
	 * @param workDetails
	 *            the workDetails to set
	 */
	public void setWorkDetails(List<WorkDetail> workDetails) {
		this.workDetails = workDetails;
	}

	/**
	 * @return the userGroup
	 */
	public List<UserGroup> getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup
	 *            the userGroup to set
	 */
	public void setUserGroup(List<UserGroup> userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * @return the userPage
	 */
	public List<UserPage> getUserPage() {
		return userPage;
	}

	/**
	 * @param userPage
	 *            the userPage to set
	 */
	public void setUserPage(List<UserPage> userPage) {
		this.userPage = userPage;
	}

	/**
	 * @return the event
	 */
	public List<Event> getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(List<Event> event) {
		this.event = event;
	}

	/**
	 * @return the likes
	 */
	public List<LikePages> getLikes() {
		return likes;
	}

	/**
	 * @param likes
	 *            the likes to set
	 */
	public void setLikes(List<LikePages> likes) {
		this.likes = likes;
	}

	/**
	 * @return the taggedPlaces
	 */
	public List<TaggedPlaces> getTaggedPlaces() {
		return taggedPlaces;
	}

	/**
	 * @param taggedPlaces
	 *            the taggedPlaces to set
	 */
	public void setTaggedPlaces(List<TaggedPlaces> taggedPlaces) {
		this.taggedPlaces = taggedPlaces;
	}

	/**
	 * @return the educationDetails
	 */
	public List<EducationDetails> getEducationDetails() {
		return educationDetails;
	}

	/**
	 * @param educationDetails the educationDetails to set
	 */
	public void setEducationDetails(List<EducationDetails> educationDetails) {
		this.educationDetails = educationDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", facebookId=" + facebookId + ", name=" + name + ", email=" + email + ", gender="
				+ gender + ", birthday=" + birthday + ", homeTownLocation=" + homeTownLocation + ", currentLocation="
				+ currentLocation + ", coverPicsURL=" + coverPicsURL + ", profilePics=" + profilePics + ", website="
				+ website + ", religion=" + religion + ", details=" + details + ", workDetails=" + workDetails
				+ ", userGroup=" + userGroup + ", userPage=" + userPage + ", event=" + event + ", likes=" + likes
				+ ", taggedPlaces=" + taggedPlaces + ", educationDetails=" + educationDetails + "]";
	}

}
