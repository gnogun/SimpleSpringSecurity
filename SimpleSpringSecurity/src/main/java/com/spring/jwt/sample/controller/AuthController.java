package com.spring.jwt.sample.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.jwt.sample.model.Member;
import com.spring.jwt.sample.model.RequestLoginUser;
import com.spring.jwt.sample.model.Response;
import com.spring.jwt.sample.service.JwtAuthService;
import com.spring.jwt.sample.util.CookieUtil;
import com.spring.jwt.sample.util.JwtUtil;
import com.spring.jwt.sample.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class AuthController {


	@Autowired
	private JwtAuthService authService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CookieUtil cookieUtil;
	@Autowired
	private RedisUtil redisUtil;

//	@RequestMapping(value = "/admin/login/id/{id}/pwd/{pwd}/", method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<Authentication> adminLogin(@PathVariable String id, @PathVariable String pwd,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		try {
//			return new ResponseEntity<Authentication>(
//					service.customLogin(id, pwd, AuthEnum.ROLE_ADMIN, request, response), HttpStatus.OK);
//		} catch (BadCredentialsException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//					"Bad credentials. Please check username, password");
//		} catch (IllegalArgumentException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//		}
//	}

	//jwt login
	@PostMapping("/admin/login")
	public @ResponseBody ResponseEntity<Response> login(@RequestBody RequestLoginUser user,
														HttpServletRequest req,
														HttpServletResponse res) {
		try {
			final Member member = authService.loginUser(user.getUsername(), user.getPassword());
			final String token = jwtUtil.generateToken(user.getUsername());
			final String refreshJwt = jwtUtil.extractUsername(token);
			Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
			Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
//			redisUtil.setDataExpire(refreshJwt, member.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
			res.addCookie(accessToken);
			res.addCookie(refreshToken);
			return new ResponseEntity<Response> (new Response("success", "로그인에 성공했습니다.", token), HttpStatus.OK);
		} catch (Exception e) {
			throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

//	@RequestMapping(value = "/users/login/id/{id}/pwd/{pwd}/", method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<Authentication> usersLogin(@PathVariable String id, @PathVariable String pwd,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		try {
//			return new ResponseEntity<Authentication>(
//					service.customLogin(id, pwd, AuthEnum.ROLE_USER, request, response), HttpStatus.OK);
//		} catch (BadCredentialsException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//					"Bad credentials. Please check username, password");
//		} catch (IllegalArgumentException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//		} catch (UsernameNotFoundException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//					"Bad credentials. Please check username, password");
//		}
//	}

}
