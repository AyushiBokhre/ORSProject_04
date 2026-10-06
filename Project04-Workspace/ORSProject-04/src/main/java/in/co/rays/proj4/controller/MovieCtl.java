package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MovieBean;
import in.co.rays.proj4.model.MovieModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/MovieCtl")
public class MovieCtl extends BaseCtl<MovieBean, MovieModel>{
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
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("movieId"))) {
		    request.setAttribute("movieId", "movie id is required");
		    pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("title"))) {
		    request.setAttribute("title", "title is required");
		    pass = false;
		} else if (!DataValidator.isName(request.getParameter("genre"))) {
		    request.setAttribute("genre", "genre should contain only alphabets.");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
		    request.setAttribute("duration", "duration is required");
		    pass = false;
		}
		if (DataValidator.isNull(request.getParameter("rating"))) {
		    request.setAttribute("rating", "rating is required");
		    pass = false;
		}
		
		return pass;
	}

	@Override
	protected String getView() {
		return ORSView.MOVIE_VIEW;
	}

	@Override
	protected MovieModel getModel() {
		return new MovieModel();
	}

}
