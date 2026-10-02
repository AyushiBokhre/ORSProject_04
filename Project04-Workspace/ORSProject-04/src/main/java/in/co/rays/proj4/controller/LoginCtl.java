package in.co.rays.proj4.controller; 
 
import java.io.IOException; 
 
import in.co.rays.proj4.bean.RoleBean; 
import in.co.rays.proj4.bean.UserBean; 
import in.co.rays.proj4.model.RoleModel; 
import in.co.rays.proj4.model.UserModel; 
import in.co.rays.proj4.util.DataUtility; 
import in.co.rays.proj4.util.DataValidator; 
import in.co.rays.proj4.util.ServletUtility; 
import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse; 
import jakarta.servlet.http.HttpSession; 
 
/**
 * LoginCtl handles user login and logout operations.
 * It validates login credentials, authenticates the user,
 * stores user and role information in the session, and redirects
 * the user to the welcome page after successful login.
 *
 * @author Aayushi
 * @version 1.0
 */
@WebServlet("/LoginCtl") 
public class LoginCtl extends BaseCtl<UserBean, UserModel> { 
 
	public static final String OP_SIGN_IN = "SignIn"; 
 
	/**
	 * Validates the login and password entered by the user.
	 *
	 * @param request HTTP servlet request
	 * @return true if the login details are valid, otherwise false
	 */
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
		if (DataValidator.isNull(request.getParameter("password"))) { 
			pass = false; 
			request.setAttribute("password", "password is required"); 
		} 
		else if (!DataValidator.isPassword(request.getParameter("password"))) { 
		    request.setAttribute("password", "Password is not in valid format"); 
		    pass = false; 
		} 
 
		return pass; 
	} 
 
	/**
	 * Populates the UserBean with login credentials received from the request.
	 *
	 * @param request HTTP servlet request
	 * @return populated UserBean object
	 */
	@Override 
	protected UserBean populateBean(HttpServletRequest request) { 
 
		UserBean bean = new UserBean(); 
 
		bean.setLogin(DataUtility.getString(request.getParameter("login"))); 
		bean.setPassword(DataUtility.getString(request.getParameter("password"))); 
 
		return bean; 
	} 
 
	/**
	 * Handles GET requests for login and logout operations.
	 *
	 * @param request HTTP servlet request
	 * @param response HTTP servlet response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
 
		String op = DataUtility.getString(request.getParameter("operation")); 
 
		if (op != null) { 
			HttpSession session = request.getSession(); 
			session.invalidate(); 
			ServletUtility.setSuccessMessage("user logout successfully", request); 
		} 
 
		ServletUtility.forward(getView(), request, response); 
 
	} 
 
	/**
	 * Handles POST requests for user authentication.
	 *
	 * @param request HTTP servlet request
	 * @param response HTTP servlet response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
 
		String op = DataUtility.getString(request.getParameter("operation")); 
		HttpSession session = request.getSession(); 
 
		UserBean bean = populateBean(request); 
 
		if (OP_SIGN_IN.equalsIgnoreCase(op)) { 
			UserModel m = getModel(); 
			bean = m.authenticate(bean.getLogin(), bean.getPassword()); 
 
			if (bean != null) { 
				session.setAttribute("user", bean); 
				RoleModel rmodel = new RoleModel(); 
				RoleBean rbean = rmodel.findByPK(bean.getRoleId()); 
				if (rbean != null) { 
					session.setAttribute("role", rbean.getName()); 
				} 
				ServletUtility.redirect(ORSView.WELCOME_CTL, request, response); 
				return; 
			} else { 
//				request.setAttribute("error", "Invalid login or password"); 
				ServletUtility.setErrorMessage("Invalid login or password", request); 
			} 
		} 
 
		ServletUtility.forward(getView(), request, response); 
	} 
 
	/**
	 * Returns the view for the login page.
	 *
	 * @return login page view
	 */
	@Override 
	protected String getView() { 
		return ORSView.LOGIN_VIEW; 
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

