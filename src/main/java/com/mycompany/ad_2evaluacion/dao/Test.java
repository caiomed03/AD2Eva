package com.mycompany.ad_2evaluacion.dao;

import com.mycompany.ad_2evaluacion.dto.Alumno;
import java.util.*;
import java.io.*;
import org.xmldb.api.base.XMLDBException;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException{
        Scanner sc = new Scanner(System.in);
        AlumnoDAO a = new AlumnoDAO();
//        System.out.println(a.createAlumno(new Alumno(1, 1, 638896940, "Caio", "123")));
//        System.out.println(a.readAlumno());
//        System.out.println(a.updateAlumno(new Alumno(1, 2, 2, "Eduardo", "1231")));
            System.out.println(a.deleteAlumno(1));
    }
}
