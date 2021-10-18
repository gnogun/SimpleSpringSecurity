package com.gnogun.sample.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gnogun.sample.config.CustomAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.gnogun.sample.common.AuthEnum;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

//	@Autowired
//	private RememberMeServices customSecurityRememberMeService;

	public Authentication customLogin(String id, String pwd, AuthEnum common, HttpServletRequest request,
			HttpServletResponse response) throws BadCredentialsException, UsernameNotFoundException {
		CustomAuthenticationToken token = new CustomAuthenticationToken(id, pwd);

		Authentication authentication = authenticationManager.authenticate(token);

		if (authentication != null) {
			HttpSession session = request.getSession();

			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					SecurityContextHolder.getContext());

//			customSecurityRememberMeService.loginSuccess(request, response, authentication);

			return authentication;

		} else {
			throw new IllegalArgumentException("account doesn't exists. Please check userid, password");
		}

	}
}
