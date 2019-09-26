package com.hmis.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "trn_ic_antibiotics")
public class TrnIcAntibiotics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "mst_antibiotic_id")
	private Integer mst_antibiotic_id;

	@Column(name = "highend_antibiotic_id")
	private Integer highend_antibiotic_id;
	
	@Column(name = "start_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date start_time;

	@Column(name = "end_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date end_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getMst_antibiotic_id() {
		return mst_antibiotic_id;
	}

	public void setMst_antibiotic_id(Integer mst_antibiotic_id) {
		this.mst_antibiotic_id = mst_antibiotic_id;
	}

	public Integer getHighend_antibiotic_id() {
		return highend_antibiotic_id;
	}

	public void setHighend_antibiotic_id(Integer highend_antibiotic_id) {
		this.highend_antibiotic_id = highend_antibiotic_id;
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
	
	
}
