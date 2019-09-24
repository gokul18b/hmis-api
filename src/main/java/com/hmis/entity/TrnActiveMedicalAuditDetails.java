package com.hmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "trn_active_medical_audit_details")
public class TrnActiveMedicalAuditDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@ManyToOne
//	@JoinColumn(name = "mst_active_medical_audit_id")
//	private MstActiveMedicalAudit mstActiveMedicalAudit;

	@Column(name="mst_active_medical_audit_id")
	private Integer mst_active_medical_audit_id;
	
	@ManyToOne
	@JoinColumn(name = "trn_active_medical_audit_id")
	private TrnActiveMedicalAudit trnActiveMedicalAudit;

	@Column(name = "option_value")
	private String option_value;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name="minutes")
	private Integer total_minutes;
	
	

//	public MstActiveMedicalAudit getMstActiveMedicalAudit() {
//		return mstActiveMedicalAudit;
//	}
//
//	public void setMstActiveMedicalAudit(MstActiveMedicalAudit mstActiveMedicalAudit) {
//		this.mstActiveMedicalAudit = mstActiveMedicalAudit;
//	}

	public TrnActiveMedicalAudit getTrnActiveMedicalAudit() {
		return trnActiveMedicalAudit;
	}

	public void setTrnActiveMedicalAudit(TrnActiveMedicalAudit trnActiveMedicalAudit) {
		this.trnActiveMedicalAudit = trnActiveMedicalAudit;
	}

	public String getOption_value() {
		return option_value;
	}

	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getMst_active_medical_audit_id() {
		return mst_active_medical_audit_id;
	}

	public void setMst_active_medical_audit_id(Integer mst_active_medical_audit_id) {
		this.mst_active_medical_audit_id = mst_active_medical_audit_id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getTotal_minutes() {
		return total_minutes;
	}

	public void setTotal_minutes(Integer total_minutes) {
		this.total_minutes = total_minutes;
	}

	
	
	
	

}
