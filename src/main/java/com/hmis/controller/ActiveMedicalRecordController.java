package com.hmis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmis.entity.MstActiveMedicalAudit;
import com.hmis.request.nabh.savemedicalchecklist.SaveActiveMedicalRecordRequest;
import com.hmis.response.FinalResponse;
import com.hmis.service.ic.ActiveMedicalRecordService;

@RestController
@RequestMapping("/nabh_medical_record")
public class ActiveMedicalRecordController {

	@Autowired
	ActiveMedicalRecordService activeMedicalRecordService;

	@GetMapping("/get_questions")
	public ResponseEntity<List<MstActiveMedicalAudit>> get_questions() {
		List<MstActiveMedicalAudit> checkList = activeMedicalRecordService.get_questions();
		return ResponseEntity.ok(checkList);
	}
	
	@PostMapping("/save_checklist")
	public ResponseEntity<FinalResponse> save_checklist(@RequestBody SaveActiveMedicalRecordRequest saveActiveMedicalRecordRequest) {
		FinalResponse response = activeMedicalRecordService.save_checklist(saveActiveMedicalRecordRequest);
		return ResponseEntity.ok().body(response);
	}
	
	

}
