package com.hmis.response;

public class GetOtRequestPatientResponse {
	private Integer id;
	private String patient_name;
	private Integer age;
	private String gender;
	private String patient_code;
	private String surgery_name;
	private String surgeon;
	private String anaesthestist;
	private String surgery_date_time;
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
	public String getAnaesthestist() {
		return anaesthestist;
	}
	public void setAnaesthestist(String anaesthestist) {
		this.anaesthestist = anaesthestist;
	}
	public String getSurgery_date_time() {
		return surgery_date_time;
	}
	public void setSurgery_date_time(String surgery_date_time) {
		this.surgery_date_time = surgery_date_time;
	}
	
	
}
