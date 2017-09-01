
package jaxbObj;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PersonasType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PersonasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Empleats" type="{}EmpleatsType"/>
 *         &lt;element name="Clients" type="{}ClientsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonasType", propOrder = {
    "empleats",
    "clients"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class PersonasType {

    @XmlElement(name = "Empleats", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected EmpleatsType empleats;
    @XmlElement(name = "Clients", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ClientsType clients;

    /**
     * Obtiene el valor de la propiedad empleats.
     * 
     * @return
     *     possible object is
     *     {@link EmpleatsType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public EmpleatsType getEmpleats() {
        return empleats;
    }

    /**
     * Define el valor de la propiedad empleats.
     * 
     * @param value
     *     allowed object is
     *     {@link EmpleatsType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setEmpleats(EmpleatsType value) {
        this.empleats = value;
    }

    /**
     * Obtiene el valor de la propiedad clients.
     * 
     * @return
     *     possible object is
     *     {@link ClientsType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ClientsType getClients() {
        return clients;
    }

    /**
     * Define el valor de la propiedad clients.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientsType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setClients(ClientsType value) {
        this.clients = value;
    }

}
