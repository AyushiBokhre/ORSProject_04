package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.TimeTableBean;
import in.co.rays.proj4.model.TimeTableModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/TimeTableListCtl")
public class TimeTableListCtl extends BaseListCtl<TimeTableBean, TimeTableModel>{
	@Override
	protected TimeTableBean populateBean(HttpServletRequest request) {
		TimeTableBean bean = new TimeTableBean();
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));
		return bean;
	}

	@Override
	protected String getView() {		
		return ORSView.TIMETABLE_LIST_VIEW;
	}

	@Override
	protected TimeTableModel getModel() {
		return new TimeTableModel();
	}

}
