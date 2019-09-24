package com.hmis.request.nabh.savemedicalchecklist;

public class Details {
	private Integer trn_active_medical_record_audit_id;
	private Integer mst_active_medical_audit_id;
	private String option_value;
	private Integer total_minutes;
	

	private String remarks;

	public Integer getTrn_active_medical_record_audit_id() {
		return trn_active_medical_record_audit_id;
	}

	public void setTrn_active_medical_record_audit_id(Integer trn_active_medical_record_audit_id) {
		this.trn_active_medical_record_audit_id = trn_active_medical_record_audit_id;
	}

	public Integer getMst_active_medical_audit_id() {
		return mst_active_medical_audit_id;
	}

	public void setMst_active_medical_audit_id(Integer mst_active_medical_audit_id) {
		this.mst_active_medical_audit_id = mst_active_medical_audit_id;
	}

	public String getOption_value() {
		return option_value;
	}

	public void setOption_value(String option_value) {
		this.option_value = option_value;
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
