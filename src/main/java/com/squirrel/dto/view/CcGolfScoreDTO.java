package com.squirrel.dto.view;

import org.apache.ibatis.type.Alias;

import com.squirrel.dto.GolfCcDTO;

@Alias("CcGolfScoreDTO")
public class CcGolfScoreDTO extends GolfCcDTO {
	
	private float score;
	private int count;


	public CcGolfScoreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CcGolfScoreDTO(String cc_id, String cc_name, String cc_addr1, String cc_addr2, String cc_phone,
			String cc_url, String cc_img, String loc_id,float score) {
		super(cc_id, cc_name, cc_addr1, cc_addr2, cc_phone, cc_url, cc_img, loc_id);
		this.score = score;
		// TODO Auto-generated constructor stub
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
