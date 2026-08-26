package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {
	public static MarksheetModel model=new MarksheetModel();
	public static void main(String[] args) {
//		testAdd();
//		testUpdate();
//		testDelete();
		testFindByPk();
//		testFindByRollNo();
	}
	public static void testAdd() {
		MarksheetBean bean=new MarksheetBean();
		bean.setRollNo("ST005");
		bean.setStudentId(8);
		bean.setName("Riya");
		bean.setPhysics(88);
		bean.setChemistry(90);
		bean.setMaths(86);
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
		
		
	}

	public static void testUpdate() {
		MarksheetBean bean=new MarksheetBean();
		bean.setId(2);
		bean.setRollNo("ST002");
		bean.setStudentId(2);
		bean.setName("Tanisha");
		bean.setPhysics(88);
		bean.setChemistry(90);
		bean.setMaths(86);
		bean.setModifiedBy("ayushi");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}
	public static void testDelete() throws Exception {
		model.delete(7);
	}
	public static void testFindByPk() {

		MarksheetBean bean = new MarksheetBean();

		bean = model.findByPK(6);

		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getStudentId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());
		
	

	}

	public static void testFindByRollNo() {

		MarksheetBean bean = new MarksheetBean();

		bean = model.findByRollNo("ST004");


		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getStudentId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());

	}
}
