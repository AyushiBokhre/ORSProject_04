package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.http.HttpServletRequest;

public class CourseCtl extends BaseCtl<CourseBean, CourseModel>{
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {
		CourseBean bean = new CourseBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		return bean;
	}
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass=true;
		if (DataValidator.isNull(request.getParameter("name"))) {
		    request.setAttribute("name", "Course Name is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("description"))) {
		    request.setAttribute("description", "description is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("duration"))) {
		    request.setAttribute("duration", "duration is required");
		    pass = false;
		} 
		return pass;
	}
	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

	@Override
	protected CourseModel getModel() {
		return new CourseModel();
	}

}
