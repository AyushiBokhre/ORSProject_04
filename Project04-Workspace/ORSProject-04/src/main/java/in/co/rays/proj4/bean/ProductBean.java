package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductBean extends BaseBean {
	public String productName;
	public String category;
	public double price;
	public int quantity;
	public double discount;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	@Override
	public void setResultset(ResultSet rs) {
		super.setResultset(rs);
		try {
			setProductName(rs.getString("product_name"));
			setCategory(rs.getString("category"));
			setPrice(rs.getDouble("price"));
			setQuantity(rs.getInt("quantity"));
			setDiscount(rs.getDouble("discount"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		return null;
	}
	

}
