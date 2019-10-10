package com.hmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="trn_ot_booking_requests")
public class TrnOtBookingRequests {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "patient_name")
	private String patient_name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "patient_code")
	private String patient_code;
	
	@Column(name = "surgery_name")
	private String surgery_name;
	
	@Column(name = "surgeon")
	private String surgeon;
	
	@Column(name = "anaesthetist")
	private String anaesthetist;
	
	@Column(name = "surgery_datetime")
	private String surgery_datetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPatient_code() {
		return patient_code;
	}

	public void setPatient_code(String patient_code) {
		this.patient_code = patient_code;
	}

	public String getSurgery_name() {
		return surgery_name;
	}

	public void setSurgery_name(String surgery_name) {
		this.surgery_name = surgery_name;
	}

	public String getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}

	public String getAnaesthetist() {
		return anaesthetist;
	}

	public void setAnaesthetist(String anaesthetist) {
		this.anaesthetist = anaesthetist;
	}

	public String getSurgery_datetime() {
		return surgery_datetime;
	}

	public void setSurgery_datetime(String surgery_datetime) {
		this.surgery_datetime = surgery_datetime;
	}
	
	
	
	
}
