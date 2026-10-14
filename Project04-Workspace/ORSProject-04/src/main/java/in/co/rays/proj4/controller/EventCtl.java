package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.model.EventModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/EventCtl")
public class EventCtl extends BaseCtl<EventBean, EventModel> {
	@Override
	protected EventBean populateBean(HttpServletRequest request) {
		EventBean bean= new EventBean();
		bean.setEventName(request.getParameter("eventName"));
		bean.setDescription(request.getParameter("description"));
		bean.setEventDate(DataUtility.getDate(request.getParameter("eventDate")));
		bean.setVenue(request.getParameter("venue"));
		bean.setOrganizer(request.getParameter("organizer"));
		bean.setStatus(request.getParameter("status"));
		return bean;
		}
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass=true;
		if (DataValidator.isNull(request.getParameter("eventName"))) {
		    request.setAttribute("eventName", "event Name is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("description"))) {
		    request.setAttribute("description", "description is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("eventDate"))) {
		    request.setAttribute("eventDate", "event date is required");
		    pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("venue"))) {
		    request.setAttribute("venue", "venue is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("organizer"))) {
		    request.setAttribute("organizer", "organizer is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("status"))) {
		    request.setAttribute("status", "status is required");
		    pass = false;
		} 
		return pass;
	}

	@Override
	protected String getView() {
		return ORSView.EVENT_VIEW;
	}

	@Override
	protected EventModel getModel() {
		return new EventModel();
	}

}

