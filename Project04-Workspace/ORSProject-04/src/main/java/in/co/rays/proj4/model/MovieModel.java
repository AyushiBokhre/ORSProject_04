package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.MovieBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MovieModel extends BaseModel<MovieBean> {

	@Override
	public long add(MovieBean bean) throws ApplicationException, DuplicateRecordException {
		
			Connection conn = null;
			MovieBean existbean = findByMovieId(bean.getMovieId());

			if (existbean != null) {
				throw new DuplicateRecordException("Movie Id already exists");
			}

			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn
						.prepareStatement("INSERT INTO "+getTable()+" VALUES(?,?,?,?,?,?,?,?,?,?)");
				pstmt.setInt(1, nextPK());
				pstmt.setString(2, bean.getMovieId());
				pstmt.setString(3, bean.getTitle());
				pstmt.setString(4, bean.getGenre());
				pstmt.setInt(5, bean.getDuration());
				pstmt.setDouble(6, bean.getRating());
				pstmt.setString(7, bean.getCreatedBy());
				pstmt.setString(8, bean.getModifiedBy());
				pstmt.setTimestamp(9, bean.getCreatedDatetime());
				pstmt.setTimestamp(10, bean.getModifiedDatetime());

				int i=pstmt.executeUpdate();
				conn.commit();
				System.out.println(i + " record inserted successfully");
			} catch (SQLException e) {
				e.printStackTrace();
				JDBCDataSource.trnRollBack(conn);
			} finally {
				JDBCDataSource.closeConnection(conn);
			}

			return bean.getId();
	}

	@Override
	public void update(MovieBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		MovieBean existbean = findByMovieId(bean.getMovieId());
		
		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("Voter Id  already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_MOVIE SET MOVIE_ID=?, TITLE=?,GENRE=?,DURATION=?,RATING=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getMovieId());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getGenre());
			pstmt.setInt(4, bean.getDuration());
			pstmt.setDouble(5, bean.getRating());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			int i=pstmt.executeUpdate();
			System.out.println(i+" record updated");
			conn.commit(); // End transaction
			
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public MovieBean findByMovieId(String movieId) throws ApplicationException {
		MovieBean bean = findByUniqueColumn("movie_id", movieId);
		return bean;
	}

	@Override
	public String getWhereClause(MovieBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getMovieId() != null && bean.getMovieId().length() > 0) {
				sql.append(" and movie_id like '" + bean.getMovieId() + "%'");
			}
			if (bean.getTitle() != null && bean.getTitle().length() > 0) {
				sql.append(" and title like '" + bean.getTitle() + "%'");
			}
			if (bean.getDuration()  > 0) {
				sql.append(" and duration = " + bean.getDuration());
			}
			if (bean.getGenre() != null && bean.getGenre().length() > 0) {
				sql.append(" and genre like '" + bean.getGenre() + "%'");
			}
			if (bean.getRating()  > 0) {
				sql.append(" and rating = " + bean.getRating() );
			}
			

		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_movie";
	}

	@Override
	public MovieBean getBean() {
		return new MovieBean();
	}

}
