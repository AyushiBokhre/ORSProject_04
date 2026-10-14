package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.model.EventModel;

public class EventReportCtl extends BaseReportCtl<EventBean>{

	@Override
	public String getView() {
		return ORSView.EXAM_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "EVENT_REPORT_LIST";
	}

	@Override
	public List<EventBean> getList() {
		EventModel model = new EventModel();
        @SuppressWarnings("unchecked")
        List<EventBean> events = model.list();
        return events;
	}
	
}
