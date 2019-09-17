package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

@Alias("TestDTO")
public class TestDTO {
	
	private String cc_id;
	private String cc_name;
	private String cc_addr1;
	private String cc_addr2;
	private String cc_phone;
	private String cc_url;
	private String cc_img;
	private String loc_id;
	public TestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TestDTO(String cc_id, String cc_name, String cc_addr1, String cc_addr2, String cc_phone, String cc_url,
			String cc_img, String loc_id) {
		super();
		this.cc_id = cc_id;
		this.cc_name = cc_name;
		this.cc_addr1 = cc_addr1;
		this.cc_addr2 = cc_addr2;
		this.cc_phone = cc_phone;
		this.cc_url = cc_url;
		this.cc_img = cc_img;
		this.loc_id = loc_id;
	}
	public String getCc_id() {
		return cc_id;
	}
	public void setCc_id(String cc_id) {
		this.cc_id = cc_id;
	}
	public String getCc_name() {
		return cc_name;
	}
	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}
	public String getCc_addr1() {
		return cc_addr1;
	}
	public void setCc_addr1(String cc_addr1) {
		this.cc_addr1 = cc_addr1;
	}
	public String getCc_addr2() {
		return cc_addr2;
	}
	public void setCc_addr2(String cc_addr2) {
		this.cc_addr2 = cc_addr2;
	}
	public String getCc_phone() {
		return cc_phone;
	}
	public void setCc_phone(String cc_phone) {
		this.cc_phone = cc_phone;
	}
	public String getCc_url() {
		return cc_url;
	}
	public void setCc_url(String cc_url) {
		this.cc_url = cc_url;
	}
	public String getCc_img() {
		return cc_img;
	}
	public void setCc_img(String cc_img) {
		this.cc_img = cc_img;
	}
	public String getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}
	
	
	
}
