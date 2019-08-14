package com.ovs.response;

public class LoginResponse {

	private int status;
	private String message;
	private String token;
	private String name;
	private String designation="";
	private String proile_image="";
	
	
	private String displayname;
	private String app_login_token;
	private String mst_designation_id;


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String display_name) {
		this.name = display_name;
	}
	
	

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getProile_image() {
		return proile_image;
	}

	public void setProile_image(String proile_image) {
		this.proile_image = proile_image;
	}
	
	
	

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getApp_login_token() {
		return app_login_token;
	}

	public void setApp_login_token(String app_login_token) {
		this.app_login_token = app_login_token;
	}

	public String getMst_designation_id() {
		return mst_designation_id;
	}

	public void setMst_designation_id(String mst_designation_id) {
		this.mst_designation_id = mst_designation_id;
	}

	@Override
	public String toString() {
		return "LoginResponse [status=" + status + ", message=" + message + ", token=" + token + ", name=" + name
				+ ", designation=" + designation + ", proile_image=" + proile_image + "]";
	}

	

}
