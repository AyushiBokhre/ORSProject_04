package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel>{
	@Override
	protected void preload(HttpServletRequest request) {
			CollegeModel cmodel =new CollegeModel();
			List<CollegeBean> collegeList=cmodel.list();
			request.setAttribute("collegeList", collegeList);
			super.preload(request);
	}
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {
		FacultyBean bean=new FacultyBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("login")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		populateDTO(bean, request);
		
		return bean;
	}
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
		    request.setAttribute("collegeId", "college Name is required");
		    pass = false;
		}
		
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

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
		    request.setAttribute("mobileNo", "Mobile number is required");
		    pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
		    request.setAttribute("mobileNo", "Mobile number is not in valid format");
		    pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("address"))) {
		    request.setAttribute("address", "address is required");
		    pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("gender"))) {
		    request.setAttribute("gender", "gender is required");
		    pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("dob"))) {
		    request.setAttribute("dob", "Date of birth is required");
		    pass = false;
		}


		return pass;
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

	@Override
	protected FacultyModel getModel() {
		return new FacultyModel();
	}

}
