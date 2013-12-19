package net.dontdrinkandroot.example.angularrestspringsecurity.security;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.user.UserDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.rest.AuthenticationTokenProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ImportResource("classpath:/context.xml")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable CSRF and Basic Authentication
        http.csrf().disable().httpBasic().disable();

        http.authorizeRequests()
                .antMatchers("/rest/user/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/news/**").hasRole("user")
                .antMatchers(HttpMethod.PUT, "/rest/news/**").hasRole("admin")
                .antMatchers(HttpMethod.POST, "/rest/news/**").hasRole("admin")
                .antMatchers(HttpMethod.DELETE, "/rest/news/**").hasRole("admin");

        // customization for REST Token AUTH
        http.addFilterBefore(new AuthenticationTokenProcessingFilter(userDao), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    private UserDao userDao;

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userDao).passwordEncoder(new SaltedSHA256PasswordEncoder("secret"));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}