package com.backend.controladores;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import com.backend.entidades.Promocion;
import com.backend.excepciones.ResourceNotFoundException;
import com.backend.repositorios.PromocionRepository;

@RestController
@RequestMapping("/promocion")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class PromocionController {
	@Autowired
	private PromocionRepository promocionRepository;
	

	// Método para obtener todas las categorías
	@GetMapping
	public List<Promocion> obtenerPromocion() {
		return promocionRepository.findAll();
	}

	// Metodo para listar el id
	@GetMapping("/{id}")
	public ResponseEntity<Promocion> obtenerPromocionPorId(@PathVariable Long id) {
		Promocion promocion = promocionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Promocion no encontrado: " + id));

		return ResponseEntity.ok(promocion);
	}

	// Método para obtener las categorías activadas
	@GetMapping("/activadas")
	public List<Promocion> obtenerPromocionActivadas() {
		return promocionRepository.findByEstadoIsTrue();
	}

	// Metodo para listar las categorias desactivadas
	@GetMapping("/desactivadas")
	public List<Promocion> obtenerPromocionDesactivadas() {
		return promocionRepository.findByEstadoIsFalse();
	}

	// Método para crear una nueva categoría
	/*@PostMapping("/")
	public ResponseEntity<Promocion> agregarPromocion(@RequestParam String titulo, @RequestParam String descuento,
	        @RequestParam(value = "estado", required = false) Boolean estado,
	        @RequestParam("imagen") MultipartFile archivo,
	        @RequestParam Long productoId) throws IOException {
	    byte[] bytesImagen = archivo.getBytes();

	    // Verificar si el estado se proporcionó en la solicitud
	    if (estado == null) {
	        estado = true; // Establecer estado en true por defecto
	    }

	    Producto producto = ProductoRepository.findById(productoId)
	            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

	    Promocion promocion = new Promocion(titulo, descuento, bytesImagen, estado, producto);
	    promocion.setProducto(producto);

	    Promocion promocionGuardada = promocionRepository.save(promocion);

	    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(promocionGuardada.getPromocionId()).toUri()).body(promocionGuardada);
	}
*/
	@PutMapping("/{id}")
	public ResponseEntity<Promocion> actualizarPromocion(@PathVariable Long id, @RequestParam String titulo,
			@RequestParam String descuento, @RequestParam(value = "imagen", required = false) MultipartFile archivo)
			throws IOException {

		Optional<Promocion> optionalPromocion = promocionRepository.findById(id);
		if (optionalPromocion.isPresent()) {
			Promocion promocionExistente = optionalPromocion.get();
			promocionExistente.setTitulo(titulo);
			promocionExistente.setDescuento(descuento);

			if (archivo != null && !archivo.isEmpty()) {
				byte[] bytesImagen = archivo.getBytes();
				promocionExistente.setImagen(bytesImagen);
			}

			Promocion promocionActualizada = promocionRepository.save(promocionExistente);
			return ResponseEntity.ok(promocionActualizada);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping("/activar/{id}")
	public ResponseEntity<Map<String, String>> activarPromocion(@PathVariable Long id) {
		Optional<Promocion> promocionOptional = promocionRepository.findById(id);
		if (promocionOptional.isPresent()) {
			Promocion promocion = promocionOptional.get();
			promocion.setEstado(true);
			promocionRepository.save(promocion);
			

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Promocion desactivada con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

// desactivar cateogria
	@PostMapping("/desactivar/{id}")
	public ResponseEntity<Map<String, String>> desactivarCategoria(@PathVariable Long id) {
		Optional<Promocion> promocionOptional = promocionRepository.findById(id);
		if (promocionOptional.isPresent()) {
			Promocion promocion = promocionOptional.get();
			promocion.setEstado(false);
			promocionRepository.save(promocion);
			

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Promocion desactivada con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
