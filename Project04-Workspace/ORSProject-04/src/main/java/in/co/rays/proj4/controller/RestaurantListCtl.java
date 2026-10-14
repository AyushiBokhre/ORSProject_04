package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RestaurantBean;
import in.co.rays.proj4.model.RestaurantModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/RestaurantListCtl")
public class RestaurantListCtl extends BaseListCtl<RestaurantBean, RestaurantModel>{
	
	 @Override
	protected RestaurantBean populateBean(HttpServletRequest request) {
		RestaurantBean bean =new RestaurantBean();
		bean.setItemId(request.getParameter("itemId"));
		bean.setItemName(request.getParameter("itemName"));
		bean.setCategory(request.getParameter("category"));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setAvailable(DataUtility.getBoolean(request.getParameter("isAvailable")));
		return bean;
		
	}

	@Override
	protected String getView() {
		return ORSView.RESTAURANT_LIST_VIEW;
	}

	@Override
	protected RestaurantModel getModel() {
		return new RestaurantModel();
	}

}
