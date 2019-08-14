package com.ovs.dao.voter;


import java.util.List;

import com.ovs.model.Employee;
import com.ovs.model.MstUsers;
import com.ovs.response.EmployeeResponse;
import com.ovs.response.LoginResponse;

public interface InfectionControlDAO {
	MstUsers login(MstUsers mstUsers);

	String token_generation(int id);
	
	LoginResponse login_join(MstUsers mstUsers);
	
	void addEmployee();
	
	List<EmployeeResponse> employeeList();
}
