package com.hmis.response;

import java.util.ArrayList;
import java.util.List;

import com.hmis.entity.TrnInfectControlDeviceHdr;


public class InfectControlDetailResponse {
	private String insert_status;
	private List<TrnInfectControlDeviceHdr> completed_details = new ArrayList<TrnInfectControlDeviceHdr>();
	private TrnInfectControlDeviceHdr in_complete_detail;

	
	public String getInsert_status() {
		return insert_status;
	}

	public void setInsert_status(String insert_status) {
		this.insert_status = insert_status;
	}

	public List<TrnInfectControlDeviceHdr> getCompleted_details() {
		return completed_details;
	}

	public void setCompleted_details(List<TrnInfectControlDeviceHdr> completed_details) {
		this.completed_details = completed_details;
	}

	

	public TrnInfectControlDeviceHdr getIn_complete_detail() {
		return in_complete_detail;
	}

	public void setIn_complete_detail(TrnInfectControlDeviceHdr in_complet_detail) {
		this.in_complete_detail = in_complet_detail;
	}

	
	@Override
	public String toString() {
		return "InfectControlDetailResponse [completed_details=" + completed_details + ", in_completed_details="
				+ in_complete_detail + "]";
	}

}
