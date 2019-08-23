package com.hmis.dao.ic;

import java.util.List;

import com.hmis.model.MstInfectControlDevices;
import com.hmis.model.MstUsers;
import com.hmis.model.trn_infect_control_device_hdr;
import com.hmis.response.ic.InfectDetails;

public interface InfectionControlDAO {
	List<?> login(MstUsers mstUsers);

	String token_generation(Integer id);

	List<MstInfectControlDevices> get_ic_devices();

	List<trn_infect_control_device_hdr> get_complete_ic(Integer device_id, Integer visit_if);

	trn_infect_control_device_hdr get_incomplete_ic(Integer device_id, Integer visit_id);

	List<Object[]> get_ic_bundle(Integer device_id);

	Integer saveInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr);

	Integer updateInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr, Integer hdr_id);

}
