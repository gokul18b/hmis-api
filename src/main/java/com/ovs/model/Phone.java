package com.ovs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PHONES")
public class Phone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECORD_ID")
	private Integer id;
	
	
	@Column(name = "NAME")
	private String no;
	
	@Column(name="EMP_id")
	private Integer emp_id;
	
	
//	@JoinColumn(name = "EMP_ID")
//	@OneToOne
//	@JsonIgnore
//	private Employee emp;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	/*public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}*/

	
	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", no=" + no + "]";
	}
	
	

}