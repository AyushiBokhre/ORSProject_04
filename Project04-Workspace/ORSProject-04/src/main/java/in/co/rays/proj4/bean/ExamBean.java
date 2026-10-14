package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamBean extends BaseBean{
	private String subject;
	private int totalQuestion;
	private int duration;
	private int totalMarks;
	
	

	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public int getTotalQuestion() {
		return totalQuestion;
	}



	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}



	public int getDuration() {
		return duration;
	}



	public void setDuration(int duration) {
		this.duration = duration;
	}



	public int getTotalMarks() {
		return totalMarks;
	}



	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

@Override
public void setResultset(ResultSet rs) {
	
	super.setResultset(rs);
	try {
		setSubject(rs.getString("subject"));
		setTotalQuestion(rs.getInt("total_questions"));
		setDuration(rs.getInt("duration"));
		setTotalMarks(rs.getInt("total_marks"));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

	@Override
	public String getValue() {
		return null;
	}

}
