package com.hmis.response.ic;

public class LoginResponse {

	private int status;
	private String message;
	private String proile_image = "";
	private String displayname;
	private String app_login_token;
	private String design_name;

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

}
