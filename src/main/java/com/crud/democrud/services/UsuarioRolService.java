package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioRol;
import com.crud.democrud.repositories.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UsuarioRolService {
    @Autowired
    UsuarioRolRepository usuarioRolRepository;

    /*public ArrayList<UsusarioRol> obtenerRoles(){
        return (ArrayList<UsusarioRol>) usuarioRolRepository.findAll();
    }*/
    public ArrayList<UsuarioRol> obtenerRoles() {
        return (ArrayList<UsuarioRol>) usuarioRolRepository.findAll();
    }

    public UsuarioRol guardarUsuarioRol(UsuarioRol usuario) {
        return usuarioRolRepository.save(usuario);
    }

    public UsuarioRol editarUsuario(long id, UsuarioRol usuario) {
        usuario.setId(id);
        return usuarioRolRepository.save(usuario);
    }

    public boolean eliminarUsuario(Long id) {
        try {
            usuarioRolRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
