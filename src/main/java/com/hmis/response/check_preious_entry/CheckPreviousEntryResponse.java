package com.hmis.response.check_preious_entry;

import java.util.List;

public class CheckPreviousEntryResponse {

	private Integer status;
	private String message;

	private List<TimeDetails> timeDetails;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TimeDetails> getTimeDetails() {
		return timeDetails;
	}

	public void setTimeDetails(List<TimeDetails> timeDetails) {
		this.timeDetails = timeDetails;
	}
	
	
}
