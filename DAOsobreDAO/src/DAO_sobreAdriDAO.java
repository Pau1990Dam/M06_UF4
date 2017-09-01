
import jaxbObj.*;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;


/**
 * Created by pau on 18/04/17.
 */
public class DAO_sobreAdriDAO {

    private String uri;
    private String driver;
    private String user;
    private String passwd;
    private String ip;

    private File docXml;
    private String currentCol;
    private Dao1 dao;

    private Practica2Type jaxbDocroot;



    public DAO_sobreAdriDAO(String uri, String driver, String user, String passwd, String ip){

        this.uri = uri;
        this.driver = driver;
        this.user = user;
        this.passwd = passwd;
        this.ip = ip;
        connection();


    }

    private void connection(){
        dao = new Dao1();

        dao.setUri(uri);
        dao.setDriver(driver);
        dao.setUser(user);
        dao.setPasswd(passwd);
        dao.setIp(ip);
    }

    public String xQuery(String query){
        try {
            return Dao1.cXQ(query);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String xPath(String query){
        try {
            return Dao1.cXp(query);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void DeleteResource(String recurs){
        try {
                Dao1.DRecur(recurs);
            System.out.println("Recurs "+recurs+" borrat.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Deletecollecton(String collection){
        try {
            Dao1.DColecc(collection);
            System.out.println("Col·lecció "+collection+" borrada.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setResourceToWorkWith(String path_resourceToWorkWith){

        try {
            docXml = new File(path_resourceToWorkWith);
            System.out.println("File -> "+docXml.getAbsolutePath());
            jaxbDocroot = ((JAXBElement<Practica2Type>) xmlToJava().unmarshal(docXml)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private Unmarshaller xmlToJava(){

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(ObjectFactory.class);
            return context.createUnmarshaller();

        } catch (JAXBException e) {
            System.out.println("No s'ha pogut fer l'Umarshalling. Error en la funció xmlToJava(). Causa: "+e.getCause());
            return null;
        }
    }

    private boolean javaToXml(){

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(ObjectFactory.class);
            Marshaller xml = context.createMarshaller();
            xml.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            QName qName = new QName("", "practica2");
            JAXBElement<Practica2Type> root = new JAXBElement<>(qName, Practica2Type.class, jaxbDocroot);
            xml.marshal(root, docXml);
           // xml.marshal(root, System.out);

            return true;

        } catch (JAXBException e) {
            System.out.println("No s'ha pogut fer el Marshalling. Error en la funció javaToXml(). Causa: "+e.getCause());
            return false;
        }
    }


    public boolean save(){

        try {
                //Dao1.DRecur(docXml.getName());
                Dao1.Recur(docXml.getAbsolutePath(),docXml.getName(),currentCol);
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se ha podido guardar el documento en la base de datos. Error en la" +
                    "función save(). Causa: "+e.getCause());
        } finally {
            return false;
        }
    }

    public Collection createCollection(String collectionName){

        try {
                Dao1.Colec(collectionName);
                currentCol = "/db/"+collectionName;
                return DatabaseManager.getCollection(uri+currentCol,user,passwd);

        } catch (Exception e){
            System.out.println("No se ha podido crear la colección "+collectionName+". Error en la función " +
                    "createCollection(String collectionName). Causas: "+e.getCause());
        }finally {
            return null;
        }

    }

    public boolean afegirEmpleat(EmpleatType emp){

        jaxbDocroot.getPersonas().getEmpleats().getEmpleat().add(emp);

        return javaToXml();
    }

    public boolean afegirClient(ClientType client){

        jaxbDocroot.getPersonas().getClients().getClient().add(client);

        return javaToXml();
    }

    public boolean afegirProducte(ProducteType producte){

        jaxbDocroot.getCataleg().getProducte().add(producte);

        return javaToXml();
    }

    public boolean afegirFactura(FacturaType factura){

        jaxbDocroot.getFacturacio().getFactura().add(factura);

        return javaToXml();
    }

    public boolean esborrarEmpleat(int index){

        jaxbDocroot.getPersonas().getEmpleats().getEmpleat().remove(index);

        return javaToXml();
    }

    public boolean esborrarClient(int index){

        jaxbDocroot.getPersonas().getClients().getClient().remove(index);
        return javaToXml();
    }

    public boolean esborrarFactura(int index){

        jaxbDocroot.getFacturacio().getFactura().remove(index);
        return javaToXml();
    }

    public boolean esborrarProducte(int index){

        jaxbDocroot.getCataleg().getProducte().remove(index);
        return javaToXml();
    }

    public int getNumClientes(){ return jaxbDocroot.getPersonas().getClients().getClient().size();}
    public int getNumEmpleats(){ return jaxbDocroot.getPersonas().getEmpleats().getEmpleat().size();}
    public int getNumFactures(){ return jaxbDocroot.getFacturacio().getFactura().size();}
    public int getNumproductes(){ return jaxbDocroot.getCataleg().getProducte().size();}

    public int getTotalStock(){

        int stocktotal = 0;

        for(ProducteType prod: jaxbDocroot.getCataleg().getProducte()){
            stocktotal += prod.getStock();
        }
        return stocktotal;
    }

    public ClientType getClient(int index){

        return jaxbDocroot.getPersonas().getClients().getClient().get(index);
    }

    public ProducteType getProducteCataleg(int index){

        return jaxbDocroot.getCataleg().getProducte().get(index);
    }




    public void mostrarContenidoXML(){


        System.out.println("Contingud del document XML");
        mostrarEmpleados();
        mostrarClientes();
        mostrarFacturas();
        mostrarCatalogo();

    }

    public void mostrarEmpleados(){

        System.out.println("\nEMPLEATS\n");
        int cont = 1;
        for(EmpleatType emp: jaxbDocroot.getPersonas().getEmpleats().getEmpleat()){

            System.out.println("\t"+cont+". Id = "+emp.getId()+"; Nom i Cognoms = "+emp.getNom()+" "+emp.getCognoms()+"; " +
                    "Anys treballats = "+emp.getAnysTreballats()+"; Sou = "+emp.getSou());
            cont++;
        }
    }

    public void mostrarClientes(){

        System.out.println("\nCLIENTS\n");
        int cont = 1;
        for(ClientType cl: jaxbDocroot.getPersonas().getClients().getClient()){

            System.out.println("\t"+cont+". Nif = "+cl.getNif()+"; Nom = "+cl.getNom()+"; Cognoms = "+cl.getCognoms());
            cont++;
        }
    }

    public void mostrarFacturas(){

        System.out.println("\nFACTURES\n");
        int cont = 1;
        for(FacturaType fac: jaxbDocroot.getFacturacio().getFactura()){

            System.out.println("-------------------------------------- "+cont+" ----------------------------------------");
            System.out.println("\tData = "+fac.getData().toString()+" "+fac.getHora()+":"+fac.getMinut()+" "+
                    fac.getSegon()+"''");
            System.out.print("\tClient = "+fac.getClient());
            System.out.print("\n\tProductes Comprats\n");

            for(IdType pr: fac.getProductes().getId())
                System.out.println("\t\tproducteID = "+pr.getValue()+"; preu sense iva = "+pr.getPreuUnitatSenseIva()+
                        "; iva aplicable = "+pr.getIva()+"%; unitats = "+pr.getUnitats());


            System.out.print("\n\tTotal sense iva = "+fac.getSenseIva());
            System.out.print("\tTotal iva = "+fac.getIva());
            System.out.println("\t\t\t\t\tTOTAL = "+fac.getTotal());
            cont++;
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void mostrarCatalogo(){

        System.out.println("\nCATÀLEG\n");
        int cont = 1;
        for (ProducteType prod: jaxbDocroot.getCataleg().getProducte()){
            System.out.println("\t"+cont+". ProducteID = "+prod.getId()+"; Nom producte = "+prod.getNom()+"; preu = "+
                    prod.getPreu()+"; stock = "+prod.getStock());
            cont++;
        }
    }



/*
*
*                                   Getters y Setters
*
*/

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public File getDocXml() {
        return docXml;
    }

    public void setDocXml(File docXml) {
        this.docXml = docXml;
    }

    public String getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(String currentCol) {
        this.currentCol = currentCol;
    }

    public Dao1 getDao() {
        return dao;
    }

    public void setDao(Dao1 dao) {
        this.dao = dao;
    }

    public Practica2Type getJaxbDoc(){return jaxbDocroot; }



}
/*

    static String Uri;

    static String driver;
    static String user;
    static String passwd;
    static String ip;
 */

/*
JAXBContext jaxbContext = JAXBContext.newInstance(my.generatedschema.dir.ObjectFactory.class);
DocumentType documentType = ((JAXBElement<DocumentType>) jaxbContext.createUnmarshaller().unmarshal(inputStream)).getValue();
 */