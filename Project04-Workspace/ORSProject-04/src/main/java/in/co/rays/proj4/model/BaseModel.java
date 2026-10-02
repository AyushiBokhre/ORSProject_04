package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM " + getTable());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

			rs.close();

		} catch (SQLException e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
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

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("delete from " + getTable() + " where id = ?");

			pstmt.setInt(1, id);

			int i = pstmt.executeUpdate();
			System.out.println("record deleted: " + i);

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
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

		T bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("select * from " + getTable() + " where id = ?");

			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultset(rs);
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
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

		T bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("select * from " + getTable()
							+ " where " + column + "='" + value + "'");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultset(rs);
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"Exception: in findByUniqueColumn, " + column + " " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
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

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultset(rs);
				list.add(bean);
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(
					"Exception : Exception in search(bean, pageNo, pageSize)");
		} finally {
			JDBCDataSource.closeConnection(conn);
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

		ArrayList<T> list = new ArrayList<T>();
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from " + getTable());

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				T bean = getBean();
				bean.setResultset(rs);
				list.add(bean);
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(
					"Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
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
		return list(0, 0);
	}
}

