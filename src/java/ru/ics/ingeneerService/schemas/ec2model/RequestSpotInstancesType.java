//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.29 at 10:35:36 PM MSK 
//


package ru.spbstu.ics.cloudclient.ec2model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RequestSpotInstancesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestSpotInstancesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="spotPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instanceCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="validUntil" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="launchGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="availabilityZoneGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="launchSpecification" type="{http://ec2.amazonaws.com/doc/2010-11-15/}LaunchSpecificationRequestType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestSpotInstancesType", propOrder = {
    "spotPrice",
    "instanceCount",
    "type",
    "validFrom",
    "validUntil",
    "launchGroup",
    "availabilityZoneGroup",
    "launchSpecification"
})
public class RequestSpotInstancesType {

    @XmlElement(required = true)
    protected String spotPrice;
    protected BigInteger instanceCount;
    protected String type;
    protected XMLGregorianCalendar validFrom;
    protected XMLGregorianCalendar validUntil;
    protected String launchGroup;
    protected String availabilityZoneGroup;
    @XmlElement(required = true)
    protected LaunchSpecificationRequestType launchSpecification;

    /**
     * Gets the value of the spotPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpotPrice() {
        return spotPrice;
    }

    /**
     * Sets the value of the spotPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpotPrice(String value) {
        this.spotPrice = value;
    }

    /**
     * Gets the value of the instanceCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getInstanceCount() {
        return instanceCount;
    }

    /**
     * Sets the value of the instanceCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setInstanceCount(BigInteger value) {
        this.instanceCount = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the validFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidFrom() {
        return validFrom;
    }

    /**
     * Sets the value of the validFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidFrom(XMLGregorianCalendar value) {
        this.validFrom = value;
    }

    /**
     * Gets the value of the validUntil property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidUntil() {
        return validUntil;
    }

    /**
     * Sets the value of the validUntil property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidUntil(XMLGregorianCalendar value) {
        this.validUntil = value;
    }

    /**
     * Gets the value of the launchGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLaunchGroup() {
        return launchGroup;
    }

    /**
     * Sets the value of the launchGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLaunchGroup(String value) {
        this.launchGroup = value;
    }

    /**
     * Gets the value of the availabilityZoneGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailabilityZoneGroup() {
        return availabilityZoneGroup;
    }

    /**
     * Sets the value of the availabilityZoneGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailabilityZoneGroup(String value) {
        this.availabilityZoneGroup = value;
    }

    /**
     * Gets the value of the launchSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link LaunchSpecificationRequestType }
     *     
     */
    public LaunchSpecificationRequestType getLaunchSpecification() {
        return launchSpecification;
    }

    /**
     * Sets the value of the launchSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link LaunchSpecificationRequestType }
     *     
     */
    public void setLaunchSpecification(LaunchSpecificationRequestType value) {
        this.launchSpecification = value;
    }

}
