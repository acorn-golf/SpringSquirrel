package com.squirrel.dto;

import org.apache.ibatis.type.Alias;

@Alias("RatingUpDTO")
public class RatingUpDTO {
//	USER_NO	NUMBER(5,0)
//	APPLYDATE	DATE
//	OKDATE	DATE
//	APPROVER	NUMBER(5,0)
	private int user_no;
	private String applydate;
	private String okdate;
	private int approver;
	
	public RatingUpDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingUpDTO(int user_no, String applydate, String okdate, int approver) {
		super();
		this.user_no = user_no;
		this.applydate = applydate;
		this.okdate = okdate;
		this.approver = approver;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getApplydate() {
		return applydate;
	}

	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}

	public String getOkdate() {
		return okdate;
	}

	public void setOkdate(String okdate) {
		this.okdate = okdate;
	}

	public int getApprover() {
		return approver;
	}

	public void setApprover(int approver) {
		this.approver = approver;
	}

	@Override
	public String toString() {
		return "RatingUp [user_no=" + user_no + ", applydate=" + applydate + ", okdate=" + okdate + ", approver="
				+ approver + "]";
	}
	
	
}
