package com.gnogun.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/pre")
public class PreRoleController {

	
	@GetMapping("/")
	public String main(HttpSession session, Authentication auth) {
		return "hello";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin/")
	public String admin(HttpSession session, Authentication auth) {
		return "hello";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/users/")
	public String users(HttpSession session, Authentication auth) {
		return "hello";
	}

}
