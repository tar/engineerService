package ru.ics.engineerService.persistence.entity;

import java.io.Serializable;

/**
 * Hibernate entity for User
 * 
 * @author <a href="mailto:mlinnik@itcwin.com">Linnik Mihail</a>
 */

public class User implements Serializable {

	private static final long serialVersionUID = 5711383667536555832L;

	private Integer id_user;

	private Integer id_authority;

	private Authority authority;

	private String last_name;

	private String first_name;

	private String username;

	private String password;

	private String city;

	private String state;

	private String street;

	private String postal;

	private Boolean is_receive = false;

	private Boolean enabled = true;

	// --------------------------------------------------------------------------------

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setId_authority(Integer id_authority) {
		this.id_authority = id_authority;
	}

	public Integer getId_authority() {
		return id_authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPostal() {
		return postal;
	}

	public void setIs_receive(Boolean is_receive) {
		this.is_receive = is_receive;
	}

	public Boolean getIs_receive() {
		return is_receive;
	}
}
