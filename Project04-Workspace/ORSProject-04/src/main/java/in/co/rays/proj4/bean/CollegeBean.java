package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollegeBean extends BaseBean {
	private String name;
	private String addresss;
	private String state;
	private String city;
	private String phoneNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

@Override
public void setResultset(ResultSet rs) {
	super.setResultset(rs);
	try {
		this.setName(rs.getString(2));
		this.setAddresss(rs.getString(3));
		this.setState(rs.getString(4));
		this.setCity(rs.getString(5));
		this.setPhoneNo(rs.getString(6));
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

	@Override
	public String getValue() {
		return null;
	}

}
