package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {
	public static SubjectModel model= new SubjectModel();
	public static void main(String[] args) throws Exception {
//		testAdd();
//		testDelete();
//		testUpdate();
//		testFindByPk();
		testFindBySubjectName();
	}
	public static void testAdd() {
		SubjectBean bean=new SubjectBean();
		bean.setName("Spring Boot");
		bean.setDescription("Develop modern Java web applications using Spring Boot");
		bean.setCourseId(3);
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
		
	}
	public static void testUpdate() throws Exception {
		SubjectBean bean = new SubjectBean();
		bean.setId(1);
		bean.setName("Core Java");
		bean.setDescription("Core and Corporate Java Programming");
		bean.setCourseId(1);
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);

	}
	public static void testDelete() throws Exception {
		model.delete(3);
	}
	
	public static void testFindByPk() {

		SubjectBean bean = new SubjectBean();

		bean = model.findByPK(2);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCourseId());
	

	}

	public static void testFindBySubjectName() {

		SubjectBean bean = new SubjectBean();

		bean = model.findByName("Core Java");

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCourseId());
	
	

	}
}



