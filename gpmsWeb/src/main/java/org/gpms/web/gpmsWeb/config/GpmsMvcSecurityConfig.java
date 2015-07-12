/**
 * 
 */
package org.gpms.web.gpmsWeb.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author narenda.kumar
 * 
 */
@Configuration
@EnableWebMvcSecurity
public class GpmsMvcSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	DataSource gpmsDataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasRole("USER").anyRequest()
				.authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().permitAll();

		System.out.println("In Configure : ");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.jdbcAuthentication().dataSource(gpmsDataSource)
				.passwordEncoder(passwordEncoder()).usersByUsernameQuery("");

		System.out.println("gpmsDataSource : "
				+ auth.jdbcAuthentication().dataSource(gpmsDataSource)
						.passwordEncoder(passwordEncoder())
						.usersByUsernameQuery(""));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;

	}

}
