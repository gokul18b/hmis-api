package com.hmis.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "trn_ic_antibiotics")
public class TrnIcAntibiotics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "mst_antibiotics_id")
	private Integer mst_antibiotic_id;
	
	@Column(name = "mst_sub_antibiotics_id")
	private Integer mst_sub_antibiotic_id;

	@Column(name = "start_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date start_time;

	@Column(name = "end_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date end_time;
	
	@Column(name="visit_id")
	private Integer visit_id;
	
	@Column(name="bed_id")
	private Integer bed_id;
	
	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="last_updated")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date last_updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getMst_antibiotic_id() {
		return mst_antibiotic_id;
	}

	public void setMst_antibiotic_id(Integer mst_antibiotic_id) {
		this.mst_antibiotic_id = mst_antibiotic_id;
	}

	public Integer getMst_sub_antibiotic_id() {
		return mst_sub_antibiotic_id;
	}

	public void setMst_sub_antibiotic_id(Integer mst_sub_antibiotic_id) {
		this.mst_sub_antibiotic_id = mst_sub_antibiotic_id;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	

	public Integer getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}

	public Integer getBed_id() {
		return bed_id;
	}

	public void setBed_id(Integer bed_id) {
		this.bed_id = bed_id;
	}
	
	

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	
	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	@Override
	public String toString() {
		return "TrnIcAntibiotics [id=" + id + ", mst_antibiotic_id=" + mst_antibiotic_id + ", mst_sub_antibiotic_id="
				+ mst_sub_antibiotic_id + ", start_time=" + start_time + ", end_time=" + end_time + ", visit_id=" + visit_id + ", bed_id=" + bed_id + ", created_by=" + created_by + "]";
	}

	
	
	
}
