package customer.engagement.domain;

/**
 * Represent all the events where the user is interested.
 * 
 * @author abdul
 *
 */
public class Event {

	private long id;
	private String description;
	private String eventName;
	private String organizerName;
	private Location location;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the organizerName
	 */
	public String getOrganizerName() {
		return organizerName;
	}

	/**
	 * @param organizerName
	 *            the organizerName to set
	 */
	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [id=" + id + ", description=" + description + ", eventName=" + eventName + ", organizerName="
				+ organizerName + ", location=" + location + "]";
	}

}
