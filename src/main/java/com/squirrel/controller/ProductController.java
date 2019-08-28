package com.squirrel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.squirrel.dto.LocationDTO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.service.LocationService;

@Controller
public class ProductController {

	@Autowired
	LocationService locService;

	@RequestMapping("productInsertForm")
	public ModelAndView productInsertForm(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		ModelAndView mav = new ModelAndView();
		if (dto.getRating().equals("M")) {
			List<LocationDTO> list = locService.locationList();
			mav.addObject("LocationList", list);
			mav.setViewName("product/product");
		} else {
			mav.addObject("mesg", "권한이 없습니다");
			mav.setViewName("main");
		}
		return mav;
	}

}
