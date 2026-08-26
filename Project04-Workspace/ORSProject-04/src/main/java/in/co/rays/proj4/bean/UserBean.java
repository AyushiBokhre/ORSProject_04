package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

public class UserBean extends BaseBean {
private String firstName;
private String lastName;
private String login;
private String password;
private Date dob;
private String mobileNo;
private long roleId;
private int unsuccessfulLogin;
private String gender;
private String lastLogin;
private String userLock;
private String registeredIp;
private String lastLoginIp;


	public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getLogin() {
	return login;
}


public void setLogin(String login) {
	this.login = login;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public Date getDob() {
	return dob;
}


public void setDob(Date dob) {
	this.dob = dob;
}


public String getMobileNo() {
	return mobileNo;
}


public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}


public long getRoleId() {
	return roleId;
}


public void setRoleId(long roleId) {
	this.roleId = roleId;
}


public int getUnsuccessfulLogin() {
	return unsuccessfulLogin;
}


public void setUnsuccessfulLogin(int unsuccessfulLogin) {
	this.unsuccessfulLogin = unsuccessfulLogin;
}


public String getGender() {
	return gender;
}


public void setGender(String gender) {
	this.gender = gender;
}


public String getLastLogin() {
	return lastLogin;
}


public void setLastLogin(String lastLogin) {
	this.lastLogin = lastLogin;
}


public String getUserLock() {
	return userLock;
}


public void setUserLock(String userLock) {
	this.userLock = userLock;
}


public String getRegisteredIp() {
	return registeredIp;
}


public void setRegisteredIp(String registeredIp) {
	this.registeredIp = registeredIp;
}


public String getLastLoginIp() {
	return lastLoginIp;
}


public void setLastLoginIp(String lastLoginIp) {
	this.lastLoginIp = lastLoginIp;
}
 @Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setFirstName(rs.getString(2));
			this.setLastName(rs.getString(3));
			this.setLogin(rs.getString(4));
			this.setPassword(rs.getString(5));
			this.setDob(rs.getDate(6));
			this.setMobileNo(rs.getString(7));
			this.setRoleId(rs.getLong(8));
			this.setUnsuccessfulLogin(rs.getInt(9));
			this.setGender(rs.getString(10));
			this.setLastLogin(rs.getString(11));
			this.setUserLock(rs.getString(12));
			this.setRegisteredIp(rs.getString(13));
			this.setLastLoginIp(rs.getString(14));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getValue() {
		return null;
	}

}
