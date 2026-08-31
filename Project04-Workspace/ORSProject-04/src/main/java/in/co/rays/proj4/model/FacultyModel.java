package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FacultyModel extends BaseModel<FacultyBean>{

	@Override
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("Faculty already exist");
		}
		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPK(bean.getCollegeId());
		if (cbean != null) {
			bean.setCollegeName(cbean.getName());
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getCollegeName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());	
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getMobileNo());	
			pstmt.setString(8, bean.getAddress());
			pstmt.setString(9, bean.getGender());
			pstmt.setDate(10,new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());

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
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("Faculty already exist");
		}
		
		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPK(bean.getCollegeId());
		if (cbean != null) {
			bean.setCollegeName(cbean.getName());
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set college_id=?,college_name=?,first_name=?,last_name=?,email=?,mobile_no=?,address=?,gender=?,date_of_birth=?,modified_by=?,modified_datetime=? where id=?");
			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setString(5, bean.getEmail());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getGender());
			pstmt.setDate(9, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		
	}
	public FacultyBean findByEmail(String email) {

		FacultyBean bean = findByUniqueColumn("email", email);

		return bean;

	}
	@Override
	public String getWhereClause(FacultyBean bean) {
		StringBuffer sql =new StringBuffer("");
		if(bean!=null) {
			if(bean.getId()>0) {
				sql.append(" and id = " +bean.getId());
			}
			if(bean.getCollegeId()>0) {
				sql.append(" and college_id = " +bean.getCollegeId());
			}
			if(bean.getCollegeName()!=null && bean.getCollegeName().length()>0) {
				sql.append(" and colleg_name like '" +bean.getCollegeName()+ "%'");
			}
			if(bean.getFirstName()!=null && bean.getFirstName().length()>0 ){
				sql.append(" and first_name like '" +bean.getFirstName()+"%'");
			}
			if(bean.getLastName()!=null && bean.getLastName().length()>0) {
				sql.append(" and last_name like '" +bean.getLastName()+ "%'");
			}
			if(bean.getEmail()!=null && bean.getEmail().length()>0) {
				sql.append(" and email = " +bean.getEmail());
			}
			if(bean.getMobileNo()!=null && bean.getMobileNo().length()>0 ){
				sql.append(" and mobile_no like '" +bean.getMobileNo()+"%'");
			}
			if(bean.getAddress()!=null) {
				sql.append(" and address like '" +bean.getAddress()+ "%'");
			}
			if(bean.getGender()!=null) {
				sql.append(" and gender like '" +bean.getGender()+ "%'");
			}
		}
			
		return sql.toString();
	}
	

	@Override
	public String getTable() {
		return "st_faculty";
	}

	@Override
	public FacultyBean getBean() {
		return new FacultyBean();
	}

	
}
