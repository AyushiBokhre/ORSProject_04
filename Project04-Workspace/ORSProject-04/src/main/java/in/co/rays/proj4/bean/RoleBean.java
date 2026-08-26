package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleBean extends BaseBean {

	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setName(rs.getString(2));
			this.setDescription(rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getValue() {
		return null;
	}

}
