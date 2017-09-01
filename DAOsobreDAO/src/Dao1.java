//import net.xqj.exist.ExistXQDataSource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.*;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XQueryService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by tomeCabello on 08/04/2016.
 */
public class Dao1 {




    static String Uri;

    static String driver;
    static String user;
    static String passwd;
    static String ip;





    public static String getUri() {
        return Uri;
    }

    public static void setUri(String uri) {
        Uri = uri;
    }

    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        Dao1.driver = driver;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Dao1.user = user;
    }

    public static String getPasswd() {
        return passwd;
    }

    public static void setPasswd(String passwd) {
        Dao1.passwd = passwd;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        Dao1.ip = ip;
    }


    /**
     * METODO PARA AÑADIR UNA COLECCION
     * @param a nombre coleccion
     * @throws XMLDBException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void Colec(String a) throws XMLDBException, IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        // initialize database driver
        Class c = Class.forName(driver);
        Database database = (Database) c.newInstance();
        database.setProperty("create-database", "true");
        // crear el manegador
        DatabaseManager.registerDatabase(database);
        //crear la collecion
        Collection parent = DatabaseManager.getCollection(Uri+"/db",user,passwd);
        CollectionManagementService c2 = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
        c2.createCollection(a);
        log("Se crea "+a);

    }


    /**
     * METODO PARA AÑADIR UN RECURSO.
     * @param direcc dirección del recurso
     * @param rec nombre  que se le dará al recurso
     * @throws ClassNotFoundException
     * @throws XMLDBException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public static void Recur(String direcc, String rec, String colec) throws ClassNotFoundException, XMLDBException, IllegalAccessException, InstantiationException, IOException {
        File f = new File(direcc);
        Collection col;

        // initialize database driver
        Class c = Class.forName(driver);
        Database database = (Database) c.newInstance();
        database.setProperty("create-database", "true");
        // crear el manegador
        DatabaseManager.registerDatabase(database);
        if(colec.isEmpty())
            col = DatabaseManager.getCollection(Uri + "/db", user, passwd);
        else
            col = DatabaseManager.getCollection(Uri + colec, user, passwd);
        //afegir el recurs que farem servir
        Resource res = col.createResource(rec,"XMLResource");
        res.setContent(f);
        col.storeResource(res);
        log("Se crea  "+rec);

    }


    /**
     * METODO PARA BPRRAR RECURSO
     * @param r nombre del recurso borrado
     * @throws ClassNotFoundException
     * @throws XMLDBException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public static void DRecur(String r) throws ClassNotFoundException, XMLDBException, IllegalAccessException, InstantiationException, IOException {

        // initialize database driver
        Class c = Class.forName(driver);
        Database database = (Database) c.newInstance();
        database.setProperty("create-database", "true");
        // crear el manegador
        DatabaseManager.registerDatabase(database);
        Collection col = DatabaseManager.getCollection(Uri + "/db", user, passwd);
        //afegir el recurs que farem servir
        Resource res = col.getResource(r);
        col.removeResource(res);
        log("Eliminando "+r);

    }


    /**
     * METODO PARA ELIMINAR COLECCIONES
     * @param r nombre colleccion
     * @throws ClassNotFoundException
     * @throws XMLDBException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public static void DColecc(String r) throws ClassNotFoundException, XMLDBException, IllegalAccessException, InstantiationException, IOException {

        // initialize database driver
        Class c = Class.forName(driver);
        Database database = (Database) c.newInstance();
        database.setProperty("create-database", "true");
        // crear el manegador
        DatabaseManager.registerDatabase(database);
        Collection parent = DatabaseManager.getCollection(Uri+"/db",user,passwd);
        CollectionManagementService c2 = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");

        c2.removeCollection(r);
        log("Eliminando "+r);

    }


    /**
     * METODO PARA HACER CONSULTAS (XPath).
     * @param a consulta
     * @return resultado de la consulta
     * @throws XMLDBException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public static String cXp(String a) throws XMLDBException, ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        Class c = Class.forName(driver);
        Database database = (Database) c.newInstance();
        database.setProperty("create-database", "true");
        // crear el manegador
        DatabaseManager.registerDatabase(database);

        Collection col = DatabaseManager.getCollection(Uri + "/db", user, passwd);

        //afegir el recurs que farem servir
        col.getResource("er");
        XQueryService xqs = (XQueryService) col.getService("XQueryService",
                "1.0");
        xqs.setProperty("indent", "yes");

        ResourceSet result = xqs.query((a));


        ResourceIterator i = result.getIterator();
        Resource res1;
        String res = " ";
        while (i.hasMoreResources()) {
            res1 = i.nextResource();

            res = res + ("LA RESPUESTA ES :"+res1.getContent());
        }log("Realizado:  "+a);return res;}


    /**
     * METODO PARA HACER CONSULTAS (XQUERY)
     * @param a consulta
     * @return resultado de la consulta.
     * @throws ClassNotFoundException
     * @throws XMLDBException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public static String cXQ(String a) throws ClassNotFoundException, XMLDBException, IllegalAccessException, InstantiationException, IOException {
        Class c = Class.forName(driver);
        Database database = (Database) c.newInstance();
        database.setProperty("create-database", "true");
        // crear el manegador
        DatabaseManager.registerDatabase(database);

        Collection col = DatabaseManager.getCollection(Uri + "/db", user, passwd);

        //afegir el recurs que farem servir
        col.getResource("Pokemon.xml");
        XQueryService xqs = (XQueryService) col.getService("XQueryService",
                "1.0");
        xqs.setProperty("indent", "yes");
        ResourceSet result = xqs.query((a));
        ResourceIterator i = result.getIterator();
        Resource res1 = null;
        String res=" ";
        while (i.hasMoreResources()) {
            res1 = i.nextResource();
            res= res + (res1.getContent());
        }
        log("Realizado: "+a);
        return res;
    }



    public static void main (String args[]) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

        Dao1 dao1 = new Dao1();

        dao1.setDriver("org.exist.xmldb.DatabaseImpl");
        dao1.setIp("localhost");
        dao1.setUser("admin");
        dao1.setPasswd("admin");
        dao1.setUri("xmldb:exist://localhost:8080/exist/xmlrpc");


        Colec("basuree");
        Recur("/home/pau/IdeaProjects/DAOsobreDAO/src/bbdd", "er","/db/basuree");
        /// DRecur("er");
        System.out.println(cXp("sum (/CATALOG/PLANT/AVAILABILITY)\n"));
        System.out.println(cXQ("      for $libro in /note/to\n" +
                "      \n" +
                "return \n" +
                "      <resultado>\n" +
                "        {$libro}\n" +
                "      </resultado>\n"));

    }




    public static void log(String a) throws IOException {

        Date f = new Date();
        String log = f.toString()+" "+a;
        BufferedWriter out = null;

        out = new BufferedWriter(new FileWriter("log.txt", true));
        out.write(log+"\n");
        out.close();





    }






}