package com.squirrel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.squirrel.dto.CcScoreDTO;
import com.squirrel.dto.LocationDTO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.service.LocationService;
import com.squirrel.service.ReviewListService;

@Controller
public class ReviewController {
	
	@Autowired
	LocationService locService;
	
	@Autowired
	ReviewListService revService;
	
	@RequestMapping(value = "/insertReviewForm")
	public ModelAndView insertReviewForm() {
		List<LocationDTO> list = locService.locationList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("LocationList", list);
		mav.setViewName("review/insertReviewForm");
		return mav;
	}
	
	@RequestMapping(value = "/insertReview")
	public String insertReview(CcScoreDTO dto, HttpSession session) {
//		MemberDTO mDTO = (MemberDTO)session.getAttribute("login");
//		dto.setUser_no(mDTO.getUser_no());
		dto.setUser_no(28); // 확인을 위한 임시pk
		int n = revService.insertReview(dto);
		
		return "redirect:/";
		 // 리뷰게시판 메서드로 보낼것이다 지금은 임시로 main으로 보냄
	}
	
//	@RequestMapping(value = "")
//	public 
	
}

