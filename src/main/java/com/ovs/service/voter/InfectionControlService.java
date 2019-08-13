package com.ovs.service.voter;

import com.ovs.model.MstUsers;
import com.ovs.response.LoginResponse;

public interface InfectionControlService {
	LoginResponse login(MstUsers mstUsers);
}
