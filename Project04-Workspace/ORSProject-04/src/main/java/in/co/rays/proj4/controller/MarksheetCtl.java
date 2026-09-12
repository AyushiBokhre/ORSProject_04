package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {
	@Override
	protected void preload(HttpServletRequest request) {
		StudentModel smodel =new StudentModel();
		List<StudentBean> studentList=smodel.list();
		request.setAttribute("studentList", studentList);
		super.preload(request);
	}
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass= true;
		if (DataValidator.isNull(request.getParameter("rollNo"))) {
		    request.setAttribute("rollNo", "Roll no is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("studentId"))) {
		    request.setAttribute("studentId", "student name is required");
		    pass = false;
		} 
		

		if (DataValidator.isNull(request.getParameter("physics"))) {
		    request.setAttribute("physics", "physics marks is required");
		    pass = false;
		}

		if (DataValidator.isNull(request.getParameter("chemistry"))) {
		    request.setAttribute("chemistry", "chemistry marks is required");
		    pass = false;
		}

		if (DataValidator.isNull(request.getParameter("maths"))) {
		    request.setAttribute("maths", "Maths marks is required");
		    pass = false;
		}

		return pass;
	}

	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {
		MarksheetBean bean =new MarksheetBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));;
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
