package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {
	public static RoleModel model=new RoleModel();
	public static void main(String[] args) throws Exception {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
		testFindByName();
		}
	public static void testAdd() {
		RoleBean bean=new RoleBean();
		RoleModel model=new RoleModel();
	
		bean.setName("Kiosk");
		bean.setDescription("Provides limited self-service access to users for basic operations.");
		bean.setCreatedBy("ayushi");
		bean.setModifiedBy("ayushi");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	
	}
	public static void testUpdate() {
		RoleBean bean=new RoleBean();
		bean.setId(1);
		bean.setName("Admin");
		bean.setDescription("Manages the entire system, users, roles, and permissions.");
		bean.setModifiedBy("ayushi");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);
	
	}
	public static void testDelete() throws Exception {
		model.delete(7);
		
	}
	
	public static void testFindByPk() {

		RoleModel model = new RoleModel();

		RoleBean bean = new RoleBean();

		bean = model.findByPK(2);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());

	}
	
	public static void testFindByName() {

		RoleModel model = new RoleModel();

		RoleBean bean = new RoleBean();

		bean = model.findByName("Admin");

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());

	}
}
