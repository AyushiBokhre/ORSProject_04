package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class EventModel extends BaseModel<EventBean>{
	private static Logger log = Logger.getLogger(UserModel.class);

	@Override
	public long add(EventBean bean) throws ApplicationException, DuplicateRecordException {
		log.info("UserModel add() method started");

		Connection conn = null;
		EventBean existBean = findByEventName(bean.getEventName());

		if (existBean != null) {
			log.warn("Event already exists : " + bean.getEventName());
			throw new DuplicateRecordException("Event Name already exist");
		}

		try {
			
			log.debug("Getting database connection for adding exam");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getEventName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setDate(4, new java.sql.Date(bean.getEventDate().getTime()));
			pstmt.setString(5, bean.getVenue());
			pstmt.setString(6, bean.getOrganizer());
			pstmt.setString(7, bean.getStatus());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			
			log.info("Event added successfully " );

		} catch (Exception e) {
			log.error("Exception while adding event: " + bean.getEventName(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed after add()");
		}

		return bean.getId();
	}

	@Override
	public void update(EventBean bean) throws ApplicationException, DuplicateRecordException {
		log.info("EventModel update() method started for event ID: " + bean.getId());

		Connection conn = null;
		EventBean existbean = findByEventName(bean.getEventName());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			log.warn("Duplicate event found while updating event: " + bean.getEventName());
			throw new DuplicateRecordException("event is already exist");
		}

		try {

			log.debug("Getting database connection for updating Subject");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE"+getTable()+"SET EVENT_NAME=?,DESCRIPTION=?,EVENT_DATE=?,VENUE=?,ORGANIZER=?,STATUS=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getEventName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setDate(3, new java.sql.Date(bean.getEventDate().getTime()));
			pstmt.setString(4, bean.getVenue());
			pstmt.setString(5, bean.getOrganizer());
			pstmt.setString(6, bean.getStatus());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " record updated");

			conn.commit(); // End transaction

			log.info("event updated successfully with ID: " + bean.getId());

			pstmt.close();

		} catch (SQLException e) {
			log.error("Exception while updating event ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed after update()");
		}
		
	}
	public EventBean findByEventName(String eventName) {
		EventBean bean =findByUniqueColumn("event_name", eventName);
		return bean;
	}


	@Override
	public String getWhereClause(EventBean bean) {
		log.debug("EventModel getWhereClause() method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getEventName() != null && bean.getEventName().length() > 0) {
				sql.append(" and event_name like '" + bean.getEventName() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}

			if (bean.getEventDate() != null && bean.getEventDate().getTime() > 0) {
				sql.append(" and event_date like '" + bean.getEventDate() + "%'");
			}

			if (bean.getVenue() != null && bean.getVenue().length() > 0) {
				sql.append(" and venue like '" + bean.getVenue() + "%'");
			}
			
			if (bean.getOrganizer() != null && bean.getOrganizer().length() > 0) {
				sql.append(" and organizer like '" + bean.getOrganizer() + "%'");
			}
			
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like '" + bean.getStatus() + "%'");
			}

		}

		log.debug("Generated EventModel where clause: " + sql.toString());

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_event";
	}

	@Override
	public EventBean getBean() {
		return new EventBean();
	}

}
