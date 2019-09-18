package com.hmis.request.nabh.savemedicalchecklist;

import java.util.List;

public class SaveActiveMedicalRecordRequest {
	private Integer visit_id;
	private List<Details> details;

	public Integer getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}

	public List<Details> getDetails() {
		return details;
	}

	public void setDetails(List<Details> details) {
		this.details = details;
	}

}
