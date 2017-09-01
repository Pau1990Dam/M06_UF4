import org.xmldb.api.base.XMLDBException;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class Main {



    private static String user = "admin";
    private static String password = "admin";
    private static String XmldbType = "exist";
    private static String ip = "localhost";
    private static String port = "8080";
    private static String collectionsSource = "/exist/xmlrpc/db";

    public static void main(String[] args) {

        XmlDaoPauImplementation pauExample = null;
        String uri = "";
        DaoManager manager = new DaoManager();


        Scanner entrada = new Scanner(System.in);
        int opcion = 0;

        try {
            //Preparem la url on cap a la bd i els drivers corresponents per conectar-se a aquesta.
            prepareConnection(manager);
            uri = manager.getURI();
            //Establim la conexió i ens situem al directori arrel de la bd, des d'on podem accedir a totes le coleccions
            pauExample = new XmlDaoPauImplementation("admin","admin",uri);
            System.out.println("Connectat a -> "+pauExample.getRootURI());
            mostrar(pauExample);

        } catch (Exception e) {
            e.printStackTrace();
        }

        do{
            menu();
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion){

                case 1:
                    try {
                        crearCollections(entrada,pauExample);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pausa(entrada);
                    break;

                case 2:
                    try {
                        crearRecursos(entrada,pauExample);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pausa(entrada);
                    break;

                case 3:
                    try {
                        consultaXQueryXpath(entrada,pauExample);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pausa(entrada);
                    break;

                case 4:
                    try {
                        esborrarRecurs(entrada,pauExample);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pausa(entrada);
                    break;

                case 5:
                    esborrarColeccio(entrada,pauExample);
                    pausa(entrada);
                    break;

                case 6:
                    try {
                        mostrar(pauExample);
                    } catch (XMLDBException e) {
                        e.printStackTrace();
                    }
                    pausa(entrada);
                    break;

                case 7:
                    try {
                        mouresAunaAltraColeccio(entrada,pauExample);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pausa(entrada);
                    break;

                default:
                    System.out.println("Adeu!!!");
                    break;

            }
        }while(opcion <8 && opcion >0);



    }

    public static void prepareConnection(DaoManager manager) throws Exception {

        manager.Connection(XmldbType);
        manager.setUri(ip,port,collectionsSource);

    }


    public static void menu(){

        System.out.println("                            MENU                           ");
        System.out.println("###########################################################");
        System.out.println("#  1. Crear una col·lecció.                               #");
        System.out.println("#  2. Crear recursos.                                     #");
        System.out.println("#  3. Consulta Xpath o XQuery.                            #");
        System.out.println("#  4. Esborrar recurs.                                    #");
        System.out.println("#  5. Esborrar col·lecció.                                #");
        System.out.println("#  6. Mostrar totes les col·leccions i recursos de la bd. #");
        System.out.println("#  7. Moure's a una altra col·lecció.                     #");
        System.out.println("#  8. Sortir.                                             #");
        System.out.println("###########################################################");
        System.out.print("\n\t\tOpció -> ");

    }

    public static void  pausa(Scanner entrada){

        System.out.println("Prem intro per tornar al menú...");
        entrada.nextLine();
        System.out.println();

    }

    /*##########################################################################################################*/

    public static void crearCollections(Scanner entrada, XmlDaoPauImplementation pauExample) throws Exception {

        System.out.println("Crear un Col·lecció");
        System.out.println("Ens trobem a -> "+pauExample.getCurrenCollectionURI());

        System.out.println("Indica el nom de la col·lecció que vols crear en " +
                ""+pauExample.getCurrentCollection().getName()+": ");
        String nomColeccio = entrada.nextLine();

        if( pauExample.createCollection("/"+nomColeccio))
            System.out.println("Col·lecció "+nomColeccio+" creada amb éxit");
        else
            System.out.println("No s'ha pogut crear la col·lecció "+nomColeccio);

    }

    public static void crearRecursos(Scanner entrada, XmlDaoPauImplementation pauExample) throws Exception {

        System.out.println("Crear Recursos: ");
        System.out.println("Ens trobem en la col·lecció -> "+pauExample.getCurrenCollectionURI());
        System.out.println("Indica la ruta del recurs que vols afegir: ");

        File f;

        if(!(f = new File(entrada.nextLine())).exists()){
            System.out.println("La ruta que has introduit "+f.getAbsolutePath()+" no porta a cap recurs.");
            return;
        }

        if(pauExample.storeResource(f,f.getName()))
            System.out.println("Recurs enmagatzemat amb exit en la col·lecció "+pauExample.getCurrentCollection().getName());
        else
            System.out.println("No s'ha pogut enmagatzemar el recurs"+f.getAbsolutePath());

    }

    public static void consultaXQueryXpath(Scanner entrada, XmlDaoPauImplementation pauExample) throws Exception {

        String consulta = "";
        String coleccioActual = pauExample.getCurrentCollection().getName();

        do{
            System.out.println("Consulat Xpath o Xquery. ");
            System.out.println("Estem a la col·lecció ->"+coleccioActual);
            System.out.println("Recursos de la col·lecció actual: "+
                    Arrays.toString(pauExample.getCurrentCollection().listResources()));


            System.out.println("\tExemples de consultes (cópia la consulta que vulguis per veure com funciona): ");
            System.out.println("\tConsultes a la col·lecció sencera.");

            System.out.println("\n\t\t - XPath (funciona si existeixen recursos xml amb tags <country>) : " +
                    "//country");

            System.out.println("\n\t\t - XQuery  (només funciona si existeix el recurs mondial.xml) : " +
                    "for $i in //record " +
                    "where $i/country = \"Germany\" " +
                    "return $i");

            System.out.println("\n\tConsultes a un recurs en concret.");

            System.out.println("\n\t\t - XPath : " +
                    "doc('Factbook.xml')//country");

            System.out.println("\n\t\t - XQuery : " +
                    "let $x:= doc('mondial.xml')" +
                    "/mondial/country[name=\"Cyprus\"] " +
                    "return data($x/@car_code)" );

            System.out.println("Escriu o copia la teva consulta. Per sortir escriu 'sortir' o '0' sense commetes:");
            System.out.print("\n\tConsulta: ");
            consulta = entrada.nextLine();

            if(!consulta.equals("sortir") && !consulta.equals("0") && !consulta.isEmpty()) {
                System.out.println(pauExample.XMLquery(consulta));
                System.out.println("\nPrem intro per continuar...");
                entrada.nextLine();
            }else
                break;

        }while(true);

    }

    public static void esborrarRecurs(Scanner entrada, XmlDaoPauImplementation pauExample) throws Exception {

        String recursos [] = pauExample.getCurrentCollection().listResources();
        String coleccióActual = pauExample.getCurrentCollection().getName();
        if(recursos.length == 0){
            System.out.println("En la col·lecció actual en la que et troves '"+coleccióActual+"' no hi ha cap recurs.");
            return;
        }

        System.out.println("Esborrar Recursos: ");
        System.out.println("Ens trobem en la col·lecció -> "+coleccióActual);

        System.out.println("INSTRUCCIONS:\n\tEscriu el nom dels recursos que vols esborrar (separats per coma) i presiona" +
                " intro. Si els vols borrar tots escriu 'tots'.");
        System.out.println("Recursos de la col·lecció actual ->"+ Arrays.toString(recursos));
        System.out.println("\n\tRecursos a esborrar: ");

        String recursoAesborrar = entrada.nextLine();

        if((recursoAesborrar.toLowerCase().equals("tots"))) {

            for (String res : recursos){
                System.out.println("Recurs "+res);

                if(pauExample.deleteResource(res))
                    System.out.println(" esborrat amb èxit.");
                else
                    System.out.println(" no s'ha pogut esborrar.");
            }

        }else{
            for(String res: recursoAesborrar.split(",")){
                System.out.println("Recurs "+res);

                if(pauExample.deleteResource(res))
                    System.out.println(" esborrat amb èxit.");
                else
                    System.out.println(" no s'ha pogut esborrar.");
            }
        }

    }

    public static void esborrarColeccio(Scanner entrada, XmlDaoPauImplementation pauExample){

        System.out.println("Borrar col·lecció");
        System.out.println("INSTRUCCIONS:");
        System.out.println("\tIndica de la llista de col·leccions que es mostren el nom de la que vols esborrar:");

        pauExample.showAllCollections();

        System.out.print("\n\tCol·lecció a esborrar: ");
        String collectionName = entrada.nextLine();

        if(pauExample.deleteCollection(collectionName)){
            System.out.println("Col·lecció "+collectionName+" esborrada.");
        }else
            System.out.println("No s'ha pogut esborrar la col·lecció "+collectionName+".");

    }

    public static void mostrar(XmlDaoPauImplementation pauExample) throws XMLDBException {

        System.out.println();
        System.out.println("Col·leccions i recursos de la base de dades: ");
        System.out.println();
        pauExample.showAllCollectionsAndResources();
    }

    public static void mouresAunaAltraColeccio(Scanner entrada, XmlDaoPauImplementation pauExample) throws Exception {

        System.out.println();
        System.out.println("Escull a quina col·lecció et vols moure indicant el seu nom.");
        System.out.println("Col·leccions existents a la base de dades:");
        System.out.println();
        pauExample.showAllCollections();

        String nomColeccio = entrada.nextLine();

        if(pauExample.moveToCollection(nomColeccio))
            System.out.println("Mogut el punter a la col·lecció -> "+pauExample.getCurrentCollection().getName());
        else
            System.out.println("La col·lecció "+nomColeccio+" no es troba a la bd");

    }

}

