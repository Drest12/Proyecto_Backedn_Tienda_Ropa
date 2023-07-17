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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.entidades.Categoria;
import com.backend.excepciones.ResourceNotFoundException;
import com.backend.repositorios.CategoriaRepository;

@RestController
@RequestMapping("/categoria")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class CategoriaController {
	@Autowired
	private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	// Método para obtener todas las categorías
	@GetMapping
	public List<Categoria> obtenerCategorias() {
		return categoriaRepository.findAll();
	}

	// Metodo para listar el id
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));

		return ResponseEntity.ok(categoria);
	}

	// Método para obtener las categorías activadas
	@GetMapping("/activadas")
	public List<Categoria> obtenerCategoriasActivadas() {
		return categoriaRepository.findByEstadoIsTrue();
	}

	// Metodo para listar las categorias desactivadas
	@GetMapping("/desactivadas")
	public List<Categoria> obtenerCategoriasDesactivadas() {
		return categoriaRepository.findByEstadoIsFalse();
	}



	// Método para crear una nueva categoría
	@PostMapping("/")
	public ResponseEntity<Categoria> agregarCategoria(@RequestParam String nombre,
			@RequestParam(value = "estado", required = false) Boolean estado,
			@RequestParam("imagen") MultipartFile archivo) throws IOException {
		byte[] bytesImagen = archivo.getBytes();

		// Verificar si el estado se proporcionó en la solicitud
		if (estado == null) {
			estado = true; // Establecer estado en true por defecto
		}

		Categoria categoria = new Categoria(nombre, bytesImagen, estado);
		Categoria categoriaGuardada = categoriaRepository.save(categoria);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaGuardada.getCategoriaId()).toUri()).body(categoriaGuardada);
	}

	// Metodo actualizar para categoria

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestParam String nombre,
			@RequestParam(value = "imagen", required = false) MultipartFile archivo) throws IOException {

		Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
		if (optionalCategoria.isPresent()) {
			Categoria categoriaExistente = optionalCategoria.get();
			categoriaExistente.setNombre(nombre);

			if (archivo != null && !archivo.isEmpty()) {
				byte[] bytesImagen = archivo.getBytes();
				categoriaExistente.setImagen(bytesImagen);
			}

			Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);
			return ResponseEntity.ok(categoriaActualizada);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

// activar categoria

	@PostMapping("/activar/{id}")
	public ResponseEntity<Map<String, String>> activarCategoria(@PathVariable Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		if (categoriaOptional.isPresent()) {
			Categoria categoria = categoriaOptional.get();
			categoria.setEstado(true);
			categoriaRepository.save(categoria);

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Categoría activar con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

// desactivar cateogria
	@PostMapping("/desactivar/{id}")
	public ResponseEntity<Map<String, String>> desactivarCategoria(@PathVariable Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		if (categoriaOptional.isPresent()) {
			Categoria categoria = categoriaOptional.get();
			categoria.setEstado(false);
			categoriaRepository.save(categoria);

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Categoría desactivada con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
