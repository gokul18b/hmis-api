package com.ovs.response;

import java.util.ArrayList;
import java.util.List;

import com.ovs.model.trn_infect_control_device_hdr;

public class InfectControlDetailResponse {
	private String insert_status;
	private List<trn_infect_control_device_hdr> completed_details = new ArrayList<trn_infect_control_device_hdr>();
	private trn_infect_control_device_hdr in_completed_details;

	
	public String getInsert_status() {
		return insert_status;
	}

	public void setInsert_status(String insert_status) {
		this.insert_status = insert_status;
	}

	public List<trn_infect_control_device_hdr> getCompleted_details() {
		return completed_details;
	}

	public void setCompleted_details(List<trn_infect_control_device_hdr> completed_details) {
		this.completed_details = completed_details;
	}

	public trn_infect_control_device_hdr getIn_completed_details() {
		return in_completed_details;
	}

	public void setIn_completed_details(trn_infect_control_device_hdr in_completed_details) {
		this.in_completed_details = in_completed_details;
	}

	@Override
	public String toString() {
		return "InfectControlDetailResponse [completed_details=" + completed_details + ", in_completed_details="
				+ in_completed_details + "]";
	}

}
