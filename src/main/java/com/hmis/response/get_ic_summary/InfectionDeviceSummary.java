package com.hmis.response.get_ic_summary;

import java.util.List;

public class InfectionDeviceSummary {
	private Integer floor_id;
	private String floor_name;
	private List<InfectionDeviceSummaryDetails> details;

	public Integer getFloor_id() {
		return floor_id;
	}

	public void setFloor_id(Integer floor_id) {
		this.floor_id = floor_id;
	}

	public String getFloor_name() {
		return floor_name;
	}

	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
	}

	public List<InfectionDeviceSummaryDetails> getDetails() {
		return details;
	}

	public void setDetails(List<InfectionDeviceSummaryDetails> details) {
		this.details = details;
	}

	
	

}
