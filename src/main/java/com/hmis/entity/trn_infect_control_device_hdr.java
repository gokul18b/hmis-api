package com.hmis.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "trn_infect_control_device_hdr")
public class trn_infect_control_device_hdr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "mst_infect_control_device_id")
	private Integer mst_infect_control_device_id;

	@Column(name = "visit_id")
	private Integer visit_id;
	
	@Column(name = "bed_id")
	private Integer bed_id;

//	@Column(name = "infect_control_date")
//	@Temporal(TemporalType.DATE)
//	private Date infect_control_date;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "insertion_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date insertion_date;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "removal_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date removal_date;

	@OneToMany(mappedBy = "trn_infect_control_device_hdr", fetch = FetchType.LAZY)
	private List<trn_infect_control_bundle_details> trn_infect_control_bundle_details;

	@OneToMany(mappedBy = "trn_infect_control_device_hdr", fetch = FetchType.LAZY)
	private List<trn_infect_control_daily_hdr> trn_infect_control_daily_hdr;

	public trn_infect_control_device_hdr() {
		super();

	}

	public trn_infect_control_device_hdr(Integer id, Integer mst_infect_control_device_id, Integer visit_id,
//			Date infect_control_date, 
			Date insertion_date, Date removal_date) {
		super();
		this.id = id;
		this.mst_infect_control_device_id = mst_infect_control_device_id;
		this.visit_id = visit_id;
	//	this.infect_control_date = infect_control_date;
		this.insertion_date = insertion_date;
		this.removal_date = removal_date;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMst_infect_control_device_id() {
		return mst_infect_control_device_id;
	}

	public void setMst_infect_control_device_id(Integer mst_infect_control_device_id) {
		this.mst_infect_control_device_id = mst_infect_control_device_id;
	}

	public Integer getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}
	
	

//	public Date getInfect_control_date() {
//		return infect_control_date;
//	}
//
//	public void setInfect_control_date(Date infect_control_date) {
//		this.infect_control_date = infect_control_date;
//	}

	public Integer getBed_id() {
		return bed_id;
	}

	public void setBed_id(Integer bed_id) {
		this.bed_id = bed_id;
	}

	public Date getInsertion_date() {
		return insertion_date;
	}

	public void setInsertion_date(Date insertion_date) {
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date dateFrom = null;
//		try {
//			dateFrom = df.parse(insertion_date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		this.insertion_date = insertion_date;

	}

	public Date getRemoval_date() {

		return removal_date;
	}

	public void setRemoval_date(Date removal_date) {
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date dateFrom = null;
//		try {
//			dateFrom = df.parse(removal_date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		this.removal_date = removal_date;
	}

	public List<trn_infect_control_bundle_details> getTrn_infect_control_bundle_details() {
		return trn_infect_control_bundle_details;
	}

	public void setTrn_infect_control_bundle_details(
			List<trn_infect_control_bundle_details> trn_infect_control_bundle_details) {
		this.trn_infect_control_bundle_details = trn_infect_control_bundle_details;
	}

	public List<trn_infect_control_daily_hdr> getTrn_infect_control_daily_hdr() {
		return trn_infect_control_daily_hdr;
	}

	public void setTrn_infect_control_daily_hdr(List<trn_infect_control_daily_hdr> trn_infect_control_daily_hdr) {
		this.trn_infect_control_daily_hdr = trn_infect_control_daily_hdr;
	}

	@Override
	public String toString() {
		return "trn_infect_control_device_hdr [id=" + id + ", mst_infect_control_device_id="
				+ mst_infect_control_device_id + ", visit_id=" + visit_id + ", bed_id=" + bed_id + ", insertion_date="
				+ insertion_date + ", removal_date=" + removal_date + ", trn_infect_control_bundle_details="
				+ trn_infect_control_bundle_details + ", trn_infect_control_daily_hdr=" + trn_infect_control_daily_hdr
				+ "]";
	}

	

}
