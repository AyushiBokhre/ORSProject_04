package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {
	public static FacultyModel model=new FacultyModel();
	public static void main(String[] args) throws Exception {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
		testFindByEmail();
	}
	public static void testAdd() throws Exception {
		FacultyBean bean=new FacultyBean();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		bean.setCollegeId(4);
		bean.setCollegeName("IPS Academy");
		bean.setFirstName("Vikas");
		bean.setLastName("Chauhan");
		bean.setEmail("vikas@gmail.com");
		bean.setMobileNo("9876543214");
		bean.setAddress("Dewas");
		bean.setGender("Male");
		bean.setDob(sdf.parse("14/01/2000"));
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}
	public static void testUpdate() throws Exception {
		FacultyBean bean=new FacultyBean();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		bean.setCollegeId(1);
		bean.setCollegeName("Acropolis");
		bean.setFirstName("Rohit");
		bean.setLastName("Mishra");
		bean.setEmail("rohit@gmail.com");
		bean.setMobileNo("9876543212");
		bean.setAddress("Rau");
		bean.setGender("Male");
		bean.setDob(sdf.parse("22/07/2000"));
		bean.setModifiedBy("ayushi");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(7);
		model.update(bean);
	}
	public static void testDelete() throws Exception {
		model.delete(7);
	}
	public static void testFindByPk() {

	FacultyBean bean = new FacultyBean();

		bean = model.findByPK(2);

		System.out.println(bean.getId());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getEmail());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getAddress());
		System.out.println(bean.getGender());
		System.out.println(bean.getDob());
	}
	
	public static void testFindByEmail() {


		FacultyBean bean = new FacultyBean();

		bean = model.findByEmail("priya@gmail.com");

		System.out.println(bean.getId());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getEmail());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getAddress());
		System.out.println(bean.getGender());
		System.out.println(bean.getDob());
		


	}


}
