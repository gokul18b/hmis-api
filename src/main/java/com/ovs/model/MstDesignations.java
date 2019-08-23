package com.ovs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "mst_designations")
public class MstDesignations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "design_name")
	private String design_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesign_name() {
		return design_name;
	}

	public void setDesign_name(String design_name) {
		this.design_name = design_name;
	}

}
