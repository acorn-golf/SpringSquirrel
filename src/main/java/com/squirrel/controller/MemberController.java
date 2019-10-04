package com.squirrel.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.annotation.Loginchk;
import com.annotation.Loginchk.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.RatingUpDTO;
import com.squirrel.dto.view.SelectRatingDTO;
import com.squirrel.service.MemberService;
import com.squirrel.util.jwt.JwtUtil;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@Autowired
	JwtUtil jwtUtil;

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
			destination = "redirect:email/isEmailchk";
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
				destination = "redirect:../email/isEmailchk";
				
				//jwt 테스트 코드 (지한뉘)
				System.out.println(jwtUtil.TestGetAccessToken(mDTO.getUser_no(), mDTO.getRating()));
				
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
	
	@RequestMapping(value = "/phoneIdCheck", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String phoneIdCheck(@RequestParam("phoneid") String phone_id) {
		MemberDTO dto = service.getPhoneUser(phone_id);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		Map<String, String> map = new HashMap<String,String>();
		if(dto==null) {
			map.put("phoneid", "정보없음");
			map.put("email", "정보없음");
			map.put("email_chk", "정보없음");
		}else {
			map.put("phoneid", dto.getPhone_id());
			map.put("email", dto.getEmail());
			map.put("email_chk", dto.getEmail_chk());
		}
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json = e.getMessage();
		}
		
		return json;
	}
	
	@RequestMapping(value = "/applyRatingUpForm")
	@Loginchk(role = Role.USER)
	public String applyRatingUpForm(HttpSession session, Model data) {
		// select ratingup table and this table data go to .jsp
		MemberDTO user = (MemberDTO)session.getAttribute("login");
		int user_no = user.getUser_no();
		
		List<RatingUpDTO> list = service.selectRatingInfo(user_no);
		String okdate = null;
		int num = 0;
		for (RatingUpDTO r : list) {
			okdate = r.getOkdate();
			num = r.getNum();
		}
		System.out.println("num : "+num+"\t"+"okdate : "+okdate);
		data.addAttribute("ratingUp", list);
		data.addAttribute("okdate", okdate);
		data.addAttribute("num", num);
		return "member/applyRatingForm";
	}
	
	@RequestMapping(value = "/applyRatingUp")
	@Loginchk(role = Role.USER)
	public String applyRatingUp(HttpSession session) {
		MemberDTO user = (MemberDTO)session.getAttribute("login");
		int user_no = user.getUser_no();	
		int result = service.applyRatingUp(user_no);
			
		return "redirect:/applyRatingUpForm";
	}

	@RequestMapping(value = "/updateRatingUp")
	@Loginchk(role = Role.USER)
	public String updateRatingUp(@RequestParam("num") int num, HttpSession session) {
		System.out.println(">>>>>>>>>>>"+num);
		int result = service.updateRatingUp(num);
		
		return "redirect:/applyRatingUpForm";
	}
	
	@RequestMapping(value = "/ratingUpConfirm")
	@Loginchk(role = Role.ADMIN)
	public String ratingUpConfirm(Model data) {
		List<SelectRatingDTO> list = service.selectRatingInfo();
		data.addAttribute("RatingInfo", list);
		
		return "member/ratingUpConfirm";
	}
	
	@RequestMapping(value = "/confirmRatingUp", produces="text/plain;charset=UTF-8")
	@Loginchk(role = Role.ADMIN)
	@ResponseBody
	public String confirmRatingUp(@RequestParam HashMap<String, Integer> map, HttpSession session) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		int admin_no = dto.getUser_no();
		map.put("admin_no", admin_no);
		int result = 0;
		try {
			result = service.updateRatinTX(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String mesg = "등업성공";
		if(result == 0) {
			mesg = "실패";
		}
		return mesg;
	}
	
	@RequestMapping(value = "/deleteRatingTable", produces="text/plain;charset=UTF-8")
	@Loginchk(role = Role.ADMIN)
	@ResponseBody
	public String deleteRatingTable(@RequestParam("num") int num) {
		int result = service.deleteRatingTable(num);
		String mesg = "등업 거절하였습니다.";
		if(result == 0) {
			mesg = "실패";
		}
		
		return mesg;
	}
	
}
