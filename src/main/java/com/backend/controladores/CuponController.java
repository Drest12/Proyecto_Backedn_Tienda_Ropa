package com.backend.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.entidades.Cupon;

import com.backend.excepciones.ResourceNotFoundException;
import com.backend.repositorios.CuponRepository;

@RestController
@RequestMapping("/cupon")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class CuponController {

	@Autowired
	private CuponRepository cuponRepository;

	// Metodo Listar
	@GetMapping
	public List<Cupon> obtenerCupon() {
		return cuponRepository.findAll();
	}

	// Metodo Listar por Id
	@GetMapping("/{id}")
	public ResponseEntity<Cupon> obtenerCuponPorId(@PathVariable Long id) {
		Cupon cupon = cuponRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado: " + id));
		return ResponseEntity.ok(cupon);
	}

	// Metodo Listar desactivadas
	@GetMapping("/desactivadas")
	public List<Cupon> obtenerCuponDesactivadas() {
		return cuponRepository.findByEstadoIsFalse();
	}

	// Metodo Listar activadas
	@GetMapping("/activadas")
	public List<Cupon> obtenerCuponActivadas() {
		return cuponRepository.findByEstadoIsTrue();
	}

	// crear proveedor
	@PostMapping("/")
	public ResponseEntity<Cupon> agregarProveedor(@RequestParam String nombre, @RequestParam String descuento,
			@RequestParam(value = "estado", required = false) Boolean estado) {
		// Verificar si el estado se proporcionó en la solicitud
		if (estado == null) {
			estado = true; // Establecer estado en true por defecto
		}
		Cupon cupon = new Cupon(nombre, descuento, estado);
		Cupon cuponGuardada = cuponRepository.save(cupon);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cuponGuardada.getCuponId()).toUri()).body(cuponGuardada);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cupon> actualizarCupon(@PathVariable Long id, @RequestParam String nombre,
			@RequestParam String descuento) {
		// Verificar si el proveedor existe en la base de datos
		Optional<Cupon> cuponOptional = cuponRepository.findById(id);
		if (!cuponOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		// Obtener el proveedor existente de la base de datos
		Cupon cuponExistente = cuponOptional.get();
		// Actualizar los datos del proveedor
		cuponExistente.setNombre(nombre);
		cuponExistente.setDescuento(descuento);

		// Guardar los cambios en la base de datos
		Cupon cuponActualizada = cuponRepository.save(cuponExistente);
		return ResponseEntity.ok(cuponActualizada);
	}
	@PostMapping("/activar/{id}")
	public ResponseEntity<Map<String, String>> activarCupon(@PathVariable Long id) {
		Optional<Cupon> cuponOptional = cuponRepository.findById(id);
		if (cuponOptional.isPresent()) {
			Cupon cupon = cuponOptional.get();
			cupon.setEstado(true);
			cuponRepository.save(cupon);

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Cupon activar con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// desactivar cateogria
	@PostMapping("/desactivar/{id}")
	public ResponseEntity<Map<String, String>> desactivarCupon(@PathVariable Long id) {
		Optional<Cupon> cuponOptional = cuponRepository.findById(id);
		if (cuponOptional.isPresent()) {
			Cupon cupon = cuponOptional.get();
			cupon.setEstado(false);
			cuponRepository.save(cupon);

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Cupon desactivar con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
