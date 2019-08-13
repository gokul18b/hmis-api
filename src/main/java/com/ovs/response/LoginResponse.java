package com.ovs.response;

public class LoginResponse {

	private int status;
	private String message;
	private String token;
	private String name;
	private String designation="";
	private String proile_image="";

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

	@Override
	public String toString() {
		return "LoginResponse [status=" + status + ", message=" + message + ", token=" + token + ", name=" + name
				+ ", designation=" + designation + ", proile_image=" + proile_image + "]";
	}

	

}
