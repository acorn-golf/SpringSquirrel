package com.squirrel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.PagingDTO;
import com.squirrel.dto.TestDTO;
import com.squirrel.service.TestService;




@Controller
public class TestController {
	
	@Autowired
	TestService service;
	
	@RequestMapping("/test")
	public ModelAndView list(@RequestParam(defaultValue = "title") String searchOption,
							@RequestParam(defaultValue = "") String keyword,
							@RequestParam(defaultValue = "1") int curPage) {
		
		// 레코드의 개수 계산
		int count = service.countList(searchOption,keyword);
		
		
		PagingDTO pdto = new PagingDTO(count, curPage);
		int start = pdto.getPageBegin();
		int end = pdto.getPageEnd();
		
		List<GolfCcDTO> list = service.listAll(start,end,searchOption,keyword);
		for (GolfCcDTO t : list) {
			System.out.println(t);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list",list); // list
		map.put("count",count); // 레코드 개수
		map.put("searchOption",searchOption); // 검색옵션
		map.put("keyword",keyword); // 검색 키워드
		map.put("pdto",pdto);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.getViewName();
		return mav;
	}
	
}
