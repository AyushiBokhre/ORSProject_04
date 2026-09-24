package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.FoodOrderBean;
import in.co.rays.proj4.bean.SmartParkingBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SmartParkingModel extends BaseModel<SmartParkingBean> {

	@Override
	public long add(SmartParkingBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		SmartParkingBean existbean = findByVehicleNumber(bean.getVehicleNumber());

		if (existbean != null) {
			throw new DuplicateRecordException("Voter Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO "+getTable()+" VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getVehicleNumber());
			pstmt.setString(3, bean.getVehicleType());
			pstmt.setString(4, bean.getEntryTime());
			pstmt.setBoolean(5, bean.isOccupied());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			int i=pstmt.executeUpdate();
			conn.commit();
			System.out.println(i + " record inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean.getId();
	}

	@Override
	public void update(SmartParkingBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		SmartParkingBean existbean = findByVehicleNumber(bean.getVehicleNumber());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("Voter Id  already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE "+getTable()+" SET VEHICLE_NUMBER=?, VEHICLE_TYPE=?,ENTRY_TIME=?,OCCUPIED=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getVehicleNumber());
			pstmt.setString(2, bean.getVehicleType());
			pstmt.setString(3, bean.getEntryTime());
			pstmt.setBoolean(4, bean.isOccupied());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());
			int i=pstmt.executeUpdate();
			System.out.println(i+" record updated");
			conn.commit(); // End transaction
			
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public SmartParkingBean findByVehicleNumber(String vehicleNumber) throws ApplicationException {
		 SmartParkingBean bean= findByUniqueColumn("vehicle_number", vehicleNumber);
		return bean;
	}
	
	@Override
	public String getWhereClause(SmartParkingBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getVehicleNumber() != null && bean.getVehicleNumber().length() > 0) {
				sql.append(" and vehicle_number like '" + bean.getVehicleNumber() + "%'");
			}
			if (bean.getVehicleType() != null && bean.getVehicleType().length() > 0) {
				sql.append(" and vehicle_type like '" + bean.getVehicleType() + "%'");
			}
			if (bean.getEntryTime() != null && bean.getEntryTime().length() > 0) {
				sql.append(" and entry_time like '" + bean.getEntryTime() + "%'");
			}
			if (bean.isOccupied()) {
			    sql.append(" and occupied = " + bean.isOccupied());
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "smart_parking";
	}

	@Override
	public SmartParkingBean getBean() {
		return new SmartParkingBean();
	}

}
