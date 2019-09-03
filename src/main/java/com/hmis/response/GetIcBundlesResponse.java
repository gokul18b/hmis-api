package com.hmis.response;

import java.util.List;

import com.hmis.entity.MstInfectControlBundle;

public class GetIcBundlesResponse {
	private Integer status;
	
	private List<MstInfectControlBundle> results;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<MstInfectControlBundle> getResults() {
		return results;
	}

	public void setResults(List<MstInfectControlBundle> results) {
		this.results = results;
	}

}
