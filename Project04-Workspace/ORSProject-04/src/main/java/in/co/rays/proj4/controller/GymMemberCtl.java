package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.GymMemberBean;
import in.co.rays.proj4.model.GymMemberModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/GymMemberCtl")
public class GymMemberCtl extends BaseCtl<GymMemberBean, GymMemberModel> {
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
		    request.setAttribute("name", "name is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("membershipType"))) {
		    request.setAttribute("membershipType", "membershipType is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("joiningDate"))) {
		    request.setAttribute("joiningDate", "joiningDate is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("trainerName"))) {
		    request.setAttribute("trainerName", "trainerName is required");
		    pass = false;
		}
	
		return pass;
	}

	@Override
	protected GymMemberBean populateBean(HttpServletRequest request) {
		GymMemberBean bean = new GymMemberBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(request.getParameter("name"));
		bean.setMembershipType(request.getParameter("membershipType"));
		bean.setJoiningDate(request.getParameter("joiningDate"));
		bean.setTrainerName(request.getParameter("trainerName"));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.GYM_MEMBER_VIEW;
	}

	@Override
	protected GymMemberModel getModel() {
		return new GymMemberModel();
	}

}
