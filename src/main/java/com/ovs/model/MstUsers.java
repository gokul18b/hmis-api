package com.ovs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "mst_users")
public class MstUsers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private Integer userref_id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String user_type;
	@Column
	private String displayname;
	@Column
	private int status;
	@Column
	private int is_deleted;
	@Column
	private String mobile_device_id;
	@Column
	private String app_login_token;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Integer getUserref_id() {
		return userref_id;
	}

	public void setUserref_id(Integer userref_id) {
		this.userref_id = userref_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getMobile_device_id() {
		return mobile_device_id;
	}

	public void setMobile_device_id(String mobile_device_id) {
		this.mobile_device_id = mobile_device_id;
	}

	public String getApp_login_token() {
		return app_login_token;
	}

	public void setApp_login_token(String app_login_token) {
		this.app_login_token = app_login_token;
	}

	
	
	

}
