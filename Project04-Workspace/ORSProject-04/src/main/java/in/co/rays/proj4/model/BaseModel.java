package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * Base model class that provides common database functionality
 * for all model classes.
 *
 * @param <T> the bean type
 * @author Aayushi
 * @version 1.0
 */
public abstract class BaseModel<T extends BaseBean> {

	private static Logger log = Logger.getLogger(BaseModel.class);

	/**
	 * Adds a new record to the database.
	 *
	 * @param bean bean containing record data
	 * @return primary key of the newly added record
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if the record already exists
	 */
	public abstract long add(T bean) throws ApplicationException, DuplicateRecordException;

	/**
	 * Updates an existing record in the database.
	 *
	 * @param bean bean containing updated record data
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if the record already exists
	 */
	public abstract void update(T bean) throws ApplicationException, DuplicateRecordException;

	/**
	 * Returns the WHERE clause used for searching records.
	 *
	 * @param bean bean containing search criteria
	 * @return WHERE clause as String
	 */
	public abstract String getWhereClause(T bean);

	/**
	 * Returns the database table name associated with the model.
	 *
	 * @return table name
	 */
	public abstract String getTable();

	/**
	 * Creates and returns a new bean object.
	 *
	 * @return new bean object
	 */
	public abstract T getBean();

	/**
	 * Returns the next primary key value for the table.
	 *
	 * @return next primary key value
	 * @throws DatabaseException if a database error occurs
	 */
	public Integer nextPK() throws DatabaseException {

		log.info("nextPK() method started");

		Connection conn = null;
		int pk = 0;

		try {

			log.debug("Getting database connection for nextPK()");

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM " + getTable());

			log.debug("Executing query to get maximum ID from table: " + getTable());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

			rs.close();

			log.info("Next primary key generated: " + (pk + 1));

		} catch (SQLException e) {

			log.error("Exception while getting next primary key from table: " + getTable(), e);

			throw new DatabaseException("Exception : Exception in getting PK");

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed after nextPK()");

		}

		return pk + 1;
	}

	/**
	 * Deletes a record from the database using its primary key.
	 *
	 * @param id primary key of the record to be deleted
	 * @throws DatabaseException if a database error occurs
	 */
	public void delete(int id) throws DatabaseException {

		log.info("delete() method started for ID: " + id);

		Connection conn = null;

		try {

			log.debug("Getting database connection for delete()");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("delete from " + getTable() + " where id = ?");

			pstmt.setInt(1, id);

			log.debug("Executing delete query for ID: " + id);

			int i = pstmt.executeUpdate();

			System.out.println("record deleted: " + i);

			conn.commit();

			log.info("Record deleted successfully for ID: " + id);

		} catch (SQLException e) {

			log.error("Exception while deleting record with ID: " + id, e);

			e.printStackTrace();

			JDBCDataSource.trnRollBack(conn);

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed after delete()");

		}
	}

	/**
	 * Finds a record by its primary key.
	 *
	 * @param pk primary key of the record
	 * @return bean containing the record data, or null if no record is found
	 * @throws ApplicationException if an application error occurs
	 */
	public T findByPK(long pk) throws ApplicationException {

		log.info("findByPK() method started for ID: " + pk);

		T bean = null;
		Connection conn = null;

		try {

			log.debug("Getting database connection for findByPK()");

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("select * from " + getTable() + " where id = ?");

			pstmt.setLong(1, pk);

			log.debug("Executing findByPK query for ID: " + pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = getBean();
				bean.setResultset(rs);

			}

			rs.close();

			if (bean != null) {
				log.info("Record found successfully for ID: " + pk);
			} else {
				log.warn("No record found for ID: " + pk);
			}

		} catch (Exception e) {

			log.error("Exception while finding record by PK: " + pk, e);

			e.printStackTrace();

			throw new ApplicationException("Exception : Exception in getting User by pk");

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed after findByPK()");

		}

		return bean;
	}

	/**
	 * Finds a record using a unique column and its value.
	 *
	 * @param column name of the unique column
	 * @param value value of the unique column
	 * @return bean containing the record data, or null if no record is found
	 */
	public T findByUniqueColumn(String column, String value) {

		log.info("findByUniqueColumn() started for column: " + column);

		T bean = null;
		Connection conn = null;

		try {

			log.debug("Getting database connection for findByUniqueColumn()");

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("select * from " + getTable()
							+ " where " + column + "='" + value + "'");

			log.debug("Executing findByUniqueColumn query for column: " + column);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = getBean();
				bean.setResultset(rs);

			}

			rs.close();

			if (bean != null) {
				log.info("Record found for column: " + column);
			} else {
				log.warn("No record found for column: " + column);
			}

		} catch (Exception e) {

			log.error("Exception in findByUniqueColumn for column: " + column, e);

			e.printStackTrace();

			System.out.println(
					"Exception: in findByUniqueColumn, " + column + " " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed after findByUniqueColumn()");

		}

		return bean;
	}

	/**
	 * Searches records using search criteria and pagination.
	 * The search criteria are obtained from the getWhereClause() method.
	 *
	 * @param bean bean containing search criteria
	 * @param pageNo page number to retrieve
	 * @param pageSize number of records per page
	 * @return list of matching records
	 * @throws ApplicationException if an application error occurs
	 */
	public List<T> search(T bean, int pageNo, int pageSize) throws ApplicationException {

		log.info("search() method started");

		ArrayList<T> list = new ArrayList<T>();
		Connection conn = null;

		StringBuffer sql = new StringBuffer(
				"select * from " + getTable() + " where 1=1");

		// Add search filter from child model.
		sql.append(this.getWhereClause(bean));

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql===> " + sql.toString());

		log.debug("Search SQL: " + sql.toString());

		try {

			log.debug("Getting database connection for search()");

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = getBean();
				bean.setResultset(rs);
				list.add(bean);

			}

			rs.close();

			log.info("Search completed successfully. Records found: " + list.size());

		} catch (Exception e) {

			log.error("Exception while searching records", e);

			e.printStackTrace();

			throw new ApplicationException(
					"Exception : Exception in search(bean, pageNo, pageSize)");

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed after search()");

		}

		return list;
	}

	/**
	 * Returns a paginated list of all records without applying
	 * any search filter.
	 *
	 * @param pageNo page number to retrieve
	 * @param pageSize number of records per page
	 * @return list of records
	 * @throws ApplicationException if an application error occurs
	 */
	public List<T> list(int pageNo, int pageSize) throws ApplicationException {

		log.info("list(pageNo, pageSize) method started");

		ArrayList<T> list = new ArrayList<T>();
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from " + getTable());

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		log.debug("List SQL: " + sql.toString());

		try {

			log.debug("Getting database connection for list()");

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				T bean = getBean();
				bean.setResultset(rs);
				list.add(bean);

			}

			rs.close();

			log.info("List completed successfully. Records found: " + list.size());

		} catch (Exception e) {

			log.error("Exception while getting list of records", e);

			e.printStackTrace();

			throw new ApplicationException(
					"Exception : Exception in getting list of users");

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed after list()");

		}

		return list;
	}

	/**
	 * Returns all records from the database without pagination
	 * and without applying any search filter.
	 *
	 * @return list of all records
	 * @throws ApplicationException if an application error occurs
	 */
	public List<T> list() throws ApplicationException {

		log.info("list() method started");

		return list(0, 0);
	}
}
