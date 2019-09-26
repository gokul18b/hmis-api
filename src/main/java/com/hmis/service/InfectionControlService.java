package com.hmis.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmis.dao.InfectionControlDAO;
import com.hmis.entity.MstAntibiotics;
import com.hmis.entity.MstInfectControlBundle;
import com.hmis.entity.MstInfectControlDevices;
import com.hmis.entity.MstUsers;
import com.hmis.entity.TrnIcAntibiotics;
import com.hmis.entity.TrnInfectControlDeviceHdr;

import com.hmis.response.GetIcBundlesResponse;
import com.hmis.response.GetIcDevicesResponse;
import com.hmis.response.InfectControlDetailResponse;
import com.hmis.response.InfectionControlSaveResponse;
import com.hmis.response.LoginResponse;
import com.hmis.response.SaveResponse;
import com.hmis.response.getBundleReport.DailyBundleDetails;
import com.hmis.response.getBundleReport.DailyBundleReport;
import com.hmis.response.getBundleReport.GetBundleReport;
import com.hmis.response.getBundleReport.InsertionBundleDetails;
import com.hmis.response.getBundleReport.InsertionBundleReport;
import com.hmis.response.get_ic_summary.InfectionDeviceSummary;
import com.hmis.response.get_ic_summary.InfectionDeviceSummaryDetails;
import com.hmis.response.ic_patient_report.DailyBundle;
import com.hmis.response.ic_patient_report.IcPatientReport;
import com.hmis.response.ic_patient_report.InsertionBundle;

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

	public InfectionControlSaveResponse saveInfectionControl(TrnInfectControlDeviceHdr trn_infect_control_device_hdr) {
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

	public InfectionControlSaveResponse updateInfectionControl(TrnInfectControlDeviceHdr trn_infect_control_device_hdr,
			Integer hdr_id) {

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

		List<TrnInfectControlDeviceHdr> getting_completed_list = infectionControlDAO.get_complete_ic(device_id,
				visit_id);
		TrnInfectControlDeviceHdr getting_incompleted = infectionControlDAO.get_incomplete_ic(device_id, visit_id);

		if (getting_incompleted != null) {
			TrnInfectControlDeviceHdr incomplete_response = new TrnInfectControlDeviceHdr();
			incomplete_response = new TrnInfectControlDeviceHdr(getting_incompleted.getId(),
					getting_incompleted.getMst_infect_control_device_id(), getting_incompleted.getVisit_id(),
//					getting_incompleted.getInfect_control_date(),
					getting_incompleted.getInsertion_date(), getting_incompleted.getRemoval_date());
			response.setInsert_status("update");
			response.setIn_complete_detail(incomplete_response);
		} else {
			response.setInsert_status("insert");
		}

		if (getting_completed_list != null && getting_completed_list.size() != 0) {
			List<TrnInfectControlDeviceHdr> complete_responseList = new ArrayList<>();

			for (TrnInfectControlDeviceHdr row : getting_completed_list) {
				complete_responseList.add(new TrnInfectControlDeviceHdr(row.getId(),
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
		GetBundleReport getBundleReport = new GetBundleReport();
		List<Object[]> obj_insertion_bundle = infectionControlDAO.getInsertionBundleSummary(from_date, to_date);

		ArrayList<Integer> floorIdList = new ArrayList<>();
		ArrayList<String> floorNameList = new ArrayList<>();

		for (Object[] row : obj_insertion_bundle) {
			Integer floor_id = (Integer) row[0];
			String floor_name = (String) row[1];

			if (floorIdList.size() == 0) {
				floorIdList.add(floor_id);
				floorNameList.add(floor_name);
			}

			if (!floorIdList.contains(floor_id)) {
				floorIdList.add(floor_id);
				floorNameList.add(floor_name);
			}
		}

		List<InsertionBundleReport> insertionBundleReportList = new ArrayList<>();

		for (int i = 0; i < floorIdList.size(); i++) {
			InsertionBundleReport insertionBundleReport = new InsertionBundleReport();
			insertionBundleReport.setFloor_id(floorIdList.get(i));
			insertionBundleReport.setFloor_name(floorNameList.get(i));

			List<InsertionBundleDetails> insertionBundleDetailsList = new ArrayList<>();

			for (Object[] row : obj_insertion_bundle) {
				Integer floor_id = (Integer) row[0];
				String floor_name = (String) row[1];
				String device_name = (String) row[2];
				BigInteger total_count = (BigInteger) row[3];
				BigInteger issue_count = (BigInteger) row[4];

				if (floorIdList.get(i) == floor_id) {
					System.out.println("Floor_id" + floor_id + " -" + (i) + " -- " + floorIdList.get(i));
					InsertionBundleDetails insertionBundleDetails = new InsertionBundleDetails();
					insertionBundleDetails.setDevice_name(device_name);
					insertionBundleDetails.setTotal_count(total_count);
					insertionBundleDetails.setIssue_count(issue_count);
					insertionBundleDetailsList.add(insertionBundleDetails);

				}

			}
			insertionBundleReport.setDetails(insertionBundleDetailsList);

			insertionBundleReportList.add(insertionBundleReport);
		}

		getBundleReport.setInsertion_bundle(insertionBundleReportList);

//		DAILY BUNDLE REPORT
		List<Object[]> obj_daily_bundle = infectionControlDAO.getDailyBundleSummary(from_date, to_date);
		ArrayList<Integer> floorIdList_daily = new ArrayList<>();
		ArrayList<String> floorNameList_daily = new ArrayList<>();

		for (Object[] row : obj_daily_bundle) {
			Integer floor_id = (Integer) row[0];
			String floor_name = (String) row[1];

			if (floorIdList_daily.size() == 0) {
				floorIdList_daily.add(floor_id);
				floorNameList_daily.add(floor_name);
			}

			if (!floorIdList_daily.contains(floor_id)) {
				floorIdList_daily.add(floor_id);
				floorNameList_daily.add(floor_name);
			}
		}

		List<DailyBundleReport> dailyBundleReportList = new ArrayList<>();

		for (int i = 0; i < floorIdList_daily.size(); i++) {
			DailyBundleReport dailyBundleReport = new DailyBundleReport();
			dailyBundleReport.setFloor_id(floorIdList_daily.get(i));
			dailyBundleReport.setFloor_name(floorNameList_daily.get(i));

			List<DailyBundleDetails> dailyBundleDetailsList = new ArrayList<>();

			for (Object[] row : obj_daily_bundle) {
				Integer floor_id = (Integer) row[0];
				String floor_name = (String) row[1];
				String device_name = (String) row[2];
				BigInteger total_count = (BigInteger) row[3];
				BigInteger issue_count = (BigInteger) row[4];

				if (floorIdList_daily.get(i) == floor_id) {
					System.out.println("Floor_id" + floor_id + " -" + (i) + " -- " + floorIdList_daily.get(i));
					DailyBundleDetails dailyBundleDetails = new DailyBundleDetails();
					dailyBundleDetails.setDevice_name(device_name);
					dailyBundleDetails.setTotal_count(total_count);
					dailyBundleDetails.setIssue_count(issue_count);
					dailyBundleDetailsList.add(dailyBundleDetails);

				}

			}
			dailyBundleReport.setDetails(dailyBundleDetailsList);

			dailyBundleReportList.add(dailyBundleReport);
		}

		getBundleReport.setDaily_bundle(dailyBundleReportList);

		return getBundleReport;
	}

	public List<MstAntibiotics> get_antibiotics() {
		return infectionControlDAO.get_antibiotics();
	}

	public SaveResponse saveOrUpdateAntibiotics(TrnIcAntibiotics trnIcAntibiotics) {
		SaveResponse response = new SaveResponse();
		Object obj = infectionControlDAO.saveOrUpdateAntibiotics(trnIcAntibiotics);
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
}
