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

import com.hmis.entity.MstActiveMedicalAudit;
import com.hmis.request.nabh.savemedicalchecklist.SaveActiveMedicalRecordRequest;
import com.hmis.response.FinalResponse;
import com.hmis.response.check_preious_entry.CheckPreviousEntryResponse;
import com.hmis.response.get_audit_summary.GetAuditSummary;
import com.hmis.service.ActiveMedicalRecordService;

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
	
	@GetMapping("/check_preious_entry/{visit_id}")
	public ResponseEntity<CheckPreviousEntryResponse> checkPreviousEntry(@PathVariable Integer visit_id){
		CheckPreviousEntryResponse response = activeMedicalRecordService.checkPreviousEntry(visit_id);
		return ResponseEntity.ok().body(response);
	}
	
	
	@GetMapping("/get_audit_summary/{from_date}/{to_date}")
	public ResponseEntity<List<GetAuditSummary>> get_summary_report(@PathVariable String from_date,@PathVariable String to_date) {
		return ResponseEntity.ok().body(activeMedicalRecordService.get_audit_summary(from_date,to_date));
	}
	
	
	
	

}
