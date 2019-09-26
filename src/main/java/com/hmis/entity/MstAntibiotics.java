package com.hmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "mst_antibiotics")
public class MstAntibiotics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

		
	@Column(name = "antibiotics_name")
	private String antibiotics_name;

	@Column(name = "status")
	private Integer status;
	
	


	public MstAntibiotics(int id, String antibiotics_name, Integer status) {
		super();
		this.id = id;
		this.antibiotics_name = antibiotics_name;
		this.status = status;
	}

	public MstAntibiotics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAntibiotics_name() {
		return antibiotics_name;
	}

	public void setAntibiotics_name(String antibiotics_name) {
		this.antibiotics_name = antibiotics_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
