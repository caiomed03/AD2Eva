package com.mycompany.ad_2evaluacion.dao;

import com.mycompany.ad_2evaluacion.dto.Curso;
import com.mycompany.ad_2evaluacion.dto.Monitor;
import java.util.*;
import java.io.*;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XQueryService;

public class CursoDAO {
    final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    final String driver = "org.exist.xmldb.DatabaseImpl";
    private XQueryService service;
    private Class cl;
    private Database database;
    private org.xmldb.api.base.Collection col;

    public CursoDAO() throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        col = DatabaseManager.getCollection(URI + "/Proyecto", "admin", "admin");
        service = (XQueryService) col.getService("XQueryService", "1.0");

    }

    public boolean createCurso(Curso x) throws XMLDBException {
        try {

//            String query = String.format("insert node '%s' into /alumnos", "<alumno>\n\t<nombre>A</nombre>\n</alumno>");
//            service.query(query);
            if (col.getResource("cursos.xml") == null) {
                Resource z = col.createResource("cursos.xml", "XMLResource");
                z.setContent("<cursos>\n</cursos>");
                col.storeResource(z);

            }
            Resource tabla = col.getResource("cursos.xml");
            String contenidoAnterior = tabla.getContent().toString();
            if (contenidoAnterior.equals("")) {
                tabla.setContent("<cursos></cursos>");
                col.storeResource(tabla);
            }
            String insertar = x.toXML();
            contenidoAnterior = contenidoAnterior.substring(0, contenidoAnterior.length() - 9) + insertar + "</cursos>";
            tabla.setContent(contenidoAnterior);
            col.storeResource(tabla);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String readCurso() throws XMLDBException {
        String query = "for $curso in /cursos/curso return $curso";
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

    public boolean updateCurso(Curso x) {
        try {
            String query = String.format("for $x in /cursos/curso where $x/precio = %f and $x/duracion = %f return update value $x with %s", x.getPrecio(), x.getDuracion(), x.toXML());
            service.query(query);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean deleteCurso(float precio, float duracion) throws XMLDBException{
        try{
            String query = String.format("for $x in /cursos/curso where $x/precio = %d and $x/duracion = %d return update delete $x", precio, duracion);
            service.query(query);
            
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
