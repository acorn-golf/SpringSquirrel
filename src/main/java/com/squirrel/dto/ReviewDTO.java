package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

@Alias("ReviewDTO")
public class ReviewDTO {

	
//	rv_no	varchar2(5 byte)
//	rv_content	varchar2(400 char)
//	rv_title	varchar2(30 char)
//	user_no	number(5,0)
//	rv_vcount	number(7,0)
	
	private String rv_no;
	private String rv_content;
	private String rv_title;
	private int user_no;
	private int rv_vcount;
	
	
	
	public ReviewDTO(String rv_no, String rv_content, String rv_title, int user_no, int rv_vcount) {
		super();
		this.rv_no = rv_no;
		this.rv_content = rv_content;
		this.rv_title = rv_title;
		this.user_no = user_no;
		this.rv_vcount = rv_vcount;
	}
	public ReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRv_no() {
		return rv_no;
	}
	public void setRv_no(String rv_no) {
		this.rv_no = rv_no;
	}
	public String getRv_content() {
		return rv_content;
	}
	public void setRv_content(String rv_content) {
		this.rv_content = rv_content;
	}
	public String getRv_title() {
		return rv_title;
	}
	public void setRv_title(String rv_title) {
		this.rv_title = rv_title;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getRv_vcount() {
		return rv_vcount;
	}
	public void setRv_vcount(int rv_vcount) {
		this.rv_vcount = rv_vcount;
	}
	
}
