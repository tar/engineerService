//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.29 at 10:35:36 PM MSK 
//


package ru.spbstu.ics.cloudclient.ec2model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DescribeReservedInstancesOfferingsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DescribeReservedInstancesOfferingsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reservedInstancesOfferingsSet" type="{http://ec2.amazonaws.com/doc/2010-11-15/}DescribeReservedInstancesOfferingsSetType" minOccurs="0"/>
 *         &lt;element name="instanceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="availabilityZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filterSet" type="{http://ec2.amazonaws.com/doc/2010-11-15/}FilterSetType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescribeReservedInstancesOfferingsType", propOrder = {
    "reservedInstancesOfferingsSet",
    "instanceType",
    "availabilityZone",
    "productDescription",
    "filterSet"
})
public class DescribeReservedInstancesOfferingsType {

    protected DescribeReservedInstancesOfferingsSetType reservedInstancesOfferingsSet;
    protected String instanceType;
    protected String availabilityZone;
    protected String productDescription;
    protected FilterSetType filterSet;

    /**
     * Gets the value of the reservedInstancesOfferingsSet property.
     * 
     * @return
     *     possible object is
     *     {@link DescribeReservedInstancesOfferingsSetType }
     *     
     */
    public DescribeReservedInstancesOfferingsSetType getReservedInstancesOfferingsSet() {
        return reservedInstancesOfferingsSet;
    }

    /**
     * Sets the value of the reservedInstancesOfferingsSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescribeReservedInstancesOfferingsSetType }
     *     
     */
    public void setReservedInstancesOfferingsSet(DescribeReservedInstancesOfferingsSetType value) {
        this.reservedInstancesOfferingsSet = value;
    }

    /**
     * Gets the value of the instanceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceType() {
        return instanceType;
    }

    /**
     * Sets the value of the instanceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceType(String value) {
        this.instanceType = value;
    }

    /**
     * Gets the value of the availabilityZone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailabilityZone() {
        return availabilityZone;
    }

    /**
     * Sets the value of the availabilityZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailabilityZone(String value) {
        this.availabilityZone = value;
    }

    /**
     * Gets the value of the productDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the value of the productDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductDescription(String value) {
        this.productDescription = value;
    }

    /**
     * Gets the value of the filterSet property.
     * 
     * @return
     *     possible object is
     *     {@link FilterSetType }
     *     
     */
    public FilterSetType getFilterSet() {
        return filterSet;
    }

    /**
     * Sets the value of the filterSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterSetType }
     *     
     */
    public void setFilterSet(FilterSetType value) {
        this.filterSet = value;
    }

}
