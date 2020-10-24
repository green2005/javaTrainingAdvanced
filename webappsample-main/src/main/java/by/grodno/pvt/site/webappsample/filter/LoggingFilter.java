package by.grodno.pvt.site.webappsample.filter;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.grodno.pvt.site.webappsample.service.UserService;

public class LoggingFilter implements Filter {

	static public final Logger LOGGER = Logger.getLogger(LoggingFilter.class);
	private boolean active;

	private AtomicInteger counter = new AtomicInteger(0);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		storeCounter((HttpServletRequest) request);

		if (active) {
			String headers = getHeaders(request);
			LOGGER.info("RequestHeaders = " + headers);
		}
		chain.doFilter(request, response);
	}

	private String getHeaders(ServletRequest request) {
		Enumeration<String> headerNames = ((HttpServletRequest) request).getHeaderNames();
		StringBuilder sb = new StringBuilder();
		while (headerNames.hasMoreElements()) {
			String string = (String) headerNames.nextElement();
			sb.append(string.concat(" ").concat(((HttpServletRequest) request).getHeader(string)).concat(";"));
		}
		return sb.toString();
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		active = "TRUE".equals(filterConfig.getInitParameter("active"));
	}

	private void storeCounter(HttpServletRequest req) {
		if (req.getSession().isNew()) {
			Integer incrementAndGet = counter.incrementAndGet();
			req.getSession().setAttribute("counter", incrementAndGet.toString());
		}
	}
}
