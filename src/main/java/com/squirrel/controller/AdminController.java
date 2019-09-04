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
	
	@RequestMapping(value="/adminPage")
	public String adminPage(HttpSession session, HttpServletRequest request, 
									@RequestParam HashMap<String, Object> map) {
		
		MemberDTO mDTO = (MemberDTO)session.getAttribute("login");
		String destination = null;
	
		if( !mDTO.getRating().equals("A") ) {
			
			session.invalidate();
			destination = "redirect:member/loginForm";	
			
		}else {
			
			int user_no = mDTO.getUser_no();			
			mDTO = mService.myPage(user_no);			
			
			String curPageStr = (String)map.get("curPage");
			String adminSelect = (String)map.get("adminSelect");
			String adminSearch = (String)map.get("adminSearch");
			
			System.out.println(">>>>"+adminSearch);
			if( curPageStr == null ) { 
				curPageStr = "1"; 
			}
			
			int curPage = Integer.parseInt(curPageStr);				
			int perPage = 10; // 페이지당 보여줄 개수
			int start = perPage * ( (curPage) - 1 ) + 1; // Rownum에 사용될 시작 값
			int end = perPage - 1 + start; // Rownum에 사용될 끝 값
			int totalRecord = 0;
			
			map.put("start", start);
			map.put("end", end);
			
			switch ( adminSelect ) {
			
				case "member" : 
					List<MemberDTO> mList = aService.adminMemberSelect(map);			
					request.setAttribute("mList", mList);
					break;
					
				case "product" :
					List<ProductDTO> pList = aService.adminProductSelect(map);					
					request.setAttribute("pList", pList);
					break;
					
				case "golfcc" :
					List<GolfCcDTO> gList = aService.adminGolfCcSelect(map);			
					request.setAttribute("gList", gList);
					break;
			}
			
			totalRecord = aService.totalRecord(map);
			
			int endPage = totalRecord / perPage; // 마지막 페이지			
			if( totalRecord % perPage != 0 ) {
				endPage++; // 페이지가 모자를 경우 endPage를 올림한당.
			}
			
			int showPage = 10; // 한번에 보여줄 페이지
			int startPage = (curPage-1) / showPage * showPage + 1; // 첫장은 1 두번짼 2
			int lastPage = startPage + showPage - 1; // 첫장은 10, 두번짼 20			
			if( curPage == endPage || endPage < startPage + showPage ) {
				lastPage = endPage;
			}else if (curPage < endPage) {
				lastPage = ( startPage + showPage ) - 1;
			}
			
			int beforeShow = curPage - showPage;			
			if( beforeShow < 1 ) {
				beforeShow = 1; // 0 는 음수값이 될 경우
			}
			
			int afterShow = beforeShow + showPage;			
			if( afterShow > totalRecord ) {
				afterShow = totalRecord; // 총 게시물을 초과할 경우
			}
			
			request.setAttribute("adminSelect", adminSelect);
			request.setAttribute("adminSearch", adminSearch);
			request.setAttribute("curPage", curPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("showPage", showPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("beforeShow", beforeShow);
			request.setAttribute("afterShow", afterShow);		
			
			session.setAttribute("login", mDTO);
			
			destination = "member/adminPageResult";
		}		
		
		return destination;
	}
	
}
