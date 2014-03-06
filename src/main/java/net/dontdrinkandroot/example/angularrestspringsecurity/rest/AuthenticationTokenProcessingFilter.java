package net.dontdrinkandroot.example.angularrestspringsecurity.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;


public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	private final UserDetailsService userService;


	public AuthenticationTokenProcessingFilter(UserDetailsService userService) {

		this.userService = userService;
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting a HTTP request");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = this.extractAuthTokenFromRequest(httpRequest);

		String userName = TokenUtils.getUserNameFromToken(authToken);

		if (userName != null) {
			UserDetails userDetails = this.userService.loadUserByUsername(userName);
			if (TokenUtils.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}


	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {

		String authToken = httpRequest.getHeader("X-Auth-Token");
		if (authToken == null) {
			authToken = httpRequest.getParameter("token");
		}

		return authToken;
	}
}
