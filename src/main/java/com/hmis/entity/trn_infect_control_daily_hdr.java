package com.hmis.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "trn_infect_control_daily_hdr")
public class trn_infect_control_daily_hdr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "bundle_date")
	private Date bundle_date;

	@Column(name = "temperature")
	private String temperature;

	@Column(name = "unit")
	private String unit;

	@OneToMany(mappedBy = "trn_infect_control_daily_hdr")
	private List<trn_infect_control_bundle_details_daily> trn_infect_control_bundle_daily_details;

	@ManyToOne
	@JoinColumn(name = "trn_infect_control_device_hdr_id")
	private trn_infect_control_device_hdr trn_infect_control_device_hdr;

	public trn_infect_control_daily_hdr() {
		super();
	}

	public trn_infect_control_daily_hdr(Integer id, Date bundle_date, String temperature, String unit,
			List<trn_infect_control_bundle_details_daily> trn_infect_control_bundle_daily_details) {
		super();
		this.id = id;
		this.bundle_date = bundle_date;
		this.temperature = temperature;
		this.unit = unit;
		this.trn_infect_control_bundle_daily_details = trn_infect_control_bundle_daily_details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBundle_date() {
		return bundle_date;
	}

	public void setBundle_date(Date bundle_date) {
		this.bundle_date = bundle_date;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<trn_infect_control_bundle_details_daily> getTrn_infect_control_bundle_daily_details() {
		return trn_infect_control_bundle_daily_details;
	}

	public void setTrn_infect_control_bundle_daily_details(
			List<trn_infect_control_bundle_details_daily> trn_infect_control_bundle_daily_details) {
		this.trn_infect_control_bundle_daily_details = trn_infect_control_bundle_daily_details;
	}
	
	

	public trn_infect_control_device_hdr getTrn_infect_control_device_hdr() {
		return trn_infect_control_device_hdr;
	}

	public void setTrn_infect_control_device_hdr(trn_infect_control_device_hdr trn_infect_control_device_hdr) {
		this.trn_infect_control_device_hdr = trn_infect_control_device_hdr;
	}

	@Override
	public String toString() {
		return "trn_infect_control_daily_hdr [id=" + id + ", bundle_date=" + bundle_date + ", temperature="
				+ temperature + ", unit=" + unit + ", trn_infect_control_bundle_daily_details="
				+ trn_infect_control_bundle_daily_details + "]";
	}

	

}
