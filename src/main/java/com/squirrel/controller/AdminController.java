package com.squirrel.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.ProductDTO;
import com.squirrel.service.AdminService;
import com.squirrel.service.MemberService;

@Controller
public class AdminController {

	@Autowired AdminService aService;	
	@Autowired MemberService mService;	
	
	@RequestMapping(value="/adminPage", method=RequestMethod.POST)
	public String adminPage(HttpSession session, HttpServletRequest request, 
									@RequestParam HashMap<String, Object> map) {
		
		MemberDTO mDTO = (MemberDTO)session.getAttribute("login");
		String destination = null;

		if( !mDTO.getRating().equals("A")) {
			session.invalidate();
			destination = "redirect:member/login";			
		}else {
			int user_no = mDTO.getUser_no();			
			mDTO = mService.myPage(user_no);
			
			String curPage = null;
			String adminSelect = (String)map.get("adminSelect");
			String adminSearch = (String)map.get("adminSearch");
			
			if( map.get("curPage") == null ) { curPage = "1"; }
			
			int perPage = 10;
			int start = perPage * ((Integer.parseInt(curPage))-1)+1 ;
			int end = perPage-1 + start;
			int totalRecord = 0;
			
			map.put("start", start);
			map.put("end", end);
			
			switch (adminSelect) {
				
				case "member" : 
					List<MemberDTO> mList = aService.adminMemberSelect(map);
					totalRecord = aService.totalRecord(map);
					request.setAttribute("mList", mList);
					break;
					
				case "product" :
					List<ProductDTO> pList = aService.adminProductSelect(map);
					totalRecord = aService.totalRecord(map);
					request.setAttribute("pList", pList);
					break;
					
				case "golfcc" :
					List<GolfCcDTO> gList = aService.adminGolfCcSelect(map);
					totalRecord = aService.totalRecord(map);
					request.setAttribute("gList", gList);
					break;
			}
		
			request.setAttribute("adminSelect", adminSelect);
			request.setAttribute("adminSearch", adminSearch);
			
			
			
			
			
			
			destination = "member/adminPageResult";
		}
		
		
		return destination;
	}
	
}
