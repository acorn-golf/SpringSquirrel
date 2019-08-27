package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

@Alias("OrderListDTO")
public class OrderListDTO {

	
//	o_no	number(5,0)
//	o_amount	number(1,0)
//	o_price	number(10,0)
//	o_date	date
//	p_id	varchar2(5 byte)
//	user_no	number(5,0)
	
	private int o_no;
	private int o_amount;
	private int o_price;
	private String o_date;
	private String p_id;
	private int user_no;
	public OrderListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderListDTO(int o_no, int o_amount, int o_price, String o_date, String p_id, int user_no) {
		super();
		this.o_no = o_no;
		this.o_amount = o_amount;
		this.o_price = o_price;
		this.o_date = o_date;
		this.p_id = p_id;
		this.user_no = user_no;
	}
	public int getO_no() {
		return o_no;
	}
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public int getO_amount() {
		return o_amount;
	}
	public void setO_amount(int o_amount) {
		this.o_amount = o_amount;
	}
	public int getO_price() {
		return o_price;
	}
	public void setO_price(int o_price) {
		this.o_price = o_price;
	}
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	
	
	
}
