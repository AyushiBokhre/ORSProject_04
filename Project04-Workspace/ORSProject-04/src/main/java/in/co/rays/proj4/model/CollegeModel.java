package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel extends BaseModel<CollegeBean> {

	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		CollegeBean existBean = findByCollegeName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("College Name already exist");
		}

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
		CollegeBean existBean = findByCollegeName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("College Name already exist");
		}

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
		StringBuffer sql =new StringBuffer("");
		if(bean!=null) {
			if(bean.getId()>0) {
				sql.append(" and id = " +bean.getId());
			}
			if(bean.getName()!=null && bean.getName().length()>0) {
				sql.append(" and name like '" +bean.getName()+ "%'");
			}
			if(bean.getAddresss()!=null && bean.getAddresss().length()>0 ){
				sql.append(" and address like '" +bean.getAddresss()+"%'");
			}
			if(bean.getState()!=null && bean.getState().length()>0) {
				sql.append(" and state like '" +bean.getState()+ "%'");
			}
			if(bean.getCity()!=null && bean.getCity().length()>0 ){
				sql.append(" and city like '" +bean.getCity()+"%'");
			}
			if(bean.getPhoneNo()!=null && bean.getPhoneNo().length()>0) {
				sql.append(" and phone_no like '" +bean.getPhoneNo()+ "%'");
			}
		}
			
		return sql.toString();
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
