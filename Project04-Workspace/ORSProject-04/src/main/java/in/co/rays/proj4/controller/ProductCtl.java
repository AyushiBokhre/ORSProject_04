package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ProductBean;
import in.co.rays.proj4.model.ProductModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ProductCtl")
public class ProductCtl extends BaseCtl<ProductBean, ProductModel>{
	
	@Override
	protected ProductBean populateBean(HttpServletRequest request) {
		ProductBean bean=new ProductBean();
		bean.setProductName(request.getParameter("productName"));
		bean.setCategory(request.getParameter("category"));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		bean.setDiscount(DataUtility.getDouble(request.getParameter("discount")));
		populateDTO(bean, request);
		return bean;
	}
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass=true;
		if (DataValidator.isNull(request.getParameter("productName"))) {
		    request.setAttribute("productName", "Product Name is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("category"))) {
		    request.setAttribute("category", "category is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("price"))) {
		    request.setAttribute("price", "price is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("quantity"))) {
		    request.setAttribute("quantity", "quantity is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("discount"))) {
		    request.setAttribute("discount", "discount is required");
		    pass = false;
		} 
		
		return pass;
	}

	@Override
	protected String getView() {
		return ORSView.PRODUCT_VIEW;
	}

	@Override
	protected ProductModel getModel() {
		return new ProductModel();
	}

}
