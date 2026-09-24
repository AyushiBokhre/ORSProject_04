package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Base controller class that provides common functionality for all controllers.
 *
 * @param <B> the bean type
 * @param <M> the model type
 * @author Aayushi
 * @version 1.0
 */
public abstract class BaseCtl<B extends BaseBean, M extends BaseModel> extends HttpServlet {

	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";

	public static final String HAS_ERROR = "haserror";
	public static final String MESSAGE = "message";

	public static final String MSG_SUCCESS = "success";
	public static final String MSG_ERROR = "error";

	/**
	 * Validates the request parameters.
	 *
	 * @param request HTTP servlet request
	 * @return true if the request is valid, otherwise false
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Preloads data required by the controller.
	 *
	 * @param request HTTP servlet request
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates a bean from the request parameters.
	 *
	 * @param request HTTP servlet request
	 * @return populated bean
	 */
	protected B populateBean(HttpServletRequest request) {
		return null;
	}

	/**
	 * Populates audit information such as created by, modified by,
	 * created datetime and modified datetime.
	 *
	 * @param dto data transfer object
	 * @param request HTTP servlet request
	 * @return populated DTO
	 */
	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;
		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();

			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());

		return dto;
	}

	/**
	 * Handles HTTP GET requests and forwards the request to the appropriate view.
	 *
	 * @param request HTTP servlet request
	 * @param response HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			BaseBean bean = getModel().findByPK(id);
			ServletUtility.setBean(bean, request);
		}

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests for adding and updating records.
	 *
	 * @param request HTTP servlet request
	 * @param response HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an input or output error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		B bean = populateBean(request);
		M model = getModel();

		if (id > 0) {

			model.update(bean);
			ServletUtility.setSuccessMessage("record is successfully updated", request);

		} else {

			model.add(bean);
			ServletUtility.setSuccessMessage("record is successfully saved", request);
		}

		ServletUtility.setBean(bean, request);

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Processes the HTTP request and performs validation before handling
	 * GET or POST requests.
	 *
	 * @param request HTTP servlet request
	 * @param response HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		preload(request);

		if ("POST".equals(request.getMethod())) {

			if (validate(request) == false) {

				BaseBean bean = populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}

		try {

			super.service(request, response);

		} catch (DuplicateRecordException e) {

			ServletUtility.setErrorMessage(e.getMessage(), request);
			ServletUtility.forward(getView(), request, response);
		}
	}

	/**
	 * Returns the view path of the controller.
	 *
	 * @return view path
	 */
	protected abstract String getView();

	/**
	 * Returns the model associated with the controller.
	 *
	 * @return model object
	 */
	protected abstract M getModel();

	/**
	 * Returns the message source instance used for application messages.
	 *
	 * @param request HTTP servlet request
	 * @return MessageSource instance
	 */
	public MessageSource getMessageSource(HttpServletRequest request) {

		MessageSource messagesource = MessageSource.getInstance();
		return messagesource;
	}

}