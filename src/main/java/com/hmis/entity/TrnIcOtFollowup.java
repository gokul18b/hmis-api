package com.hmis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "trn_ic_ot_followup")
public class TrnIcOtFollowup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "trn_ot_booking_requests_id")
	private Integer trn_ot_booking_requests_id;
	
	@Column(name = "mst_antibiotics_id")
	private Integer mst_antibiotics_id;
	
	@Column(name = "mst_sub_antibiotics_id")
	private Integer mst_sub_antibiotics_id;
	
	@Column(name = "medication_administration")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date medication_administration;
	
	@Column(name = "incision_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date incision_time;
	
	@Column(name = "policy")
	private String policy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTrn_ot_booking_requests_id() {
		return trn_ot_booking_requests_id;
	}

	public void setTrn_ot_booking_requests_id(Integer trn_ot_booking_requests_id) {
		this.trn_ot_booking_requests_id = trn_ot_booking_requests_id;
	}

	public Integer getMst_antibiotics_id() {
		return mst_antibiotics_id;
	}

	public void setMst_antibiotics_id(Integer mst_antibiotics_id) {
		this.mst_antibiotics_id = mst_antibiotics_id;
	}

	public Integer getMst_sub_antibiotics_id() {
		return mst_sub_antibiotics_id;
	}

	public void setMst_sub_antibiotics_id(Integer mst_sub_antibiotics_id) {
		this.mst_sub_antibiotics_id = mst_sub_antibiotics_id;
	}

	public Date getMedication_administration() {
		return medication_administration;
	}

	public void setMedication_administration(Date medication_administration) {
		this.medication_administration = medication_administration;
	}

	public Date getIncision_time() {
		return incision_time;
	}

	public void setIncision_time(Date incision_time) {
		this.incision_time = incision_time;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}
	
	
}
