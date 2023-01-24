package com.mycompany.ad_2evaluacion.dto;

import java.util.*;
import java.io.*;

public class Monitor {

    private int id, telefono;
    private String nombre, email;

    public Monitor(int id, int telefono, String nombre, String email) {
        this.id = id;
        this.telefono = telefono;
        this.nombre = nombre;
        this.email = email;
    }

    public String toXML() {
        return String.format("<monitor><id>%d</id><nombre>%s</nombre><telefono>%d</telefono><email>%s</email></monitor>", id, nombre, telefono, email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Monitor other = (Monitor) obj;
        return this.id == other.id;
    }

}
