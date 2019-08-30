package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("MemberDTO")
@Data
public class MemberDTO {
//	user_no	number(5,0)
//	phone_id	varchar2(15 byte)
//	userpw	varchar2(20 char)s
//	username	varchar2(4 char)s
//	nickname	varchar2(10 char)
//	userssn	varchar2(6 byte)
//	gender	char(1 byte)
//	rating	varchar2(3 char)
//	rstartdate	date
//	renddate	date
//	email	varchar2(30 byte)
//	email_chk	char(1 byte)

	private int user_no;
	private String phone_id;
	private String userpw;
	private String username;
	private String nickname;
	private String userssn;
	private String gender;
	private String rating;
	private String rstartdate;
	private String renddate;
	private String email;
	private String email_chk;
	private String kakao_id;

	public MemberDTO(int user_no, String phone_id, String userpw, String username, String nickname, String userssn,
			String gender, String rating, String rstartdate, String renddate, String email, String email_chk) {
		super();
		this.user_no = user_no;
		this.phone_id = phone_id;
		this.userpw = userpw;
		this.username = username;
		this.nickname = nickname;
		this.userssn = userssn;
		this.gender = gender;
		this.rating = rating;
		this.rstartdate = rstartdate;
		this.renddate = renddate;
		this.email = email;
		this.email_chk = email_chk;
	}

	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(String phone_id, String userpw, String username, String nickname, String userssn,
			String gender, String rating, String email) {
		super();
		this.phone_id = phone_id;
		this.userpw = userpw;
		this.username = username;
		this.nickname = nickname;
		this.userssn = userssn;
		this.gender = gender;
		this.rating = rating;
		this.email = email;
	}

	public MemberDTO(String phone_id, String userpw, String email) {
		super();
		this.phone_id = phone_id;
		this.userpw = userpw;
		this.email = email;
	}

	public MemberDTO(String phone_id, String username, String nickname, String rating, String rstartdate,
			String renddate, String email) {
		super();
		this.phone_id = phone_id;
		this.username = username;
		this.nickname = nickname;
		this.rating = rating;
		this.rstartdate = rstartdate;
		this.renddate = renddate;
		this.email = email;
	
	}

}
