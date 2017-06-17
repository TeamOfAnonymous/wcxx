package com.anonymous.domain.PropagandaInformation;

import com.anonymous.domain.PropagandaInformation.PropagandaInformation;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/15.
 */
public class PpgdaInfQueryCondition {

	private Integer currentPage;

	private Integer limit;

	private String sortName;

	private PropagandaInformation propagandaInformation;

	public PropagandaInformation getPropagandaInformation() {
		return propagandaInformation;
	}

	public void setPropagandaInformation(PropagandaInformation propagandaInformation) {
		this.propagandaInformation = propagandaInformation;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
}