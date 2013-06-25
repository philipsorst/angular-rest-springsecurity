package net.dontdrinkandroot.example.angularrestspringsecurity.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;


public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	//
	// @Autowired
	// private TokenUtils tokenUtils;

	private final AuthenticationManager authManager;

	private UserDetailsService userService;


	public AuthenticationTokenProcessingFilter(AuthenticationManager authManager, UserDetailsService userDetailsService) {

		this.authManager = authManager;
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		// @SuppressWarnings("unchecked")
		// Map<String, String[]> parms = request.getParameterMap();
		//
		// if (parms.containsKey("token")) {
		// String token = parms.get("token")[0]; // grab the first "token" parameter
		//
		// // validate the token
		// if (this.tokenUtils.validate(token)) {
		// // determine the user based on the (already validated) token
		// UserDetails userDetails = this.tokenUtils.getUserFromToken(token);
		// // build an Authentication object with the user's info
		// UsernamePasswordAuthenticationToken authentication =
		// new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
		// userDetails.getPassword());
		// authentication.setDetails(new
		// WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
		// // set the authentication into the SecurityContext
		// SecurityContextHolder.getContext().setAuthentication(this.authManager.authenticate(authentication));
		// }
		// }
		// continue thru the filter chain
		chain.doFilter(request, response);
	}
}
