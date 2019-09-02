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

	@RequestMapping(value="/memberAdd")
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
	
	@RequestMapping(value="/multiCheck")
	@ResponseBody
	public int multiCheck(@RequestParam HashMap<String, String> map) {
		
		int confirm = service.multiCheck(map);
		
		return confirm;
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(@RequestParam HashMap<String, String> map, HttpSession session) {
		
		MemberDTO mDTO = service.login(map);	
		String destination = null;
		
			if( mDTO == null ) {
				destination = "redirect:member/loginForm";
			}else {
				session.setAttribute("login", mDTO);
				destination = "redirect:/";
			}
				
		
		return destination;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/myPage")
	public String myPage(HttpSession session) {
		
		MemberDTO mDTO = (MemberDTO)session.getAttribute("login");
		System.out.println(mDTO);
		mDTO = service.myPage(mDTO.getUser_no());
		session.setAttribute("login", mDTO);
		
		return "member/myPage";
	}
	
	@RequestMapping(value="/myPageUpdate")
	public String myPageUpdate(HttpSession session, MemberDTO mDTO) {
		
		service.myPageUpdate(mDTO);
		
		mDTO = service.myPage(mDTO.getUser_no());
		session.setAttribute("login", mDTO);
		
		return "redirect:/myPage";
	}
}
