import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;

public class DaoManager {

    private Database dbDriver;
    private String URI;
    private  XmlSgdb sgdb;
    private boolean isUriSeted;

    public DaoManager(){
        isUriSeted = false;
    }

    public void Connection(String xmldb) throws Exception {

        switch (xmldb.toLowerCase()){

            case "exist":

                sgdb = XmlSgdb.exist;
                break;
            case "basex":

                sgdb = XmlSgdb.basex;
                break;
            case "sedna":

                sgdb = XmlSgdb.sedna;
                break;
            default:

                throw new Exception("Error: "+xmldb+" no esta reconegut ni suportat.");
        }
        dbDriver = (Database)Class.forName(sgdb.getDriver()).newInstance();
        DatabaseManager.registerDatabase(dbDriver);

        System.out.println("Ya es pot conectar.");

    }

    public boolean setUri(String ip, String port, String sourceCollection){

        if(isUriSeted =
                (!ip.isEmpty() && !port.isEmpty() && !sourceCollection.isEmpty()
                        && !sgdb.name().isEmpty())
                ){

            StringBuilder uri = new StringBuilder();
            uri.append("xmldb").append(":").append(sgdb.name()).append("://").append(ip).append(":").append(port).
                    append(sourceCollection);
            URI = uri.toString();

        }else
            System.out.println("Les dades de conexió a la bd xml nativa no són correctes. ");


        return isUriSeted;
    }


    public String getURI() throws Exception {
        if(isUriSeted)
            return URI;
        else
            throw new Exception("Error: no has indicat una URI vàlida o no l'has establert.");
    }


}

enum XmlSgdb{

    exist("org.exist.xmldb.DatabaseImpl"),
    basex("org.basex.api.xmldb.BXDatabase"),
    sedna("net.cfoster.sedna.DatabaseImpl");

    private String driver;

    private  XmlSgdb(String driver){
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
