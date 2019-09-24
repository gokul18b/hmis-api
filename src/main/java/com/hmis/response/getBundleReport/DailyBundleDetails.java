package com.hmis.response.getBundleReport;

import java.math.BigInteger;

public class DailyBundleDetails {
private String device_name;
private BigInteger total_count;
private BigInteger issue_count;
public String getDevice_name() {
	return device_name;
}
public void setDevice_name(String device_name) {
	this.device_name = device_name;
}
public BigInteger getTotal_count() {
	return total_count;
}
public void setTotal_count(BigInteger total_count) {
	this.total_count = total_count;
}
public BigInteger getIssue_count() {
	return issue_count;
}
public void setIssue_count(BigInteger issue_count) {
	this.issue_count = issue_count;
}


}
