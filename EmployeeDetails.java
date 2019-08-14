package com.ovs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {
	@Id
	private Integer id;
	@Column
	private Integer emp_id;
	@Column
	private String mobile_number;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "emp_id", nullable = false)
	private Employee user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}
	
	

}
