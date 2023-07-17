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

import com.backend.entidades.Empresa;

import com.backend.excepciones.ResourceNotFoundException;
import com.backend.repositorios.EmpresaRepository;

@RestController
@RequestMapping("/empresa")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;

	@GetMapping
	public List<Empresa> obtenerMarca() {
		return empresaRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable Long id) {
		Empresa empresa = empresaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrado: " + id));
		return ResponseEntity.ok(empresa);
	}

	@GetMapping("/desactivadas")
	public List<Empresa> obtenerEmpresaDesactivadas() {
		return empresaRepository.findByEstadoIsFalse();
	}

	@GetMapping("/activadas")
	public List<Empresa> obtenerEmpresaActivadas() {
		return empresaRepository.findByEstadoIsTrue();
	}

	@PostMapping("/")
	public ResponseEntity<Empresa> agregarMarca(@RequestParam String nombre, @RequestParam String descripcion,
			@RequestParam String eslogan, @RequestParam String direccion, @RequestParam String telefono,
			@RequestParam String correo, @RequestParam String celular,
			@RequestParam(value = "estado", required = false) Boolean estado,
			@RequestParam("imagen") MultipartFile archivo) throws IOException {
		byte[] bytesImagen = archivo.getBytes();

		if (estado == null) {
			estado = true;
		}

		Empresa empresa = new Empresa(nombre, descripcion, eslogan, direccion, telefono, correo, celular, estado,
				bytesImagen);
		Empresa empresaGuardada = empresaRepository.save(empresa);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(empresaGuardada.getEmpresaId()).toUri()).body(empresaGuardada);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id, @RequestParam String nombre,
			@RequestParam String descripcion, @RequestParam String eslogan, @RequestParam String direccion,
			@RequestParam String telefono, @RequestParam String correo, @RequestParam String celular,
			@RequestParam(value = "imagen", required = false) MultipartFile archivo) throws IOException {

		Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
		if (optionalEmpresa.isPresent()) {
			Empresa empresaExistente = optionalEmpresa.get();
			empresaExistente.setNombre(nombre);
			empresaExistente.setDescripcion(descripcion);
			empresaExistente.setEslogan(eslogan);
			empresaExistente.setDireccion(direccion);
			empresaExistente.setTelefono(telefono);
			empresaExistente.setCorreo(correo);
			empresaExistente.setCelular(celular);

			if (archivo != null && !archivo.isEmpty()) {
				byte[] bytesImagen = archivo.getBytes();
				empresaExistente.setImagen(bytesImagen);
			}
			Empresa empresaActualizada = empresaRepository.save(empresaExistente);
			return ResponseEntity.ok(empresaActualizada);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/desactivar/{id}")
	public ResponseEntity<Map<String, String>> desactivarEmpresa(@PathVariable Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		if (empresaOptional.isPresent()) {
			Empresa empresa = empresaOptional.get();
			empresa.setEstado(false);
			empresaRepository.save(empresa);

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Empresa desactivar con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping("/activar/{id}")
	public ResponseEntity<Map<String, String>> activarEmpresa(@PathVariable Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		if (empresaOptional.isPresent()) {
			Empresa empresa = empresaOptional.get();
			empresa.setEstado(true);
			empresaRepository.save(empresa);

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Empresa activada con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
