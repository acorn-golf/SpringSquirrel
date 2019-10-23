package com.squirrel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.annotation.Loginchk;
import com.annotation.Loginchk.Role;
import com.kr.co.bootpay.javaApache.BootpayApi;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.PageDTO;
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
			test.put("p_id", "p71"); //이거 2개만 넣으면 됨.
			test.put("g_amount", "2"); // for test
			IsOrderListDTO dto = orderService.selectIsOrder(test);
			
			mav.addObject("dto", dto);
			mav.addObject("pick_no", null);
			mav.addObject("amount", test.get("g_amount"));
			
		}
		//Runtime.getRuntime().exec("");
		BootpayApi api = new BootpayApi(
		        "5dafee3f5ade160030569ac1",
		        "IglrTcbxJHo3N6b+7FsWZaaeL1W7r9dwpE5uExZ0cjw="
		);
		try {
			String token  = api.getAccessToken();
			mav.addObject("token", token);
			System.out.println(token);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(api);
		
		mav.setViewName("orderlist/orderlist");
		return mav;
	}
	
	@RequestMapping(value = "/addOrder", produces = "text/plain;charset=utf-8")
	@Loginchk
	@ResponseBody
	public String addOrder(@RequestParam HashMap<String, Object> map, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		int user_no = dto.getUser_no();
		map.put("user_no", user_no);
		// map.put("user_no", 3);
		String mesg = "";
		// need to user_no, p_id, (pick_no), o_amount, o_price is has to map 
		if(map.get("pick_no") == null) {
			System.out.println("占쏙옙품占쏙옙占쏙옙占쏙옙占쏙옙");
			System.out.println(map.get("p_id"));
			System.out.println(map.get("g_amount"));
			System.out.println(map.get("p_price"));
			
			if(map.get("o_amount")==null) {

				map.put("o_amount",Integer.parseInt((String)map.get("g_amount")));
				System.out.println(map.get("o_amount"));
				//map.put("o_price", map.get("g_amount"));
				map.put("o_price",(int)map.get("o_amount")*Integer.parseInt((String)map.get("p_price")));
			}
			
			mesg = "결제완료"; 
			try {
				int result = orderService.addOrderByCartTx(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}else {
    		System.out.println("占쏙옙袂占쏙옙占� 占쏙옙占쏙옙");
    		
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
    		mesg = "결제완료";
    		
		}
		
		return mesg;
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
	
	@RequestMapping(value = "/orderListPayment")
	public ModelAndView orderListPayment(@RequestParam Map<String, String> map, HttpSession session) {
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
		
		
		PageDTO<OrderInfoDTO> pdto = orderService.selectOrderListPayment(user_no, curPage);
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
		mav.setViewName("orderlist/orderlistviewPayment");
		return mav;
	}
	
	@RequestMapping(value = "/orderCancle")
	@Loginchk(role = Role.USER)
	public String orderCancle(@RequestParam HashMap<String, Object> map) {
		//System.out.println(map.get("o_no")+"\t"+map.get("o_amount"+map.get("o_no")));
		map.put("o_amount", map.get("o_amount"+map.get("o_no")));
		map.put("p_id", map.get("p_id"+map.get("o_no")));
		System.out.println(map.get("o_no")+"\t"+map.get("o_amount")+"\t"+map.get("p_id"));
		try {
			int result = orderService.deleteAndUpdateTx(map);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/orderList";
	}
	
}
