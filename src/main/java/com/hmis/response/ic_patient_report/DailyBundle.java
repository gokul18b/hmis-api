package com.hmis.response.ic_patient_report;

import java.util.Date;
import java.util.List;

public class DailyBundle {
	private Date bundle_date;
	private String temperature;
	private String unit;
	private List<DailyBundleDetails> details;

	public Date getBundle_date() {
		return bundle_date;
	}

	public void setBundle_date(Date bundle_date) {
		this.bundle_date = bundle_date;
	}

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

	public List<DailyBundleDetails> getDetails() {
		return details;
	}

	public void setDetails(List<DailyBundleDetails> details) {
		this.details = details;
	}

}
