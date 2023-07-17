package com.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.backend.modelo.UsuarioRol;
import com.backend.repositorios.UsuarioRolRepository;

@RestController
@RequestMapping("/usuariorol")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class UsuarioRolController {


	  private final UsuarioRolRepository usuarioRolRepository;

	  @Autowired
	  public UsuarioRolController(UsuarioRolRepository usuarioRolRepository) {
	    this.usuarioRolRepository = usuarioRolRepository;
	  }

	  @GetMapping
	  public ResponseEntity<List<UsuarioRol>> obtenerTodosLosUsuarioRoles() {
	    List<UsuarioRol> usuarioRoles = usuarioRolRepository.findAll();
	    return new ResponseEntity<>(usuarioRoles, HttpStatus.OK);
	  }
	  
	  @GetMapping("/admin")
	  public ResponseEntity<List<UsuarioRol>> obtenerAdminRoles() {
	      List<UsuarioRol> usuarioRoles = usuarioRolRepository.findByRolRolNombre("ADMIN");
	      return new ResponseEntity<>(usuarioRoles, HttpStatus.OK);
	  }
	  @GetMapping("/normal")
	  public ResponseEntity<List<UsuarioRol>> obtenerNormalRoles() {
	      List<UsuarioRol> usuarioRoles = usuarioRolRepository.findByRolRolNombre("NORMAL");
	      return new ResponseEntity<>(usuarioRoles, HttpStatus.OK);
	  }

	
}
