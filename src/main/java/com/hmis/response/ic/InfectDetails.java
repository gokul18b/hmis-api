package com.hmis.response.ic;

import java.util.List;

public class InfectDetails {

	private Integer status;
	private String insertion_date_time;
	List<InfectDetailsResult> details;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInsertion_date_time() {
		return insertion_date_time;
	}

	public void setInsertion_date_time(String insertion_date_time) {
		this.insertion_date_time = insertion_date_time;
	}

	public List<InfectDetailsResult> getDetails() {
		return details;
	}

	public void setDetails(List<InfectDetailsResult> details) {
		this.details = details;
	}

}
