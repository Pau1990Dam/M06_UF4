
package jaxbObj;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EmpleatType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EmpleatType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="10000000"/>
 *               &lt;maxInclusive value="99999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nom">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="([a-zA-Z])*"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cognoms">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="([a-zA-Z])*"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="anys_treballats">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="51"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="sou">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *               &lt;minInclusive value="200"/>
 *               &lt;maxInclusive value="60000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmpleatType", propOrder = {
    "id",
    "nom",
    "cognoms",
    "anysTreballats",
    "sou"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class EmpleatType {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected int id;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String nom;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String cognoms;
    @XmlElement(name = "anys_treballats")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected int anysTreballats;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected float sou;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public int getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad nom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getNom() {
        return nom;
    }

    /**
     * Define el valor de la propiedad nom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Obtiene el valor de la propiedad cognoms.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCognoms() {
        return cognoms;
    }

    /**
     * Define el valor de la propiedad cognoms.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCognoms(String value) {
        this.cognoms = value;
    }

    /**
     * Obtiene el valor de la propiedad anysTreballats.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public int getAnysTreballats() {
        return anysTreballats;
    }

    /**
     * Define el valor de la propiedad anysTreballats.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAnysTreballats(int value) {
        this.anysTreballats = value;
    }

    /**
     * Obtiene el valor de la propiedad sou.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public float getSou() {
        return sou;
    }

    /**
     * Define el valor de la propiedad sou.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSou(float value) {
        this.sou = value;
    }

}
