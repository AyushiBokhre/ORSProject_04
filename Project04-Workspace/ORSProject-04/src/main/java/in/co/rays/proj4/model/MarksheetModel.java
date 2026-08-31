package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MarksheetModel extends BaseModel<MarksheetBean>{

	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		
			Connection conn = null;
			MarksheetBean existBean = findByRollNo(bean.getRollNo());

			if (existBean != null) {
				throw new DuplicateRecordException("Roll No already exist");
			}
			StudentModel smodel = new StudentModel();
			StudentBean sbean = smodel.findByPK(bean.getStudentId());
			bean.setName(sbean.getFirstName() + " " + sbean.getLastName());

			try {

				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?)");
				pstmt.setInt(1, nextPK());
				pstmt.setString(2, bean.getRollNo());
				pstmt.setLong(3, bean.getStudentId());
				pstmt.setString(4, bean.getName());
				pstmt.setInt(5, bean.getPhysics());
				pstmt.setInt(6,bean.getChemistry());
				pstmt.setInt(7,bean.getMaths());
				pstmt.setString(8, bean.getCreatedBy());
				pstmt.setString(9, bean.getModifiedBy());
				pstmt.setTimestamp(10, bean.getCreatedDatetime());
				pstmt.setTimestamp(11 ,bean.getModifiedDatetime());

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
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		MarksheetBean existBean = findByRollNo(bean.getRollNo());

		if (existBean != null) {
			throw new DuplicateRecordException("Roll No already exist");
		}
		StudentModel smodel = new StudentModel();
		StudentBean sbean = smodel.findByPK(bean.getStudentId());
		bean.setName(sbean.getFirstName() + " " + sbean.getLastName());
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set roll_no=?,student_id=?,name=?,physics=?,chemistry=?,maths=?,modified_by=?,modified_datetime=? where id=?");
			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}


	}
	
	public MarksheetBean findByRollNo(String rollNo) {

		MarksheetBean bean = findByUniqueColumn("roll_no", rollNo);

		return bean;

	}
		
	

	@Override
	public String getWhereClause(MarksheetBean bean) {
		StringBuffer sql =new StringBuffer("");
		if(bean!=null) {
			if(bean.getId()>0) {
				sql.append(" and id = " +bean.getId());
			}
			if(bean.getRollNo()!=null && bean.getRollNo().length()>0) {
				sql.append(" and roll_no like '" +bean.getRollNo()+ "%'");
			}
			if(bean.getStudentId()>0) {
				sql.append(" and student_id = " +bean.getStudentId()+ "%'");
			}
			if(bean.getName()!=null  && bean.getName().length()>0) {
				sql.append(" and name like '" +bean.getName()+ "%'");
			}
			if(bean.getPhysics()>0 ){
				sql.append(" and physics like '" +bean.getPhysics()+"%'");
			}
			if(bean.getChemistry()>0) {
				sql.append(" and chemistry like '" +bean.getChemistry()+ "%'");
			}
			if(bean.getMaths()>0) {
				sql.append(" and maths like '" +bean.getMaths()+ "%'");
			}
		}
			
		return sql.toString();
	
	}

	@Override
	public String getTable() {
		return "st_marksheet";
	}

	@Override
	public MarksheetBean getBean() {
		return new MarksheetBean();
	}

	
}
