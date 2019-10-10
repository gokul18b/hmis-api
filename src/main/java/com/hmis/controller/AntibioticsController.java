package com.hmis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hmis.dao.AntibioticsDao;
import com.hmis.entity.MstAntibiotics;
import com.hmis.entity.MstSubAntibiotics;
import com.hmis.entity.TrnIcAntibiotics;
import com.hmis.response.SaveResponse;
import com.hmis.response.getAntibioticsStatus.GetCompletedIncompletedAntibiotics;
import com.hmis.service.AntibioticsService;
import com.hmis.service.InfectionControlService;

@RestController
@RequestMapping("/antibiotics")
public class AntibioticsController {
	
	@Autowired
	AntibioticsService service;
	
	@GetMapping("/get_antibiotics")
	public ResponseEntity<List<MstAntibiotics>> get_antibiotics(){
		List<MstAntibiotics> response = service.get_antibiotics();
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/get_sub_antibiotics/{antibiotics_id}")
	public ResponseEntity<List<MstSubAntibiotics>> get_sub_antibiotics(@PathVariable("antibiotics_id") Integer antibiotics_id){
		List<MstSubAntibiotics> response = service.get_sub_antibiotics(antibiotics_id);
		return ResponseEntity.ok().body(response);
	}	
	
	@PostMapping("/saveOrUpdate_antibiotics")
	public ResponseEntity<SaveResponse> saveOrUpdateAntibiotics(@RequestBody TrnIcAntibiotics trnIcAntibiotics){
		SaveResponse response = new SaveResponse();
		response = service.saveOrUpdateAntibiotics(trnIcAntibiotics);
		return ResponseEntity.ok().body(response);
	}
	

	@GetMapping("/getAntibioticsStatus")
	public ResponseEntity<GetCompletedIncompletedAntibiotics> getAntibioticsStatus(@RequestParam("visit_id") Integer visit_id,@RequestParam("antibiotic_id")  Integer antibiotic_id,@RequestParam("sub_antibiotic_id")  Integer sub_antibiotic_id){
		return ResponseEntity.ok().body(service.getAntibioticsStatus(visit_id, antibiotic_id, sub_antibiotic_id));
	}
	
	

	
	
}
