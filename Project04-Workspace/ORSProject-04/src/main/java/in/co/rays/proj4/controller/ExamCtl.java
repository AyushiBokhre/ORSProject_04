package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ExamBean;
import in.co.rays.proj4.model.ExamModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ExamCtl")
public class ExamCtl extends BaseCtl<ExamBean, ExamModel>{
	@Override
	protected ExamBean populateBean(HttpServletRequest request) {
		ExamBean bean=new ExamBean();
		bean.setSubject(request.getParameter("subject"));
		bean.setTotalQuestion(DataUtility.getInt(request.getParameter("totalQuestions")));
		bean.setDuration(DataUtility.getInt(request.getParameter("duration")));
		bean.setTotalMarks(DataUtility.getInt(request.getParameter("totalMarks")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass=true;
		if (DataValidator.isNull(request.getParameter("subject"))) {
		    request.setAttribute("subject", "subject is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("totalQuestions"))) {
		    request.setAttribute("totalQusetions", "totalQusetions is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("duration"))) {
		    request.setAttribute("duration", "duration is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("totalMarks"))) {
		    request.setAttribute("totalMarks", "totalMarks is required");
		    pass = false;
		} 
		
		return pass;
	}
	@Override
	protected String getView() {
		return ORSView.EXAM_VIEW;
	}

	@Override
	protected ExamModel getModel() {
		return new ExamModel();
	}

}
