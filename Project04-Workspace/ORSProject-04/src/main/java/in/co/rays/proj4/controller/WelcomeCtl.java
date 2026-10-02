package in.co.rays.proj4.controller;

import in.co.rays.proj4.model.BaseModel;
import jakarta.servlet.annotation.WebServlet;

/**
 * WelcomeCtl is a controller class that handles requests for the welcome page.
 *
 * @author Aayushi
 * @version 1.0
 */
@WebServlet("/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {

	/**
	 * Returns the view for the welcome page.
	 *
	 * @return welcome page view
	 */
	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

	/**
	 * Returns the model associated with this controller.
	 *
	 * @return BaseModel instance, or null if no model is required
	 */
	@Override
	protected BaseModel getModel() {
		return null;
	}

}

