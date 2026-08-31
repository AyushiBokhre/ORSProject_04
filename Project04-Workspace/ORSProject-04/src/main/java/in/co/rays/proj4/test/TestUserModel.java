package in.co.rays.proj4.test;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {
	public static UserModel model = new UserModel();

	public static void main(String[] args) throws Exception {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByLogin();
//		testAuthenticate();
		testSearch();
	}

	public static void testAdd() throws Exception {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("Shivani");
		bean.setLastName("Gehlot");
		bean.setLogin("shivani@gmail.com");
		bean.setPassword("Shivani123");
		bean.setDob(sdf.parse("2004-08-25"));
		bean.setMobileNo("9876543214");
		bean.setRoleId(5);
		bean.setUnsuccessfulLogin(0);
		bean.setGender("Female");
		bean.setLastLogin("2026-08-20");
		bean.setUserLock("No");
		bean.setRegisteredIp("192.168.1.14");
		bean.setLastLoginIp("192.168.1.19");

		model.add(bean);

	}

	public static void testUpdate() throws Exception {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(3);
		bean.setFirstName("Rahul");
		bean.setLastName("Sharma");
		bean.setLogin("rahul@gmail.com");
		bean.setPassword("Rahul@123");
		bean.setDob(sdf.parse("2003-03-10"));
		bean.setMobileNo("9876543213");
		bean.setRoleId(2);
		bean.setUnsuccessfulLogin(0);
		bean.setGender("Male");
		bean.setLastLogin("2026-08-21");
		bean.setUserLock("No");
		bean.setRegisteredIp("192.168.1.13");
		bean.setLastLoginIp("192.168.1.18");

		model.update(bean);

	}

	public static void testDelete() throws Exception {
		model.delete(1);

	}

	public static void testFindByPk() {

		UserBean bean = new UserBean();

		bean = model.findByPK(2);

		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getLogin());
		System.out.println(bean.getDob());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getRoleId());

	}

	public static void testFindByLogin() {

		UserBean bean = new UserBean();

		bean = model.findByLogin("aayushi@gmail.com");

		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getLogin());
		System.out.println(bean.getDob());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getRoleId());

	}

	public static void testAuthenticate() {
		UserBean bean = new UserBean();
		bean = model.authenticate("aayushi@gmail.com", "aayushi@123");
		if (bean != null) {
			System.out.println("User Found..");

		} else {
			System.out.println("User not found..");

		}
	}

	public static void testSearch() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		
		bean.setFirstName("S");
		List<UserBean> list = model.search(bean, 1, 5);
		Iterator<UserBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println("----------------");
		}
	}

}
