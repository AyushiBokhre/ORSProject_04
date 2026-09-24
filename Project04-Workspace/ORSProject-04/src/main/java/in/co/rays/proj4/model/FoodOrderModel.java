package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.FoodOrderBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FoodOrderModel extends BaseModel<FoodOrderBean>{

	@Override
	public long add(FoodOrderBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		FoodOrderBean existbean = findByOrderId(bean.getOrderId());

		if (existbean != null) {
			throw new DuplicateRecordException("Voter Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO "+getTable()+" VALUES(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getOrderId());
			pstmt.setString(3, bean.getCustomerName());
			pstmt.setString(4, bean.getRestaurant());
			pstmt.setDouble(5, bean.getOrderAmount());
			pstmt.setString(6, bean.getDeliveryStatus());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			int i=pstmt.executeUpdate();
			conn.commit();
			System.out.println(i + " record inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean.getId();
	}

	@Override
	public void update(FoodOrderBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		FoodOrderBean existbean = findByOrderId(bean.getOrderId());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("Voter Id  already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE "+getTable()+" SET ORDER_ID=?, CUSTOME_NAME=?,RESTAURANT=?,ORDER_AMOUNT=?,DELIVERY_STATUS=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getOrderId());
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setString(3, bean.getRestaurant());
			pstmt.setDouble(4, bean.getOrderAmount());
			pstmt.setString(5, bean.getDeliveryStatus());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
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
	
		
	public FoodOrderBean findByOrderId(String orderId) throws ApplicationException {
		 FoodOrderBean bean= findByUniqueColumn("order_id", orderId);
		return bean;
	}

	@Override
	public String getWhereClause(FoodOrderBean bean) {
		StringBuffer sql =new StringBuffer();
		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_food_order";
	}

	@Override
	public FoodOrderBean getBean() {
		return new FoodOrderBean();
	}

}
