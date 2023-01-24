package com.mycompany.ad_2evaluacion.dao;

import com.mycompany.ad_2evaluacion.dto.Escuela;
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

public class EscuelaDAO {
    final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    final String driver = "org.exist.xmldb.DatabaseImpl";
    private XQueryService service;
    private Class cl;
    private Database database;
    private org.xmldb.api.base.Collection col;

    public EscuelaDAO() throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        col = DatabaseManager.getCollection(URI + "/Proyecto", "admin", "admin");
        service = (XQueryService) col.getService("XQueryService", "1.0");

    }

    public boolean createEscuela(Escuela x) throws XMLDBException {
        try {

//            String query = String.format("insert node '%s' into /alumnos", "<alumno>\n\t<nombre>A</nombre>\n</alumno>");
//            service.query(query);
            if (col.getResource("escuelas.xml") == null) {
                Resource z = col.createResource("escuelas.xml", "XMLResource");
                z.setContent("<escuelas>\n</escuelas>");
                col.storeResource(z);

            }
            Resource tabla = col.getResource("escuelas.xml");
            String contenidoAnterior = tabla.getContent().toString();
            if (contenidoAnterior.equals("")) {
                tabla.setContent("<escuelas></escuelas>");
                col.storeResource(tabla);
            }
            String insertar = x.toXML();
            contenidoAnterior = contenidoAnterior.substring(0, contenidoAnterior.length() - 11) + insertar + "</escuelas>";
            tabla.setContent(contenidoAnterior);
            col.storeResource(tabla);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String readEscuela() throws XMLDBException {
        String query = "for $escuela in /escuelas/escuela return $escuela";
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

    public boolean updateEscuela(Escuela x) {
        try {
            String query = String.format("for $x in //escuelas/escuela where $x/id = %d return update replace $x with %s", x.getId(), x.toXML());
            ResourceSet result = service.query(query);
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource resource = i.nextResource();
                System.out.println((String) resource.getContent());
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean deleteEscuela(int id) throws XMLDBException{
        try{
            String query = String.format("for $x in /escuelas/escuela where $x/id = %s return xmldb:delete-node($x)", id);
            service.query(query);
            
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
