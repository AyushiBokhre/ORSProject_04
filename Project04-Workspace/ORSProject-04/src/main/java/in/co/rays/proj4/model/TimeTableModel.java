package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.TimeTableBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class TimeTableModel extends BaseModel<TimeTableBean>{

	@Override
	public long add(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		TimeTableBean existBean = findBySemester(bean.getSemester());

		if (existBean != null) {
			throw new DuplicateRecordException("TimeTable already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getSemester());
			pstmt.setString(3, bean.getDescription());
			pstmt.setDate(4, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(5, bean.getExamTime());
			pstmt.setLong(6, bean.getCourseId());
			pstmt.setString(7, bean.getCourseName());
			pstmt.setLong(8, bean.getSubjectId());
			pstmt.setString(9, bean.getSubjectName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			int i=pstmt.executeUpdate();
			conn.commit();
			System.out.println(i+" record added successfully");

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
			
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean.getId();
	}

	@Override
	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		TimeTableBean existbean = findBySemester(bean.getSemester());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("TimeTable is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_	TIMETABLE  SET SEMESTER=?,DESCRIPTION=?,EXAM_DATE=?,EXAM_TIME=?,COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
			
			pstmt.setString(1, bean.getSemester());
			pstmt.setString(2, bean.getDescription());
			pstmt.setDate(3, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(4, bean.getExamTime());
			pstmt.setLong(5, bean.getCourseId());
			pstmt.setString(6, bean.getCourseName());
			pstmt.setLong(7, bean.getSubjectId());
			pstmt.setString(8, bean.getSubjectName());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
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

	public 	TimeTableBean findBySemester(String semester) {

		TimeTableBean bean = findByUniqueColumn("semester", semester);

		return bean;

	}
	
	@Override
	public String getWhereClause(TimeTableBean bean) {
		StringBuffer sql =new StringBuffer("");
		if(bean!=null) {
			if(bean.getId()>0) {
				sql.append(" and id = " +bean.getId());
			}
			if(bean.getSemester()!=null && bean.getSemester().length()>0) {
				sql.append(" and semester like '" +bean.getSemester()+ "%'");
			}
		
			if(bean.getCourseId()>0  ) {
				sql.append(" and course_id like '" +bean.getCourseId()+ "%'");
			}
			
			if(bean.getCourseName()!=null && bean.getCourseName().length()>0) {
				sql.append(" and course_name like '" +bean.getCourseName()+ "%'");
			}
			
			if(bean.getSubjectId()>0 ){
				sql.append(" and subject_id like '" +bean.getSubjectId()+"%'");
			}
		
			if(bean.getSubjectName()!=null && bean.getSubjectName().length()>0) {
				sql.append(" and subject_name like '" +bean.getSubjectName()+ "%'");
			}
		
			
		}
			
		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_timetable";
	}

	@Override
	public TimeTableBean getBean() {
		return new TimeTableBean();
	}

	
}
