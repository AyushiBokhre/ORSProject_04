package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.GymMemberBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class GymMemberModel extends BaseModel<GymMemberBean> {

	@Override
	public long add(GymMemberBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
//		GymMemberBean existbean = findByVehicleNumber(bean.getVehicleNumber());
//
//		if (existbean != null) {
//			throw new DuplicateRecordException("Voter Id already exists");
//		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO "+getTable()+" VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getMembershipType());
			pstmt.setString(4, bean.getJoiningDate());
			pstmt.setString(5, bean.getTrainerName());
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
	public void update(GymMemberBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
//		GymMemberBean existbean = findByVehicleNumber(bean.getVehicleNumber());
//
//		if (existbean != null && !(existbean.getId() == bean.getId())) {
//			throw new DuplicateRecordException("Voter Id  already exist");
//		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE "+getTable()+" SET NAME=?, MEMBERSHIP_TYPE=?,JOINING_DATE=?,TRAINER_NAME=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMembershipType());
			pstmt.setString(3, bean.getJoiningDate());
			pstmt.setString(4, bean.getTrainerName());
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

	@Override
	public String getWhereClause(GymMemberBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getMembershipType() != null && bean.getMembershipType().length() > 0) {
				sql.append(" and membership_type like '" + bean.getMembershipType() + "%'");
			}
			if (bean.getJoiningDate() != null && bean.getJoiningDate().length() > 0) {
				sql.append(" and joining_date like '" + bean.getJoiningDate() + "%'");
			}
			if (bean.getTrainerName() != null && bean.getTrainerName().length() > 0) {
				sql.append(" and trainer_name like '" + bean.getTrainerName() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_gym_member";
	}

	@Override
	public GymMemberBean getBean() {
		return new GymMemberBean();
	}

}
