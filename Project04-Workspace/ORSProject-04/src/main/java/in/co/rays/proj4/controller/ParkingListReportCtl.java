package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.SmartParkingBean;
import in.co.rays.proj4.model.SmartParkingModel;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ctl/ParkingListReportCtl")
public class ParkingListReportCtl extends BaseReportCtl<SmartParkingBean>{

	@Override
	public String getView() {
		return ORSView.SMART_PARKING_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "PARKING_LIST_COMPILED_REPORT";
	}

	@Override
	public List<SmartParkingBean> getList() {
		 SmartParkingModel model = new SmartParkingModel();
	        @SuppressWarnings("unchecked")
	        List<SmartParkingBean> parking = model.list();
	        return parking;
	}

}
