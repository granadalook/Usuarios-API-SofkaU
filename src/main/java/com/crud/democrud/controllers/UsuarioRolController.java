package com.crud.democrud.controllers;


import com.crud.democrud.models.UsuarioRol;
import com.crud.democrud.services.UsuarioRolService;
import com.crud.democrud.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@CrossOrigin
@RestController
@RequestMapping("/usuario/rol")
public class UsuarioRolController {

    @Autowired
    UsuarioRolService usuarioRolService;

    private final Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;


    @GetMapping()
    public ResponseEntity<Response> getAllUserRols() {
        response.restart();
        try {
            response.data = usuarioRolService.getUserRols();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> getUserRolById(@PathVariable(value = "id") Long id) {
        response.restart();
        try {
            response.data = usuarioRolService.findById(id);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }


    @GetMapping("/query")
    public ResponseEntity<Response> getUserRolByRol(@RequestParam(value = "rol") String rol) {
        response.restart();
        try {
            response.data = usuarioRolService.findByRol(rol);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }


    @PostMapping()
    public ResponseEntity<Response> saveUserRol(@RequestBody UsuarioRol usuarioRol) {
        response.restart();
        try {
            response.data = usuarioRolService.saveUserRol(usuarioRol);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUserRol(@RequestBody UsuarioRol usuarioRol, @PathVariable(value = "id") Long id) {

        response.restart();
        try {
            response.data = usuarioRolService.updateUserRol(id, usuarioRol);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response> deleteUserRol(@PathVariable(value = "id") Long id) {
        response.restart();
        try {
            response.data = usuarioRolService.deleteUserRol(id);
            if (response.data == null) {
                response.message = "NO EXISTE";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "ELIMINADO";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }


    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if (exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "REGISTRADO";
                    break;
                case 1452:
                    response.message = "NO EXISTE";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
