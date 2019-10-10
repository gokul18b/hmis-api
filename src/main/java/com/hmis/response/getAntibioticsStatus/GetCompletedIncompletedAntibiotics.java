package com.hmis.response.getAntibioticsStatus;

import java.util.List;

import com.hmis.entity.TrnIcAntibiotics;

public class GetCompletedIncompletedAntibiotics {

	private List<AntibioticDetails> incompletedAntibiotics;
	private List<AntibioticDetails> completedAntibiotics;

	

	public List<AntibioticDetails> getIncompletedAntibiotics() {
		return incompletedAntibiotics;
	}

	public void setIncompletedAntibiotics(List<AntibioticDetails> incompletedAntibiotics) {
		this.incompletedAntibiotics = incompletedAntibiotics;
	}

	public List<AntibioticDetails> getCompletedAntibiotics() {
		return completedAntibiotics;
	}

	public void setCompletedAntibiotics(List<AntibioticDetails> completedAntibiotics) {
		this.completedAntibiotics = completedAntibiotics;
	}

}
