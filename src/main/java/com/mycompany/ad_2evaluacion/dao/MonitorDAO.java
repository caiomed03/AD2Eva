package com.mycompany.ad_2evaluacion.dao;

import com.mycompany.ad_2evaluacion.dto.Alumno;
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

public class MonitorDAO {
    final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    final String driver = "org.exist.xmldb.DatabaseImpl";
    private XQueryService service;
    private Class cl;
    private Database database;
    private org.xmldb.api.base.Collection col;

    public MonitorDAO() throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        col = DatabaseManager.getCollection(URI + "/Proyecto", "admin", "admin");
        service = (XQueryService) col.getService("XQueryService", "1.0");

    }

    public boolean createMonitor(Monitor x) throws XMLDBException {
        try {

//            String query = String.format("insert node '%s' into /alumnos", "<alumno>\n\t<nombre>A</nombre>\n</alumno>");
//            service.query(query);
            if (col.getResource("monitores.xml") == null) {
                Resource z = col.createResource("monitores.xml", "XMLResource");
                z.setContent("<monitores>\n</monitores>");
                col.storeResource(z);

            }
            Resource tabla = col.getResource("monitores.xml");
            String contenidoAnterior = tabla.getContent().toString();
            if (contenidoAnterior.equals("")) {
                tabla.setContent("<monitores></monitores>");
                col.storeResource(tabla);
            }
            String insertar = x.toXML();
            contenidoAnterior = contenidoAnterior.substring(0, contenidoAnterior.length() - 12) + insertar + "</monitores>";
            tabla.setContent(contenidoAnterior);
            col.storeResource(tabla);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String readMonitor() throws XMLDBException {
        String query = "for $monitor in /monitores/monitor return $monitor";
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

    public boolean updateMonitor(Monitor x) {
        try {
            String query = String.format("for $x in //monitores/monitor where $x/id = %d return update replace $x with %s", x.getId(), x.toXML());
            ResourceSet result = service.query(query);
            System.out.println("a");
            // Iterate over the results
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
    
    public boolean deleteMonitor(int id) throws XMLDBException{
        try{
            String query = String.format("for $x in /monitores/monitor where $x/id = %s return xmldb:delete-node($x)", id);
            service.query(query);
            
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
