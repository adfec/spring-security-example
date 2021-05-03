package com.example.security.security;

import static com.example.security.security.ApplicationUserPermissions.COURSE_READ;
import static com.example.security.security.ApplicationUserPermissions.COURSE_WRITE;
import static com.example.security.security.ApplicationUserPermissions.STUDENT_READ;
import static com.example.security.security.ApplicationUserPermissions.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

// Setting roles and permisison in a static way
public enum ApplicationUserRoles {
	
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
	ADMIN_TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));
	
	private final Set<ApplicationUserPermissions> permissions;

	private ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermissions> getPermissions() {
		return permissions;
	}
	
	// Get authorities over roles example
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}

}
