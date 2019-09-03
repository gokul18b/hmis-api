package com.hmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "trn_infect_control_bundle_details_daily")
public class trn_infect_control_bundle_details_daily {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "trn_infect_control_daily_hdr_id")
	private trn_infect_control_daily_hdr trn_infect_control_daily_hdr;

	@Column(name = "mst_infect_control_bundle_id")
	private Integer mst_infect_control_bundle_id;

	@Column(name = "option_value")
	private String option_value;

	@Column(name = "remarks")
	private String remarks;

	public trn_infect_control_bundle_details_daily() {
		super();
	}

	public trn_infect_control_bundle_details_daily(Integer id,
			com.hmis.entity.trn_infect_control_daily_hdr trn_infect_control_daily_hdr,
			Integer mst_infect_control_bundle_id, String option_value, String remarks) {
		super();
		this.id = id;
		this.trn_infect_control_daily_hdr = trn_infect_control_daily_hdr;
		this.mst_infect_control_bundle_id = mst_infect_control_bundle_id;
		this.option_value = option_value;
		this.remarks = remarks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public trn_infect_control_daily_hdr getTrn_infect_control_daily_hdr() {
		return trn_infect_control_daily_hdr;
	}

	public void setTrn_infect_control_daily_hdr(trn_infect_control_daily_hdr trn_infect_control_daily_hdr) {
		this.trn_infect_control_daily_hdr = trn_infect_control_daily_hdr;
	}

	public Integer getMst_infect_control_bundle_id() {
		return mst_infect_control_bundle_id;
	}

	public void setMst_infect_control_bundle_id(Integer mst_infect_control_bundle_id) {
		this.mst_infect_control_bundle_id = mst_infect_control_bundle_id;
	}

	public String getOption_value() {
		return option_value;
	}

	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "trn_infect_control_bundle_details_daily [id=" + id + ", trn_infect_control_daily_hdr="
				+ trn_infect_control_daily_hdr + ", mst_infect_control_bundle_id=" + mst_infect_control_bundle_id
				+ ", option_value=" + option_value + ", remarks=" + remarks + "]";
	}

}
