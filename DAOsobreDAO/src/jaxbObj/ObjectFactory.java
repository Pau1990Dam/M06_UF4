
package jaxbObj;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxbObj package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Practica2_QNAME = new QName("", "practica2");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxbObj
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Practica2Type }
     * 
     */
    public Practica2Type createPractica2Type() {
        return new Practica2Type();
    }

    /**
     * Create an instance of {@link EmpleatType }
     * 
     */
    public EmpleatType createEmpleatType() {
        return new EmpleatType();
    }

    /**
     * Create an instance of {@link ClientType }
     * 
     */
    public ClientType createClientType() {
        return new ClientType();
    }

    /**
     * Create an instance of {@link PersonasType }
     * 
     */
    public PersonasType createPersonasType() {
        return new PersonasType();
    }

    /**
     * Create an instance of {@link IdType }
     * 
     */
    public IdType createIdType() {
        return new IdType();
    }

    /**
     * Create an instance of {@link ClientsType }
     * 
     */
    public ClientsType createClientsType() {
        return new ClientsType();
    }

    /**
     * Create an instance of {@link EmpleatsType }
     * 
     */
    public EmpleatsType createEmpleatsType() {
        return new EmpleatsType();
    }

    /**
     * Create an instance of {@link ProductesType }
     * 
     */
    public ProductesType createProductesType() {
        return new ProductesType();
    }

    /**
     * Create an instance of {@link CatalegType }
     * 
     */
    public CatalegType createCatalegType() {
        return new CatalegType();
    }

    /**
     * Create an instance of {@link FacturaType }
     * 
     */
    public FacturaType createFacturaType() {
        return new FacturaType();
    }

    /**
     * Create an instance of {@link FacturacioType }
     * 
     */
    public FacturacioType createFacturacioType() {
        return new FacturacioType();
    }

    /**
     * Create an instance of {@link ProducteType }
     * 
     */
    public ProducteType createProducteType() {
        return new ProducteType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Practica2Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "practica2")
    public JAXBElement<Practica2Type> createPractica2(Practica2Type value) {
        return new JAXBElement<Practica2Type>(_Practica2_QNAME, Practica2Type.class, null, value);
    }

}
