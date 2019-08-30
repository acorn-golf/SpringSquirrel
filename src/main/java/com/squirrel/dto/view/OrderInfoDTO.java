package com.squirrel.dto.view;

import org.apache.ibatis.type.Alias;

@Alias("OrderInfoDTO")
public class OrderInfoDTO {
//	CC_NAME	VARCHAR2(40 CHAR)
//	P_PRICE	NUMBER(10,0)
//	O_AMOUNT	NUMBER(1,0)
//	O_PRICE	NUMBER(10,0)
//	O_DATE	DATE
//	NICKNAME	VARCHAR2(10 CHAR)
//	PHONE_ID	VARCHAR2(11 BYTE)
	private String cc_name;
	private int p_price;
	private int o_amount;
	private int o_price;
	private String o_date;
	private String nickname;
	private String phone_id;
	
	public OrderInfoDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderInfoDTO(String cc_name, int p_price, int o_amount, int o_price, String o_date, String nickname,
			String phone_id) {
		super();
		this.cc_name = cc_name;
		this.p_price = p_price;
		this.o_amount = o_amount;
		this.o_price = o_price;
		this.o_date = o_date;
		this.nickname = nickname;
		this.phone_id = phone_id;
	}

	public String getO_date() {
		return o_date;
	}

	public void setO_date(String o_date) {
		this.o_date = o_date;
	}

	public String getCc_name() {
		return cc_name;
	}

	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(String phone_id) {
		this.phone_id = phone_id;
	}
	
	
	
}
