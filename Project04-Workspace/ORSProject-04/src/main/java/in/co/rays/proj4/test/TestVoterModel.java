package in.co.rays.proj4.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.VoterBean;
import in.co.rays.proj4.model.VoterModel;

public class TestVoterModel {
	public static VoterModel model= new VoterModel();
	public static void main(String[] args) throws SQLException {
		testAdd();
//		testUpdate();
//		testSearch();
//		testFindByVoterId();
	}
	
	public static void testAdd() {
		VoterBean bean =new VoterBean();
		bean.setVoterId("104");
		bean.setName("Mahak");
		bean.setAge(23);
		bean.setConstituency("Indore");
		bean.setHasVoted(true);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
		
	}
	public static void testUpdate() throws SQLException {
		VoterBean bean =new VoterBean();
		bean.setId(1);
		bean.setVoterId("101");
		bean.setName("Ayushi");
		bean.setAge(22);
		bean.setConstituency("Indore");
		bean.setHasVoted(true);
		bean.setModifiedBy("admin");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);

	}
	public static void testFindByVoterId() {

		VoterBean bean = new VoterBean();

		bean = model.findByVoterId("101");

		System.out.println(bean.getId());
		System.out.println(bean.getVoterId());
		System.out.println(bean.getName());
		System.out.println(bean.getAge());
		System.out.println(bean.getConstituency());
		System.out.println(bean.isHasVoted());

	}

	
	public static void testSearch() throws SQLException {
		VoterBean bean =new VoterBean();
		
//		bean.setName("S");
		List<VoterBean> list = model.search(bean, 1, 5);
		Iterator<VoterBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getVoterId());
			System.out.println(bean.getName());
			System.out.println(bean.getAge());
			System.out.println(bean.getConstituency());
			System.out.println(bean.isHasVoted());
			
			System.out.println("----------------");
		}
	}

}
