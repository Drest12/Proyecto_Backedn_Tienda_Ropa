package com.backend.controladores;

import java.io.IOException;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.entidades.Categoria;

import com.backend.entidades.Producto;

import com.backend.excepciones.ResourceNotFoundException;

import com.backend.repositorios.CategoriaRepository;

import com.backend.repositorios.ProductoRepository;

@RestController
@RequestMapping("/producto")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class ProductoController {
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;



	@GetMapping
	public List<Producto> obtenerProducto() {
		return productoRepository.findAll();
	}

	// Metodo para listar el id
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));

		return ResponseEntity.ok(producto);
	}

	// Método para obtener las categorías activadas
	@GetMapping("/activadas")
	public List<Producto> obtenerProductoActivadas() {
		return productoRepository.findByEstadoIsTrue();
	}

	// Metodo para listar las categorias desactivadas
	@GetMapping("/desactivadas")
	public List<Producto> obtenerProductoDesactivadas() {
		return productoRepository.findByEstadoIsFalse();
	}

	@PostMapping("/")
	public ResponseEntity<Producto> agregarProducto(@RequestParam String nombre, @RequestParam String precio,
			@RequestParam String descripcion, 
			@RequestParam String version, @RequestParam String material, @RequestParam String atributo, 
			@RequestParam String equipo, @RequestParam String temporada, 
			@RequestParam String genero, 
			@RequestParam(value = "estado", required = false) Boolean estado,
			@RequestParam("imagen1") MultipartFile imagen1, @RequestParam("imagen2") MultipartFile imagen2,
			@RequestParam Long categoriaId) throws IOException {
		byte[] bytesImagen1 = imagen1.getBytes();
		byte[] bytesImagen2 = imagen2.getBytes();

		// Verificar si el estado se proporcionó en la solicitud
		if (estado == null) {
			estado = true; // Establecer estado en true por defecto
		}

		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

	

		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setDescripcion(descripcion);

		producto.setVersion(version);
		producto.setMaterial(material);
		producto.setGenero(genero);
		producto.setEquipo(equipo);
		producto.setAtributo(atributo);
		producto.setTemporada(temporada);
		producto.setEstado(estado);
		producto.setImagen1(bytesImagen1);
		producto.setImagen2(bytesImagen2);

		producto.setCategoria(categoria);

		Producto productoGuardado = productoRepository.save(producto);

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productoGuardado.getProductoId()).toUri()).body(productoGuardado);
	}
	
	
	// nombres de productos
	
	  @GetMapping("/guatemala")
	  public ResponseEntity<List<Producto>> obtenerGuatemala() {
	      List<Producto> producto = productoRepository.findByEquipo("Seleccion Guatemala");
	      return new ResponseEntity<>(producto, HttpStatus.OK);
	  }
	  @GetMapping("/costa-rica")
	  public ResponseEntity<List<Producto>> obtenerCostaRica() {
	      List<Producto> producto = productoRepository.findByEquipo("Seleccion Costa Rica");
	      return new ResponseEntity<>(producto, HttpStatus.OK);
	  }

	  @GetMapping("/panama")
	  public ResponseEntity<List<Producto>> obtenerPanama() {
	      List<Producto> producto = productoRepository.findByEquipo("Seleccion Panama");
	      return new ResponseEntity<>(producto, HttpStatus.OK);
	  }
	  @GetMapping("/jamaica")
	  public ResponseEntity<List<Producto>> obtenerJamaica() {
	      List<Producto> producto = productoRepository.findByEquipo("Seleccion Jamaica");
	      return new ResponseEntity<>(producto, HttpStatus.OK);
	  }
	  @GetMapping("/honduras")
	  public ResponseEntity<List<Producto>> obtenerHonduras() {
	      List<Producto> producto = productoRepository.findByEquipo("Seleccion Honduras");
	      return new ResponseEntity<>(producto, HttpStatus.OK);
	  }

}
