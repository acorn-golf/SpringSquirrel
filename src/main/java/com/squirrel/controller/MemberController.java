package com.squirrel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.squirrel.dto.MemberDTO;
import com.squirrel.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	@RequestMapping("/memberAdd")
	public String memberAdd(MemberDTO mDTO, HttpSession session) {

		String gender = mDTO.getGender();
		String destination = null;
		if (gender.equals("male")) {
			gender = "1";
		} else {
			gender = "2";
		}
		mDTO.setGender(gender);
		int result = service.MemberAdd(mDTO);
		switch (result) {
		case 0:
			destination = "memberUI";
			break;
		case 1:
			session.setAttribute("login", mDTO);
			destination = "email/isEmailchk.jsp";
			break;
		}
		return destination;

	}
}
