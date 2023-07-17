package com.backend.controladores;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.backend.entidades.Producto;
import com.backend.entidades.Producto_Talla;
import com.backend.excepciones.ResourceNotFoundException;
import com.backend.repositorios.ProductoRepository;
import com.backend.repositorios.ProductoTallaRepository;

@RestController
@RequestMapping("/producto-total")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class ProductoTallaController {
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ProductoTallaRepository productoTallaRepository;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto_Talla> obtenerProductoTallaPorId(@PathVariable Long id) {
		Producto_Talla producto = productoTallaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));

		return ResponseEntity.ok(producto);
	}


	@GetMapping
	public List<Producto_Talla> obtenerProductoActivadas() {
		return productoTallaRepository.findAll();
	}
	@PostMapping("/")
	public ResponseEntity<Producto_Talla> agregarProducto(@RequestParam String talla, @RequestParam int stock,
	        @RequestParam Long productoId) throws IOException {

	    Producto producto = productoRepository.findById(productoId)
	            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

	    Producto_Talla productoTalla = new Producto_Talla();
	    productoTalla.setTalla(talla);
	    productoTalla.setStock(stock);
	    productoTalla.setProducto(producto);

	    Producto_Talla productoTallaGuardado = productoTallaRepository.save(productoTalla);

	    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(productoTallaGuardado.getProductoTallaid()).toUri()).body(productoTallaGuardado);
	}

	
}
