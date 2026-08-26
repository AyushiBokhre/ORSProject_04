package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CourseModel extends BaseModel<CourseBean>{

	@Override
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
//		RoleBean existBean = findByName(bean.getName());
//
//		if (existBean != null) {
//			throw new DuplicateRecordException("role name already exist");
//		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

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
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
//		RoleBean existBean = findByName(bean.getName());
//
//		if (existBean != null && existBean.getId() != bean.getId()) {
//			throw new DuplicateRecordException("role name already exist");
//		}
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name=?,description=?,duration=?,modified_by=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getModifiedDatetime());
			pstmt.setLong(6, bean.getId());
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}


	}
	public CourseBean findByCourseName(String name) {

		CourseBean bean = findByUniqueColumn("name", name);

		return bean;

	}

	@Override
	public String getWhereClause(CourseBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTable() {
		return "st_course";
	}

	@Override
	public CourseBean getBean() {
		return new CourseBean();
	}

	

}
