package com.squirrel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.annotation.Loginchk;
import com.annotation.Loginchk.Role;
import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.LocationDTO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.service.GolfccService;
import com.squirrel.service.LocationService;

@Controller
public class ProductController {

	@Autowired
	LocationService locService;
	
	@Autowired
	GolfccService golfService;

	@RequestMapping("productInsertForm")
	@Loginchk(role = Role.MANAGER)
	public ModelAndView productInsertForm(HttpSession session) { // 상품등록 Form 보여주기위한 메서드
		List<LocationDTO> list = locService.locationList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("LocationList", list);
		mav.setViewName("product/product");
		return mav; // M 매니저 등급이 아닌 접근시 권한없다는 메시지와 main으로 보내야함 -> 인터셉터
	}
	
	@RequestMapping(value = "productSelectGolfCC", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String productSelectGolfCC(@RequestParam("loc_ID") String loc_id) { // 상품 등록 : 지역선택시 해당 골프장 나오는 거 Ajax
		String mesg=null;
		List<GolfCcDTO> list = golfService.selectGolfccName(loc_id);
		for (GolfCcDTO g : list) {
			mesg += "<option value='"+g.getCc_id()+"'>"+g.getCc_name()+"</option>";
		}
		return mesg;
	}
	
	public String productInsert() { // 넘어오는 파라미터 이름을 productDTO 안에 인스턴스 변수랑 맞춰야됨 ㅅㅂ
		return "";
	}
	
	

}
