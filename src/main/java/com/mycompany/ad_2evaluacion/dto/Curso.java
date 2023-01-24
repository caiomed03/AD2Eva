package com.mycompany.ad_2evaluacion.dto;

import java.util.*;
import java.io.*;

public class Curso {
    private int id;
    private float precio, duracion;

    public Curso(int id, float precio, float duracion) {
        this.id = id;
        this.precio = precio;
        this.duracion = duracion;
    }
    
     public String toXML() {
        return String.format("<curso><id>%d</id><precio>%f</precio><duracion>%f</duracion></curso>", id, precio, duracion);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
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
        final Curso other = (Curso) obj;
        return this.id == other.id;
    }
    
    
}
