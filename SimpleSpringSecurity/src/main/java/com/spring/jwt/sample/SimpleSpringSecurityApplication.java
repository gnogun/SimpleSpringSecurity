package com.spring.jwt.sample;

import com.spring.jwt.sample.model.Member;
import com.spring.jwt.sample.service.JwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SimpleSpringSecurityApplication {


	@Autowired
	private JwtAuthService authService;

	@PostConstruct
	public void init(){
		Member member = new Member();
		member.setUsername("user4");
		member.setPassword("a1234");
		member.setName("test");
		member.setEmail("testaaaa@aaaa.com");
		member.setAddress("bbbbb");
		authService.signUpUser(member);
//		memberRepository.save(member);
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringSecurityApplication.class, args);
	}

}
