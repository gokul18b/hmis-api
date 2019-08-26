package com.hmis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmis.model.MstUsers;
import com.hmis.model.trn_infect_control_device_hdr;
import com.hmis.response.ic.GetIcBundlesResponse;
import com.hmis.response.ic.GetIcDevicesResponse;
import com.hmis.response.ic.InfectControlDetailResponse;
import com.hmis.response.ic.InfectDetails;
import com.hmis.response.ic.InfectionControlSaveResponse;
import com.hmis.response.ic.LoginResponse;
import com.hmis.service.ic.InfectionControlService;

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

	@PostMapping("/save_infection_control/{hdr_id}")
	public ResponseEntity<InfectionControlSaveResponse> save_infection_control(
			@RequestBody trn_infect_control_device_hdr trn_infect_control_device_hdr,
			@PathVariable Integer hdr_id) {
		InfectionControlSaveResponse response = null;
		
		response = infectionControlService.updateInfectionControl(trn_infect_control_device_hdr, hdr_id);
		

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/save_infection_control")
	public ResponseEntity<InfectionControlSaveResponse> save_infection_control1(
			@RequestBody trn_infect_control_device_hdr trn_infect_control_device_hdr) {
		InfectionControlSaveResponse response = null;
		response = infectionControlService.saveInfectionControl(trn_infect_control_device_hdr);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/get_infect_details/{device_id}/{visit_id}")
	public ResponseEntity<InfectControlDetailResponse> getInfectDetails(@PathVariable Integer device_id,
			@PathVariable Integer visit_id) {
		System.out.println(visit_id);
		return ResponseEntity.ok().body(infectionControlService.get_infectDetails(device_id, visit_id));
	}
}
