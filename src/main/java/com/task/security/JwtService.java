package com.task.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtService {

	@Autowired
	private ObjectMapper objectMapper;

	private static final String ROLE_CLAIMS = "roles";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.clockSkew}")
	private Long clockSkew;

	@Value("${jwt.validFor}")
	private Long validFor;

	public String issueToken(UserPrincipal userPrincipal) {
		String id = generateTokenIdentifier();
		ZonedDateTime issuedDate = ZonedDateTime.now();
		ZonedDateTime expirationDate = calculateExpirationDate(issuedDate);
		// @formatter:off
		return Jwts.builder().setId(id).setSubject(userPrincipal.getUsername())
				.setIssuedAt(Date.from(issuedDate.toInstant())).setExpiration(Date.from(expirationDate.toInstant()))
				.claim(ROLE_CLAIMS, userPrincipal.getAuthorities()).signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		// @formatter:on
	}

	private ZonedDateTime calculateExpirationDate(ZonedDateTime issuedDate) {
		return issuedDate.plusSeconds(validFor);
	}

	private String generateTokenIdentifier() {
		return UUID.randomUUID().toString();
	}

	public UserPrincipal parseToken(String token) {
		// @formatter:off

		Claims claims = Jwts.parser().setSigningKey(secret).setAllowedClockSkewSeconds(clockSkew).parseClaimsJws(token)
				.getBody();
		return new UserPrincipal.Builder().withId(extractTokenIdFromClaims(claims))
				.withUsername(extractUsernameFromClaims(claims)).withAuthorities(extractAuthoritiesFromClaims(claims))
				.build();

		// @formatter:on
	}

	private String extractTokenIdFromClaims(Claims claims) {
		return (String) claims.get(Claims.ID);
	}

	private String extractUsernameFromClaims(Claims claims) {
		return claims.getSubject();
	}

	@SuppressWarnings({ "unchecked" })
	private List<UserAuthority> extractAuthoritiesFromClaims(Claims claims) {
		List<Object> parsedObject = (ArrayList<Object>) claims.getOrDefault(ROLE_CLAIMS, new ArrayList<>());
		return parsedObject.stream().map(obj -> objectMapper.convertValue(obj,  UserAuthority.class))
				.collect(Collectors.toList());

	}

}
