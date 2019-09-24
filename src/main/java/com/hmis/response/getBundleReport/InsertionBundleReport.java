package com.hmis.response.getBundleReport;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class InsertionBundleReport {
	private Integer floor_id;
	private String floor_name;
	List<InsertionBundleDetails> details = new ArrayList<>();

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

	public List<InsertionBundleDetails> getDetails() {
		return details;
	}

	public void setDetails(List<InsertionBundleDetails> details) {
		this.details = details;
	}

}
