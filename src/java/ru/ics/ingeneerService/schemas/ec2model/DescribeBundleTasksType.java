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
 * <p>Java class for DescribeBundleTasksType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DescribeBundleTasksType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bundlesSet" type="{http://ec2.amazonaws.com/doc/2010-11-15/}DescribeBundleTasksInfoType"/>
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
@XmlType(name = "DescribeBundleTasksType", propOrder = {
    "bundlesSet",
    "filterSet"
})
public class DescribeBundleTasksType {

    @XmlElement(required = true)
    protected DescribeBundleTasksInfoType bundlesSet;
    protected FilterSetType filterSet;

    /**
     * Gets the value of the bundlesSet property.
     * 
     * @return
     *     possible object is
     *     {@link DescribeBundleTasksInfoType }
     *     
     */
    public DescribeBundleTasksInfoType getBundlesSet() {
        return bundlesSet;
    }

    /**
     * Sets the value of the bundlesSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescribeBundleTasksInfoType }
     *     
     */
    public void setBundlesSet(DescribeBundleTasksInfoType value) {
        this.bundlesSet = value;
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