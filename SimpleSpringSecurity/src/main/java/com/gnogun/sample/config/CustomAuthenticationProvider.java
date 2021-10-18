package com.gnogun.sample.config;

import com.gnogun.sample.service.CustomSecurityService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


//spring security를 사용할때 사용함.
//jwt는 jwt filter내에서 사용.
@Deprecated
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomSecurityService userDetailsService;
    private final PasswordEncoder customPasswordEncoder;
    //    private final SamplePasswordEncoder passwordEncoder;


    public CustomAuthenticationProvider(CustomSecurityService userDetailsService, PasswordEncoder customPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("username is not found. username=" + username);
        }

        if (!this.customPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("password is not matched");
        }

        return new CustomAuthenticationToken(username, password, user.getAuthorities());
    }

    // Auth check class를 필히 변경된 class로 변경해야함.
    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
