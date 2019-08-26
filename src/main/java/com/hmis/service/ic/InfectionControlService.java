package com.hmis.service.ic;

import com.hmis.model.MstUsers;
import com.hmis.model.trn_infect_control_device_hdr;
import com.hmis.response.ic.GetIcBundlesResponse;
import com.hmis.response.ic.GetIcDevicesResponse;
import com.hmis.response.ic.InfectControlDetailResponse;
import com.hmis.response.ic.InfectionControlSaveResponse;
import com.hmis.response.ic.LoginResponse;

public interface InfectionControlService {
	LoginResponse login(MstUsers mstUsers);

	GetIcDevicesResponse get_ic_devices();

	InfectControlDetailResponse get_infectDetails(Integer device_id, Integer visit_id);

	GetIcBundlesResponse get_ic_bundle(Integer device_id);

	InfectionControlSaveResponse saveInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr);

	InfectionControlSaveResponse updateInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr,
			Integer hdr_id);

}
