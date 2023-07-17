package com.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.modelo.UsuarioRol;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {
	List<UsuarioRol> findByRolRolNombre(String rolNombre);
}
