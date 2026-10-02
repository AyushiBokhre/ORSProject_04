package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.model.HospitalModel;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * HospitalCtl handles requests related to hospital patient information.
 * It validates patient details, populates the HospitalBean,
 * and provides the model and view for hospital operations.
 *
 * @author Aayushi
 * @version 1.0
 */
@WebServlet("/ctl/HospitalCtl")
public class HospitalCtl extends BaseCtl<HospitalBean, HospitalModel>{
	
	/**
	 * Validates the hospital patient information received from the request.
	 *
	 * @param request HTTP servlet request
	 * @return true if all patient details are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("patientId"))) {
		    request.setAttribute("patientId", "patient ID is required");
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
		if (DataValidator.isNull(request.getParameter("bloodGroup"))) {
		    request.setAttribute("bloodGroup", "bloodGroup is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("disease"))) {
		    request.setAttribute("disease", "It is required");
		    pass = false;
		}

		return pass;
	}
	
	/**
	 * Populates the HospitalBean with patient information
	 * received from the HTTP request.
	 *
	 * @param request HTTP servlet request
	 * @return populated HospitalBean object
	 */
	@Override
	protected HospitalBean populateBean(HttpServletRequest request) {
		HospitalBean bean =new HospitalBean();
		bean.setId(Integer.parseInt(request.getParameter("id")));
		bean.setPatientId(request.getParameter("patientId"));
		bean.setName(request.getParameter("name"));
		bean.setAge(Integer.parseInt(request.getParameter("age")));
		bean.setBloodGroup(request.getParameter("bloodGroup"));
		bean.setDisease(request.getParameter("disease"));
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Returns the view for hospital patient operations.
	 *
	 * @return hospital view
	 */
	@Override
	protected String getView() {
		return ORSView.HOSPITAL_VIEW;
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

