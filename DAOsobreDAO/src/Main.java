
import jaxbObj.*;

import java.util.GregorianCalendar;
import java.util.Scanner;

/*
Heu de crear la base de dades per a una empresa
bàsica. Ha de contenir la informació dels següents
recursos de l'empresa:

- Empleats (id, nom, cognoms, anys treballats, sou)
- Clients (nif, nom, cognoms)
- Catàleg (idproducte, producte, preu, unitats en stock)
- Factures (client, productes, preu per unitat, preu total, iva)

Si considereu que hi ha d'haver algun altre camp, afegiu-lo. Es valorarà el producte final.
La base de dades ha de ser capaç de:
 # Introduir nous empleats i clients.
 # Esborrar empleats i clients.
 # Consultar empleats pels diferents camps (edat, sou, anys treballats).
 # Buscar factures en una franja concreta de temps.
 # Consultar totes les factures d'un client, ...

Si considereu que hi ha d'haver algun altra consulta, afegiu-la.
 */
public class Main {


    public static void main(String[] args) {

        String uri = "xmldb:exist://localhost:8080/exist/xmlrpc";
        String driver = "org.exist.xmldb.DatabaseImpl";
        String ip = "localhost";
        String user = "admin";
        String pass = "admin";
        String rutaXML = System.getProperty("user.dir")+"/DAOsobreDAO/src/bbdd.xml";//  "/home/pau/IdeaProjects/DAOsobreDAO/bbdd.xml"

        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        //String uri, String driver, String user, String passwd, String ip
        DAO_sobreAdriDAO dao= new DAO_sobreAdriDAO(uri,driver,user,pass, ip);
        dao.setResourceToWorkWith(rutaXML);
        dao.mostrarContenidoXML();

        dao.createCollection("DaoSobreDao");



        do{
            menu();
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion){

                case 1:
                    afegirEmpleat(entrada, dao);
                    pausa(entrada);
                    break;
                case 2:
                    afegirClient(entrada, dao);
                    pausa(entrada);
                    break;
                case 3:
                    afegirProducte(entrada,dao);
                    pausa(entrada);
                    break;
                case 4:
                    afegirFactura(entrada,dao);
                    pausa(entrada);
                    break;
                case 5:
                    eliminarEmpleat(entrada,dao);
                    pausa(entrada);
                    break;
                case 6:
                    eliminarClient(entrada,dao);
                    pausa(entrada);
                    break;
                case 7:
                    eliminarProducte(entrada,dao);
                    pausa(entrada);
                    break;
                case 8:
                    eliminarFactura(entrada,dao);
                    pausa(entrada);
                    break;
                case 9:
                    dao.mostrarEmpleados();
                    pausa(entrada);
                    break;
                case 10:
                    dao.mostrarClientes();
                    pausa(entrada);
                    break;
                case 11:
                    dao.mostrarCatalogo();
                    pausa(entrada);
                    break;
                case 12:
                    dao.mostrarFacturas();
                    pausa(entrada);
                    break;
                case 13:
                    consultaXquery(entrada,dao);
                    pausa(entrada);
                    break;
                case 14:
                    consultaXpath(entrada,dao);
                    pausa(entrada);
                    break;
                default:
                    System.out.println("Adeu!!!");
                    break;
            }
        }while (opcion>0&&opcion<15);

    }



    public static void menu(){

        System.out.println("MENU");
        System.out.println("1.Afegir nou empleat.");
        System.out.println("2.Afegir nou client.");
        System.out.println("3.Afegir nou producte.");
        System.out.println("4.Afegir nova factura.");
        System.out.println("5.Esborrar empleat.");
        System.out.println("6.Esborrar client.");
        System.out.println("7.Esborrar producte.");
        System.out.println("8.Esborrar factura .");
        System.out.println("9.Mostrar tots els empleats.");
        System.out.println("10.Mostrar tots els clients.");
        System.out.println("11.Mostrar catàleg.");
        System.out.println("12.Mostrar factures.");
        System.out.println("13.Consultes Xquery.");
        System.out.println("14.Consultes Xpath.");
        System.out.println("15.Sortir.");
        System.out.print("\n\tOpció -> ");

    }

    public static void pausa(Scanner entrada){
        System.out.println("Prem intro per tornar al menu...");
        entrada.nextLine();
    }

    public static void afegirEmpleat(Scanner entrada, DAO_sobreAdriDAO dao){

        EmpleatType emp = new EmpleatType();

        System.out.println("AFEGIR EMPLEAT");

        System.out.print("\nIntrodueix la id del empleat (només els números del DNI) -> ");
        emp.setId(entrada.nextInt());
        entrada.nextLine();
        System.out.print("\nIntrodueix el nom del empleat -> ");
        emp.setNom(entrada.nextLine());
        System.out.print("\nIntrodueix els cognoms del empleat -> ");
        emp.setCognoms(entrada.nextLine());
        System.out.print("\nIntrodueix els anys treballats del empleat -> ");
        emp.setAnysTreballats(entrada.nextInt());
        entrada.nextLine();
        System.out.print("\nIntrodueix els sou del empleat -> ");
        emp.setSou(entrada.nextFloat());
        entrada.nextLine();

        if(dao.afegirEmpleat(emp))
            System.out.println("Empleat afegit correctament...");
        else
            System.out.println("No s'ha pogut afegir l'empleat a la bd. Hi ha agut un error d'algun tipus.");

        dao.save();
    }

    public static void afegirClient(Scanner entrada, DAO_sobreAdriDAO dao){

        ClientType client = new ClientType();

        System.out.println("AFEGIR CLIENT");
        System.out.print("\nIntrodueix el NIF del client (DNI sencer) -> ");
        client.setNif(entrada.nextLine());
        System.out.print("\nIntrodueix el nom del client -> ");
        client.setNom(entrada.nextLine());
        System.out.print("\nIntrodueix els cognoms del client -> ");
        client.setCognoms(entrada.nextLine());

        if(dao.afegirClient(client))
            System.out.println("Client afegit correctament...");
        else
            System.out.println("No s'ha pogut afegir el client a la bd. Hi ha agut un error d'algun tipus.");

        dao.save();

    }

    public static void afegirFactura(Scanner entrada, DAO_sobreAdriDAO dao){

        FacturaType fact = new FacturaType();


        String opcio;
        GregorianCalendar cal = new GregorianCalendar();


        System.out.println("AFEGIR FACTURA");
        System.out.print("\nEscull un dels clients (indica el número d'opcio) -> ");
        dao.mostrarClientes();
        opcio = entrada.nextLine();

        fact.setClient(dao.getClient(Integer.valueOf(opcio)-1).getNif());
        System.out.print("\nIndica el tipus de producte, unitats comprades i iva aplicable per producte: ");

        dao.mostrarCatalogo();
        System.out.print("\nEscull els productes indicant el número de cadascún separat per una comma: ");
        opcio = entrada.nextLine();
        for(String op: opcio.split(",")){
            setProductesFactura(op,dao,fact,entrada);
        }

        fact.setTotal(fact.getIva()+fact.getSenseIva());

        fact.setData(cal.getTime());
        fact.setHora(cal.getTime().getHours());
        fact.setMinut(cal.getTime().getMinutes());
        fact.setSegon(cal.getTime().getSeconds());

        System.out.println(fact.toString());

        if(dao.afegirFactura(fact))
            System.out.println("Factura afegida correctament...");
        else
            System.out.println("No s'ha pogut afegir la factura a la bd. Hi ha agut un error d'algun tipus.");

        dao.save();

    }


    private static void setProductesFactura(String opcio, DAO_sobreAdriDAO dao, FacturaType fac, Scanner entrada){
        ProducteType p;
        IdType prod = new IdType();


        float preuProd;
        short iva;
        int cantitat;

        int op;
        try{

            op = Integer.parseInt(opcio);
            p = dao.getProducteCataleg(op-1);

            preuProd = p.getPreu();
            prod.setValue(p.getId());
            prod.setPreuUnitatSenseIva(preuProd);

            System.out.print("\nIndica el número d'unitats comprades de "+p.getNom()+" -> ");
            cantitat = entrada.nextInt();
            entrada.nextLine();
            prod.setUnitats(cantitat);

            System.out.print("\nIndica l'iva aplicable en número enter (ex 21% = 21) -> ");
            iva = entrada.nextShort();
            entrada.nextLine();
            prod.setIva(iva);


            fac.getProductes().getId().add(prod);
            fac.setSenseIva(fac.getSenseIva()+preuProd*prod.getUnitats());
            fac.setIva(((fac.getIva()+preuProd*cantitat*iva)/100));


        }catch (Exception e){
            e.printStackTrace();
            return;
        }

    }


    public static void afegirProducte(Scanner entrada, DAO_sobreAdriDAO dao){

        ProducteType prod = new ProducteType();

        System.out.print("AFEGIR PRODUCTE");
        System.out.print("\nIndica la Id del producte -> ");
        prod.setId(entrada.nextInt());
        entrada.nextLine();
        System.out.print("\nIndica el nom del producte producte -> ");
        prod.setNom(entrada.nextLine());
        System.out.print("\nIndica el preu del producte -> ");
        prod.setPreu(entrada.nextFloat());
        entrada.nextLine();
        System.out.print("\nIndica la quantitat de productes en stock -> ");
        prod.setStock(entrada.nextInt());
        entrada.nextLine();

        if(dao.afegirProducte(prod))
            System.out.println("Producte afegit acorrectament...");
        else
            System.out.println("No s'ha pogut afegir el producte a la bd. Hi ha agut un error d'algun tipus.");

        dao.save();

    }

    private static void eliminarEmpleat(Scanner entrada, DAO_sobreAdriDAO dao) {

        String opcio;
        int i = 1;
        dao.mostrarEmpleados();
        System.out.println("ELIMINAR EMPLEATS");
        System.out.print("\nIndica el número de cadascún dels empleats que vols eliminar separat per una coma: ");
        opcio = entrada.nextLine();

        for(String op: opcio.split(",")){
            try{
                dao.esborrarEmpleat(Integer.valueOf(op)-i);
                System.out.println("Empleat "+op+" eliminat corectament...");
                i++;
            }catch (Exception e){
                break;
            }
        }

        dao.save();
    }

    private static void eliminarClient(Scanner entrada, DAO_sobreAdriDAO dao){

        String opcio;
        int i = 1;
        dao.mostrarClientes();

        System.out.println("ELIMINAR CLIENTS");
        System.out.print("\nIndica el número de cadascún dels clients que vols eliminar separat per una coma: ");
        opcio = entrada.nextLine();

        for(String op: opcio.split(",")){
            try{
                dao.esborrarClient(Integer.valueOf(op)-i);
                System.out.println("Client "+op+" eliminat corectament...");
                i++;
            }catch (Exception e){
                break;
            }
        }

        dao.save();
    }

    private static void eliminarProducte(Scanner entrada, DAO_sobreAdriDAO dao){

        String opcio;
        int i = 1;
        dao.mostrarCatalogo();

        System.out.println("ELIMINAR PRODUCTES");
        System.out.print("\nIndica el número de cadascún dels productes que vols eliminar separat per una coma: ");
        opcio = entrada.nextLine();


        for(String op: opcio.split(",")){
            try{
                dao.esborrarProducte(Integer.valueOf(op)-i);
                System.out.println("Producte "+op+" eliminat corectament...");
                i++;
            }catch (Exception e){
                break;
            }
        }

        dao.save();
    }

    private static void eliminarFactura(Scanner entrada, DAO_sobreAdriDAO dao){

        String opcio;
        int i = 1;
        dao.mostrarFacturas();

        System.out.println("ELIMINAR FACTURES");
        System.out.print("\nIndica el número de cadascúna de les factures que vols eliminar separada per una coma: ");
        opcio = entrada.nextLine();

        for(String op: opcio.split(",")){
            try{
                dao.esborrarFactura(Integer.valueOf(op)-i);
                System.out.println("Factura "+op+" eliminada corectament...");
                i++;
            }catch (Exception e){
                break;
            }
        }

        dao.save();
    }

    private static void consultaXquery(Scanner entrada, DAO_sobreAdriDAO dao) {

         do{
            System.out.println("Consulat Xquery. ");

            System.out.println("Escriu o copia la teva consulta. Per sortir escriu 'sortir' o '0' sense commetes:");
            System.out.print("\n\tConsulta: ");
            String consulta = entrada.nextLine();

            if(!consulta.equals("sortir") && !consulta.equals("0") && !consulta.isEmpty()) {
                System.out.println(dao.xQuery(consulta));
                System.out.println("\nPrem intro per continuar...");
                entrada.nextLine();
            }else
                break;

        }while(true);
    }

    private static void consultaXpath(Scanner entrada, DAO_sobreAdriDAO dao) {

        do{
            System.out.println("Consulat Xpath. ");

            System.out.println("Escriu o copia la teva consulta. Per sortir escriu 'sortir' o '0' sense commetes:");
            System.out.print("\n\tConsulta: ");
            String consulta = entrada.nextLine();

            if(!consulta.equals("sortir") && !consulta.equals("0") && !consulta.isEmpty()) {
                System.out.println(dao.xPath(consulta));
                System.out.println("\nPrem intro per continuar...");
                entrada.nextLine();
            }else
                break;

        }while(true);
    }

}
