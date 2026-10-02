package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalBean extends BaseBean{
	private String patientId;
	private String name;
	private int age;
	private String bloodGroup;
	private String disease;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			setPatientId(rs.getString("patient_id"));
			setName(rs.getString("name"));
			setAge(rs.getInt("age"));
			setBloodGroup(rs.getString("blood_group"));
			setDisease(rs.getString("diseases"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String getValue() {
		return null;
	}

}
