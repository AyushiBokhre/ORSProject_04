package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ExamBean;
import in.co.rays.proj4.model.ExamModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ExamListCtl")
public class ExamListCtl extends BaseListCtl<ExamBean, ExamModel>{
	@Override
	protected ExamBean populateBean(HttpServletRequest request) {
		ExamBean bean=new ExamBean();
		bean.setSubject(request.getParameter("subject"));
		bean.setTotalQuestion(DataUtility.getInt(request.getParameter("totalQuestions")));
		bean.setDuration(DataUtility.getInt(request.getParameter("duration")));
		bean.setTotalMarks(DataUtility.getInt(request.getParameter("totalMarks")));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EXAM_LIST_VIEW;
	}

	@Override
	protected ExamModel getModel() {
		return new ExamModel();
	}

}
