package com.gnogun.sample;

import com.gnogun.sample.model.Member;
import com.gnogun.sample.repository.MemberRepository;
import com.gnogun.sample.service.JwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SimpleSpringSecurityApplication {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private JwtAuthService authService;

	@PostConstruct
	public void init(){
		Member member = new Member();
		member.setUsername("user4");
		member.setPassword("a1234");
		member.setName("김동근");
		member.setEmail("ehdrms2034@naver.com");
		member.setAddress("부산광역시 부산진구 땡땡로 땡땡길 101동 1001호");
		authService.signUpUser(member);
//		memberRepository.save(member);
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringSecurityApplication.class, args);
	}

}
