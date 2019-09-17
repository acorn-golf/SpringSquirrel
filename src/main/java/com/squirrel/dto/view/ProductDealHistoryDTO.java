package com.squirrel.dto.view;

import org.apache.ibatis.type.Alias;


@Alias("ProductDealHistoryDTO")
public class ProductDealHistoryDTO {
//	CC_NAME	VARCHAR2(40 CHAR)
//	CC_IMG	VARCHAR2(40 BYTE)
//	LOC_ID	VARCHAR2(10 CHAR)
//	P_PDATE	DATE
//	P_UPLOADDATE	DATE
//	P_PRICE	NUMBER(10,0)
//	O_DATE	DATE
//	O_AMOUNT	NUMBER(1,0)
//	O_PRICE	NUMBER(10,0)
//	NICKNAME	VARCHAR2(10 CHAR)
//	PHONE_ID	VARCHAR2(11 BYTE)
//	EMAIL	VARCHAR2(30 BYTE)
	private String cc_name;
	private String cc_img;
	private String loc_id;
	private String p_pdate;
	private String p_uploaddate;
	private int p_price;
	private String o_date;
	private int o_amount;
	private int o_price;
	private String nickname;
	private String phone_id;
	private String email;
	
	public ProductDealHistoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDealHistoryDTO(String cc_name, String cc_img, String loc_id, String p_pdate, String p_uploaddate,
			int p_price, String o_date, int o_amount, int o_price, String nickname, String phone_id, String email) {
		super();
		this.cc_name = cc_name;
		this.cc_img = cc_img;
		this.loc_id = loc_id;
		this.p_pdate = p_pdate;
		this.p_uploaddate = p_uploaddate;
		this.p_price = p_price;
		this.o_date = o_date;
		this.o_amount = o_amount;
		this.o_price = o_price;
		this.nickname = nickname;
		this.phone_id = phone_id;
		this.email = email;
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

	public String getP_pdate() {
		return p_pdate;
	}

	public void setP_pdate(String p_pdate) {
		this.p_pdate = p_pdate;
	}

	public String getP_uploaddate() {
		return p_uploaddate;
	}

	public void setP_uploaddate(String p_uploaddate) {
		this.p_uploaddate = p_uploaddate;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getO_date() {
		return o_date;
	}

	public void setO_date(String o_date) {
		this.o_date = o_date;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ProductDealHistoryDTO [cc_name=" + cc_name + ", cc_img=" + cc_img + ", loc_id=" + loc_id
				+ ", p_pdate=" + p_pdate + ", p_uploaddate=" + p_uploaddate + ", p_price=" + p_price + ", o_date="
				+ o_date + ", o_amount=" + o_amount + ", o_price=" + o_price + ", nickname=" + nickname + ", phone_id="
				+ phone_id + ", email=" + email + "]";
	}
	
	
}
