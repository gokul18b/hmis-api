package com.hmis.service.ic;

import com.hmis.entity.MstUsers;
import com.hmis.entity.trn_infect_control_device_hdr;
import com.hmis.response.GetIcBundlesResponse;
import com.hmis.response.GetIcDevicesResponse;
import com.hmis.response.InfectControlDetailResponse;
import com.hmis.response.InfectionControlSaveResponse;
import com.hmis.response.LoginResponse;

public interface InfectionControlService {
	LoginResponse login(MstUsers mstUsers);

	GetIcDevicesResponse get_ic_devices();

	InfectControlDetailResponse get_infectDetails(Integer device_id, Integer visit_id);

	GetIcBundlesResponse get_ic_bundle(Integer device_id);

	GetIcBundlesResponse get_ic_bundles_daily(Integer device_id);

	InfectionControlSaveResponse saveInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr);

	InfectionControlSaveResponse updateInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr,
			Integer hdr_id);

}
