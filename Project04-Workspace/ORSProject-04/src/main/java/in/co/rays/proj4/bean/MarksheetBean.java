package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksheetBean extends BaseBean{
	private String rollNo;
	private long studentId;
	private String name;
	private int physics;
	private int chemistry;
	private int maths;
	

	public String getRollNo() {
		return rollNo;
	}


	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}


	public long getStudentId() {
		return studentId;
	}


	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPhysics() {
		return physics;
	}


	public void setPhysics(int physics) {
		this.physics = physics;
	}


	public int getChemistry() {
		return chemistry;
	}


	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}


	public int getMaths() {
		return maths;
	}


	public void setMaths(int maths) {
		this.maths = maths;
	}

@Override
public void setResultset(ResultSet rs) {
	
	super.setResultset(rs);
	try {
		super.setResultset(rs);
		this.setRollNo(rs.getString(2));
		this.setStudentId(rs.getLong(3));
		this.setName(rs.getString(4));
		this.setPhysics(rs.getInt(5));
		this.setChemistry(rs.getInt(6));
		this.setMaths(rs.getInt(7));
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	@Override
	public String getValue() {
		return null;
	}

}
