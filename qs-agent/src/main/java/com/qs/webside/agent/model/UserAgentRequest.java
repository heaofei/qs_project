package com.qs.webside.agent.model;

import java.io.Serializable;

import com.qs.webside.member.model.MemberAgents;

public class UserAgentRequest extends MemberAgents implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int province;

	private int city;
 
	private int county;
	
	private String sitemid;
	
	private int imd;
	
	public int getImd() {
		return imd;
	}

	public void setImd(int imd) {
		this.imd = imd;
	}

	public String getSitemid() {
		return sitemid;
	}

	public void setSitemid(String sitemid) {
		this.sitemid = sitemid;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCounty() {
		return county;
	}

	public void setCounty(int county) {
		this.county = county;
	}
	
	

}
