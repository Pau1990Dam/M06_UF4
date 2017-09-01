
package jaxbObj;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para practica2Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="practica2Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Personas" type="{}PersonasType"/>
 *         &lt;element name="Facturacio" type="{}FacturacioType"/>
 *         &lt;element name="Cataleg" type="{}CatalegType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "practica2Type", propOrder = {
    "personas",
    "facturacio",
    "cataleg"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Practica2Type {

    @XmlElement(name = "Personas", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected PersonasType personas;
    @XmlElement(name = "Facturacio", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected FacturacioType facturacio;
    @XmlElement(name = "Cataleg", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected CatalegType cataleg;

    /**
     * Obtiene el valor de la propiedad personas.
     * 
     * @return
     *     possible object is
     *     {@link PersonasType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public PersonasType getPersonas() {
        return personas;
    }

    /**
     * Define el valor de la propiedad personas.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonasType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPersonas(PersonasType value) {
        this.personas = value;
    }

    /**
     * Obtiene el valor de la propiedad facturacio.
     * 
     * @return
     *     possible object is
     *     {@link FacturacioType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public FacturacioType getFacturacio() {
        return facturacio;
    }

    /**
     * Define el valor de la propiedad facturacio.
     * 
     * @param value
     *     allowed object is
     *     {@link FacturacioType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFacturacio(FacturacioType value) {
        this.facturacio = value;
    }

    /**
     * Obtiene el valor de la propiedad cataleg.
     * 
     * @return
     *     possible object is
     *     {@link CatalegType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public CatalegType getCataleg() {
        return cataleg;
    }

    /**
     * Define el valor de la propiedad cataleg.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalegType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCataleg(CatalegType value) {
        this.cataleg = value;
    }

}
