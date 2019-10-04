package com.squirrel.dto.view;

import org.apache.ibatis.type.Alias;

@Alias("MyReviewDTO")
public class MyReviewDTO {
//	SCORE_NO	NUMBER(5,0)   pk      ●
//	SCORE	NUMBER(1,0)    평점           ●
//	CC_ID	CHAR(6 BYTE)   golfcc와 조인  ●
//	SCORE_DATE	DATE  평점등록날짜    ●
//	USER_NO	NUMBER(5,0)    유저 pk        ●
//	RV_TITLE	VARCHAR2(30 CHAR)  글 제목   ●
//	RV_VCOUNT	NUMBER(7,0)   조회수  ●
//	CC_NAME	VARCHAR2(40 CHAR)  ●
//	CC_IMG	VARCHAR2(40 BYTE)  ●
//	LOC_ID	VARCHAR2(2 CHAR)  loc와 조인 ●
	
	private int score_no;
	private int score;
	private String cc_id;
	private String score_date;
	private int user_no;
	private String rv_title;
	private int rv_vcount;
	private String cc_name;
	private String cc_img;
	private String loc_id;
	
	public MyReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyReviewDTO(int score_no, int score, String cc_id, String score_date, int user_no, String rv_title,
			int rv_vcount, String cc_name, String cc_img, String loc_id) {
		super();
		this.score_no = score_no;
		this.score = score;
		this.cc_id = cc_id;
		this.score_date = score_date;
		this.user_no = user_no;
		this.rv_title = rv_title;
		this.rv_vcount = rv_vcount;
		this.cc_name = cc_name;
		this.cc_img = cc_img;
		this.loc_id = loc_id;
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

	public int getRv_vcount() {
		return rv_vcount;
	}

	public void setRv_vcount(int rv_vcount) {
		this.rv_vcount = rv_vcount;
	}

	public String getCc_name() {
		return cc_name;
	}

	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
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

	@Override
	public String toString() {
		return "MyReviewDTO [score_no=" + score_no + ", score=" + score + ", cc_id=" + cc_id + ", score_date="
				+ score_date + ", user_no=" + user_no + ", rv_title=" + rv_title + ", rv_vcount=" + rv_vcount
				+ ", cc_name=" + cc_name + ", cc_img=" + cc_img + ", loc_id=" + loc_id + "]";
	}
	
	
}
