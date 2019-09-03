package com.hmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "mst_employees")
public class MstEmployees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "mst_department_id")
	private Integer mst_department_id;

	@Column(name = "mst_designation_id")
	private Integer mst_designation_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getMst_department_id() {
		return mst_department_id;
	}

	public void setMst_department_id(Integer mst_department_id) {
		this.mst_department_id = mst_department_id;
	}

	public Integer getMst_designation_id() {
		return mst_designation_id;
	}

	public void setMst_designation_id(Integer mst_designation_id) {
		this.mst_designation_id = mst_designation_id;
	}

}
