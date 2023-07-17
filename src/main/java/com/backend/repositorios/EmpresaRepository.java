package com.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.backend.entidades.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	List<Empresa> findByEstadoIsTrue();

	List<Empresa> findByEstadoIsFalse();
}
