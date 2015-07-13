/**
 * 
 */
package org.gpms.web.gpmsWeb.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
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

	private static final Logger logger = Logger
			.getLogger(GpmsMvcSecurityConfig.class);

	@Resource
	DataSource gpmsDataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERED");
		}

		http.anonymous().and().authorizeRequests().antMatchers("/resources/**")
				.permitAll();

		http.anonymous().and().authorizeRequests().antMatchers("/login?lang**")
				.permitAll();

		http.formLogin().loginPage("/login").usernameParameter("userId")
				.passwordParameter("password").permitAll().and().csrf();

		http.exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().antMatchers("/*", "/navigation")
				.access("hasAnyRole('gpmsAdminGroup', 'gpmsSecurityGroup')")
				.anyRequest().authenticated();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("gpmsDataSource " + gpmsDataSource);
		}

		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbcUserDetailsManagerConfigurer = auth
				.jdbcAuthentication()
				.dataSource(gpmsDataSource)
				.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery(
						"select gpms_user_login.USER_CORP_EMAIL, gpms_user_login.USER_PASSWORD, 1 from gpms_user_login where gpms_user_login.USER_CORP_EMAIL=?")
				.authoritiesByUsernameQuery(
						"select gpms_user_login.USER_CORP_EMAIL, gpms_user_login.USER_GROUP_ID from gpms_user_login where gpms_user_login.USER_CORP_EMAIL=?");

		if (logger.isDebugEnabled()) {
			logger.debug("jdbcUserDetailsManagerConfigurer "
					+ jdbcUserDetailsManagerConfigurer);
		}

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;

	}

}
