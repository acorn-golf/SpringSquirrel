package com.squirrel.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

public class PageDTO<T> {
	private int perPage=10; // 페이지당 보여줄 개수
	private int totalRecord; // 전체 레코드 개수
	private int curPage; // 현재페이지
	private List<T> list; // 페이지에 보여줄 데이터
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
