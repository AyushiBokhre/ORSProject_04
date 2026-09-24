package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.bean.VoterBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class VoterModel extends BaseModel<VoterBean> {

	@Override
	public long add(VoterBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		VoterBean existbean = findByVoterId(bean.getVoterId());

		if (existbean != null) {
			throw new DuplicateRecordException("Voter Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_VOTER VALUES(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getVoterId());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getAge());
			pstmt.setString(5, bean.getConstituency());
			pstmt.setBoolean(6, bean.isHasVoted());
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
	public void update(VoterBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		VoterBean existbean = findByVoterId(bean.getVoterId());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("Voter Id  already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_VOTER SET VOTER_ID=?, NAME=?,AGE=?,CONSTITUENCY=?,HAS_VOTED=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getVoterId());
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getAge());
			pstmt.setString(4, bean.getConstituency());
			pstmt.setBoolean(5, bean.isHasVoted());
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
	

	public VoterBean findByVoterId(String voterId) throws ApplicationException {
		VoterBean bean = findByUniqueColumn("voter_id", voterId);
		return bean;
	}

	

	@Override
	public String getWhereClause(VoterBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getVoterId() != null && bean.getVoterId().length() > 0) {
				sql.append(" and voter_id like '" + bean.getVoterId() + "%'");
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getAge()  > 0) {
				sql.append(" and age = " + bean.getAge());
			}
			if (bean.getConstituency() != null && bean.getConstituency().length() > 0) {
				sql.append(" and constituency like '" + bean.getConstituency() + "%'");
			}
			

		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_voter";
	}

	@Override
	public VoterBean getBean() {
		return new VoterBean();
	}

}
