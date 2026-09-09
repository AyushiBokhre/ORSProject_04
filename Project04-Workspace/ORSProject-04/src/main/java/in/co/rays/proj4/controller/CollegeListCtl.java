package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/CollegeListCtl")
public class CollegeListCtl extends BaseListCtl<CollegeBean, CollegeModel> {
@Override
protected CollegeBean populateBean(HttpServletRequest request) {
	CollegeBean bean=new CollegeBean();
	bean.setName(DataUtility.getString(request.getParameter("name")));
	bean.setAddresss(DataUtility.getString(request.getParameter("address")));
	return bean;
}
	@Override
	protected String getView() {
		return ORSView.COLLEGE_LIST_VIEW;
	}

	@Override
	protected CollegeModel getModel() {
		return new CollegeModel();
	}

}
