package com.ovs.service.voter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ovs.dao.voter.InfectionControlDAO;
import com.ovs.model.Employee;
import com.ovs.model.MstUsers;
import com.ovs.response.EmployeeResponse;
import com.ovs.response.LoginResponse;

@Service
@Transactional
public class InfectionControlServiceImpl implements InfectionControlService {

	@Autowired
	InfectionControlDAO infectionControlDAO;

	@Override
	public LoginResponse login(MstUsers mstUsers) {
		MstUsers result = infectionControlDAO.login(mstUsers);
		LoginResponse response = new LoginResponse();
		System.out.println(result);
		if (result != null) {
			infectionControlDAO.token_generation(result.getId());
			String token = infectionControlDAO.token_generation(result.getId());

			response.setStatus(1);
			response.setMessage("Success");
			response.setToken(token);
			response.setName(result.getDisplayname());
//			response.setDesignation(result.get);
		} else {
			response.setStatus(0);
			response.setMessage("Invalid username and password");
		}
		return response;
	}

	@Override
	public void addEmployee() {
		// TODO Auto-generated method stub
		infectionControlDAO.addEmployee();
	}

	@Override
	public List<EmployeeResponse> employeeList() {
		// TODO Auto-generated method stub
		return infectionControlDAO.employeeList();
	}

	


}
