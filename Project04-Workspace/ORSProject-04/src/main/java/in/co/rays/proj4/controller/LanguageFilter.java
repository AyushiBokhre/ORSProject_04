package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * LanguageFilter is a servlet filter that handles language selection
 * for the application. It reads the language parameter from the request
 * and sets the selected locale using MessageSource.
 *
 * @author Aayushi
 * @version 1.0
 */
@WebFilter("/*")
public class LanguageFilter implements Filter {

	/**
	 * Initializes the language filter.
	 *
	 * @param conf filter configuration
	 * @throws ServletException if an initialization error occurs
	 */
	@Override
	public void init(FilterConfig conf) throws ServletException {
	}

	/**
	 * Processes each request and updates the application language
	 * when a valid language parameter is provided.
	 *
	 * @param req servlet request
	 * @param resp servlet response
	 * @param chain filter chain
	 * @throws IOException if an input or output error occurs
	 * @throws ServletException if a servlet-related error occurs
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		MessageSource messagesource = MessageSource.getInstance();

		String lang = DataUtility.getString(request.getParameter("lang"));
		if (DataValidator.isNotNull(lang)) {
			messagesource.setLocale(lang);
		}

		chain.doFilter(req, resp);
	}

	/**
	 * Destroys the language filter and releases any resources.
	 */
	@Override
	public void destroy() {
	}

}

