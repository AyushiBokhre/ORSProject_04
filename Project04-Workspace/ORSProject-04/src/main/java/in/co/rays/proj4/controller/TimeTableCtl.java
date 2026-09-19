package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.bean.TimeTableBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.model.TimeTableModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;


@WebServlet("/ctl/TimeTableCtl")
public class TimeTableCtl extends BaseCtl<TimeTableBean, TimeTableModel>{
@Override
protected void preload(HttpServletRequest request) {
	CourseModel cmodel =new CourseModel();
	List<CourseBean> courseList=cmodel.list();
	request.setAttribute("courseList",courseList);
	
	SubjectModel smodel =new SubjectModel();
	List<SubjectBean> subjectList=smodel.list();
	request.setAttribute("subjectList", subjectList);
	super.preload(request);
}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

	@Override
	protected TimeTableModel getModel() {
		return new TimeTableModel();
	}
	@Override
	protected TimeTableBean populateBean(HttpServletRequest request) {
		TimeTableBean bean =new TimeTableBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
//		bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
//		bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));;
		return bean;
	}
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass= true;
		if (DataValidator.isNull(request.getParameter("semester"))) {
		    request.setAttribute("semester", "semester no is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("description"))) {
		    request.setAttribute("description", "description is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("examDate"))) {
		    request.setAttribute("examDate", "Exam Date is required");
		    pass = false;
		}

		if (DataValidator.isNull(request.getParameter("examTime"))) {
		    request.setAttribute("examTime", "Exam Time is required");
		    pass = false;
		}

		if (DataValidator.isNull(request.getParameter("courseId"))) {
		    request.setAttribute("courseId", "course Id is required");
		    pass = false;
		}
//		if (DataValidator.isNull(request.getParameter("courseName"))) {
//		    request.setAttribute("courseName", "course Name is required");
//		    pass = false;
//		}
		if (DataValidator.isNull(request.getParameter("subjectId"))) {
		    request.setAttribute("subjectId", "subject Id is required");
		    pass = false;
		}
		
//		if (DataValidator.isNull(request.getParameter("subjectName"))) {
//		    request.setAttribute("subjectName", "subject Name is required");
//		    pass = false;
//		}
//		

		return pass;
	}

}
