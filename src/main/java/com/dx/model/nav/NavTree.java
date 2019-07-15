package com.dx.model.nav;

import java.util.List;


public class NavTree {

	private Integer id;
	
	private String text;
	
	private Integer pid;
	
	private Boolean selectable;
	
	private List<NavTree> nodes;
	
	private String href;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Boolean getSelectable() {
		return selectable;
	}

	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}

	public List<NavTree> getNodes() {
		return nodes;
	}

	public void setNodes(List<NavTree> nodes) {
		this.nodes = nodes;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
}
