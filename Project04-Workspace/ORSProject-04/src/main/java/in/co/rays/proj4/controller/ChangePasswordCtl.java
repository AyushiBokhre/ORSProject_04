package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl<UserBean, UserModel>{

	@Override
	protected String getView() {
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

	@Override
	protected UserModel getModel() {
		return new UserModel();
	}
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			pass = false;
			request.setAttribute("login", "login is required");
		}else if (!DataValidator.isEmail(request.getParameter("login"))) {
		    request.setAttribute("login", "Login is not in valid format");
		    pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			pass = false;
			request.setAttribute("newPassword", "New password is required");
		}
		
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			pass = false;
			request.setAttribute("oldPassword", "Old password is required");
		}

		return pass;
	}
	
	@Override
	protected UserBean populateBean(HttpServletRequest request) {
		UserBean bean=new UserBean();
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		return bean;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserModel model = getModel();
		UserBean bean = populateBean(request);
		String newPassword = request.getParameter("newPassword");
		String oldPassword = request.getParameter("oldPassword");

		bean = model.changePassword(newPassword, oldPassword, bean.getLogin());

		if (bean != null) {
			ServletUtility.setSuccessMessage("password changed successfully check your mail", request);
		} else {
			ServletUtility.setErrorMessage("something went wrong please verify you emial again", request);
		}

		ServletUtility.forward(getView(), request, response);

	}

}
