package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class BaseBean implements DropdownListBean {
	// id is a non business pk it is an auto increment column. it is just used to
	// uniquely identified the record . it is not related to business data.

	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDatetime;
	protected Timestamp modifiededDatetime;

	public long getId() {
		return id;
	}

	
// other four attribute are the metadata column which contain info related to database changes.
	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiededDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiededDatetime) {
		this.modifiededDatetime = modifiededDatetime;
	}

	public void setResultset(ResultSet rs) {
		try {
			this.setId(rs.getLong("ID"));
			this.setCreatedBy(rs.getString("CREATED_BY"));
			this.setModifiedBy(rs.getString("MODIFIED_BY"));
			this.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME"));
			this.setModifiedDatetime(rs.getTimestamp("MODIFIED_DATETIME"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getKey() {
		return null;
	}

}
