package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("ProductDTO")
@Data
public class ProductDTO {

//	p_id	varchar2(5 byte)
//	p_pdate	date
//	p_uploaddate	date
//	p_maxpeople	number(1,0)
//	p_hole	number(2,0)
//	p_caddyyn	char(1 byte)
//	p_babyn	char(1 byte)
//	p_cartyn	char(1 byte)
//	p_price	number(10,0)
//	p_content	varchar2(400 char)
//	user_no	number(5,0)
//	cc_id	char(6 byte)
//	p_vcount	number(7,0)
	
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
	private int user_no;
	private String cc_id;
	private int p_vcount;
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(String p_id, String p_pdate, String p_uploaddate, int p_maxpeople, int p_hole,
			String p_caddyyn, String p_babyn, String p_cartyn, int p_price, String p_content, int user_no, String cc_id,
			int p_vcount) {
		super();
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
		this.user_no = user_no;
		this.cc_id = cc_id;
		this.p_vcount = p_vcount;
	}
	
}
