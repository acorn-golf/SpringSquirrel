package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

@Alias("CcScoreDTO")
public class CcScoreDTO {
//	score_no	number(5,0)
//	score	number(1,0)
//	cc_id	char(6 byte)
//	score_date	date
//	user_no	number(5,0)
//	RV_TITLE VARCHAR2(30 CHAR)
//	RV_CONTENT VARCHAR2(400 CHAR)
//	RV_VCOUNT NUMBER(7,0)
	
	private int score_no;
	private int score;
	private String cc_id;
	private String score_date;
	private int user_no;
	private String rv_title;
	private String rv_content;
	private int rv_vcount;
	
	public CcScoreDTO() {
		// TODO Auto-generated constructor stub
	}

	public CcScoreDTO(int score_no, int score, String cc_id, String score_date, int user_no, String rv_title,
			String rv_content, int rv_vcount) {
		super();
		this.score_no = score_no;
		this.score = score;
		this.cc_id = cc_id;
		this.score_date = score_date;
		this.user_no = user_no;
		this.rv_title = rv_title;
		this.rv_content = rv_content;
		this.rv_vcount = rv_vcount;
	}

	public int getScore_no() {
		return score_no;
	}

	public void setScore_no(int score_no) {
		this.score_no = score_no;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getCc_id() {
		return cc_id;
	}

	public void setCc_id(String cc_id) {
		this.cc_id = cc_id;
	}

	public String getScore_date() {
		return score_date;
	}

	public void setScore_date(String score_date) {
		this.score_date = score_date;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getRv_title() {
		return rv_title;
	}

	public void setRv_title(String rv_title) {
		this.rv_title = rv_title;
	}

	public String getRv_content() {
		return rv_content;
	}

	public void setRv_content(String rv_content) {
		this.rv_content = rv_content;
	}

	public int getRv_vcount() {
		return rv_vcount;
	}

	public void setRv_vcount(int rv_vcount) {
		this.rv_vcount = rv_vcount;
	}
	
	
}
