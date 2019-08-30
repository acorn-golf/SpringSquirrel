package com.squirrel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.PickListviewDTO;
import com.squirrel.service.PickService;

@Controller
public class PickListController {

	@Autowired
	PickService pickService;
	
	@RequestMapping(value = "/pickListView")
	public ModelAndView pickListView(@RequestParam Map<String, String> map, HttpSession session) {
//		MemberDTO user = (MemberDTO)session.getAttribute("login");
//		int user_no = user.getUser_no();
		System.out.println("호출됬니?");
		int user_no = 3; // 확인을 위한 임시pk
		boolean ReSearchChk = false;
		int curPage; // 현재페이지
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

		int showBlock = 5; // 보여줄 페이지 1,2,3,4,5 // 6,7,8,9,10
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
	
//	@RequestMapping(value = "/insertPickList")
//	public String insertPickList() {
//		
//	}
	
}

