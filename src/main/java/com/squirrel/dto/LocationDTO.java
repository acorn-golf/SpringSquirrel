package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

@Alias("LocationDTO")
public class LocationDTO {
//	loc_id	varchar2(2 char)
//	loc_name	varchar2(10 char)
	
	private String loc_id;
	private String loc_name;
	public LocationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LocationDTO(String loc_id, String loc_name) {
		super();
		this.loc_id = loc_id;
		this.loc_name = loc_name;
	}
	public String getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	
	
	
}
