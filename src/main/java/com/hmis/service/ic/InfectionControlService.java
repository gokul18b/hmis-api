package com.hmis.service.ic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmis.dao.ic.InfectionControlDAO;
import com.hmis.entity.MstInfectControlBundle;
import com.hmis.entity.MstInfectControlDevices;
import com.hmis.entity.MstUsers;
import com.hmis.entity.trn_infect_control_device_hdr;
import com.hmis.response.GetIcBundlesResponse;
import com.hmis.response.GetIcDevicesResponse;
import com.hmis.response.InfectControlDetailResponse;
import com.hmis.response.InfectionControlSaveResponse;
import com.hmis.response.LoginResponse;
import com.hmis.response.getBundleReport.DailyBundleReport;
import com.hmis.response.getBundleReport.GetBundleReport;
import com.hmis.response.getBundleReport.InsertionBundleReport;
import com.hmis.response.get_ic_summary.InfectionDeviceSummary;
import com.hmis.response.get_ic_summary.InfectionDeviceSummaryDetails;
import com.hmis.response.ic_patient_report.DailyBundle;
import com.hmis.response.ic_patient_report.DailyBundleDetails;
import com.hmis.response.ic_patient_report.IcPatientReport;
import com.hmis.response.ic_patient_report.InsertionBundle;
import com.hmis.response.ic_patient_report.InsertionBundleDetails;

@Service
@Transactional
public class InfectionControlService {

	@Autowired
	InfectionControlDAO infectionControlDAO;

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

	public GetIcBundlesResponse get_ic_bundles_daily(Integer device_id) {
		GetIcBundlesResponse getIcBundlesResponse = new GetIcBundlesResponse();
		List<MstInfectControlBundle> infectionControlBundles = new ArrayList<>();
		List<Object[]> results = infectionControlDAO.get_ic_bundle_daily(device_id);
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

	public InfectionControlSaveResponse updateInfectionControl(
			trn_infect_control_device_hdr trn_infect_control_device_hdr, Integer hdr_id) {

		InfectionControlSaveResponse response = new InfectionControlSaveResponse();
		Integer Daoresponse = infectionControlDAO.updateInfectionControl(trn_infect_control_device_hdr, hdr_id);

		if (Daoresponse == 1) {
			response.setStatus(1);
			response.setMessage("Successfully updated");
		} else {
			response.setStatus(0);
			response.setMessage("Something went wrong");
		}
		return response;
	}

	public InfectControlDetailResponse get_infectDetails(Integer device_id, Integer visit_id) {
		InfectControlDetailResponse response = new InfectControlDetailResponse();

		List<trn_infect_control_device_hdr> getting_completed_list = infectionControlDAO.get_complete_ic(device_id,
				visit_id);
		trn_infect_control_device_hdr getting_incompleted = infectionControlDAO.get_incomplete_ic(device_id, visit_id);

		if (getting_incompleted != null) {
			trn_infect_control_device_hdr incomplete_response = new trn_infect_control_device_hdr();
			incomplete_response = new trn_infect_control_device_hdr(getting_incompleted.getId(),
					getting_incompleted.getMst_infect_control_device_id(), getting_incompleted.getVisit_id(),
//					getting_incompleted.getInfect_control_date(),
					getting_incompleted.getInsertion_date(), getting_incompleted.getRemoval_date());
			response.setInsert_status("update");
			response.setIn_complete_detail(incomplete_response);
		} else {
			response.setInsert_status("insert");
		}

		if (getting_completed_list != null && getting_completed_list.size() != 0) {
			List<trn_infect_control_device_hdr> complete_responseList = new ArrayList<>();

			for (trn_infect_control_device_hdr row : getting_completed_list) {
				complete_responseList.add(new trn_infect_control_device_hdr(row.getId(),
						row.getMst_infect_control_device_id(), row.getVisit_id(),
//						row.getInfect_control_date(), 
						row.getInsertion_date(), row.getRemoval_date()));

			}

			response.setCompleted_details(complete_responseList);

		}
		return response;

	}

	public IcPatientReport getInsertionDailyReport(Integer hdr_id) {
		IcPatientReport icPatientReport = new IcPatientReport();
		InsertionBundle insertionBundle = infectionControlDAO.getInserionReport(hdr_id);
		List<DailyBundle> dailyBundle = infectionControlDAO.getDailyReport(hdr_id);
		icPatientReport.setInsertionBundles(insertionBundle);
		icPatientReport.setDailyBundles(dailyBundle);
		return icPatientReport;
	}

	public List<InfectionDeviceSummary> getIcSummary(String from_date, String to_date) {

		List<Object[]> getIcSummaryList = infectionControlDAO.getIcSummary(from_date, to_date);

		List<InfectionDeviceSummary> infectionDeviceSummaryList = new ArrayList<>();

		ArrayList<String> floor_name_list = new ArrayList<>();
		ArrayList<Integer> floor_id_list = new ArrayList<>();

		for (Object[] row : getIcSummaryList) {
			Integer floor_id = (Integer) row[1];
			String floor_name = (String) row[2];

			if (floor_name_list.size() != 0) {
				if (!floor_name_list.contains(floor_name)) {
					floor_name_list.add(floor_name);
					floor_id_list.add(floor_id);
				}
			} else {
				floor_name_list.add(floor_name);
				floor_id_list.add(floor_id);
			}

		}

		for (int i = 0; i < floor_id_list.size(); i++) {
			Integer floor_id = floor_id_list.get(i);
			String floor_name = floor_name_list.get(i);
			InfectionDeviceSummary infectionDeviceSummary = new InfectionDeviceSummary();
			infectionDeviceSummary.setFloor_id(floor_id);
			infectionDeviceSummary.setFloor_name(floor_name);

			List<InfectionDeviceSummaryDetails> infectionDeviceSummaryDetailsList = new ArrayList<>();
			for (Object[] row : getIcSummaryList) {
				Integer flr_id = (Integer) row[1];
				String device_name = (String) row[3];
				Double count = (Double) row[4];

				if (floor_id == flr_id) {
					InfectionDeviceSummaryDetails infectionDeviceSummaryDetails = new InfectionDeviceSummaryDetails();
					infectionDeviceSummaryDetails.setDevice_name(device_name);
					infectionDeviceSummaryDetails.setCount(count);
					infectionDeviceSummaryDetailsList.add(infectionDeviceSummaryDetails);
				}

			}
			infectionDeviceSummary.setDetails(infectionDeviceSummaryDetailsList);
			infectionDeviceSummaryList.add(infectionDeviceSummary);

		}

		return infectionDeviceSummaryList;

	}

	public GetBundleReport get_bundle_report(String from_date, String to_date) {
		List<Object[]> obj_insertion_bundle = infectionControlDAO.getInsertionBundleSummary(from_date,to_date);
		List<Object[]> obj_daily_bundle = infectionControlDAO.getDailyBundleSummary(from_date,to_date);
		GetBundleReport getBundleReport = new GetBundleReport();
		
		List<InsertionBundleReport> insertion_report_list= new ArrayList<>();  
		for (Object[] row : obj_insertion_bundle) {
			String ward_name = (String) row[0];
			BigInteger count = (BigInteger) row[1];
			InsertionBundleReport insertionBundleReport = new InsertionBundleReport();
			insertionBundleReport.setWard_name(ward_name);
			insertionBundleReport.setCount(count);
			insertion_report_list.add(insertionBundleReport);
		}
		getBundleReport.setInsertion_bundle(insertion_report_list);
		
		List<DailyBundleReport> daily_report_list= new ArrayList<>();  
		for (Object[] row : obj_daily_bundle) {
			String ward_name = (String) row[0];
			BigInteger count = (BigInteger) row[1];
			DailyBundleReport dailyBundleReport = new DailyBundleReport();
			dailyBundleReport.setWard_name(ward_name);
			dailyBundleReport.setCount(count);
			daily_report_list.add(dailyBundleReport);
		}
		getBundleReport.setDaily_bundle(daily_report_list);
		return getBundleReport;
	}
}
