package com.hmis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmis.entity.MstAntibiotics;
import com.hmis.entity.MstUsers;
import com.hmis.entity.TrnIcAntibiotics;
import com.hmis.entity.TrnInfectControlDeviceHdr;
import com.hmis.response.GetIcBundlesResponse;
import com.hmis.response.GetIcDevicesResponse;
import com.hmis.response.InfectControlDetailResponse;
import com.hmis.response.InfectionControlSaveResponse;
import com.hmis.response.LoginResponse;
import com.hmis.response.SaveResponse;
import com.hmis.response.getBundleReport.GetBundleReport;
import com.hmis.response.get_ic_summary.InfectionDeviceSummary;
import com.hmis.response.ic_patient_report.IcPatientReport;
import com.hmis.service.InfectionControlService;

@RestController
@RequestMapping("/infection")
public class InfectionControlController {

	@Autowired
	InfectionControlService infectionControlService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody MstUsers mstUsers) {
		LoginResponse loginResponse = infectionControlService.login(mstUsers);
		return ResponseEntity.ok().body(loginResponse);
	}

	@GetMapping("/get_ic_devices")
	public ResponseEntity<GetIcDevicesResponse> get_ic_devices() {
		return ResponseEntity.ok().body(infectionControlService.get_ic_devices());
	}

	@GetMapping("/get_ic_bundles/{device_id}")
	public ResponseEntity<GetIcBundlesResponse> get_ic_bundles(@PathVariable Integer device_id) {
		return ResponseEntity.ok().body(infectionControlService.get_ic_bundle(device_id));
	}

	@GetMapping("/get_ic_bundles_daily/{device_id}")
	public ResponseEntity<GetIcBundlesResponse> get_ic_bundles_daily(@PathVariable Integer device_id) {
		return ResponseEntity.ok().body(infectionControlService.get_ic_bundles_daily(device_id));
	}

	@PostMapping("/save_infection_control/{hdr_id}")
	public ResponseEntity<InfectionControlSaveResponse> save_infection_control(
			@RequestBody TrnInfectControlDeviceHdr trn_infect_control_device_hdr, @PathVariable Integer hdr_id) {
		InfectionControlSaveResponse response = null;
		System.out.println(trn_infect_control_device_hdr);
		response = infectionControlService.updateInfectionControl(trn_infect_control_device_hdr, hdr_id);

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/save_infection_control")
	public ResponseEntity<InfectionControlSaveResponse> save_infection_control1(
			@RequestBody TrnInfectControlDeviceHdr trn_infect_control_device_hdr) {
		InfectionControlSaveResponse response = null;
		System.out.println(trn_infect_control_device_hdr);
		response = infectionControlService.saveInfectionControl(trn_infect_control_device_hdr);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/get_infect_details/{device_id}/{visit_id}")
	public ResponseEntity<InfectControlDetailResponse> getInfectDetails(@PathVariable Integer device_id,
			@PathVariable Integer visit_id) {
		InfectControlDetailResponse response = infectionControlService.get_infectDetails(device_id, visit_id);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/get_report_insertion_daily/{hdr_id}")
	public ResponseEntity<IcPatientReport> get_report_insertion_daily(@PathVariable Integer hdr_id) {
		return ResponseEntity.ok().body(infectionControlService.getInsertionDailyReport(hdr_id));

	}

	@GetMapping("/get_ic_summary/{from_date}/{to_date}")
	public ResponseEntity<List<InfectionDeviceSummary>> get_ic_summary(@PathVariable String from_date , @PathVariable String to_date) {
		return ResponseEntity.ok().body(infectionControlService.getIcSummary(from_date,to_date));

	}
	
	@GetMapping("/get_bundle_report/{from_date}/{to_date}")
	public ResponseEntity<GetBundleReport> get_bundle_report(@PathVariable String from_date , @PathVariable String to_date){
		return ResponseEntity.ok().body(infectionControlService.get_bundle_report(from_date,to_date));
	}
	
	
	
}
