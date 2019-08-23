package com.ovs.service.voter;

import com.ovs.model.MstUsers;
import com.ovs.model.trn_infect_control_device_hdr;
import com.ovs.response.GetIcBundlesResponse;
import com.ovs.response.GetIcDevicesResponse;
import com.ovs.response.InfectControlDetailResponse;
import com.ovs.response.InfectionControlSaveResponse;
import com.ovs.response.LoginResponse;

public interface InfectionControlService {
	LoginResponse login(MstUsers mstUsers);

	GetIcDevicesResponse get_ic_devices();

	InfectControlDetailResponse get_infectDetails(Integer device_id,Integer visit_id);

	GetIcBundlesResponse get_ic_bundle(Integer device_id);

	InfectionControlSaveResponse saveInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr);
	InfectionControlSaveResponse updateInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr,Integer hdr_id);
	

}
