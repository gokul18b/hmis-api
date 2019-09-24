package com.hmis.service.ic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmis.dao.ic.ActiveMedicalRecordDao;
import com.hmis.entity.MstActiveMedicalAudit;
import com.hmis.entity.TrnActiveMedicalAudit;
import com.hmis.request.nabh.savemedicalchecklist.SaveActiveMedicalRecordRequest;
import com.hmis.response.FinalResponse;
import com.hmis.response.check_preious_entry.CheckPreviousEntryResponse;
import com.hmis.response.check_preious_entry.TimeDetails;

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

	public CheckPreviousEntryResponse checkPreviousEntry(Integer visit_id) {
		List<TrnActiveMedicalAudit> listObj = activeMedicalRecordDao.checkPreviousEntry(visit_id);

		CheckPreviousEntryResponse response = new CheckPreviousEntryResponse();
		if (listObj == null || listObj.size() == 0) {
			response.setStatus(1);
			response.setMessage("No Data found");
		} else {
			response.setStatus(0);
			
			String message = "Active Medical Record Audit Checklist already taken on ";
			
			for(TrnActiveMedicalAudit obj :listObj) {
				 
			            Date initDate = null;
			            try {
			                initDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(String.valueOf(obj.getCreated_date()));

			                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			                String date = formatter.format(initDate);
			                
			                message = message +"\n"+date;
			            } catch (ParseException e) {
			                e.printStackTrace();
			            }
			        
				
				
			}
			response.setMessage(message);
			
			List<Object[]> responseList = (List<Object[]>) activeMedicalRecordDao.getPreviousTime(visit_id);
			List<TimeDetails> timeDetailsList = new ArrayList<TimeDetails>();

			if (responseList.size() != 0) {
				for (Object[] responseObj : responseList) {
					TimeDetails timeDetails = new TimeDetails();
					timeDetails.setQuestion_id((Integer) responseObj[0]);
					timeDetails.setMinutes((Integer) responseObj[1]);
					timeDetails.setOption_value((String) responseObj[2]);

					timeDetailsList.add(timeDetails);
				}
			}

			response.setTimeDetails(timeDetailsList);
		}

		return response;
	}

	public void getActiveMedicalSummary() {
		//activeMedicalRecordDao.getActiveMedicalSummary();
		
	}
}
