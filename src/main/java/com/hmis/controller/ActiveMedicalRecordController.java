package com.hmis.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.hmis.entity.MstActiveMedicalAudit;
import com.hmis.request.nabh.savemedicalchecklist.SaveActiveMedicalRecordRequest;
import com.hmis.response.FinalResponse;
import com.hmis.response.SaveResponse;
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

//	@PostMapping("/save_checklist")
//	public ResponseEntity<FinalResponse> save_checklist(
//			@RequestBody SaveActiveMedicalRecordRequest saveActiveMedicalRecordRequest) {
//		FinalResponse response = activeMedicalRecordService.save_checklist(saveActiveMedicalRecordRequest);
//		return ResponseEntity.ok().body(response);
//	}

	@GetMapping("/check_preious_entry/{visit_id}")
	public ResponseEntity<CheckPreviousEntryResponse> checkPreviousEntry(@PathVariable Integer visit_id) {
		CheckPreviousEntryResponse response = activeMedicalRecordService.checkPreviousEntry(visit_id);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/get_audit_summary/{from_date}/{to_date}")
	public ResponseEntity<List<GetAuditSummary>> get_summary_report(@PathVariable String from_date,
			@PathVariable String to_date) {
		return ResponseEntity.ok().body(activeMedicalRecordService.get_audit_summary(from_date, to_date));
	}

	@PostMapping("/uploads")
	public String singleFileUploads(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {

	/*	if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		return "Upload success";
	}

	@PostMapping("/save_checklist")
	public ResponseEntity<FinalResponse> save_checklist(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("result") String result) {
		Gson g = new Gson();
		SaveActiveMedicalRecordRequest saveActiveMedicalRecordRequest = g.fromJson(result,
				SaveActiveMedicalRecordRequest.class);
		FinalResponse response = activeMedicalRecordService.save_checklist(saveActiveMedicalRecordRequest,files);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/rootPath")
	public String singleFileUpload() {
		String rootPath = System.getProperty("catalina.home")+File.separator +"webapps"+File.separator+"audit_images"+File.separator;
		return rootPath;

	}

}
