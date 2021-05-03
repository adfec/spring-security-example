package com.example.security.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.Usuario;
import com.example.security.repository.UsuariosRepository;

@Service("data")
public class ApplicationUserDAOService implements ApplicationUserDAO {
	
	@Autowired
	private UsuariosRepository usuarios;

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		Usuario usuario = usuarios.getUsuarioByUserName(username);
		ApplicationUser applicationUser = new ApplicationUser(usuario, usuario.getContrasenia(), usuario.getNombre());
		return Optional.of(applicationUser);
	}

}
