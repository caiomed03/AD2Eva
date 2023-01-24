package com.mycompany.ad_2evaluacion.dto;

import java.util.*;
import java.io.*;

public class Escuela {
    private int id, telefono;
    private String denominacion, direccion, email;

     public String toXML() {
        return String.format("<escuela>"
                + "<id>%d</id>"
                + "<denominacion>%s</denominacion>"
                + "<telefono>%d</telefono>"
                + "<direccion>%s</direccion>"
                + "<email>%s</email>"
                + "</escuela>", id, denominacion, telefono, direccion, email);
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

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Escuela(int id, int telefono, String denominacion, String direccion, String email) {
        this.id = id;
        this.telefono = telefono;
        this.denominacion = denominacion;
        this.direccion = direccion;
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.denominacion);
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
        final Escuela other = (Escuela) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.denominacion, other.denominacion);
    }
    
    
}
