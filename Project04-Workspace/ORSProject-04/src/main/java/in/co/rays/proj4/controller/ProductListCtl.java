package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ProductBean;
import in.co.rays.proj4.model.ProductModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ProductListCtl")
public class ProductListCtl extends BaseListCtl<ProductBean, ProductModel>{
	@Override
	protected ProductBean populateBean(HttpServletRequest request) {
		ProductBean bean=new ProductBean();
		bean.setProductName(request.getParameter("productName"));
		bean.setCategory(request.getParameter("category"));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		bean.setDiscount(DataUtility.getDouble(request.getParameter("discount")));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.PRODUCT_LIST_VIEW;
	}

	@Override
	protected ProductModel getModel() {
		return new ProductModel();
	}

}
