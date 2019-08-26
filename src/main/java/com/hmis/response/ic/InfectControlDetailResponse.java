package com.hmis.response.ic;

import java.util.ArrayList;
import java.util.List;

import com.hmis.model.trn_infect_control_device_hdr;

public class InfectControlDetailResponse {
	private String insert_status;
	private List<trn_infect_control_device_hdr> completed_details = new ArrayList<trn_infect_control_device_hdr>();
	private trn_infect_control_device_hdr in_complete_detail;

	
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

	

	public trn_infect_control_device_hdr getIn_complete_detail() {
		return in_complete_detail;
	}

	public void setIn_complete_detail(trn_infect_control_device_hdr in_complet_detail) {
		this.in_complete_detail = in_complet_detail;
	}

	
	@Override
	public String toString() {
		return "InfectControlDetailResponse [completed_details=" + completed_details + ", in_completed_details="
				+ in_complete_detail + "]";
	}

}
