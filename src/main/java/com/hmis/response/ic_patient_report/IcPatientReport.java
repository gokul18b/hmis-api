package com.hmis.response.ic_patient_report;

import java.util.ArrayList;
import java.util.List;

public class IcPatientReport {
	private Integer status;
	InsertionBundle insertionBundles;
	List<DailyBundle> dailyBundles = new ArrayList<>();

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public InsertionBundle getInsertionBundles() {
		return insertionBundles;
	}

	public void setInsertionBundles(InsertionBundle insertionBundles) {
		this.insertionBundles = insertionBundles;
	}

	public List<DailyBundle> getDailyBundles() {
		return dailyBundles;
	}

	public void setDailyBundles(List<DailyBundle> dailyBundles) {
		this.dailyBundles = dailyBundles;
	}

}
