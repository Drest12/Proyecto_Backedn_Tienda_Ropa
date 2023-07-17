package com.backend.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.entidades.Proveedor;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
	List<Proveedor> findByEstadoIsTrue();

	List<Proveedor> findByEstadoIsFalse();

	@Query("SELECT p FROM Proveedor p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
	List<Proveedor> buscarPorNombreIgnoreCase(@Param("nombre") String nombre);
}
