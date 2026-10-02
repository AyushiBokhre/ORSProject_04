package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.model.HospitalModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * HospitalListCtl handles the listing and searching of hospital patient records.
 * It extends BaseListCtl to provide common list controller functionality.
 *
 * @author Aayushi
 * @version 1.0
 */
@WebServlet("/ctl/HospitalListCtl")
public class HospitalListCtl extends BaseListCtl<HospitalBean, HospitalModel>{

	/**
	 * Populates the HospitalBean with patient information
	 * received from the HTTP request.
	 *
	 * @param request HTTP servlet request
	 * @return populated HospitalBean object
	 */
	@Override
	protected HospitalBean populateBean(HttpServletRequest request) {
		HospitalBean bean = new HospitalBean();
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setPatientId(request.getParameter("patientId"));
		bean.setName(request.getParameter("name"));
		bean.setAge(DataUtility.getInt(request.getParameter("age")));
		bean.setBloodGroup(request.getParameter("bloodGroup"));
		bean.setDisease(request.getParameter("disease"));
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Returns the view for the hospital patient list.
	 *
	 * @return hospital list view
	 */
	@Override
	protected String getView() {
		return ORSView.HOSPITAL_LIST_VIEW;
	}

	/**
	 * Returns the HospitalModel instance used by this controller.
	 *
	 * @return HospitalModel object
	 */
	@Override
	protected HospitalModel getModel() {
		return new HospitalModel();
	}

}

