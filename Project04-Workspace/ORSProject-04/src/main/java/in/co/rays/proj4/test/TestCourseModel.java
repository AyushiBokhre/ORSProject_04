package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {
	public static CourseModel model = new CourseModel();

	public static void main(String[] args) throws Exception {
//		testAdd();
//		testDelete();
//		testUpdate();
//		testFindByPk();
//		testFindByCourseName();
		testSearch();
	}

	public static void testAdd() throws Exception {
		CourseBean bean = new CourseBean();
		bean.setName("Android Development");
		bean.setDescription("Learn to build modern Android mobile applications");
		bean.setDuration("5 months");
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	public static void testUpdate() throws Exception {
		CourseBean bean = new CourseBean();
		bean.setName("java");
		bean.setDescription("java programming language");
		bean.setDuration("6 months");
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);

	}
	public static void testDelete() throws Exception {
		model.delete(7);
	}
	public static void testFindByPk() {

		CourseBean bean = new CourseBean();

		bean = model.findByPK(2);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getDuration());

	}
	
	public static void testFindByCourseName() {


		CourseBean bean = new CourseBean();

		bean = model.findByCourseName("Java");

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getDuration());


	}
	
	public static void testSearch() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		
//		bean.setName("S");
		List<CourseBean> list = model.search(bean, 1, 5);
		Iterator<CourseBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
			System.out.println("----------------");
		}
	}
}
