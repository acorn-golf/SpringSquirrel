package com.squirrel.util.curl;

import java.util.Map;

public class CurlException extends Exception {

	private String errMasg;
	private int errCode;
	private Map<String, Object> errJson;
	public String getErrMasg() {
		return errMasg;
	}
	public void setErrMasg(String errMasg) {
		this.errMasg = errMasg;
	}
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public Map<String, Object> getErrJson() {
		return errJson;
	}
	public void setErrJson(Map<String, Object> errJson) {
		this.errJson = errJson;
	}
	public CurlException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CurlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public CurlException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public CurlException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public CurlException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public CurlException(String errMasg, int errCode, Map<String, Object> errJson) {
		super();
		this.errMasg = errMasg;
		this.errCode = errCode;
		this.errJson = errJson;
	}
	
	
	
}
