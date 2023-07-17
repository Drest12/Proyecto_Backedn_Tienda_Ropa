package com.backend.controladores;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.entidades.Compra;
import com.backend.entidades.Proveedor;
import com.backend.excepciones.ResourceNotFoundException;
import com.backend.repositorios.CompraRepository;
import com.backend.repositorios.ProveedorRepository;

@RestController
@RequestMapping("/compra")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class CompraController {
	@Autowired
	private CompraRepository compraRepository;
	 private final ProveedorRepository proveedorRepository;
	
	@Autowired
    public CompraController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }
	
	@GetMapping
	public List<Compra> obtenerCompras() {
		return compraRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Compra> obtenerCompraPorId(@PathVariable Long id) {
		Compra cupon = compraRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Compra no encontrado: " + id));
		return ResponseEntity.ok(cupon);
	}
	
	@PostMapping("/")
	public ResponseEntity<Compra> agregarCompra(@RequestBody Compra compra) {
	    // Obtener el proveedor por su ID
	    Optional<Proveedor> proveedorOptional = proveedorRepository.findById(compra.getProveedor().getProveedorId());

	    if (proveedorOptional.isPresent()) {
	        Proveedor proveedor = proveedorOptional.get();
	        compra.setProveedor(proveedor);

	        // Establecer la fecha de compra como la fecha actual
	        compra.setFechaCompra(new Date());

	        // Calcular el total de la compra
	        compra.calcularTotalCompra();

	        // Guardar la instancia de Compra en la base de datos
	        Compra compraGuardada = compraRepository.save(compra);

	        // Devolver la respuesta con el código de estado 201 (CREATED) y la ubicación de la nueva compra creada
	        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(compraGuardada.getCompraId()).toUri()).body(compraGuardada);
	    } else {
	        // Manejar el caso si el proveedor no existe
	        return ResponseEntity.badRequest().build();
	    }
	}




}
