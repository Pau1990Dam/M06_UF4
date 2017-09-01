
package jaxbObj;

import javax.annotation.Generated;
import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Clase Java para idType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="idType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>byte">
 *       &lt;attribute name="preu_unitat_sense_iva" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *             &lt;minInclusive value="0"/>
 *             &lt;maxInclusive value="1000000"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="unitats" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
 *       &lt;attribute name="iva" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *             &lt;enumeration value="0"/>
 *             &lt;enumeration value="4"/>
 *             &lt;enumeration value="10"/>
 *             &lt;enumeration value="21"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "idType", propOrder = {
    "value"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class IdType {

    @XmlValue
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected int value;
    @XmlAttribute(name = "preu_unitat_sense_iva", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected float preuUnitatSenseIva;
    @XmlAttribute(name = "unitats", required = true)
    @XmlSchemaType(name = "unsignedShort")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected int unitats;
    @XmlAttribute(name = "iva", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected short iva;

    /**
     * Obtiene el valor de la propiedad value.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public int getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la propiedad preuUnitatSenseIva.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public float getPreuUnitatSenseIva() {
        return preuUnitatSenseIva;
    }

    /**
     * Define el valor de la propiedad preuUnitatSenseIva.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPreuUnitatSenseIva(float value) {
        this.preuUnitatSenseIva = value;
    }

    /**
     * Obtiene el valor de la propiedad unitats.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public int getUnitats() {
        return unitats;
    }

    /**
     * Define el valor de la propiedad unitats.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setUnitats(int value) {
        this.unitats = value;
    }

    /**
     * Obtiene el valor de la propiedad iva.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public short getIva() {
        return iva;
    }

    /**
     * Define el valor de la propiedad iva.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIva(short value) {
        this.iva = value;
    }

}
