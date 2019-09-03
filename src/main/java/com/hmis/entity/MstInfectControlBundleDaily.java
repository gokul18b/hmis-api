package com.hmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "mst_infect_control_bundle_daily")
public class MstInfectControlBundleDaily {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "mst_infect_control_devices_id")
	private Integer mst_infect_control_devices_id;

	@Column(name = "bundle_name")
	private String bundle_name;

	public MstInfectControlBundleDaily() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MstInfectControlBundleDaily(Integer id, Integer mst_infect_control_devices_id, String bundle_name) {
		super();
		this.id = id;
		this.mst_infect_control_devices_id = mst_infect_control_devices_id;
		this.bundle_name = bundle_name;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMst_infect_control_devices_id() {
		return mst_infect_control_devices_id;
	}

	public void setMst_infect_control_devices_id(Integer mst_infect_control_devices_id) {
		this.mst_infect_control_devices_id = mst_infect_control_devices_id;
	}

	public String getBundle_name() {
		return bundle_name;
	}

	public void setBundle_name(String bundle_name) {
		this.bundle_name = bundle_name;
	}

	@Override
	public String toString() {
		return "InfectionControlBundle [id=" + id + ", mst_infect_control_devices_id=" + mst_infect_control_devices_id
				+ ", bundle_name=" + bundle_name + "]";
	}

}

