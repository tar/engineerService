//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.29 at 10:35:36 PM MSK 
//


package ru.spbstu.ics.cloudclient.ec2model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeleteTagsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeleteTagsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resourcesSet" type="{http://ec2.amazonaws.com/doc/2010-11-15/}ResourceIdSetType"/>
 *         &lt;element name="tagSet" type="{http://ec2.amazonaws.com/doc/2010-11-15/}DeleteTagsSetType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteTagsType", propOrder = {
    "resourcesSet",
    "tagSet"
})
public class DeleteTagsType {

    @XmlElement(required = true)
    protected ResourceIdSetType resourcesSet;
    @XmlElement(required = true)
    protected DeleteTagsSetType tagSet;

    /**
     * Gets the value of the resourcesSet property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceIdSetType }
     *     
     */
    public ResourceIdSetType getResourcesSet() {
        return resourcesSet;
    }

    /**
     * Sets the value of the resourcesSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceIdSetType }
     *     
     */
    public void setResourcesSet(ResourceIdSetType value) {
        this.resourcesSet = value;
    }

    /**
     * Gets the value of the tagSet property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteTagsSetType }
     *     
     */
    public DeleteTagsSetType getTagSet() {
        return tagSet;
    }

    /**
     * Sets the value of the tagSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteTagsSetType }
     *     
     */
    public void setTagSet(DeleteTagsSetType value) {
        this.tagSet = value;
    }

}
