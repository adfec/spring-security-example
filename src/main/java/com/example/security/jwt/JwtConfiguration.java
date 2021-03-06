package com.example.security.jwt;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.security.Keys;

@Configuration
@ConfigurationProperties(prefix="application.jwt") // Tell what properties read
public class JwtConfiguration {
	
	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpirationAfterDays;
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	public Integer getTokenExpirationAfterDays() {
		return tokenExpirationAfterDays;
	}
	public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
		this.tokenExpirationAfterDays = tokenExpirationAfterDays;
	}
	@Bean
	public SecretKey getSecretKeyForSigning() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION; // Guava
	}

}
