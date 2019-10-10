package com.hmis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmis.dao.OTRequestDao;
import com.hmis.entity.TrnIcOtFollowup;
import com.hmis.entity.TrnOtBookingRequests;
import com.hmis.response.GetOtRequestPatientResponse;
import com.hmis.response.SaveResponse;

@Service
@Transactional
public class OTRequestService {
	@Autowired
	OTRequestDao dao;
	
	public List<GetOtRequestPatientResponse> get_ot_request_patients(){
		List<Object[]> resultObject = dao.get_ot_request_patients();
		List<GetOtRequestPatientResponse> result = new ArrayList<GetOtRequestPatientResponse>();
		if(resultObject.size()!=0) {
			for(Object[] obj:resultObject) {
				GetOtRequestPatientResponse response_obj = new GetOtRequestPatientResponse();
				response_obj.setId((Integer)obj[0]);
				response_obj.setPatient_name((String)obj[1]);
				response_obj.setAge((Integer)obj[2]);
				response_obj.setGender((String)obj[3]);
				response_obj.setPatient_code((String)obj[4]);
				response_obj.setSurgery_name((String)obj[5]);
				response_obj.setSurgeon((String)obj[6]);
				response_obj.setAnaesthestist((String)obj[7]);
				response_obj.setSurgery_date_time((String)obj[8]);
				
				result.add(response_obj);
			}
		}
		return result;
	}
	
	public SaveResponse save_ot_request(TrnIcOtFollowup trnIcOtFollowup) {
		Integer status = dao.save_ot_request(trnIcOtFollowup);
		SaveResponse saveResponse = new SaveResponse();
		saveResponse.setStatus(status);
		if(status==1) {
			saveResponse.setMessage("Save Sucessfully Completed...");
		}else {
			saveResponse.setMessage("Something went wrong...");
		}
		
		return saveResponse;
	}
}
