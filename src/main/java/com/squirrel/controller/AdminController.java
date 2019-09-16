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

import com.annotation.Loginchk;
import com.annotation.Loginchk.Role;
import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.ProductDTO;
import com.squirrel.service.AdminService;
import com.squirrel.service.MemberService;

@Controller
public class AdminController {

	@Autowired AdminService aService;	
	@Autowired MemberService mService;	
	
	@Loginchk(role = Role.ADMIN)
	@RequestMapping(value="/adminPage")	
	public String adminPage(HttpSession session, HttpServletRequest request, 
									@RequestParam HashMap<String, Object> map) {
		
		MemberDTO mDTO = (MemberDTO)session.getAttribute("login");
			
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
		int perPage = 10; // �������� ������ ����
		int start = perPage * ( (curPage) - 1 ) + 1; // Rownum�� ���� ���� ��
		int end = perPage - 1 + start; // Rownum�� ���� �� ��
		int totalRecord = 0;
		
		map.put("start", start);
		map.put("end", end);
		
		switch ( adminSelect ) {
		
			case "member" : 
				List<MemberDTO> mList = aService.adminMemberSelect(map);			
				request.setAttribute("mList", mList);
				System.out.println(mList);
				break;
				
			case "product" :
				List<ProductDTO> pList = aService.adminProductSelect(map);					
				request.setAttribute("pList", pList);
				System.out.println(pList);
				break;
				
			case "golfcc" :
				List<GolfCcDTO> gList = aService.adminGolfCcSelect(map);			
				request.setAttribute("gList", gList);
				System.out.println(gList);
				break;
		}
		
		totalRecord = aService.totalRecord(map);
		
		int endPage = totalRecord / perPage; // ������ ������			
		if( totalRecord % perPage != 0 ) {
			endPage++; // �������� ���ڸ� ��� endPage�� �ø��Ѵ�.
		}
		
		int showPage = 10; // �ѹ��� ������ ������
		int startPage = (curPage-1) / showPage * showPage + 1; // ù���� 1 �ι�² 2
		int lastPage = startPage + showPage - 1; // ù���� 10, �ι�² 20			
		if( curPage == endPage || endPage < startPage + showPage ) {
			lastPage = endPage;
		}else if (curPage < endPage) {
			lastPage = ( startPage + showPage ) - 1;
		}
		
		int beforeShow = startPage - showPage;			
		if( beforeShow < 1 ) {
			beforeShow = 1; // 0 �� �������� �� ���
		}
		
		int afterShow = beforeShow + showPage;			
		if( afterShow > totalRecord ) {
			afterShow = totalRecord; // �� �Խù��� �ʰ��� ���
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
				
		return "member/adminPageResult";
	}
	
	public String adminModified() {
		
		return "";
	}
}
