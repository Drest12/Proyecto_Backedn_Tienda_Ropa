package com.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.modelo.Rol;

public interface RolRepository extends JpaRepository<Rol,Long> {
}
