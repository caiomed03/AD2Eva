package com.mycompany.ad_2evaluacion.dto;

import java.util.*;
import java.io.*;

public class Alumno {

    private int id, id_escuela, telefono;
    private String nombre, n_matricula;

    public Alumno(int id, int id_escuela, int telefono, String nombre, String n_matricula) {
        this.id = id;
        this.id_escuela = id_escuela;
        this.telefono = telefono;
        this.nombre = nombre;
        this.n_matricula = n_matricula;
    }

    public String toXML() {
        return String.format("<alumno><id>%d</id><nombre>%s</nombre><telefono>%d</telefono><numero_matricula>%s</numero_matricula><id_escuela>%d</id_escuela></alumno>", id, nombre, telefono, n_matricula, id_escuela);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_escuela() {
        return id_escuela;
    }

    public void setId_escuela(int id_escuela) {
        this.id_escuela = id_escuela;
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

    public String getN_matricula() {
        return n_matricula;
    }

    public void setN_matricula(String n_matricula) {
        this.n_matricula = n_matricula;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.nombre);
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
        final Alumno other = (Alumno) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

}
