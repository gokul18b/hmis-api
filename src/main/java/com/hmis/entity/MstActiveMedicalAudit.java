package com.hmis.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "mst_active_medical_audit")
public class MstActiveMedicalAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@OneToMany(mappedBy = "mstActiveMedicalAudit")
//	private List<TrnActiveMedicalAudit> trn_active_medical_audit;
	
//	@OneToMany(mappedBy="mstActiveMedicalAudit")
//	private List<TrnActiveMedicalAuditDetails> trn_active_medical_audit_details;

	@Column(name = "question_name")
	public String question_name;

	@Column(name = "status")
	public Integer status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion_name() {
		return question_name;
	}

	public void setQuestion_name(String question_name) {
		this.question_name = question_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

//	public List<TrnActiveMedicalAudit> getTrn_active_medical_audit() {
//		return trn_active_medical_audit;
//	}
//
//	public void setTrn_active_medical_audit(List<TrnActiveMedicalAudit> trn_active_medical_audit) {
//		this.trn_active_medical_audit = trn_active_medical_audit;
//	}

	
}
