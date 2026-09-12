package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/StudentCtl")
public class StudentCtl extends BaseCtl<StudentBean, StudentModel> {
	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel cmodel =new CollegeModel();
		List<CollegeBean> collegeList=cmodel.list();
		request.setAttribute("collegeList", collegeList);
		super.preload(request);
	}

	@Override
	protected StudentBean populateBean(HttpServletRequest request) {
		StudentBean bean=new StudentBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("login")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		populateDTO(bean, request);
		
		return bean;
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
		    request.setAttribute("firstName", "First name is required");
		    pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
		    request.setAttribute("firstName", "First name should contain only alphabets.");
		    pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
		    request.setAttribute("lastName", "Last name is required");
		    pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
		    request.setAttribute("lastName", "Last name should contain only alphabets.");
		    pass = false;
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
		    request.setAttribute("email", "email is required");
		    pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
		    request.setAttribute("email", "email is not in valid format");
		    pass = false;
		}

		

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
		    request.setAttribute("collegeId", "college Name is required");
		    pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
		    request.setAttribute("dob", "Date of birth is required");
		    pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
		    request.setAttribute("mobileNo", "Mobile number is required");
		    pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
		    request.setAttribute("mobileNo", "Mobile number is not in valid format");
		    pass = false;
		}

		return pass;
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_VIEW;
	}

	@Override
	protected StudentModel getModel() {
		return new StudentModel();
	}

}
