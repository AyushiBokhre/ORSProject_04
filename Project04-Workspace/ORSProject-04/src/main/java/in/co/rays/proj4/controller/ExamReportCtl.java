package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.ExamBean;
import in.co.rays.proj4.model.ExamModel;

public class ExamReportCtl extends BaseReportCtl<ExamBean>{

	@Override
	public String getView() {
		return ORSView.EXAM_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "EXAM_LIST_COMPILED_REPORT";
	}

	@Override
	public List<ExamBean> getList() {
		 ExamModel model = new ExamModel();
	        @SuppressWarnings("unchecked")
	        List<ExamBean> colleges = model.list();
	        return colleges;
	}

}
