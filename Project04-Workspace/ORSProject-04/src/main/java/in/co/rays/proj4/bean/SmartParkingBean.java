package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SmartParkingBean extends BaseBean {
	private String vehicleNumber;
	private String vehicleType;
	private String entryTime;
	private boolean occupied;
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	@Override
	public void setResultset(ResultSet rs) {
		
		try {
			super.setResultset(rs);
			setVehicleNumber(rs.getString("vehicle_number"));
			setVehicleType(rs.getString("vehicle_type"));
			setEntryTime(rs.getString("entry_time"));
			setOccupied(rs.getBoolean("occupied"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		return null;
	}

}
