package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.SmartParkingBean;
import in.co.rays.proj4.model.SmartParkingModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/SmartParkingCtl")
public class SmartParkingCtl extends BaseCtl<SmartParkingBean, SmartParkingModel> {
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
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("vehicleNumber"))) {
		    request.setAttribute("vehicleNumber", "vehicleNumber is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("vehicleType"))) {
		    request.setAttribute("vehicleType", "vehicleType is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("entryTime"))) {
		    request.setAttribute("entryTime", "entryTime is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("occupied"))) {
		    request.setAttribute("occupied", "occupied is required");
		    pass = false;
		}
	
		return pass;
	}
	

	@Override
	protected String getView() {
		return ORSView.SMART_PARKING_VIEW;
	}

	@Override
	protected SmartParkingModel getModel() {
		return new SmartParkingModel();
	}

}
