package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieBean extends BaseBean {
	private String movieId;
	private String title;
	private String genre;
	private int duration;
	private double rating;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public void setResultset(ResultSet rs) {
		
		try {
			super.setResultset(rs);
			setMovieId(rs.getString("movie_id"));
			setTitle(rs.getString("title"));
			setGenre(rs.getString("genre"));
			setDuration(rs.getInt("duration"));
			setRating(rs.getDouble("rating"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		return null;
	}

}
