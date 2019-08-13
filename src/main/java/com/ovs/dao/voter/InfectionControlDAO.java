package com.ovs.dao.voter;


import com.ovs.model.MstUsers;

public interface InfectionControlDAO {
	MstUsers login(MstUsers mstUsers);

	String token_generation(int id);
	
//	Employee employee();
}
