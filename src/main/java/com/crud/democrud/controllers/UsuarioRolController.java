package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioRol;
import com.crud.democrud.services.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/usuariorol")
public class UsuarioRolController {
    @Autowired
    UsuarioRolService usuarioRolService;


    @GetMapping()
    public ArrayList<UsuarioRol> obtenerRoles() {
        return usuarioRolService.obtenerRoles();
    }

    @PostMapping()
    public UsuarioRol guardarUsuarioRol(@RequestBody UsuarioRol usuario) {
        return this.usuarioRolService.guardarUsuarioRol(usuario);
    }

    @PutMapping(path = "/{id}")
    public UsuarioRol editarUsuarios(@PathVariable("id") Long id, @RequestBody UsuarioRol usuario) {
        return this.usuarioRolService.editarUsuario(id, usuario);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.usuarioRolService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id" + id;
        }
    }
}
