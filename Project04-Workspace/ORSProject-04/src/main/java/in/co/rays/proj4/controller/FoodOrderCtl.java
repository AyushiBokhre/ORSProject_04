package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.FoodOrderBean;
import in.co.rays.proj4.bean.VoterBean;
import in.co.rays.proj4.model.FoodOrderModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FoodOrderCtl")
public class FoodOrderCtl extends BaseCtl<FoodOrderBean, FoodOrderModel> {
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("orderId"))) {
		    request.setAttribute("orderId", "Order ID is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("customerName"))) {
		    request.setAttribute("customer_name", "customer_name is required");
		    pass = false;
		} else if (!DataValidator.isName(request.getParameter("customerName"))) {
		    request.setAttribute("customerName", "Name should contain only alphabets.");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("restaurant"))) {
		    request.setAttribute("restaurant", "restaurant is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("orderAmount"))) {
		    request.setAttribute("orderAmount", "order Amount is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("deliveryStatus"))) {
		    request.setAttribute("deliveryStatus", "deliveryStatus is required");
		    pass = false;
		}

		return pass;
	}

	@Override
	protected FoodOrderBean populateBean(HttpServletRequest request) {
		FoodOrderBean bean =new FoodOrderBean();
		bean.setId(DataUtility.getInt(request.getParameter("id")));
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
		return ORSView.FOOD_ORDER_VIEW;
	}

	@Override
	protected FoodOrderModel getModel() {
		return new FoodOrderModel();
	}

}
