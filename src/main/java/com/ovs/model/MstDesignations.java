package com.ovs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "mst_employees")
public class MstDesignations {
	@Id
	private Integer id;
	@Column
	private String design_name;
	@Column
	private Integer status;
	@Column
	private Integer is_deleted;
	@Column
	private String design_code;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesign_name() {
		return design_name;
	}

	public void setDesign_name(String design_name) {
		this.design_name = design_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Integer is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getDesign_code() {
		return design_code;
	}

	public void setDesign_code(String design_code) {
		this.design_code = design_code;
	}

	@Override
	public String toString() {
		return "MstEmployees [id=" + id + ", design_name=" + design_name + ", status=" + status + ", is_deleted="
				+ is_deleted + ", design_code=" + design_code + "]";
	}
}
