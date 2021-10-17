package com.gnogun.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.gnogun.sample.common.AuthEnum;
import com.gnogun.sample.service.AuthService;

@Controller
public class AuthController {

	@Autowired
	private AuthService service;

	@RequestMapping(value = "/admin/login/id/{id}/pwd/{pwd}/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Authentication> adminLogin(@PathVariable String id, @PathVariable String pwd,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			return new ResponseEntity<Authentication>(
					service.customLogin(id, pwd, AuthEnum.ROLE_ADMIN, request, response), HttpStatus.OK);
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Bad credentials. Please check username, password");
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/users/login/id/{id}/pwd/{pwd}/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Authentication> usersLogin(@PathVariable String id, @PathVariable String pwd,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			return new ResponseEntity<Authentication>(
					service.customLogin(id, pwd, AuthEnum.ROLE_USER, request, response), HttpStatus.OK);
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Bad credentials. Please check username, password");
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (UsernameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Bad credentials. Please check username, password");
		}
	}

}
