package ru.ics.engineerService.persistence.entity;

import java.io.Serializable;

public class Authority implements Serializable {

	private static final long serialVersionUID = -1267236368517937957L;

	private Integer id_authority;

	private String role_name;

	// --------------------------------------------------------------------

	public void setId_authority(Integer id_authority) {
		this.id_authority = id_authority;
	}

	public Integer getId_authority() {
		return id_authority;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_name() {
		return role_name;
	}

}