package com.squirrel.dto.view;

import org.apache.ibatis.type.Alias;

@Alias("PickListviewDTO")
public class PickListviewDTO {

//	CC_NAME,CC_IMG,p.P_ID p_id,P_PDATE,P_UPLOADDATE,P_MAXPEOPLE,P_HOLE,P_CADDYYN,P_BABYN,P_CARTYN,P_PRICE,P_CONTENT,m.USER_NO manager_user_no,P_VCOUNT,NICKNAME,substr(PHONE_ID,1,3)||'-'||substr(PHONE_ID,4,4)||'-'||substr(PHONE_ID,8,4) PHONE_ID,
//	case when P_PDATE-sysdate <= 0.5 then '긴급'
//	end emergency,pl.USER_NO user_no
	
	private String cc_name;
	private String cc_img;
	private String p_id;
	private String p_pdate;
	private String p_uploaddate;
	private int p_maxpeople;
	private int p_hole;
	private String p_caddyyn;
	private String p_babyn;
	private String p_cartyn;
	private int p_price;
	private String p_content;
	private int manager_user_no;
	private int p_vcount;
	private String nickname;
	private String phone_id;
	private String emergency;
	private int user_no;
	private String loc_id;
	private int pick_no;
	private int pick_amount;
	
	public PickListviewDTO() {
		// TODO Auto-generated constructor stub
	}
		
	public PickListviewDTO(String cc_name, String cc_img, String p_id, String p_pdate, String p_uploaddate,
			int p_maxpeople, int p_hole, String p_caddyyn, String p_babyn, String p_cartyn, int p_price,
			String p_content, int manager_user_no, int p_vcount, String nickname, String phone_id, String emergency,
			int user_no, String loc_id, int pick_no, int pick_amount) {
		super();
		this.cc_name = cc_name;
		this.cc_img = cc_img;
		this.p_id = p_id;
		this.p_pdate = p_pdate;
		this.p_uploaddate = p_uploaddate;
		this.p_maxpeople = p_maxpeople;
		this.p_hole = p_hole;
		this.p_caddyyn = p_caddyyn;
		this.p_babyn = p_babyn;
		this.p_cartyn = p_cartyn;
		this.p_price = p_price;
		this.p_content = p_content;
		this.manager_user_no = manager_user_no;
		this.p_vcount = p_vcount;
		this.nickname = nickname;
		this.phone_id = phone_id;
		this.emergency = emergency;
		this.user_no = user_no;
		this.loc_id = loc_id;
		this.pick_no = pick_no;
		this.pick_amount = pick_amount;
	}
	
	public int getPick_amount() {
		return pick_amount;
	}

	public void setPick_amount(int pick_amount) {
		this.pick_amount = pick_amount;
	}

	public int getPick_no() {
		return pick_no;
	}

	public void setPick_no(int pick_no) {
		this.pick_no = pick_no;
	}

	public String getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
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

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
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

	public int getP_maxpeople() {
		return p_maxpeople;
	}

	public void setP_maxpeople(int p_maxpeople) {
		this.p_maxpeople = p_maxpeople;
	}

	public int getP_hole() {
		return p_hole;
	}

	public void setP_hole(int p_hole) {
		this.p_hole = p_hole;
	}

	public String getP_caddyyn() {
		return p_caddyyn;
	}

	public void setP_caddyyn(String p_caddyyn) {
		this.p_caddyyn = p_caddyyn;
	}

	public String getP_babyn() {
		return p_babyn;
	}

	public void setP_babyn(String p_babyn) {
		this.p_babyn = p_babyn;
	}

	public String getP_cartyn() {
		return p_cartyn;
	}

	public void setP_cartyn(String p_cartyn) {
		this.p_cartyn = p_cartyn;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public int getManager_user_no() {
		return manager_user_no;
	}

	public void setManager_user_no(int manager_user_no) {
		this.manager_user_no = manager_user_no;
	}

	public int getP_vcount() {
		return p_vcount;
	}

	public void setP_vcount(int p_vcount) {
		this.p_vcount = p_vcount;
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

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	
	
}
