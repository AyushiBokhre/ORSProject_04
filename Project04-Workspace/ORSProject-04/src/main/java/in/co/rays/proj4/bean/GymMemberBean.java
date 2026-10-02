package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GymMemberBean extends BaseBean{
	private String name;
	private String membershipType;
	private String joiningDate;
	private String trainerName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	@Override
	public void setResultset(ResultSet rs) {
		
		try {
			super.setResultset(rs);
			setName(rs.getString("name"));
			setMembershipType(rs.getString("membership_type"));
			setJoiningDate(rs.getString("joining_date"));
			setTrainerName(rs.getString("trainer_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
