package com.hmis.request.nabh.savemedicalchecklist;

import java.util.List;

public class SaveActiveMedicalRecordRequest {
	private Integer visit_id;
	private Integer created_by;
	private Integer bed_id;
	private List<Details> details;
	
	

	public Integer getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}
	
	

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public List<Details> getDetails() {
		return details;
	}

	public void setDetails(List<Details> details) {
		this.details = details;
	}

	public Integer getBed_id() {
		return bed_id;
	}

	public void setBed_id(Integer bed_id) {
		this.bed_id = bed_id;
	}
	
	

}
