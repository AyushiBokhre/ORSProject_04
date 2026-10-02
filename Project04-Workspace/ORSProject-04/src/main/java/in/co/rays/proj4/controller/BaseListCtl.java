package in.co.rays.proj4.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Base list controller class that provides common functionality for
 * listing, searching, deleting and navigating records.
 *
 * @param <B> the bean type
 * @param <M> the model type
 * @author Aayushi
 * @version 1.0
 */
public abstract class BaseListCtl<B extends BaseBean, M extends BaseModel> extends BaseCtl<B, M> {

	/**
	 * Handles HTTP GET requests and retrieves the first page of records.
	 *
	 * @param request HTTP servlet request
	 * @param response HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		int pageSize = 10;
		BaseBean bean = populateBean(request);
		M model = getModel();
		List<B> list = model.search(bean, pageNo, pageSize);

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests for deleting, searching and navigating
	 * through records.
	 *
	 * @param request HTTP servlet request
	 * @param response HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		BaseBean bean = populateBean(request);
		M model = getModel();

		if (OP_DELETE.equals(op)) {
			String[] ids = request.getParameterValues("ids");
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					model.delete(DataUtility.getInt(id));
					ServletUtility.setSuccessMessage("records deleted successfully", request);
				}
			} else {
				ServletUtility.setErrorMessage("select at least one record", request);
			}
		}

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = 10;

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		}

		if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		}

		if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo--;
		}

		List<B> list = model.search(bean, pageNo, pageSize);

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}

		ServletUtility.forward(getView(), request, response);

	}

}

