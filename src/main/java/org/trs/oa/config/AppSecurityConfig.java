package org.trs.oa.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	//	auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authProvider());
	}
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/protected/**").access("hasRole('ROLE_ADMIN')")
//				.antMatchers("/confidential/**").access("hasRole('ROLE_SUPERADMIN')").and().formLogin();
		  http.userDetailsService(userDetailsService)
	        .authorizeRequests()
	        .antMatchers("/", "/dashboard","/project/**","/timesheet/**").access("hasAnyRole('USER','ADMIN')")
//	        .antMatchers("/manageEmp", "/manageDept", "/addDept","/addEmp").access("hasRole('SUPER_ADMIN')")
	            .and()
	        .formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").and().exceptionHandling().accessDeniedPage("/deny");

//		http.csrf().disable();
	}

}
