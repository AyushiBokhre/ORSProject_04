package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.VoterBean;
import in.co.rays.proj4.model.VoterModel;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/VoterListCtl")
public class VoterListCtl extends BaseListCtl<VoterBean, VoterModel> {
	@Override
	protected VoterBean populateBean(HttpServletRequest request) {
		VoterBean bean = new VoterBean();
		bean.setVoterId(request.getParameter("voterId"));
		bean.setConstituency(request.getParameter("constituency"));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.VOTER_LIST_VIEW;
	}

	@Override
	protected VoterModel getModel() {
		return new VoterModel();
	}

}
