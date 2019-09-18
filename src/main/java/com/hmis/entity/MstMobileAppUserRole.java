package com.hmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="mst_mobileapp_user_role")
public class MstMobileAppUserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="mst_employee_id")
	private Integer mst_employee_id;
	
	@Column(name="role_name")
	private String role_name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMst_employee_id() {
		return mst_employee_id;
	}

	public void setMst_employee_id(Integer mst_employee_id) {
		this.mst_employee_id = mst_employee_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	
	
}
