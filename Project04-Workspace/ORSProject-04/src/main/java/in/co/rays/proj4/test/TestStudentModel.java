package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {
	public static StudentModel model=new StudentModel();
	public static void main(String[] args) throws Exception {
//		testAdd();
//		testDelete();
//		testUpdate();
		testFindByPk();
//		testFindByEmail();
	}
	public static void testAdd() throws Exception {
		StudentBean bean=new StudentBean();
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		bean.setCollegeId(4);
		bean.setCollegeName("DAVV");
		bean.setFirstName("Neha");
		bean.setLastName("Singh");
		bean.setDateOfBirth(sdf.parse("09/11/2003"));
		bean.setMobileNo("9876543218");
		bean.setEmail("neha@gmail.com");
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {
		StudentBean bean=new StudentBean();
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		bean.setCollegeId(4);
		bean.setCollegeName("DAVV");
		bean.setFirstName("Neha");
		bean.setLastName("Sharma");
		bean.setDateOfBirth(sdf.parse("09/11/2003"));
		bean.setMobileNo("9876543218");
		bean.setEmail("neha@gmail.com");
		bean.setModifiedBy("ayushi");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(9);

		model.update(bean);
	}
	public static void testDelete() throws Exception {
		model.delete(24);
		
	}
	public static void testFindByPk() {

		StudentBean bean = new StudentBean();

		bean = model.findByPK(6);

		System.out.println(bean.getId());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getDateOfBirth());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getEmail());
	

	}

	public static void testFindByEmail() {

		StudentBean bean = new StudentBean();

		bean = model.findByEmail("tanisha@gmail.com");

		System.out.println(bean.getId());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getDateOfBirth());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getEmail());

	}
}



