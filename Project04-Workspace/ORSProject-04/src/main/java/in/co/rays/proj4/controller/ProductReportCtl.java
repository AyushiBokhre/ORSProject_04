package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.ProductBean;
import in.co.rays.proj4.model.ProductModel;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ctl/ProductReportCtl")
public class ProductReportCtl extends BaseReportCtl<ProductBean>{

	@Override
	public String getView() {
		return ORSView.PRODUCT_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "PRODUCT_REPORT_COMPILED_REPORT";
	}

	@Override
	public List<ProductBean> getList() {
		 ProductModel model = new ProductModel();
	        @SuppressWarnings("unchecked")
	        List<ProductBean> product = model.list();
	        return product;
	}

}
