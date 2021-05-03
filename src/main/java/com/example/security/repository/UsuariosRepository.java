package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.security.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{
	
	@Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
	public Usuario getUsuarioByUserName(@Param("nombre") String nombre);

}
