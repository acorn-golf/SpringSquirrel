package com.squirrel.dto.view;

import org.apache.ibatis.type.Alias;

@Alias("SelectRatingDTO")
public class SelectRatingDTO {
//	USER_NO	NUMBER(5,0)
//	PHONE_ID	VARCHAR2(11 BYTE)
//	USERNAME	VARCHAR2(4 CHAR)
//	NICKNAME	VARCHAR2(10 CHAR)
//	EMAIL	VARCHAR2(30 BYTE)
	
	private int num;
	private int user_no;
	private String phone_id;
	private String username;
	private String nickname;
	private String email;
	
	public SelectRatingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelectRatingDTO(int num, int user_no, String phone_id, String username, String nickname, String email) {
		super();
		this.num = num;
		this.user_no = user_no;
		this.phone_id = phone_id;
		this.username = username;
		this.nickname = nickname;
		this.email = email;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(String phone_id) {
		this.phone_id = phone_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SelectRatingDTO [num=" + num + ", user_no=" + user_no + ", phone_id=" + phone_id + ", username="
				+ username + ", nickname=" + nickname + ", email=" + email + "]";
	}
	
	
}
