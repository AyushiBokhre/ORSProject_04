package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.CollegeModel;

public class TestCollegeModel {
	public static CollegeModel model=new CollegeModel();
	public static void main(String[] args) throws Exception {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
		testFindByCollegeName();
	}
	public static void testAdd() {
		CollegeBean bean=new CollegeBean();
	
		bean.setName("DAVV");
		bean.setAddresss("Nalanda Campus");
		bean.setCity("Indore");
		bean.setState("Madhya Pradesh");
		bean.setPhoneNo("5566778899");
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}
	public static void testUpdate() {
		CollegeBean bean=new CollegeBean();
		bean.setId(6);
		bean.setName("Acroplois Institute");
		bean.setAddresss("Manglia" );
		bean.setCity("Indore");
		bean.setState("Madhya Pradesh");
		bean.setPhoneNo("123456789");
		bean.setModifiedBy("ayushi");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}
	public static void testDelete() throws Exception {
		model.delete(2);
		
	}
	public static void testFindByPk() {

		CollegeBean bean = new CollegeBean();

		bean = model.findByPK(2);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getAddresss());
		System.out.println(bean.getState());
		System.out.println(bean.getCity());
		System.out.println(bean.getPhoneNo());
	

	}

	public static void testFindByCollegeName() {

		CollegeBean bean = new CollegeBean();

		bean = model.findByCollegeName("DAVV");

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getAddresss());
		System.out.println(bean.getState());
		System.out.println(bean.getCity());
		System.out.println(bean.getPhoneNo());
	

	}

}
