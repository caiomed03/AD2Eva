/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ad_2evaluacion.tiposDeImplementacion;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XQueryService;

/**
 *
 * @author cmedeiros
 */
public class Consultas {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        // Configuración de la base de datos
        final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
        final String driver = "org.exist.xmldb.DatabaseImpl";

        // Inicialización del driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        // Obtener el servicio de XQuery
        Collection col = DatabaseManager.getCollection(URI + "/OperacionesCRUD", "admin", "admin");
        XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");
        service.setProperty("indent", "yes");

        // Escribir la consulta en XQuery
        String query = "for $book in /libros/libro where $book/titulo = 'Prueba Documentacion' return $book";

        // Realizar la consulta
        ResourceSet result = service.query(query);

        // Comprobar los resultados
        ResourceIterator i = result.getIterator();
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());
        }
    }
}
