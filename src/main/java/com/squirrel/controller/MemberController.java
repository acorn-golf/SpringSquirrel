package com.squirrel.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
			destination = "redirect:member/memberForm";
			break;
		case 1:
			session.setAttribute("login", mDTO);
			destination = "email/isEmailchk";
			break;
		}
		return destination;
	}
	
	@RequestMapping("/multiCheck")
	@ResponseBody
	public int multiCheck(@RequestParam HashMap<String, String> map) {
		
		int confirm = service.multiCheck(map);
		
		return confirm;
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(@RequestParam HashMap<String, String> map, HttpSession session) {
		
		MemberDTO mDTO = service.login(map);
		String  destination = null;		
		
		if( mDTO == null){
			destination = "redirect:member/loginForm";
		}else {
			session.setAttribute("login", mDTO);
			destination = "redirect:/";
		}
		
		return destination;
	}
/*	String phone_id = request.getParameter("phoneid");
	String userpw = request.getParameter("password");
	String nickname = request.getParameter("nickname");
	String email = request.getParameter("email");
	
	MemberService service = new MemberService();
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("phone_id", phone_id);
	map.put("userpw", userpw);	
	map.put("nickname", nickname);
	map.put("email", email);		
	int confirm = service.multiCheck(map);	
	response.getWriter().print(confirm);;*/
	
}
