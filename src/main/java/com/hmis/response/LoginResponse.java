package com.hmis.response;

public class LoginResponse {

	private int status;
	private int userref_id;
	private String message;
	private String proile_image = "";
	private String displayname;
	private String app_login_token;
	private String design_name;
	private String role_name;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	public int getUserref_id() {
		return userref_id;
	}

	public void setUserref_id(int userref_id) {
		this.userref_id = userref_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getDesign_name() {
		return design_name;
	}

	public void setDesign_name(String design_name) {
		this.design_name = design_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	

}
