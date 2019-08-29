package com.squirrel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewController {



//평점 리스트 
	
	@RequestMapping(value = "/ReviewList",method = RequestMethod.GET)
	public String reviewList()
	{
		return "";
	}
	
	
	@RequestMapping(value = "/ReviewList/{curnum}",method = RequestMethod.GET)
	public String reviewListCurNum(@PathVariable("curnum")int curNum)
	{
		return "";
	}
	
	@RequestMapping(value = "/ReviewList/{curnum}/{golfName}",method = RequestMethod.GET)
	public String reviewListCurNumGolf(@PathVariable("curnum")int curNum,@PathVariable("golfName")String golfName)
	{
		return "";
	}
//평점 리스트 / 페이지 번호 ? 검색조건 
//평점 리스트 / 페이지 번호 / 골프장명 ? 검색조건
	
	
}
