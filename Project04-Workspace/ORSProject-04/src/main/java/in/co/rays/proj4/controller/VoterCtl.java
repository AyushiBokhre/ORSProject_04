package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.VoterBean;
import in.co.rays.proj4.model.VoterModel;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/VoterCtl")
public class VoterCtl extends BaseCtl<VoterBean, VoterModel> {
	@Override
	protected VoterBean populateBean(HttpServletRequest request) {
		VoterBean bean =new VoterBean();
		bean.setId(Integer.parseInt(request.getParameter("id")));
		bean.setVoterId(request.getParameter("voterId"));
		bean.setName(request.getParameter("name"));
		bean.setAge(Integer.parseInt(request.getParameter("age")));
		bean.setConstituency(request.getParameter("constituency"));
		bean.setHasVoted(Boolean.parseBoolean(request.getParameter("hasVoted")));
		populateDTO(bean, request);
		return bean;
	}
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("voterId"))) {
		    request.setAttribute("voterId", "Voter ID is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("name"))) {
		    request.setAttribute("name", "Name is required");
		    pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
		    request.setAttribute("name", "Name should contain only alphabets.");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("age"))) {
		    request.setAttribute("age", "Age is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("constituency"))) {
		    request.setAttribute("constituency", "constituency is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("hasVoted"))) {
		    request.setAttribute("hasVoted", "It is required");
		    pass = false;
		}

		return pass;
	}

	@Override
	protected String getView() {
		return ORSView.VOTER_VIEW;
	}

	@Override
	protected VoterModel getModel() {
		return new VoterModel();
	}

}
