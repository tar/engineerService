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
 * <p>Java class for ImportInstanceVolumeDetailItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImportInstanceVolumeDetailItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bytesConverted" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="availabilityZone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="image" type="{http://ec2.amazonaws.com/doc/2010-11-15/}DiskImageDescriptionType"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="volume" type="{http://ec2.amazonaws.com/doc/2010-11-15/}DiskImageVolumeDescriptionType"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImportInstanceVolumeDetailItemType", propOrder = {
    "bytesConverted",
    "availabilityZone",
    "image",
    "description",
    "volume",
    "status",
    "statusMessage"
})
public class ImportInstanceVolumeDetailItemType {

    protected long bytesConverted;
    @XmlElement(required = true)
    protected String availabilityZone;
    @XmlElement(required = true)
    protected DiskImageDescriptionType image;
    protected String description;
    @XmlElement(required = true)
    protected DiskImageVolumeDescriptionType volume;
    @XmlElement(required = true)
    protected String status;
    protected String statusMessage;

    /**
     * Gets the value of the bytesConverted property.
     * 
     */
    public long getBytesConverted() {
        return bytesConverted;
    }

    /**
     * Sets the value of the bytesConverted property.
     * 
     */
    public void setBytesConverted(long value) {
        this.bytesConverted = value;
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
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link DiskImageDescriptionType }
     *     
     */
    public DiskImageDescriptionType getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiskImageDescriptionType }
     *     
     */
    public void setImage(DiskImageDescriptionType value) {
        this.image = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link DiskImageVolumeDescriptionType }
     *     
     */
    public DiskImageVolumeDescriptionType getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiskImageVolumeDescriptionType }
     *     
     */
    public void setVolume(DiskImageVolumeDescriptionType value) {
        this.volume = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

}
