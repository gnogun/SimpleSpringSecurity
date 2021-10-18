package com.gnogun.sample.config;

import com.gnogun.sample.filter.JwtFilter;
import com.gnogun.sample.service.CustomSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import com.gnogun.sample.filter.CustomRememberMeFilter;
import com.gnogun.sample.handler.CustomLogoutSuccessHandler;
import com.gnogun.sample.service.CustomSecurityRememberMeService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private UserDetailsService customSecurityService;

	@Autowired
	private CustomSecurityService userDetailsService;

	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http
//				.addFilterAt(customRememberMeFilter(), RememberMeAuthenticationFilter.class).cors().and().csrf().disable() // csrf
//				.rememberMe().rememberMeServices(customSecurityRememberMeService())
//				.and().logout().logoutUrl("/logout")
//				.logoutSuccessHandler(logoutSuccessHandler()).and().authorizeRequests().antMatchers("/users/**")
//				.hasAnyRole("USER").antMatchers("/admin/**").hasAnyRole("ADMIN").antMatchers("/**").permitAll();
		http.cors()
				.and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT인증사용하므로 세션 생성 안함
				.and()
				.httpBasic()
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
				.and()
				.authorizeRequests()
				.antMatchers("/users/**").hasAnyRole("USER")
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/**").permitAll();
		//spring security filter가 아닌 jwt filter로 재정의
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/users/login/**").antMatchers("/admin/login/**");
	}

//	@Bean
//	public PasswordEncoder customPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

//	@Bean
//	public LogoutSuccessHandler logoutSuccessHandler() {
//		return new CustomLogoutSuccessHandler();
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customSecurityService).passwordEncoder(customPasswordEncoder());
//	}
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		return new CustomAuthenticationProvider(userDetailsService, customPasswordEncoder());
//	}


//	@Bean
//	public RememberMeServices customSecurityRememberMeService() {
//
//		return new CustomSecurityRememberMeService("qwer", customSecurityService);
//	}

//	@Bean
//	public RememberMeAuthenticationFilter customRememberMeFilter() throws Exception {
//		return new CustomRememberMeFilter(authenticationManagerBean(), customSecurityRememberMeService());
//	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
