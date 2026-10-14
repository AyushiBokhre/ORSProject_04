package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.RestaurantBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class RestaurantModel extends BaseModel<RestaurantBean>{

	@Override
	public long add(RestaurantBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		RestaurantBean existBean = findByItemName(bean.getItemName());

		if (existBean != null) {
			throw new DuplicateRecordException("College Name already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getItemId());
			pstmt.setString(3, bean.getItemName());
			pstmt.setString(4, bean.getCategory());
			pstmt.setDouble(5, bean.getPrice());
			pstmt.setBoolean(6, bean.isAvailable());
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
	public void update(RestaurantBean bean) throws ApplicationException, DuplicateRecordException {
		 Connection conn = null;

		 RestaurantBean existBean = findByItemName(bean.getItemName());

		    if (existBean != null && existBean.getId() != bean.getId()) {
		        throw new DuplicateRecordException("College Name already exist");
		    }

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set item_id=?, item_name=?,category=?,price=?,is_available=?,modified_by=?,modified_datetime=? where id=? ");

			pstmt.setString(1, bean.getItemId());
			pstmt.setString(2, bean.getItemName());
			pstmt.setString(3, bean.getCategory());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setBoolean(5, bean.isAvailable());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			int i =pstmt.executeUpdate();
			conn.commit();
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	public RestaurantBean findByItemName(String itemName) {

		RestaurantBean bean = findByUniqueColumn("item_name", itemName);

		return bean;
	}

	@Override
	public String getWhereClause(RestaurantBean bean) {
		StringBuffer sql = new StringBuffer("");
		if (bean != null) {
			if (bean.getItemId()!=null && bean.getItemId().length()>0) {
				sql.append(" and item_id = " + bean.getItemId());
			}
			if (bean.getItemName()!=null && bean.getItemName().length()>0) {
				sql.append(" and item_name like '" + bean.getItemName() + "%'");
			}
			if (bean.getCategory()!=null && bean.getCategory().length()>0) {
				sql.append(" and category like '" + bean.getCategory() + "%'");
			}
			if (bean.getPrice()>0) {
				sql.append(" and price =" + bean.getPrice());
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_restaurant";
	}

	@Override
	public RestaurantBean getBean() {
		return new RestaurantBean();
	}

}
