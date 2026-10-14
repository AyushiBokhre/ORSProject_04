package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.ExamBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class ExamModel extends BaseModel<ExamBean>{
	private static Logger log = Logger.getLogger(UserModel.class);

	@Override
	public long add(ExamBean bean) throws ApplicationException, DuplicateRecordException {
		log.info("UserModel add() method started");

		Connection conn = null;
		ExamBean existBean = findBySubject(bean.getSubject());

		if (existBean != null) {
			log.warn("Subject already exists with login: " + bean.getSubject());
			throw new DuplicateRecordException("Subject Name already exist");
		}

		try {
			
			log.debug("Getting database connection for adding exam");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getSubject());
			pstmt.setInt(3, bean.getTotalQuestion());
			pstmt.setInt(4, bean.getDuration());
			pstmt.setInt(5, bean.getTotalMarks());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			
			log.info("Exam added successfully " );

		} catch (Exception e) {
			log.error("Exception while adding exam: " + bean.getSubject(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed after add()");
		}

		return bean.getId();
	}

	@Override
	public void update(ExamBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("UserModel update() method started for user ID: " + bean.getId());

		Connection conn = null;
		ExamBean existbean = findBySubject(bean.getSubject());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			log.warn("Duplicate subject found while updating user: " + bean.getSubject());
			throw new DuplicateRecordException("subject is already exist");
		}

		try {

			log.debug("Getting database connection for updating Subject");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE"+getTable()+" SUBJECT=?,TOTAL_QUESTIONS=?,DURATION=?,TOTAL_MARKS=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getSubject());
			pstmt.setInt(2, bean.getTotalQuestion());
			pstmt.setInt(3, bean.getDuration());
			pstmt.setInt(4, bean.getTotalMarks());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " record updated");

			conn.commit(); // End transaction

			log.info("Subject updated successfully with ID: " + bean.getId());

			pstmt.close();

		} catch (SQLException e) {
			log.error("Exception while updating subject ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed after update()");
		}
	}

	@Override
	public String getWhereClause(ExamBean bean) {
		log.debug("ExamModel getWhereClause() method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getSubject() != null && bean.getSubject().length() > 0) {
				sql.append(" and subject like '" + bean.getSubject() + "%'");
			}

			if (bean.getTotalQuestion() > 0) {
				sql.append(" and total_questions like '" + bean.getTotalQuestion() + "%'");
			}

			if (bean.getDuration() > 0) {
				sql.append(" and duration like '" + bean.getDuration() + "%'");
			}

			if (bean.getTotalMarks() > 0) {
				sql.append(" and total_marks like '" + bean.getTotalMarks() + "%'");
			}

		}

		log.debug("Generated ExamModel where clause: " + sql.toString());

		return sql.toString();
	}
	
	public ExamBean findBySubject(String subject) {
		ExamBean bean =findByUniqueColumn("subject", subject);
		return bean;
	}

	@Override
	public String getTable() {
		return "st_exam";
	}

	@Override
	public ExamBean getBean() {
		return new ExamBean();
	}

}
