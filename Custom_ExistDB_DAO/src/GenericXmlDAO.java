import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;

import java.io.File;


public interface GenericXmlDAO {

    public Resource getResource(String resourceName);

    public Collection getCollection(String collectionName);

    public String getRootURI();

    public Collection getCurrentCollection() throws Exception;

    public Collection getRootCollection();

    public boolean setToRootCollection(String url);

    public boolean createCollection(String collectionName);

    public boolean storeResource(File source, String name);

    public boolean deleteResource(String resourceName);

    public boolean deleteCollection(String collectionName);

    public String XMLquery(String query);

    public Collection findAndMoveToRootCollection(String uri);

    public boolean moveToCollection(String collectionToMove);

}
