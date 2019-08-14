package com.ovs.service.voter;

import java.util.List;


import com.ovs.model.Employee;
import com.ovs.model.MstUsers;
import com.ovs.response.EmployeeResponse;
import com.ovs.response.LoginResponse;

public interface InfectionControlService {
	LoginResponse login(MstUsers mstUsers);

	void addEmployee();

	List<EmployeeResponse> employeeList();
}
