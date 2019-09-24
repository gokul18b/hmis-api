package com.hmis.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "trn_active_medical_audit")
public class TrnActiveMedicalAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "visit_id")
	private Integer visit_id;
	@Column(name = "created_by")
	private Integer created_by;
	@Column(name = "bed_id")
	private Integer bed_id;
	
	@Column(name="created_date")
	@CreationTimestamp
	private Date created_date;
	
	@OneToMany(mappedBy = "trnActiveMedicalAudit")
	private List<TrnActiveMedicalAuditDetails> trnActiveMedicalAuditDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}

	public List<TrnActiveMedicalAuditDetails> getTrnActiveMedicalAuditDetails() {
		return trnActiveMedicalAuditDetails;
	}

	public void setTrnActiveMedicalAuditDetails(List<TrnActiveMedicalAuditDetails> trnActiveMedicalAuditDetails) {
		this.trnActiveMedicalAuditDetails = trnActiveMedicalAuditDetails;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Integer getBed_id() {
		return bed_id;
	}

	public void setBed_id(Integer bed_id) {
		this.bed_id = bed_id;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	

//	public MstActiveMedicalAudit getMstActiveMedicalAudit() {
//		return mstActiveMedicalAudit;
//	}
//
//	public void setMstActiveMedicalAudit(MstActiveMedicalAudit mstActiveMedicalAudit) {
//		this.mstActiveMedicalAudit = mstActiveMedicalAudit;
//	}

}
