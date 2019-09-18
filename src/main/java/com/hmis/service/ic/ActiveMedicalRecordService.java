package com.hmis.service.ic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmis.dao.ic.ActiveMedicalRecordDao;
import com.hmis.entity.MstActiveMedicalAudit;
import com.hmis.request.nabh.savemedicalchecklist.SaveActiveMedicalRecordRequest;
import com.hmis.response.FinalResponse;

@Service
@Transactional
public class ActiveMedicalRecordService {

	@Autowired
	ActiveMedicalRecordDao activeMedicalRecordDao;

	public List<MstActiveMedicalAudit> get_questions() {
		List<MstActiveMedicalAudit> checkLists = activeMedicalRecordDao.get_questions();
		return checkLists;
	}

	public FinalResponse save_checklist(SaveActiveMedicalRecordRequest saveActiveMedicalRecordRequest) {
		// TODO Auto-generated method stub
		FinalResponse response = new FinalResponse();
		Integer status = activeMedicalRecordDao.save_checklist(saveActiveMedicalRecordRequest);
		response.setStatus(status);
		if (status == 1) {
			response.setMessage("Succesfully Saved...");
		} else {
			response.setMessage("Something went wrong...");
		}
		return response;
	}
}
