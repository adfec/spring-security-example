package com.example.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.security.model.Roles;
import com.example.security.model.Usuario;

public class ApplicationUser implements UserDetails {
	
	private static final long serialVersionUID = -6357645428912755244L;
	
	private final Usuario usuario;
	private final String password;
	private final String username;

	public ApplicationUser(Usuario usuario, String password, String username) {
		this.usuario = usuario;
		this.password = password;
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// The permissions inside each role, can be iterated and added as authorities
		Set<Roles> roles = this.usuario.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(rol -> {
			authorities.add(new SimpleGrantedAuthority("ROL_" + rol.getNombre()));
			rol.getPermisos().forEach(per -> authorities.add(new SimpleGrantedAuthority(rol.getNombre() + ":" + per.getNombre())));
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
