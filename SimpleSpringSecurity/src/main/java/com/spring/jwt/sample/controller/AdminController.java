package com.spring.jwt.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/")
	public String main(HttpSession session, Authentication auth) {
		System.out.println(auth.getPrincipal());
		return "admin";
	}
}
