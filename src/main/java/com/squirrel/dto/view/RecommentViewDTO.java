package com.squirrel.dto.view;

import org.apache.ibatis.type.Alias;

@Alias("RecommentViewDTO")
public class RecommentViewDTO {
//	RE_NO	NUMBER(5,0)
//	RE_DATE	DATE
//	RE_CONTENT	VARCHAR2(100 CHAR)
//	SCORE_NO	NUMBER(5,0)
//	USER_NO	NUMBER(5,0)
//	NICKNAME	VARCHAR2(10 CHAR)
	
	private int re_no;
	private String re_date;
	private String re_content;
	private int score_no;
	private int user_no;
	private String nickname;
	
	public RecommentViewDTO() {
		// TODO Auto-generated constructor stub
	}

	public RecommentViewDTO(int re_no, String re_date, String re_content, int score_no, int user_no, String nickname) {
		super();
		this.re_no = re_no;
		this.re_date = re_date;
		this.re_content = re_content;
		this.score_no = score_no;
		this.user_no = user_no;
		this.nickname = nickname;
	}

	public int getRe_no() {
		return re_no;
	}

	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}

	public String getRe_date() {
		return re_date;
	}

	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}

	public String getRe_content() {
		return re_content;
	}

	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}

	public int getScore_no() {
		return score_no;
	}

	public void setScore_no(int score_no) {
		this.score_no = score_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
