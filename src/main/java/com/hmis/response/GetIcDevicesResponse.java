package com.hmis.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hmis.entity.MstInfectControlDevices;

public class GetIcDevicesResponse {
	private Integer status;
	private String date;
	private List<MstInfectControlDevices> results;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return formatter.format(date);
	}

	public List<MstInfectControlDevices> getResults() {
		return results;
	}

	public void setResults(List<MstInfectControlDevices> results) {
		this.results = results;
	}

}
