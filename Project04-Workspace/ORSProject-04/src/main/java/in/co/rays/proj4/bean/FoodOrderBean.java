package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodOrderBean extends BaseBean {
	private String orderId;
	private String customerName;
	private String restaurant;
	private double orderAmount;
	private String deliveryStatus;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setOrderId(rs.getString("order_id"));
			this.setCustomerName(rs.getString("customer_name"));
			this.setRestaurant(rs.getString("restaurant"));
			this.setOrderAmount(rs.getDouble("order_amount"));
			this.setDeliveryStatus(rs.getString("delivery_status"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		return null;
	}

}
