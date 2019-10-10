package com.hmis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmis.entity.TrnIcOtFollowup;
import com.hmis.entity.TrnOtBookingRequests;
import com.hmis.response.GetOtRequestPatientResponse;
import com.hmis.response.SaveResponse;
import com.hmis.service.OTRequestService;

@RestController
@RequestMapping("/ot_request")
public class OTRequestController {

	@Autowired
	OTRequestService otRequestService;

	@GetMapping("/get_ot_request_patients")
	public ResponseEntity<List<GetOtRequestPatientResponse>> get_ot_request_patients(){
		return ResponseEntity.ok().body(otRequestService.get_ot_request_patients());
	}
	
	@PutMapping("/save_ot_request")
	public ResponseEntity<SaveResponse> save_ot_request(@RequestBody TrnIcOtFollowup trnIcOtFollowup){
		System.out.println("tyets");
		return ResponseEntity.ok().body(otRequestService.save_ot_request(trnIcOtFollowup));
	}
	
}
