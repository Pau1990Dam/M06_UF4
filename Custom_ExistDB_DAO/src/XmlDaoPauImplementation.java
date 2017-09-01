import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 *Aquest dao te la particularitat de que permet navegar, indicant el nom sencer de les col·leccions, per la jerarquia de col·leccions
 * de la bd, a més de visualitzar l'estructura i recursos d'aquesta.
 * Simplement s'ha de settejar l'usuari, el password i passar la uri sencenra d'una col·lecció i a partir d'aquí ja podem
 * mouren's, borrar, crear col·leccions i pujar recursos passant només strings com a paràmetres.
 *
 *                                              NOTA IMPORTANT:
 * DES D'AQUESTA CLASE NO ES POT REGISTRAR LA BD - DatabaseManager.registerDatabase(DRIVER) -.  AIXÒ S'HA DE FER DES DE
 * FORA, EN EL MEU CAS HO FAIG DES DEL DAOMANAGER.
 */
public class XmlDaoPauImplementation implements GenericXmlDAO {

    /**
     * rootURI facilita trobar la col·lecció arrel, necessaria per navegar per l'estructura de col·leccions i recursos de la base de dades.
     *  - rootURI -> direcció del sgdb ex: 'xmldb:exist://192.168.1.120:8080/exist/xmlrpc'
     */
    private String rootURI;
    /**
     * rootCol guarda la col·lecció arrel, que ens permet trobar totes les col·leccions i recursos que penjen d'aquesta.
     *  - rootCol -> col·lecció arrel de la que penjen toa la resta de col·leccions i recursos de la bd.
     */
    private String rootCol;
    /**
     * user usuari
     */
    private String user;
    /**
     * pass contrasenya
     */
    private String pass;
    /**
     * Si val true evita que al executar-se el mètode auxiliar recursiveCollectionDisplayer(Collection) mostri per
     * pantalla els resources de cada col·lecció.
     * @see #showAllCollections
     * @see #recursiveCollectionDisplayer(Collection)
     */
    private boolean showOnlyCollections;
    /**
     * pointerCollection es una Collection que funciona com un punter. Enmagatzema la col·lecció a la que em donat ordre
     * de mouren's, permetent d'aquesta manera navegar per l'arbre de col·leccions de la base de dades estalviant-nos
     * tenir que referenciar-la cada vegada que volguem fer una acció sobre ella.
     */
    private Collection pointerCollection;
    /**
     * Objecte Logger
     */
    private FunctionLogging logger;
    /**
     *Constructor buit per defecte
     */
    public XmlDaoPauImplementation(){}

    /**
     *Instancia l'objecte XmlDaoPauImplementation amb els paràmetres necessaris per fer una conexió a la col·lecció
     * arrel de la base de dades indicada mitjançant el paràmetre rootURI.
     * @param user
     * @param pass
     * @param rootURI
     * @throws Exception
     * @see #setToRootCollection(String)
     */
    public XmlDaoPauImplementation(String user, String pass, String rootURI) throws Exception {

        this.user = user;
        this.pass = pass;
        showOnlyCollections = false;
        //l'arxiu de log es guardarà en la carpeta pare que allotja src
        logger = new FunctionLogging(new File("").getAbsolutePath()+"/",3, true);//new File("").getAbsolutePath()+"/"
        //Si no s'hapogut establir la conexió a la col·lecció arrel de la bd llança una excepció indicant les possibles causes.
       if(!setToRootCollection(rootURI)) throw new Exception("Error: algun/s dels següents paràmetres no són correctes: "+
       "\n\t USUARI ->  "+user+ "\n\t PASSWORD ->  "+pass+"\n\t Ruta arrel ->  "+rootURI);

    }

    //########################################  GETTERS  ########################################

    /**
     *Retorna la variable user.
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     *Retorna la variable pass
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     *Retorna la variable rootCol
     * @return
     */
    public String getRootColURI() {return rootCol;}

    /**
     *Retorna la variable rootURI
     * @return
     */
    @Override
    public String getRootURI() {
        return rootURI;
    }

    /**
     *Retorna la URI sencera la la col·lecció a la que apunta pointerCollection. Així podem saber sobre quina col·lecció
     * estem treballant o situats i en quina bd estem connectats.
     * @return
     */
    public String getCurrenCollectionURI() throws IOException {

        try {
            logger.writeToFile(3,"getCurrentColletcionURI -> Demanant URI de la col·lecció actual.");
            return rootURI+pointerCollection.getName();
        } catch (XMLDBException e) {
            logger.writeToFile(0,"getCurrentColletcionURI -> URI de la col·lecció actual no retornada. " +
                    "Causa: "+e.getCause());
            e.printStackTrace();
            return null;
        }
    }

    /**
     *Retorna l'objecte Collection al que apunta la referencia pointerCollection.
     * @return
     * @throws Exception
     */
    @Override
    public Collection getCurrentCollection() {

        if (pointerCollection != null) {
            logger.writeToFile(2,"getCurrentCollection -> demanant Col·lecció actual, després de comprobar" +
                    "que està inicialitzada.");
            return pointerCollection;
        }else{
            System.out.println("Error: Algun/s dels següents paràmetres no són correctes: "+
                    "\n\t USUARI ->  "+user+ "\n\t PASSWORD ->  "+pass+"\n\t Ruta arrel ->  "+rootURI);
            logger.writeToFile(0,"getCurrentCollection-> col·lecció actual no retornada. " +
                    "Possibles valors conflictius: user ="+user+"; pass = "+pass+"; rootURI = "+rootURI+"; pointerCollection = "+pointerCollection.toString());
            return null;
        }

    }

    /**
     * Retorna la col·lecció arrel de la bd.
     * @return
     * @see #getRootColURI()
     */
    public Collection getRootCollection(){

        try {
            logger.writeToFile(3,"getRootCollection() -> demanant col·lecció arrel.");
            return DatabaseManager.getCollection(getRootColURI());
        } catch (XMLDBException e) {
            String causa = ""+e.getCause();
            logger.writeToFile(0,"getRootCollection() -> col·lecció arrel no retornada. " +
                    "Causa: "+causa);
            System.out.println("Error: "+causa);
            return null;
        }
    }

    /**
     *Retorna la col·lecció indicada per paràmetre sempre que existeixi a la bd, sino retorna null.
     * @param collectionName
     * @return
     */
    @Override
    public Collection getCollection(String collectionName) {

        try {
            logger.writeToFile(3,"getCollection("+collectionName+") -> " +
                    "demanant la col·lecció indicada per paràmetre.");
            return DatabaseManager.getCollection(rootURI+collectionName);
        } catch (XMLDBException e) {
            String causa = ""+e.getCause();
            logger.writeToFile(0,"getCollection("+collectionName+") -> col·lecció no retornada. " +
                    "Causa: "+causa);
            System.out.println("Error: "+causa);
            return null;
        }
    }

    /**
     *Retorna el recurs inidicat per paràmetre sempre que aquest es trobi en la col·lecció punter.
     * @param resourceName
     * @return
     */
    @Override
    public Resource getResource(String resourceName) {

        try {
            logger.writeToFile(3,"getResource("+resourceName+") -> demanant el recurs indicat per paràmetre.");
            return DatabaseManager.getCollection(rootURI+pointerCollection.getName()).getResource(resourceName);
        } catch (XMLDBException e) {
            System.out.println("No s'ha trobat el recurs "+resourceName+" en la col·lecció actual.");
            logger.writeToFile(0,"getResource("+resourceName+") -> Recurs no retornat. " +
                    "Causa: "+e.getCause());
            return null;
        }
    }

    //########################################  SETTERS ########################################

    /**
     *Dona el valor indicat per paràmetre a la variable user
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }


    /**
     *Dona el valor indicat per paràmetre a la variable pass
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }


    /**
     *Dona el valor indicat per paràmetre a la variable rootCol
     * @param rootCol
     */
    public void setRootCol(String rootCol) {this.rootCol = rootCol;}


    /**
     * A partir de la url indicada per paràmetre - si aquesta porta a alguna col·lecció de un bd xml - troba la
     * col·lecció arrel del la base de dades, així com la URL de la base de dades; inicialitza les variables rootURI i
     * rootCol; i fa apntar la referencia pointerCollection a la Collection arrel de la base de dades.
     * @param url
     * @return
     */
    @Override
    public boolean setToRootCollection(String url) {

        //Mirem que els paràmetres per inicialitzar una connexió estiguin instanciats.
        if(user.isEmpty() || pass.isEmpty() || url.isEmpty()){
            logger.writeToFile(1,"setRootCollection("+url+") -> no s'ha pogut trobar la col·lecció arrel. " +
                    "Les següents variables o alguna de elles no esta inicialitzada: user ="+user+"; pass = "+pass+"; rootURI = "+rootURI);
            return false;
        }

        try {
            logger.writeToFile(2,"setRootCollection("+url+") -> Iniciat el procés de trobar la col·lecció arrel," +
                    " establir la col·lecció punter en l'arrel i establir la uri que apunta a la bd.");
            pointerCollection = DatabaseManager.getCollection(url,user,pass);
            //Settejem la referencia pointerCollection per a que apunti a la col·lecció pare de totes.
            do{
                //Comprovem si pointerCollection té una col·lecció  pare
                if(pointerCollection.getParentCollection()!= null){
                    //Si té una col·lecció pare el nou objecte referenciat per pointerCollection serà el seu pare.
                    pointerCollection = pointerCollection.getParentCollection();
                }else
                    //Quan pointerCollection ja no té més pares vol dir que ja hem arribat a la col·lecció pare de totes.
                    break;

            }while (true);
            System.out.println("Conexió establida amb éxit");
            //Agafem la url de la collecció pointer collection ja que aquesta és la ruta arrel que porta al sgdb
            rootURI = url.substring(0,url.lastIndexOf(pointerCollection.getName()));
            //Agafem el nom de la col·lecció arrel
            rootCol = rootURI+pointerCollection.getName();
            logger.writeToFile(3,"setRootCollection("+url+") -> col.lecció arrel trobada, col·lecció punter" +
                    "establida en l'arrel, uri de la bd trobada i establida.");
            return true;
        } catch (XMLDBException e) {
            String causa = ""+e.getCause();
            logger.writeToFile(0,"setRootCollection("+url+") -> no s'ha pogut trobar la col·lecció arrel. " +
                    "Causa: "+causa);
            System.out.println(causa);
            return false;
        }

    }

    //########################################  OTHER METHODS  ########################################

    /**
     * Inicialitza l'objecte FunctionLogging
     * @param directoryPath
     * @param appendToFile
     */
    public void setLogger(String directoryPath, int maxLogLevel, boolean appendToFile) {
        this.logger = new FunctionLogging(directoryPath, maxLogLevel, appendToFile);
    }

    /**
     *Retorna la col·lecció arrel i si aquesta no està instanciada intentarà trobarla mitjançant la url donada.
     * @param uri
     * @return
     * @see #setToRootCollection(String)
     */
    @Override
    public Collection findAndMoveToRootCollection(String uri) {

        if(rootCol.isEmpty()) {
            logger.writeToFile(1,"findAndMoveToRootCollection("+uri+") -> col·lecció root buida i no" +
                    "identificada. Començant procés d'establir la col·lecció root.");
            setToRootCollection(uri);

        }
        try {
            logger.writeToFile(2,"findAndMoveToRootCollection("+uri+") -> demanant la col·lecció root.");
            return DatabaseManager.getCollection(rootCol, user, pass);
        } catch (XMLDBException e) {
            String causa =""+ e.getCause();
            System.out.println(""+causa);
            logger.writeToFile(0,"findAndMoveToRootCollection("+uri+") -> no s'ha pogut retornar la" +
                    " col·lecció root. Possible causa: "+causa);
            return null;
        }
    }

    /**
     *Canvia el valor de la Collection punter pointerCollection al indicat per paràmetre. D'aquesta manera emulem la
     * navegació a través de la jerarquia de col·leccions.
     * @param collectionToMove
     * @return
     */
    @Override
    public boolean moveToCollection(String collectionToMove) {

        try {
            logger.writeToFile(3,"moveToCollection("+collectionToMove+") -> Movent la col·lecció punter" +
                    " cap a la col·lecció indicada per paràmetre.");
            pointerCollection = DatabaseManager.getCollection(rootURI+collectionToMove, user, pass);
            return true;
        } catch (XMLDBException e) {
            logger.writeToFile(1,"moveToCollection("+collectionToMove+") -> No a sigut possible moure's" +
                    " cap a la col·lecció pasada com a paràmetre.");
            return false;
        }


    }

    /**
     *Mostra per consola només les col·leccions contingudes en la bd a la que estem connectats.
     * @see #showAllCollectionsAndResources()
     * @see #recursiveCollectionDisplayer(Collection)
     */
    public void showAllCollections(){

        logger.writeToFile(3,"showAllCollections() -> executant tasca de mostrar continguts");
        showOnlyCollections = true;

        showAllCollectionsAndResources();

        showOnlyCollections = false;
        logger.writeToFile(3,"showAllCollections() -> tasca de mostrar continguts de la bd executada" +
                " amb èxit.");
    }

    /**
     * Mostra totes les col·leccions i els recursos d'aquestes que están continguts en la bd a la que estem connectats.
     *@see #recursiveCollectionDisplayer(Collection)
     */
    public void showAllCollectionsAndResources(){

        if(rootURI.isEmpty()){
            System.out.println("No es poden mostrar els recurosos ni les collections perquè encara no s'ha indicat cap " +
                    "uri que apunti a una bd xml. Per aquest propósit estableix a travès del mètode setRootCollection o" +
                    "findAndMoveToRootCollection la uri cap a la bd.");
            return;
        }

        Collection col = null;
        try {
            col = DatabaseManager.getCollection(rootCol, user, pass);
        } catch (XMLDBException e) {
            logger.writeToFile(0,"showAllCollectionsAndResources() -> No s'ha pogut extreure la col·lecció arrel." +
                    " causa: "+e.getCause());
            e.printStackTrace();
        }

        recursiveCollectionDisplayer(col);

    }

    /**
     *Recorre i mostra totes les col·leccions i recursos per a les que l'usuari te permisos (exepte les de sistema) de
     * lectura.
     * @param col
     * @see #getCollection(String)
     */
    private void recursiveCollectionDisplayer(Collection col){

        try {
                logger.writeToFile(3,"recursiveCollectionDisplayer("+col+") -> Recorrent la col·lecció" +
                        " "+col.getName()+".");
                String resoures [] = col.listResources();

                if(col.listChildCollections().length > 0){

                    System.out.println("Col·lecció ->"+col.getName());
                    if(!showOnlyCollections)System.out.println("\trecursos: "+ Arrays.toString(resoures));

                    String arr[] = col.listChildCollections();
                    String collectionName;

                    for(String collection : arr){

                        if(collection.equals("apps") || collection.equals("system"))continue;

                        collectionName = col.getName()+"/"+collection;

                        recursiveCollectionDisplayer(getCollection(collectionName));
                    }
                }else{
                    logger.writeToFile(2,"recursiveCollectionDisplayer("+col+") -> Col·lecció " +
                            " "+col.getName()+", recorrida amb èxit.");
                    System.out.println("Col·lecció -> "+col.getName());
                    if(!showOnlyCollections)System.out.println("\trecursos: "+ Arrays.toString(resoures));
                }

        } catch (XMLDBException e) {
            logger.writeToFile(0," recursiveCollectionDisplayer("+col+") -> Error. Causa: "+e.getCause());
            e.printStackTrace();
        }
    }

    /**
     *Crea una col·lecció amb el nom indicat per paràmetre.
     * @param collectionName
     * @return
     * @see
     */
    @Override
    public boolean createCollection(String collectionName) {

        try {
                logger.writeToFile(3,"createCollection("+collectionName+") -> Creant la col·lecció " +
                        " indicada per paràmetre.");
                Service service = pointerCollection.getService("CollectionManagementService","1.0");
                CollectionManagementService cms = (CollectionManagementService) service;
                cms.createCollection(collectionName);
                logger.writeToFile(2,"createCollection("+collectionName+") -> Col·lecció indicada per" +
                        " paràmetre creada amb èxit.");
                return true;
        } catch (XMLDBException e) {
            logger.writeToFile(1,"createCollection("+collectionName+") -> No s'ha pogut crear la col·lecció" +
                    " "+collectionName+". Causa possible: "+e.getCause());
            return false;
        }
    }

    /**
     *Puja el arxiu indicat per paràmetre a la col·lecció en la que es troba pointerCollection amb el nom indicat
     * també per paràmetre.
     * @param source arxiu
     * @param name  nom que es vol donar al recurs dintre de la col·lecció
     * @return
     */
    @Override
    public boolean storeResource(File source, String name) {
        try {
                logger.writeToFile(3,"storeResource("+source+", "+name+") -> Enamagatzemant el" +
                        " recurs indicat per paràmetre (arxiu, nomrecurs).");

                Resource resource = pointerCollection.createResource(name, "XMLResource");
                resource.setContent(source);
                pointerCollection.storeResource(resource);

                logger.writeToFile(2,"storeResource("+source+", "+name+") -> Recurs indicat per " +
                        "paràmetre (Arxiu, nomrecurs ) enmagatzemat amb èxit .");
                return true;
        } catch (XMLDBException e) {
            logger.writeToFile(1,"storeResource("+source+", "+name+") -> no s'ha pogut enmagatzemar" +
                    " l'arxiu "+source.getAbsolutePath()+" com a recurs. Causa possible: "+e.getCause());
            return false;
        }
    }

    /**
     *Retorna un String amb els resultats de la consulta passada per paràmetre.
     * @param query
     * @return
     */
    @Override
    public String XMLquery(String query) {
        try {
                logger.writeToFile(3,"XMLquery("+query+") -> Executant la query indicada per" +
                        " paràmetre.");

                XPathQueryService xpqs = (XPathQueryService)pointerCollection.getService("XPathQueryService", "1.0");
                xpqs.setProperty("indent", "yes");
                ResourceSet result = xpqs.query(query);
                ResourceIterator i = result.getIterator();
                Resource res = null;
                StringBuilder answer = new StringBuilder();

                while(i.hasMoreResources()) {
                    res = i.nextResource();
                    answer.append(res.getContent()).append("\n");
                }

                logger.writeToFile(2,"XMLquery("+query+") -> Query indicada per paràmetre executada" +
                        " sense problemes.");

                return answer.toString();
        } catch (XMLDBException e) {
            logger.writeToFile(1,"XMLquery("+query+") -> no s'ha pogut resoldre la query. Causa possible:" +
                    " "+e.getCause());
            return "La consulta "+ query +" està mal formulada.";
        }

    }

    /**
     *Esborra el recurs indicat per paràmetre de la col·lecció que apunta pointerCollection.
     * @param resourceName
     * @return
     */
    @Override
    public boolean deleteResource(String resourceName) {

        try {
                logger.writeToFile(3,"deleteResource("+resourceName+") -> Esborrant el recurs indicat " +
                        "per paràmetre.");
                Resource r = getResource(resourceName);
                pointerCollection.removeResource(r);

                logger.writeToFile(2,"deleteResource("+resourceName+") -> Recurs indicat per paràmetre" +
                        " esborrat amb èxit.");

                return true;
        } catch (XMLDBException e) {
            logger.writeToFile(0,"deleteResource("+resourceName+") -> No s'ha pogut borrar el recurs passat" +
                    " per paràmetre. Causa possible "+e.getCause());
            return false;
        }
    }

    /**
     *Esborra la col·lecció indicada per paràmetre de la bd.
     * @param collectionName
     * @return
     */
    @Override
    public boolean deleteCollection(String collectionName) {

        if(rootCol.lastIndexOf(collectionName)!=-1 || (rootURI+collectionName).lastIndexOf("system")!=-1 ||
                (rootURI+collectionName).lastIndexOf("apps")!=-1){
            logger.writeToFile(2,"deleteCollection("+collectionName+") -> La col·lecció indicada" +
                    "per paràmetre no es pot esborrar perquè es la col·lecció arrel o una col·lecció del sistema sgdb.");
            System.out.println("No es pot borrar la col·lecció arrel ni cap col·lecció del sistema.");
            return false;
        }
        try {
                logger.writeToFile(3,"deleteCollection("+collectionName+") -> Esborrant la col·lecció" +
                        " indicada per paràmetre.");
                Service service = pointerCollection.getService("CollectionManagementService","1.0");
                CollectionManagementService cms = (CollectionManagementService) service;
                //Si la col·lecció que volem esborrar
                if(pointerCollection.getName().contains(collectionName)){
                    findAndMoveToRootCollection(collectionName);
                }

                cms.removeCollection(collectionName);
                logger.writeToFile(2,"deleteCollection("+collectionName+") -> Col·lecció indicada per" +
                        " paràmetre esborrada amb èxit.");
            return true;
        } catch (XMLDBException e) {
            logger.writeToFile(0,"deleteCollection("+collectionName+") -> No s'ha pogut borrar la " +
                    "col·lecció passada per paràmetre. Causa possible "+e.getCause());
            return false;
        }
    }
}
