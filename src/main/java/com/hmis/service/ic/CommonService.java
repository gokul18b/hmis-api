package com.hmis.service.ic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmis.dao.ic.CommonDao;
import com.hmis.dao.ic.InfectionControlDAO;
import com.hmis.entity.MstUsers;
import com.hmis.response.LoginResponse;

@Service
@Transactional
public class CommonService {

	@Autowired
	CommonDao commonDao;

	@Autowired
	InfectionControlDAO infectionControlDAO;

	public LoginResponse login(MstUsers mstUsers) {
		LoginResponse login_response = new LoginResponse();
		List<Object[]> resultObjectList = commonDao.login(mstUsers);
		if (resultObjectList != null) {
			if (resultObjectList.size() != 0) {
				Object[] resultObject = resultObjectList.get(0);

				Integer id = (Integer) resultObject[0];
				String display_name = (String) resultObject[1];
				String role_name = (String) resultObject[2];

				login_response.setStatus(1);
				login_response.setMessage("Login success");
				login_response.setApp_login_token(infectionControlDAO.token_generation(id));
				login_response.setDisplayname(display_name);
				login_response.setRole_name(role_name);
			}else {
				login_response.setStatus(0);
				login_response.setMessage("Invalid username or password");
			}
			return login_response;
		} else {
			return null;
		}

	}
}
