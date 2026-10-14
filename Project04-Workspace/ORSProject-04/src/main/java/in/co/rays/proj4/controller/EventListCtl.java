package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.model.EventModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/EventListCtl")
public class EventListCtl extends BaseListCtl<EventBean, EventModel> {
	@Override
	protected EventBean populateBean(HttpServletRequest request) {
		EventBean bean= new EventBean();
		bean.setEventName(request.getParameter("eventName"));
		bean.setDescription(request.getParameter("description"));
		bean.setEventDate(DataUtility.getDate(request.getParameter("event_date")));
		bean.setVenue(request.getParameter("venue"));
		bean.setOrganizer(request.getParameter("organizer"));
		bean.setStatus(request.getParameter("status"));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EVENT_LIST_VIEW;
	}

	@Override
	protected EventModel getModel() {
		return new EventModel();
	}

}
