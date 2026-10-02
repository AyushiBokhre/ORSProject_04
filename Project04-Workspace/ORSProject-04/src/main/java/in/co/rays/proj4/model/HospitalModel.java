package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class HospitalModel extends BaseModel<HospitalBean> {

	@Override
	public long add(HospitalBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		HospitalBean existbean = findByPatientId(bean.getPatientId());

		if (existbean != null) {
			throw new DuplicateRecordException("Patient Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO "+ getTable() +" VALUES(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getPatientId());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getAge());
			pstmt.setString(5, bean.getBloodGroup());
			pstmt.setString(6, bean.getDisease());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

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
	public void update(HospitalBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		HospitalBean existbean = findByPatientId(bean.getPatientId());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("Patient Id  already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE "+getTable()+" SET PATIENT_ID=?, NAME=?,AGE=?,BLOOD_GROUP=?,DISEASES=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getPatientId());
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getAge());
			pstmt.setString(4, bean.getBloodGroup());
			pstmt.setString(5, bean.getDisease());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
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
	public String getWhereClause(HospitalBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getPatientId() != null && bean.getPatientId().length() > 0) {
				sql.append(" and patient_id like '" + bean.getPatientId() + "%'");
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getAge()  > 0) {
				sql.append(" and age = " + bean.getAge());
			}
			if (bean.getBloodGroup() != null && bean.getBloodGroup().length() > 0) {
				sql.append(" and blood_group like '" + bean.getBloodGroup() + "%'");
			}
			if (bean.getDisease() != null && bean.getDisease().length() > 0) {
				sql.append(" and disease like '" + bean.getDisease() + "%'");
			}
		}

		return sql.toString();
	}

	public HospitalBean findByPatientId(String patientId) throws ApplicationException {
		HospitalBean bean = findByUniqueColumn("patient_id", patientId);
		return bean;
	}

	@Override
	public String getTable() {
		return "st_hospital";
	}

	@Override
	public HospitalBean getBean() {
		return new HospitalBean();
	}

}
