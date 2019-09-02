package com.squirrel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.PickListDTO;
import com.squirrel.dto.view.PickListviewDTO;
import com.squirrel.service.PickService;

@Controller
public class PickListController {

	@Autowired
	PickService pickService;
	
	@RequestMapping(value = "/pickListView")
	public ModelAndView pickListView(@RequestParam Map<String, String> map, HttpSession session) {
		MemberDTO user = (MemberDTO)session.getAttribute("login");
		int user_no = user.getUser_no();
		//int user_no = 3; // 확인을 위한 임시pk
		boolean ReSearchChk = false;
		int curPage; 
		{
			String curPageStr = map.get("curPage");
			if (curPageStr == null) {
				curPage = 0;
				ReSearchChk = true;
			}else
				curPage=Integer.parseInt(curPageStr)-1;
		}
		
		PageDTO<PickListviewDTO> pdto = pickService.selectPickList(user_no, curPage);
		List<PickListviewDTO> list = pdto.getList();
		
		int perPage = pdto.getPerPage();
		int totalRecord = pdto.getTotalRecord();
		int totalPage = totalRecord / perPage;

		if (totalRecord % (float)perPage != 0) {
			totalPage++;
		}

		int showBlock = 5; //보여질 페이지번호 수  1,2,3,4,5 // 6,7,8,9,10
		int minBlock = (curPage / (showBlock)) * showBlock;
		int maxBlock = 0;
		if (curPage == totalPage || totalPage < minBlock+showBlock) {
			maxBlock = totalPage;
		} else if (curPage < totalPage) {
			maxBlock = minBlock + showBlock;
		}
		int perBlock = 0;//totalPage/showBlock;
		if(totalPage%showBlock==0) {
			perBlock = (totalPage/showBlock)-1;
		}else {
			perBlock = totalPage/showBlock;
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("perBlock", perBlock);
		mav.addObject("minBlock", minBlock);
		mav.addObject("maxBlock", maxBlock);
		mav.addObject("showBlock", showBlock);
		mav.addObject("pickList", list);
		mav.addObject("totalPage", totalPage);
		mav.addObject("curPage", curPage);
		mav.setViewName("picklist/picklist");
		
		return mav;
	}
	
	// 한뉘야 상품 자세히보기에서 장바구니담을 때 여기다가 보내면됨
	@RequestMapping(value = "/insertPickList")
	public String insertPickList(@RequestParam Map<String, String> map, HttpSession session, RedirectAttributes data) {
		MemberDTO user = (MemberDTO)session.getAttribute("login");
		int user_no = user.getUser_no();
		PickListDTO dto = new PickListDTO();
		dto.setP_id(map.get("p_id"));
		dto.setPick_amount(Integer.parseInt(map.get("g_amount")));
		dto.setUser_no(user_no);
		int n = pickService.insertPick(dto);
		String mesg = "장바구니에 추가하였습니다";
		if(n==0) {
			mesg = "장바구니에 담지 못했습니다";
		}
		data.addFlashAttribute("pickMesg", mesg);
		return "redirect:/pickListView"; // 원래 있었던 상품 자세히 보기로 보내고 메시지 날려야하는 데 임시로 장바구니 리스트로보냄
	}
	
	@RequestMapping(value = "deletePick")
	public String deletePick(@RequestParam("check") List<Integer> list, RedirectAttributes data) {
		int n = pickService.deletePick(list);
		String mesg = "삭제되었습니다";
		if(n==0) {
			mesg = "삭제실패";
		}
		data.addFlashAttribute("pickMesg", mesg);
		return "redirect:/pickListView";
	}
	
	
}

