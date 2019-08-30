package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

@Alias("PickListDTO")
public class PickListDTO {
//	pick_no	number(5,0)
//	pick_amount	number(1,0)
//	user_no	number(5,0)
//	p_id	varchar2(5 byte)
	
	private int pick_no;
	private int pick_amount;
	private int user_no;
	private String p_id;
	
	public PickListDTO() {
		// TODO Auto-generated constructor stub
	}

	public PickListDTO(int pick_no, int pick_amount, int user_no, String p_id) {
		super();
		this.pick_no = pick_no;
		this.pick_amount = pick_amount;
		this.user_no = user_no;
		this.p_id = p_id;
	}

	public int getPick_no() {
		return pick_no;
	}

	public void setPick_no(int pick_no) {
		this.pick_no = pick_no;
	}

	public int getPick_amount() {
		return pick_amount;
	}

	public void setPick_amount(int pick_amount) {
		this.pick_amount = pick_amount;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	
	
}
