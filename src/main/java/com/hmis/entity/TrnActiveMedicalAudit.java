package com.hmis.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "trn_active_medical_audit")
public class TrnActiveMedicalAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "visit_id")
	private Integer visit_id;
	
	@OneToMany(mappedBy="trnActiveMedicalAudit")
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

//	public MstActiveMedicalAudit getMstActiveMedicalAudit() {
//		return mstActiveMedicalAudit;
//	}
//
//	public void setMstActiveMedicalAudit(MstActiveMedicalAudit mstActiveMedicalAudit) {
//		this.mstActiveMedicalAudit = mstActiveMedicalAudit;
//	}
	
	

}
