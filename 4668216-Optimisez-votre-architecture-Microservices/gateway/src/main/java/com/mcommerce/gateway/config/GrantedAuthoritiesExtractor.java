package com.mcommerce.gateway.config;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.Jwt;

public class GrantedAuthoritiesExtractor {//implements Converter<Jwt, Collection<GrantedAuthority>> {

	public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<?> authorities = (Collection<?>)
                jwt.getClaims().getOrDefault("roles", Collections.emptyList());

        return authorities.stream()
                .map(Object::toString)
                .map(r -> "ROLE_" + r)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
