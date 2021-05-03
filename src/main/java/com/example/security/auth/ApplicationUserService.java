package com.example.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {
	
	@Autowired
	@Qualifier("data") // If you have more implementations
	private ApplicationUserDAO applicationUserDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return applicationUserDAO.selectApplicationUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
