package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/RoleCtl")
public class RoleCtl extends BaseCtl<RoleBean, RoleModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
		    request.setAttribute("name", "Role name is required");
		    pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
		    request.setAttribute("name", "Role name should contain only alphabets.");
		    pass = false;
		}

		if (request.getParameter("description").equals("")) {
			request.setAttribute("description", "description is required");
			pass = false;
		}

		return pass;
	}

	@Override
	protected RoleBean populateBean(HttpServletRequest request) {

		RoleBean bean = new RoleBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

	@Override
	protected RoleModel getModel() {
		return new RoleModel();
	}

}