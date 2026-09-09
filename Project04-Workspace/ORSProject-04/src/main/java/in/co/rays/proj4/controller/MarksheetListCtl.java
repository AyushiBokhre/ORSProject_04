package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/MarksheetListCtl")
public class MarksheetListCtl extends BaseListCtl<MarksheetBean, MarksheetModel> {
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {
		MarksheetBean bean = new MarksheetBean();
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_LIST_VIEW;
	}

	@Override
	protected MarksheetModel getModel() {
		return new MarksheetModel();
	}

}
