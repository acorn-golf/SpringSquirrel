package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

@Alias("NoticeListDTO")
public class NoticeListDTO {

//	note_content	varchar2(400 char)
//	note_no	number(5,0)
//	note_title	varchar2(30 char)
//	user_no	number(5,0)
//	notedate	date
//	note_division	varchar2(3 char)
//	note_file	varchar2(30 byte)
//	note_vcount	number(7,0)
	
	
	private String note_content;
	private int note_no;
	private String note_title;
	private int user_no;
	private String notedate;
	private String note_division;
	private String note_file;
	private int note_vcount;
	
	public NoticeListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeListDTO(String note_content, String note_title, String note_division) {
		super();
		this.note_content = note_content;
		this.note_no = note_no;
		this.note_title = note_title;
		this.user_no = user_no;
		this.notedate = notedate;
		this.note_division = note_division;
		this.note_file = note_file;
		this.note_vcount = note_vcount;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

	public int getNote_no() {
		return note_no;
	}

	public void setNote_no(int note_no) {
		this.note_no = note_no;
	}

	public String getNote_title() {
		return note_title;
	}

	public void setNote_title(String note_title) {
		this.note_title = note_title;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getNotedate() {
		return notedate;
	}

	public void setNotedate(String notedate) {
		this.notedate = notedate;
	}

	public String getNote_division() {
		return note_division;
	}

	public void setNote_division(String note_division) {
		this.note_division = note_division;
	}

	public String getNote_file() {
		return note_file;
	}

	public void setNote_file(String note_file) {
		this.note_file = note_file;
	}

	public int getNote_vcount() {
		return note_vcount;
	}

	public void setNote_vcount(int note_vcount) {
		this.note_vcount = note_vcount;
	}
	
	
	
}
