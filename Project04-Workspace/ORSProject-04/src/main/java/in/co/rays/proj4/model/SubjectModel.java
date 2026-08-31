package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SubjectModel extends BaseModel<SubjectBean>{

	@Override
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("Subject already exist");
		}


		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8 ,bean.getModifiedDatetime());

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
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("Subject already exist");
		}


		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name=?,description=?,course_id=?,modified_by=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setLong(3, bean.getCourseId());
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
	
	public SubjectBean findByName(String name) {

		SubjectBean bean = findByUniqueColumn("name", name);

		return bean;

	}
	@Override
	public String getWhereClause(SubjectBean bean) {
		StringBuffer sql =new StringBuffer("");
		if(bean!=null) {
			if(bean.getId()>0) {
				sql.append(" and id = " +bean.getId());
			}
			if(bean.getName()!=null && bean.getName().length()>0) {
				sql.append(" and name like '" +bean.getName()+ "%'");
			}
			if(bean.getDescription()!=null && bean.getDescription().length()>0 ){
				sql.append(" and description like '" +bean.getDescription()+"%'");
			}
			if(bean.getCourseId()>0) {
				sql.append(" and course like '" +bean.getCourseId()+ "%'");
			}
		}
			
		return sql.toString();
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_subject";
	}

	@Override
	public SubjectBean getBean() {
		return new SubjectBean();
	}

	

}
