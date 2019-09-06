package com.squirrel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.annotation.Loginchk;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.PickListDTO;
import com.squirrel.dto.view.IsOrderListDTO;
import com.squirrel.dto.view.OrderInfoDTO;
import com.squirrel.dto.view.PickOrderListDTO;
import com.squirrel.service.OrderListService;
import com.squirrel.service.PickService;

@Controller
public class OrderListController {
	
	@Autowired
	PickService pickService;
	
	@Autowired
	OrderListService orderService;
	
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
	
	@RequestMapping(value = "/addOrder")
	@Loginchk
	public String addOrder(@RequestParam HashMap<String, Object> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		int user_no = dto.getUser_no();
		map.put("user_no", user_no);
		// map.put("user_no", 3);
		String goingPage = null;
		// need to user_no, p_id, (pick_no), o_amount, o_price is has to map 
		if(map.get("pick_no") == null) {
			System.out.println("��ǰ��������");
			System.out.println(map.get("p_id"));
			System.out.println(map.get("o_amount"));
			System.out.println(map.get("o_price"));
			
			if(map.get("o_amount")!=null) {
				map.put("o_amount", map.get("g_amount"));
				//map.put("o_price", map.get("g_amount"));
			}
			
			goingPage = "redirect:"; 
			try {
				int result = orderService.addOrder(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}else {
    		System.out.println("��ٱ��� ����");
    		
    		System.out.println(map.get("pick_no"));
    		System.out.println(map.get("p_id"));
    		System.out.println(map.get("o_amount"));
    		System.out.println(map.get("o_price"));
    		
    		try {
				int result = orderService.addOrderByCartTx(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		goingPage = "redirect:/orderList";
    		
		}
		
		return goingPage;
	}

	@RequestMapping(value = "/orderList")
	public ModelAndView orderList(@RequestParam Map<String, String> map, HttpSession session) {
		MemberDTO user = (MemberDTO)session.getAttribute("login");
		int user_no = user.getUser_no();
		//int user_no = 3; 
		
		int curPage; 
		{
			String curPageStr = map.get("curPage");
			if (curPageStr == null) {
				curPage = 0;
			}else
				curPage=Integer.parseInt(curPageStr)-1;
		}
		
		
		PageDTO<OrderInfoDTO> pdto = orderService.selectOrderList(user_no, curPage);
		List<OrderInfoDTO> list = pdto.getList();
		
		int perPage = pdto.getPerPage();
		int totalRecord = pdto.getTotalRecord();
		int totalPage = totalRecord / perPage;

		if (totalRecord % (float)perPage != 0) {
			totalPage++;
		}

		int showBlock = 5; // for show page 1,2,3,4,5 // 6,7,8,9,10
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
		mav.addObject("orderList", list);
		mav.addObject("totalPage", totalPage);
		mav.addObject("curPage", curPage);
		mav.setViewName("orderlist/orderlistview");
		return mav;
	}
	
}
