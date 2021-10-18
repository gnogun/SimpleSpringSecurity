package com.gnogun.sample.service;

import java.util.ArrayList;
import java.util.List;

import com.gnogun.sample.model.Member;
import com.gnogun.sample.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomSecurityService implements UserDetailsService {
	
//	@Autowired
//	private PasswordEncoder customPasswordEncoder;

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member member = memberRepository.findByUsername(username);
		if(member == null){
			throw new UsernameNotFoundException(username + " : 사용자 존재하지 않음");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		if (username.equals("user4")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		

		User user = new User(username,"{noop}"+ member.getPassword(),  authorities);

		return user;
	}

}
