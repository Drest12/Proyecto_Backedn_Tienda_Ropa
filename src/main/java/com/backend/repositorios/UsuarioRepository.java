package com.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsername(String username);

}
