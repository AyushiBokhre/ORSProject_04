package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ctl/GetMarksheetCtl")
public class GetMarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String rollNo = DataUtility.getString(request.getParameter("rollNo"));

		if (rollNo == null || rollNo.trim().isEmpty()) {
			ServletUtility.setErrorMessage("Roll No is required", request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		MarksheetBean bean = getModel().findByRollNo(rollNo);

		if (bean == null) {
			ServletUtility.setErrorMessage(
					"Marksheet not found for Roll No : " + rollNo, request);
		} else {
			ServletUtility.setBean(bean, request);
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.GET_MARKSHEET_VIEW;
	}

	@Override
	protected MarksheetModel getModel() {
		return new MarksheetModel();
	}
}