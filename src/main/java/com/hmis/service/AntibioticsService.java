package com.hmis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmis.dao.AntibioticsDao;
import com.hmis.entity.MstAntibiotics;
import com.hmis.entity.MstSubAntibiotics;
import com.hmis.entity.TrnIcAntibiotics;
import com.hmis.response.SaveResponse;
import com.hmis.response.getAntibioticsStatus.AntibioticDetails;
import com.hmis.response.getAntibioticsStatus.GetCompletedIncompletedAntibiotics;


@Service
@Transactional
public class AntibioticsService {

	@Autowired
	AntibioticsDao dao;
	
	public List<MstAntibiotics> get_antibiotics() {
		return dao.get_antibiotics();
	}
	
	public List<MstSubAntibiotics> get_sub_antibiotics(Integer antibiotics_id) {
		return dao.get_sub_antibiotics(antibiotics_id);
	}
	
	
	public SaveResponse saveOrUpdateAntibiotics(TrnIcAntibiotics trnIcAntibiotics) {
		SaveResponse response = new SaveResponse();
		Object obj = dao.saveOrUpdateAntibiotics(trnIcAntibiotics);
		if (obj == null) {
			response.setStatus(0);
			response.setMessage("Something went wrong");
		} else {
			response.setStatus(1);
			response.setMessage("Succesfully Saved...");
		}
		System.out.println(response);
		return response;
	}
	
	public GetCompletedIncompletedAntibiotics getAntibioticsStatus(Integer visit_id, Integer antibiotic_id, Integer sub_antibiotic_id) {
		GetCompletedIncompletedAntibiotics result = new GetCompletedIncompletedAntibiotics();
		
		AntibioticDetails inCompletedAntibiotics;
		List<AntibioticDetails> InCompletedAntibioticsList = new ArrayList<AntibioticDetails>();
		
		List<Object[]> InCompletedObj = dao.getInCompletedAntibiotics(visit_id, antibiotic_id, sub_antibiotic_id);
		if(InCompletedObj.size()!=0) {
			for(Object[] row:InCompletedObj) {
				
				AntibioticDetails IncompletedAntibiotics = new AntibioticDetails();
				IncompletedAntibiotics.setId((Integer)row[0]);
				IncompletedAntibiotics.setAntibiotics_name((String)row[1]);
				IncompletedAntibiotics.setSub_antibiotics_name((String)row[2]);
				IncompletedAntibiotics.setStart_time((Date)row[3]);
				IncompletedAntibiotics.setAntibiotic_id((Integer)row[5]);
				IncompletedAntibiotics.setSub_antibiotic_id((Integer)row[6]);
				
				
				
				InCompletedAntibioticsList.add(IncompletedAntibiotics);
			}
		}
		result.setIncompletedAntibiotics(InCompletedAntibioticsList);
			
		List<AntibioticDetails> completedAntibioticsList = new ArrayList<AntibioticDetails>();
		List<Object[]> completedObj = dao.getCompletedAntibiotics(visit_id, antibiotic_id, sub_antibiotic_id);
		if(completedObj.size()!=0) {
			for(Object[] row:completedObj) {
				
				AntibioticDetails completedAntibiotics = new AntibioticDetails();
				completedAntibiotics.setId((Integer)row[0]);
				completedAntibiotics.setAntibiotics_name((String)row[1]);
				completedAntibiotics.setSub_antibiotics_name((String)row[2]);
				completedAntibiotics.setStart_time((Date)row[3]);
				completedAntibiotics.setEnd_time((Date)row[4]);
				
				System.out.println(completedAntibiotics);
				
				completedAntibioticsList.add(completedAntibiotics);
			}
		}
		result.setCompletedAntibiotics(completedAntibioticsList);
		
		return result;
	}
	
	
	
}
