package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * MyProfileCtl handles the user's profile information.
 * It validates profile data, populates the UserBean,
 * and provides the model and view required for the profile page.
 *
 * @author Aayushi
 * @version 1.0
 */
@WebServlet("/ctl/MyProfileCtl")
public class MyProfileCtl extends BaseCtl<UserBean, UserModel> {

	/**
	 * Validates the user profile information received from the request.
	 *
	 * @param request HTTP servlet request
	 * @return true if all profile fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "firstName is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
		} else if (!request.getParameter("login").matches("[A-Za-z][A-Za-z0-9_]*")) {
			request.setAttribute("login", "login is not in valid formate");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			pass = false;
		}
		return pass;
	}

	/**
	 * Populates the UserBean with profile information received from the request.
	 *
	 * @param request HTTP servlet request
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRoleId(DataUtility.getInt(request.getParameter("roleId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
//		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		populateDTO(bean, request); // Its work is to set only 4 attributes

		return bean;
	}

	/**
	 * Returns the view for the user's profile page.
	 *
	 * @return profile view
	 */
	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

	/**
	 * Returns the UserModel instance used by this controller.
	 *
	 * @return UserModel object
	 */
	@Override
	protected UserModel getModel() {
		return new UserModel();
	}

}

