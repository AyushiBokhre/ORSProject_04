package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.bean.ProductBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class ProductModel extends BaseModel<ProductBean>{
	public static Logger log = Logger.getLogger(ProductModel.class);

	@Override
	public long add(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.info("ProductModel add() method started");
		Connection conn = null;
		ProductBean existBean = findByProductName(bean.getProductName());

		if (existBean != null) {
			log.warn("product already exists : " + bean.getProductName());
			throw new DuplicateRecordException("Product Name already exist");
		}

		try {
			
			log.debug("Getting database connection for adding product");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getProductName());
			pstmt.setString(3, bean.getCategory());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setInt(5, bean.getQuantity());
			pstmt.setDouble(6, bean.getDiscount());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			
			log.info("Product added successfully " );

		} catch (Exception e) {
			log.error("Exception while adding product: " + bean.getProductName(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed after add()");
		}

		return bean.getId();
	}

	@Override
	public void update(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.info("EventModel update() method started for event ID: " + bean.getId());

		Connection conn = null;
		ProductBean existbean = findByProductName(bean.getProductName());

		if (existbean != null && !(existbean.getId() == bean.getId())) {
			log.warn("Duplicate product found while updating event: " + bean.getProductName());
			throw new DuplicateRecordException("Product is already exist");
		}

		try {

			log.debug("Getting database connection for updating Subject");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE"+getTable()+"SET PRODUCT_NAME=?,CATEGORY=?,PRICE=?,QUANTITY=?,DISCOUNT=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getProductName());
			pstmt.setString(2, bean.getCategory());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setInt(5, bean.getQuantity());
			pstmt.setDouble(6, bean.getDiscount());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();

			System.out.println(i + " record updated");

			conn.commit(); // End transaction

			log.info("product updated successfully with ID: " + bean.getId());

			pstmt.close();

		} catch (SQLException e) {
			log.error("Exception while updating product ID: " + bean.getId(), e);
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed after update()");
		}
	}
	public ProductBean findByProductName(String producttName) {
		ProductBean bean=findByUniqueColumn("product_name", producttName);
		return bean; 
	}
	

	@Override
	public String getWhereClause(ProductBean bean) {
		StringBuffer sql = new StringBuffer("");

	if (bean != null) {
		if (bean.getProductName() != null && bean.getProductName().length() > 0) {
			sql.append(" and product_name like '" + bean.getProductName() + "%'");
		}
		if (bean.getCategory() != null && bean.getCategory().length() > 0) {
			sql.append(" and category like '" + bean.getCategory() + "%'");
		}
		if (bean.getPrice()  > 0) {
			sql.append(" and price = " + bean.getPrice());
		}
		if (bean.getQuantity()  > 0) {
			sql.append(" and quantity = " + bean.getQuantity());
		}
		if (bean.getDiscount()  > 0) {
			sql.append(" and discount = " + bean.getDiscount());
		}
	}
	return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_product";
	}

	@Override
	public ProductBean getBean() {
		return new ProductBean();
	}

}
