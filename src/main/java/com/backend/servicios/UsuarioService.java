package com.backend.servicios;

import java.util.Set;

import com.backend.modelo.Usuario;
import com.backend.modelo.UsuarioRol;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);
    Set<Usuario> obtenerMUsuario();
}
