package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass= true;
		return pass;
	}

	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {
		MarksheetBean bean =new MarksheetBean();
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

	@Override
	protected MarksheetModel getModel() {
		return new MarksheetModel();
	}

}
