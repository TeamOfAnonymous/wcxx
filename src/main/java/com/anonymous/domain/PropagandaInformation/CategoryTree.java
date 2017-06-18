package com.anonymous.domain.PropagandaInformation;

import java.util.List;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/16.
 */
public class CategoryTree {

	private String id;

	private String text;

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

	public List<CategoryTree> getNodes() {
		return nodes;
	}

	public void setNodes(List<CategoryTree> nodes) {
		this.nodes = nodes;
	}
}
