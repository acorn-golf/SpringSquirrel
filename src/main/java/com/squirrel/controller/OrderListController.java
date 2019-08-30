package com.squirrel.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.squirrel.dto.PickListDTO;
import com.squirrel.dto.view.IsOrderListDTO;
import com.squirrel.dto.view.PickOrderListDTO;
import com.squirrel.service.OrderListService;
import com.squirrel.service.PickService;

@Controller
public class OrderListController {
	
	@Autowired
	PickService pickService;
	
	@Autowired
	OrderListService orderService;
	
	// 한뉘야 상품 자세히 보기에서 구매하기 눌렀을 때 일로오면됨
	@RequestMapping(value = "/orderConfirm")
	@ResponseBody
	public ModelAndView orderConfirm(@RequestParam HashMap<String, String> map) {
		ModelAndView mav = new ModelAndView();
		if(map.get("pick_no")!=null) {
			PickOrderListDTO dto = pickService.selectPickofNo(map);
			
			mav.addObject("dto", dto);
			mav.addObject("pick_no", dto.getPick_no());
			mav.addObject("amount", dto.getPick_amount());
			//
		}else {
			// neet to p_id, g_amount
			HashMap<String, String> test = new HashMap<String, String>();
			test.put("p_id", "p61");
			test.put("g_amount", "2"); // for test
			IsOrderListDTO dto = orderService.selectIsOrder(test);
			
			mav.addObject("dto", dto);
			mav.addObject("pick_no", null);
			mav.addObject("amount", test.get("g_amount"));
			
		}
		mav.setViewName("orderlist/orderlist");
		return mav;
	}
}
