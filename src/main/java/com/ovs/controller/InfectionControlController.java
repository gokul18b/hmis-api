package com.ovs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovs.model.Employee;
import com.ovs.model.MstUsers;
import com.ovs.model.Phone;
import com.ovs.response.EmployeeResponse;
import com.ovs.response.LoginResponse;
import com.ovs.service.voter.InfectionControlService;

@RestController
@RequestMapping("/infection")
public class InfectionControlController {

	@Autowired
	InfectionControlService infectionControlService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody MstUsers mstUsers) {
		LoginResponse loginResponse = infectionControlService.login(mstUsers);
		return ResponseEntity.ok().body(loginResponse);
	}

	@GetMapping("/test")
	public String login() {
		return "test";
	}

	@GetMapping("/add_employee")
	public String addEmployee() {
		infectionControlService.addEmployee();
		return "success";
	}

	@GetMapping("/get_employee")
	public ResponseEntity<List<EmployeeResponse>> get_employee() {
		
		return ResponseEntity.ok().body(infectionControlService.employeeList());
	}
}
