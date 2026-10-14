package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EventBean extends BaseBean{
	private String eventName;
	private String description;
	private Date eventDate;
	private String venue;
	private String organizer;
	private String status;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public void setResultset(ResultSet rs) {
		super.setResultset(rs);
		try {
			setEventName(rs.getString("event_name"));
			setDescription(rs.getString("description"));
			setEventDate(rs.getDate("event_date"));
			setVenue(rs.getString("venue"));
			setOrganizer(rs.getString("organizer"));
			setStatus(rs.getString("status"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getValue() {
		return null;
	}

}
