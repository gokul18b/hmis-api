package com.hmis.response.getAntibioticsStatus;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AntibioticDetails {
	private Integer id;
	private String antibiotics_name;
	private String sub_antibiotics_name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Kolkata")
	private Date start_time;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Kolkata")
	private Date end_time;
	private Integer antibiotic_id;
	private Integer sub_antibiotic_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAntibiotics_name() {
		return antibiotics_name;
	}
	public void setAntibiotics_name(String antibiotics_name) {
		this.antibiotics_name = antibiotics_name;
	}
	public String getSub_antibiotics_name() {
		return sub_antibiotics_name;
	}
	public void setSub_antibiotics_name(String sub_antibiotics_name) {
		this.sub_antibiotics_name = sub_antibiotics_name;
	}
	
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	
	public Integer getAntibiotic_id() {
		return antibiotic_id;
	}
	public void setAntibiotic_id(Integer antibiotic_id) {
		this.antibiotic_id = antibiotic_id;
	}
	public Integer getSub_antibiotic_id() {
		return sub_antibiotic_id;
	}
	public void setSub_antibiotic_id(Integer sub_antibiotic_id) {
		this.sub_antibiotic_id = sub_antibiotic_id;
	}
	@Override
	public String toString() {
		return "AntibioticDetails [id=" + id + ", antibiotics_name=" + antibiotics_name + ", sub_antibiotics_name="
				+ sub_antibiotics_name + ",  start_time=" + start_time + ", end_time=" + end_time
				+ "]";
	}
	
	
}
