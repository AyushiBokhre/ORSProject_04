package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.GymMemberBean;
import in.co.rays.proj4.model.GymMemberModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;


@WebServlet("/ctl/GymMemberListCtl")
public class GymMemberListCtl extends BaseListCtl<GymMemberBean, GymMemberModel> {
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
		return ORSView.GYM_MEMBER_LIST_VIEW;
	}

	@Override
	protected GymMemberModel getModel() {
		return new GymMemberModel();
	}

}
