package com.hmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "mst_sub_antibiotics")
public class MstSubAntibiotics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="mst_antibiotics_id")
	private Integer mst_antibiotics_id;

	@Column(name = "sub_antibiotics_name")
	private String sub_antibiotics_name;

	@Column(name = "status")
	private Integer status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Integer getMst_antibiotics_id() {
		return mst_antibiotics_id;
	}

	public void setMst_antibiotics_id(Integer mst_antibiotics_id) {
		this.mst_antibiotics_id = mst_antibiotics_id;
	}

	public String getSub_antibiotics_name() {
		return sub_antibiotics_name;
	}

	public void setSub_antibiotics_name(String sub_antibiotics_name) {
		this.sub_antibiotics_name = sub_antibiotics_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
