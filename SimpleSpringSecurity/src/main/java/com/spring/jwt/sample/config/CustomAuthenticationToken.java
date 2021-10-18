package com.spring.jwt.sample.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

//jwt사용으로 인해 deprecated
@Deprecated
public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private String id;
    private String credentials;

    public CustomAuthenticationToken(String id, String credentials) {
        super(Collections.emptyList());
        this.id = id;
        this.credentials = credentials;
    }

    public CustomAuthenticationToken(String id, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.id = id;
        this.credentials = credentials;
    }

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.id;
    }
}
