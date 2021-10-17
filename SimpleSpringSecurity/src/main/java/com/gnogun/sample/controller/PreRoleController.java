package com.gnogun.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController()
@RequestMapping("/pre")
public class PreRoleController {
	
	@GetMapping("/")
	public String main(HttpSession session, Authentication auth) {
		return "hello";
	}

}
