//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.12.29 at 10:35:36 PM MSK 
//


package ru.ics.engineerService.ws_schemas.ec2model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DescribeCustomerGatewaysResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DescribeCustomerGatewaysResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerGatewaySet" type="{http://ec2.amazonaws.com/doc/2010-11-15/}CustomerGatewaySetType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescribeCustomerGatewaysResponseType", propOrder = {
    "requestId",
    "customerGatewaySet"
})
public class DescribeCustomerGatewaysResponseType {

    @XmlElement(required = true)
    protected String requestId;
    @XmlElement(required = true)
    protected CustomerGatewaySetType customerGatewaySet;

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the customerGatewaySet property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerGatewaySetType }
     *     
     */
    public CustomerGatewaySetType getCustomerGatewaySet() {
        return customerGatewaySet;
    }

    /**
     * Sets the value of the customerGatewaySet property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerGatewaySetType }
     *     
     */
    public void setCustomerGatewaySet(CustomerGatewaySetType value) {
        this.customerGatewaySet = value;
    }

}
