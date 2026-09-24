package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.FoodOrderBean;
import in.co.rays.proj4.model.FoodOrderModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FoodOrderListCtl")
public class FoodOrderListCtl extends BaseListCtl<FoodOrderBean, FoodOrderModel> {
	@Override
	protected FoodOrderBean populateBean(HttpServletRequest request) {
		FoodOrderBean bean =new FoodOrderBean();
		bean.setOrderId(request.getParameter("orderId"));
		bean.setCustomerName(request.getParameter("customerName"));
		bean.setRestaurant(request.getParameter("restaurant"));
		bean.setOrderAmount(DataUtility.getDouble(request.getParameter("orderAmount")));
		bean.setDeliveryStatus(request.getParameter("deliveryStatus"));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.FOOD_ORDER_LIST_VIEW;
	}

	@Override
	protected FoodOrderModel getModel() {
		return new FoodOrderModel();
	}

}
