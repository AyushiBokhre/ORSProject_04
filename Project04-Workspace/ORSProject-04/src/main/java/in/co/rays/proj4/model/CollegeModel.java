package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel extends BaseModel<CollegeBean> {

	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
//		RoleBean existBean = findByName(bean.getName());
//
//		if (existBean != null) {
//			throw new DuplicateRecordException("role name already exist");
//		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddresss());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean.getId();

	}

	@Override
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
//		RoleBean existBean = findByName(bean.getName());
//
//		if (existBean != null && existBean.getId() != bean.getId()) {
//			throw new DuplicateRecordException("role name already exist");
////		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name=?, address=?,state=?,city=?,phone_no=?,modifid_by=?,modified_datetime=? where id=? ");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddresss());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	public CollegeBean findByCollegeName(String name) {

		CollegeBean bean = findByUniqueColumn("name", name);

		return bean;

	}


	@Override
	public String getWhereClause(CollegeBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTable() {
		return "st_college";
	}

	@Override
	public CollegeBean getBean() {
		return new CollegeBean();
	}

}
