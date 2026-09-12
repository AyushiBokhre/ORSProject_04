package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FacultyListCtl")
public class FacultyListCtl extends BaseListCtl<FacultyBean, FacultyModel> {
	
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {
		FacultyBean bean=new FacultyBean();
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
//		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
//		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		

		populateDTO(bean, request);
		
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_LIST_VIEW;
	}

	@Override
	protected FacultyModel getModel() {
		return new FacultyModel();
	}

}
