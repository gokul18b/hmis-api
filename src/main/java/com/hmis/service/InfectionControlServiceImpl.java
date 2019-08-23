package com.hmis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmis.dao.ic.InfectionControlDAO;
import com.hmis.model.MstInfectControlBundle;
import com.hmis.model.MstInfectControlDevices;
import com.hmis.model.MstUsers;
import com.hmis.model.trn_infect_control_device_hdr;
import com.hmis.response.ic.GetIcBundlesResponse;
import com.hmis.response.ic.GetIcDevicesResponse;
import com.hmis.response.ic.InfectControlDetailResponse;
import com.hmis.response.ic.InfectDetails;
import com.hmis.response.ic.InfectionControlSaveResponse;
import com.hmis.response.ic.LoginResponse;

@Service
@Transactional
public class InfectionControlServiceImpl implements InfectionControlService {

	@Autowired
	InfectionControlDAO infectionControlDAO;

	@Override
	public LoginResponse login(MstUsers mstUsers) {
		LoginResponse response = new LoginResponse();
		List<?> results = infectionControlDAO.login(mstUsers);

		if (results.size() != 0) {
			List<Object[]> resultObj = (List<Object[]>) results;
			for (Object[] result : resultObj) {
				Integer id = (Integer) result[0];
				String name = (String) result[1];
				String dept_name = (String) result[2];
				String design_name = (String) result[3];

				response.setStatus(1);
				response.setMessage("success");
				response.setApp_login_token(infectionControlDAO.token_generation(id));
				response.setDisplayname(name);
				response.setDesign_name(design_name);
			}
		} else {
			response.setStatus(0);
			response.setMessage("Invalid username and password");
		}

		return response;
	}

	@Override
	public GetIcDevicesResponse get_ic_devices() {
		GetIcDevicesResponse getIcDevicesResponse = new GetIcDevicesResponse();
		List<MstInfectControlDevices> infectionControlDevices = infectionControlDAO.get_ic_devices();
		if (infectionControlDevices.size() != 0) {
			getIcDevicesResponse.setStatus(1);
			getIcDevicesResponse.setResults(infectionControlDevices);
		} else {
			getIcDevicesResponse.setStatus(2);
		}
		return getIcDevicesResponse;
	}

	@Override
	public GetIcBundlesResponse get_ic_bundle(Integer device_id) {
		GetIcBundlesResponse getIcBundlesResponse = new GetIcBundlesResponse();
		List<MstInfectControlBundle> infectionControlBundles = new ArrayList<>();
		List<Object[]> results = infectionControlDAO.get_ic_bundle(device_id);
		if (results.size() != 0) {
			for (Object[] result : results) {
				Integer id = (Integer) result[0];
				String audit_name = (String) result[1];
				Integer infection_control_device_id = (Integer) result[2];

				infectionControlBundles.add(new MstInfectControlBundle(id, infection_control_device_id, audit_name));
			}
			getIcBundlesResponse.setResults(infectionControlBundles);
			getIcBundlesResponse.setStatus(1);
		} else {
			getIcBundlesResponse.setStatus(0);
		}
		return getIcBundlesResponse;
	}

	@Override
	public InfectionControlSaveResponse saveInfectionControl(
			trn_infect_control_device_hdr trn_infect_control_device_hdr) {
		InfectionControlSaveResponse response = new InfectionControlSaveResponse();
		Integer Daoresponse = infectionControlDAO.saveInfectionControl(trn_infect_control_device_hdr);

		if (Daoresponse == 1) {
			response.setStatus(1);
			response.setMessage("Successfully Saved");
		} else {
			response.setStatus(0);
			response.setMessage("Something went wrong");
		}
		return response;
	}

	@Override
	public InfectionControlSaveResponse updateInfectionControl(
			trn_infect_control_device_hdr trn_infect_control_device_hdr, Integer hdr_id) {
		
		InfectionControlSaveResponse response = new InfectionControlSaveResponse();
		Integer Daoresponse = infectionControlDAO.updateInfectionControl(trn_infect_control_device_hdr,hdr_id);
		
		if (Daoresponse == 1) {
			response.setStatus(1);
			response.setMessage("Successfully updated");
		} else {
			response.setStatus(0);
			response.setMessage("Something went wrong");
		}
		return response;
	}

	@Override
	public InfectControlDetailResponse get_infectDetails(Integer device_id, Integer visit_id) {
		InfectControlDetailResponse response = new InfectControlDetailResponse();

		List<trn_infect_control_device_hdr> getting_completed_list = infectionControlDAO.get_complete_ic(device_id,
				visit_id);
		trn_infect_control_device_hdr getting_incompleted = infectionControlDAO.get_incomplete_ic(device_id, visit_id);

		if (getting_incompleted != null) {
			trn_infect_control_device_hdr incomplete_response = new trn_infect_control_device_hdr();
			incomplete_response = new trn_infect_control_device_hdr(getting_incompleted.getId(),
					getting_incompleted.getMst_infect_control_device_id(), getting_incompleted.getVisit_id(),
					getting_incompleted.getInfect_control_date(), getting_incompleted.getTemperature(),
					getting_incompleted.getInsertion_date(), getting_incompleted.getRemoval_date());
			response.setInsert_status("update");
			response.setIn_completed_details(incomplete_response);
		} else {
			response.setInsert_status("insert");
		}

		if (getting_completed_list != null && getting_completed_list.size() != 0) {
			List<trn_infect_control_device_hdr> complete_responseList = new ArrayList<>();

			for (trn_infect_control_device_hdr row : getting_completed_list) {
				complete_responseList.add(new trn_infect_control_device_hdr(row.getId(),
						row.getMst_infect_control_device_id(), row.getVisit_id(), row.getInfect_control_date(),
						row.getTemperature(), row.getInsertion_date(), row.getRemoval_date()));
			}

			response.setCompleted_details(complete_responseList);

		}
		return response;

	}

}
