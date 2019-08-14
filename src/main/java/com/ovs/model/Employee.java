package com.ovs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECORD_ID")
	private Integer id;
	
	
	@Column(name = "NAME")
	private String name;
	
	
	@Column(name="AGE")
	private Integer age;
	
	
//	@OneToOne(mappedBy = "emp")
//	private Phone phone;
	
	
	
	public Employee(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
		
	}

	public Employee() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
//	public Phone getPhone() {
//		return phone;
//	}
//
//	public void setPhone(Phone phone) {
//		this.phone = phone;
//	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + "";
	}
	
	
	
}
