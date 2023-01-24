/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ad_2evaluacion.documentacion;

import java.util.Arrays;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.base.Resource;

/**
 *
 * @author cmedeiros
 */
public class CRUD {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        // Configuración de la base de datos
        final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
        final String driver = "org.exist.xmldb.DatabaseImpl";

        // Inicialización del driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        // CREATE
        // Conexión a la base de datos. 
        // Primer parámetro: Ruta de la colección
        // Segundo parámetro: Usuario
        // Tercer parámetro: Contraseña para el usuario
        Collection col = DatabaseManager.getCollection(URI + "/OperacionesCRUD", "admin", "admin");

        // Creación del elemento XML
        String xml = "<libro><autor>Gustavo</autor></libro>";

        // Creación del objeto Resource con el XML
        // Primer parámetro: Nombre del recurso que se quiere guardar
        // Segundo parámetro: Tipo de recurso (XMLResource o BinaryResource)
        Resource res = col.createResource("miLibro.xml", "XMLResource");
        res.setContent(xml);

        // Agregar el recurso a la colección
        col.storeResource(res);


        // READ
        // Listar los recursos dentro de una colección
        System.out.println(Arrays.toString(col.listResources()));
        
        // Ver contenido del recurso
        System.out.println(col.getResource(col.listResources()[0]).getContent().toString());
        
        
        // UPDATE
        // Actualización de un recurso
        String xml2 = "<libro><autor>Gustavo</autor></libro>";
        Resource res2 = col.createResource("miLibro.xml", "XMLResource");
        res.setContent(xml2);
        col.storeResource(res2);


        // DELETE
        // Obtener el recurso que se quiera eliminar.
        // Primer parámetro: ID del recurso
        Resource eliminar = col.getResource("miLibro.xml");
        
        // Borrado
        col.removeResource(eliminar);
        
    }
}
