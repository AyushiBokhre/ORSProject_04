package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel extends BaseModel<StudentBean> {

	@Override
	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("Email already exist");
		}
		
		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPK(bean.getCollegeId());
		if (cbean != null) {
			bean.setCollegeName(cbean.getName());
		}
		
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getCollegeName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setDate(6, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(7,bean.getMobileNo());
			pstmt.setString(8,bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12 ,bean.getModifiedDatetime());

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
	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("Email already exist");
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
					+ " set college_id=?,college_name=?,first_name=?,last_name=?,date_of_birth=?,mobile_no=?,email=?,modified_by=?,modified_datetime=? where id=?");
			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setDate(5, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getModifiedBy());	
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	public StudentBean findByEmail(String email) {

		StudentBean bean = findByUniqueColumn("email", email);

		return bean;

	}

	@Override
	public String getWhereClause(StudentBean bean) {
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
			if(bean.getDateOfBirth()!=null && bean.getDateOfBirth().getTime()>0) {
				sql.append(" and date_of_birth like '" +bean.getDateOfBirth()+ "%'");
			}
			if(bean.getEmail()!=null && bean.getEmail().length()>0) {
				sql.append(" and email = " +bean.getEmail());
			}
			if(bean.getMobileNo()!=null && bean.getMobileNo().length()>0 ){
				sql.append(" and mobile_no like '" +bean.getMobileNo()+"%'");
			}
			
		}
			
		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_student";
	}

	@Override
	public StudentBean getBean() {
		return new StudentBean();
	}

}
