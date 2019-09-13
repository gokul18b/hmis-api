package com.hmis.response.ic_patient_report;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsertionBundle {

	private String temperature;
	private String unit;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date bundle_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Kolkata")
	private Date insertion_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Kolkata")
	private Date removal_date;
	
	private List<InsertionBundleDetails> insertion_details;

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getBundle_date() {
		return bundle_date;
	}

	public void setBundle_date(Date bundle_date) {
		this.bundle_date = bundle_date;
	}

	public List<InsertionBundleDetails> getInsertion_details() {
		return insertion_details;
	}

	public void setInsertion_details (List<InsertionBundleDetails> details) {
		this.insertion_details = details;
	}

	public Date getInsertion_date() {
		return insertion_date;
	}

	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}

	public Date getRemoval_date() {
		return removal_date;
	}

	public void setRemoval_date(Date removal_date) {
		this.removal_date = removal_date;
	}
	

}
