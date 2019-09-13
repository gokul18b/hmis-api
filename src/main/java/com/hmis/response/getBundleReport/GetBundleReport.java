package com.hmis.response.getBundleReport;

import java.util.List;

public class GetBundleReport {

	private List<InsertionBundleReport> insertion_bundle;
	private List<DailyBundleReport> daily_bundle;

	public List<InsertionBundleReport> getInsertion_bundle() {
		return insertion_bundle;
	}

	public void setInsertion_bundle(List<InsertionBundleReport> insertion_bundle) {
		this.insertion_bundle = insertion_bundle;
	}

	public List<DailyBundleReport> getDaily_bundle() {
		return daily_bundle;
	}

	public void setDaily_bundle(List<DailyBundleReport> daily_bundle) {
		this.daily_bundle = daily_bundle;
	}

}
