package com.hmis.response.getBundleReport;

import java.math.BigInteger;

public class DailyBundleReport {
	private String ward_name;
	private BigInteger count;

	public String getWard_name() {
		return ward_name;
	}

	public void setWard_name(String ward_name) {
		this.ward_name = ward_name;
	}

	public BigInteger getCount() {
		return count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

}
