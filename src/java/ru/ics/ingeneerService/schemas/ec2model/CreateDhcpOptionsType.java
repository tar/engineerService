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
 * <p>Java class for CreateDhcpOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreateDhcpOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dhcpConfigurationSet" type="{http://ec2.amazonaws.com/doc/2010-11-15/}DhcpConfigurationItemSetType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateDhcpOptionsType", propOrder = {
    "dhcpConfigurationSet"
})
public class CreateDhcpOptionsType {

    @XmlElement(required = true)
    protected DhcpConfigurationItemSetType dhcpConfigurationSet;

    /**
     * Gets the value of the dhcpConfigurationSet property.
     * 
     * @return
     *     possible object is
     *     {@link DhcpConfigurationItemSetType }
     *     
     */
    public DhcpConfigurationItemSetType getDhcpConfigurationSet() {
        return dhcpConfigurationSet;
    }

    /**
     * Sets the value of the dhcpConfigurationSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link DhcpConfigurationItemSetType }
     *     
     */
    public void setDhcpConfigurationSet(DhcpConfigurationItemSetType value) {
        this.dhcpConfigurationSet = value;
    }

}
