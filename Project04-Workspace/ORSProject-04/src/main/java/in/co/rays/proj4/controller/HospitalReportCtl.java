package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.model.HospitalModel;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ctl/HospitalReportCtl")
public class HospitalReportCtl extends BaseReportCtl<HospitalBean> {

    /**
     * Fetches all Patient data from the database.
     *
     * @return list of all {@link CollegeBean} records
     */
    public List<HospitalBean> getList() {
        HospitalModel model = new HospitalModel();
        @SuppressWarnings("unchecked")
        List<HospitalBean> patients = model.list();
        return patients;
    }

    /**
     * Returns the JRXML template path for the list report.
     *
     * @return {@link ORSView#HOSPITAL_REPORT_VIEW}
     */
    public String getView() {
        return ORSView.HOSPITAL_REPORT_VIEW;
    }

    /**
     * Returns the ServletContext cache key for the compiled  report.
     *
     * @return {@code "HOSPITAL_LIST_COMPILED_REPORT"}
     */
    public String getCompiledReportKey() {
        return "HOSPITAL_LIST_COMPILED_REPORT";
    }
}
