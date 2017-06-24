package com.anonymous.domain.PropagandaInformation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/16.
 */
public class CategoryTree {

	private String id;

	private String text;

	private String pid;

	private String creator;

	private LocalDateTime lastModifiedTime;

	private Integer status;

	private List<CategoryTree> nodes;

	public CategoryTree() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public LocalDateTime getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<CategoryTree> getNodes() {
		return nodes;
	}

	public void setNodes(List<CategoryTree> nodes) {
		this.nodes = nodes;
	}
}
