package com.mycompany.ad_2evaluacion.dao;

import com.mycompany.ad_2evaluacion.dto.Alumno;
import java.io.*;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XQueryService;

public class AlumnoDAO {

    final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    final String driver = "org.exist.xmldb.DatabaseImpl";
    private XQueryService service;
    private Class cl;
    private Database database;
    private Collection col;

    public AlumnoDAO() throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        col = DatabaseManager.getCollection(URI + "/Proyecto", "admin", "admin");
        service = (XQueryService) col.getService("XQueryService", "1.0");

    }

    public boolean createAlumno(Alumno x) throws XMLDBException {
        try {

//            String query = String.format("insert node '%s' into /alumnos", "<alumno>\n\t<nombre>A</nombre>\n</alumno>");
//            service.query(query);
            if (col.getResource("alumnos.xml") == null) {
                Resource z = col.createResource("alumnos.xml", "XMLResource");
                z.setContent("<alumnos>\n</alumnos>");
                col.storeResource(z);

            }
            Resource tabla = col.getResource("alumnos.xml");
            String contenidoAnterior = tabla.getContent().toString();
            if (contenidoAnterior.equals("")) {
                tabla.setContent("<alumnos></alumnos>");
                col.storeResource(tabla);
            }
            String insertar = x.toXML();
            contenidoAnterior = contenidoAnterior.substring(0, contenidoAnterior.length() - 10) + insertar + "</alumnos>";
            tabla.setContent(contenidoAnterior);
            col.storeResource(tabla);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String readAlumnoByName(String nombre) throws XMLDBException{
        String query = String.format("for $alumno in /alumnos/alumno where $alumno/nombre = %s return $alumno", nombre);
        service.query(query);

        // Realizar la consulta
        ResourceSet result = service.query(query);
        String res = "";
        // Comprobar los resultados
        ResourceIterator i = result.getIterator();
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            res += (String) r.getContent();
        }
        return res;
    }

    public String readAlumno() throws XMLDBException {
        String query = "for $alumno in /alumnos/alumno return $alumno";
        service.query(query);

        // Realizar la consulta
        ResourceSet result = service.query(query);
        String res = "";
        // Comprobar los resultados
        ResourceIterator i = result.getIterator();
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            res += (String) r.getContent();
        }
        return res;
    }

    public boolean updateAlumno(Alumno x) {
        try {
            String query = String.format("for $x in /alumnos/alumno where $x/nombre = %s return update value $x with %s", x.getNombre(), x.toXML());
            service.query(query);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean deleteAlumno(String name) throws XMLDBException{
        try{
            String query = String.format("for $x in /alumnos/alumno where $x/id = %s return update delete", name);
            service.query(query);
            
            return true;
        } catch(Exception e){
            return false;
        }
    }

}
