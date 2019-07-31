package com.dx.model.nav;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NavTree {

	private Integer id;
	
	private String text;
	
	private Integer pid;
	
	private Boolean selectable;

	private static Map<String, Object> stateMap = new HashMap<String, Object>();

	static {
		stateMap.put("checked", true);
	}

	private Map<String, Object> state;

	
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

	public Map<String, Object> getState() {
		return state;
	}

	public void setState() {
		this.state = stateMap;
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
