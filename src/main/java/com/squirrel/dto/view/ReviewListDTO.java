package com.squirrel.dto.view;

import org.apache.ibatis.type.Alias;

@Alias("ReviewListDTO")
public class ReviewListDTO {
//	,c.SCORE_NO score_no,c.USER_NO user_no
	
	private String cc_name;
	private int score;
	private String rv_title;
	private String nickname;
	private String score_date;
	private int score_no;
	private int user_no;


	public ReviewListDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewListDTO(String cc_name, int score, String rv_title, String nickname, String score_date, int score_no,
			int user_no) {
		super();
		this.cc_name = cc_name;
		this.score = score;
		this.rv_title = rv_title;
		this.nickname = nickname;
		this.score_date = score_date;
		this.score_no = score_no;
		this.user_no = user_no;
	}

	public String getCc_name() {
		return cc_name;
	}

	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRv_title() {
		return rv_title;
	}

	public void setRv_title(String rv_title) {
		this.rv_title = rv_title;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getScore_date() {
		return score_date;
	}

	public void setScore_date(String score_date) {
		this.score_date = score_date;
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
	
	
	@Override
	public String toString() {
		return "ReviewListDTO [cc_name=" + cc_name + ", score=" + score + ", rv_title=" + rv_title + ", nickname="
				+ nickname + ", score_date=" + score_date + ", score_no=" + score_no + ", user_no=" + user_no + "]";
	}
	
	
}
