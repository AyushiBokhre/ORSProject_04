package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MovieBean;
import in.co.rays.proj4.model.MovieModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/MovieListCtl")
public class MovieListCtl extends BaseListCtl<MovieBean, MovieModel>{
	@Override
	protected MovieBean populateBean(HttpServletRequest request) {
		MovieBean bean =new MovieBean();
		bean.setMovieId(request.getParameter("movieId"));
		bean.setTitle(request.getParameter("title"));
		bean.setGenre(request.getParameter("genre"));
		bean.setDuration(DataUtility.getInt(request.getParameter("duration")));
		bean.setRating(DataUtility.getDouble(request.getParameter("rating")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.MOVIE_LIST_VIEW;
	}

	@Override
	protected MovieModel getModel() {
		return new MovieModel();
	}

}
