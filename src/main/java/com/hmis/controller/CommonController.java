package com.hmis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmis.entity.MstUsers;
import com.hmis.response.LoginResponse;
import com.hmis.service.CommonService;

@RestController
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	CommonService commonService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody MstUsers mstUsers) {
		LoginResponse loginResponse = commonService.login(mstUsers);
		return ResponseEntity.ok().body(loginResponse);
	}
}
