package com.example.security.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.security.security.ApplicationUserRoles;

@Repository("mock")
public class MockApplicationUserDAOService implements ApplicationUserDAO {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		//return getApplicationUsers().stream().filter(apUser -> username.equals(apUser.getUsername())).findFirst();
		return null;
	}
	
	// This list would be filled using the information from the database
	private List<ApplicationUserMock> getApplicationUsers() {
		List<ApplicationUserMock> applicationUsers = new ArrayList<>();
		applicationUsers.add(
				new ApplicationUserMock("admin", 
						passwordEncoder.encode("admin"), 
						ApplicationUserRoles.STUDENT.getGrantedAuthorities(), 
						true, 
						true, 
						true, 
						true));
		applicationUsers.add(
				new ApplicationUserMock("student", 
						passwordEncoder.encode("student"), 
						ApplicationUserRoles.STUDENT.getGrantedAuthorities(), 
						true, 
						true, 
						true, 
						true));
		return applicationUsers;
	}
	
	private List<ApplicationUserMock> getApplicationUsersRepository() {
		return null;
	}

}
