package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.SmartParkingBean;
import in.co.rays.proj4.bean.VoterBean;
import in.co.rays.proj4.model.SmartParkingModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/SmartParkingListCtl")
public class SmartParkingListCtl extends BaseListCtl<SmartParkingBean, SmartParkingModel> {
	@Override
	protected SmartParkingBean populateBean(HttpServletRequest request) {
		SmartParkingBean bean = new SmartParkingBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setVehicleNumber(request.getParameter("vehicleNumber"));
		bean.setVehicleType(request.getParameter("vehicleType"));
		bean.setEntryTime(request.getParameter("entryTime"));
		bean.setOccupied(DataUtility.getBoolean(request.getParameter("occupied")));
		populateDTO(bean, request);
		return bean;
		
	}

	@Override
	protected String getView() {
		return ORSView.SMART_PARKING_LIST_VIEW;
	}

	@Override
	protected SmartParkingModel getModel() {
		return new SmartParkingModel();
	}

}
