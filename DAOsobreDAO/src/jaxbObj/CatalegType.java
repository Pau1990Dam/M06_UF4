
package jaxbObj;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para CatalegType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CatalegType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="producte" type="{}producteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalegType", propOrder = {
    "producte"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class CatalegType {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<ProducteType> producte;

    /**
     * Gets the value of the producte property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the producte property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducte().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProducteType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-04-22T04:15:12+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<ProducteType> getProducte() {
        if (producte == null) {
            producte = new ArrayList<ProducteType>();
        }
        return this.producte;
    }

}
