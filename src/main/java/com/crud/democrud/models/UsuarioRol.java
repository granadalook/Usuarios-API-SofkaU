package com.crud.democrud.models;

import javax.persistence.*;

public class UsuarioRol {
    @Entity
    @Table(name = "usuarioRoles")
    public class UsusarioRol {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)

        private long id;
        private long idRol;
        private String rol;

        public void setId(long id) {
            this.id = id;
        }

        public void setIdRol(long idRol) {
            this.idRol = idRol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }

        public long getId() {
            return id;
        }

        public long getIdRol() {
            return idRol;
        }

        public String getRol() {
            return rol;
        }

        public UsusarioRol(long id, long idRol, String rol) {
            this.id = id;
            this.idRol = idRol;
            this.rol = rol;
        }

        public UsusarioRol() {
        }

    }
}
