package com.squirrel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.annotation.Loginchk;
import com.annotation.Loginchk.Role;
import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.LocationDTO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.ProductDTO;
import com.squirrel.dto.view.ProductListDTO;
import com.squirrel.service.GolfccService;
import com.squirrel.service.LocationService;
import com.squirrel.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	LocationService locService;

	@Autowired
	GolfccService golfService;

	@Autowired
	ProductService proService;

	@RequestMapping("productInsertForm")
	// @Loginchk(role = Role.MANAGER)
	public ModelAndView productInsertForm(HttpSession session) { // 상품 등록 Form, 인터셉터에서 M등급이 아닌접근 -> main으로 보내고 권한없음 메시지
																	// 띄우기

		List<LocationDTO> list = locService.locationList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("LocationList", list);
		mav.setViewName("product/product");
		return mav; //
	}

	@RequestMapping(value = "productSelectGolfCC", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String productSelectGolfCC(@RequestParam("loc_ID") String loc_id) { // �긽�뭹 �벑濡� : 吏��뿭�꽑�깮�떆 �빐�떦 怨⑦봽�옣
																				// �굹�삤�뒗 嫄� Ajax
		String mesg = null;
		List<GolfCcDTO> list = golfService.selectGolfccName(loc_id);
		for (GolfCcDTO g : list) {
			mesg += "<option value='" + g.getCc_id() + "'>" + g.getCc_name() + "</option>";
		}
		return mesg;
	}

	@RequestMapping(value = "/productInsert")
	public String productInsert(ProductDTO dto, HttpSession session) { 
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		// dto.setUser_no(mDTO.getUser_no());
		dto.setUser_no(3); // 임시 확인을 위함
		dto.setP_pdate(dto.getP_pdate().replace('T', '/'));
		if (dto.getP_babyn() == null) {
			dto.setP_babyn("N");
		} else {
			dto.setP_babyn("Y");
		}
		if (dto.getP_cartyn() == null) {
			dto.setP_cartyn("N");
		} else {
			dto.setP_cartyn("Y");
		}
		int n = proService.productInsert(dto);
		
		return "redirect:/productList"; // 상품이 등록되었으면 등록성공/실패 여부를 메시지로 띄우고 productList로 감
	}

	@RequestMapping(value = "/productList", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView productList(@RequestParam Map<String, String> reqParam, HttpSession session) {
		System.out.println(reqParam);
		boolean ReSearchChk = false;
		int curPage; // 현재페이지
		System.out.println(reqParam.get("curPage"));
		{
			String curPageStr = reqParam.get("curPage");
			if (curPageStr == null) {
				curPage = 0;
				ReSearchChk = true;
			} else
				curPage = Integer.parseInt(curPageStr) - 1;
		}

		if (reqParam.get("productOrderby") != null) { // 정렬값 재세팅
			if (!reqParam.get("productOrderby").equals((String) session.getAttribute("productOrderby"))) {
				session.setAttribute("productOrderby", reqParam.get("productOrderby"));
			}
		}

		if (reqParam.get("emergency") != null) { // 긴급상품 재세팅
			session.setAttribute("emergency", reqParam.get("emergency"));
		} else {
			session.removeAttribute("emergency");
		}

		if (ReSearchChk) {// 검색값 재세팅
			if (reqParam.get("productDivision") == null) {
				// 검색값을 아무것도 입력하지 않은 경우 전부검색
				session.removeAttribute("productDivision");
				session.removeAttribute("productValue");
			} else {
				// 검색값 입력 시
				session.setAttribute("productDivision", reqParam.get("productDivision"));
				session.setAttribute("productValue", reqParam.get("productValue"));
			}
			if (reqParam.get("loc_id") == null || reqParam.get("loc_id").equals("all")) { // 지역별 재세팅
				session.removeAttribute("loc_id");
			} else {
				session.setAttribute("loc_id", reqParam.get("loc_id"));
			}
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("loc_id", (String) session.getAttribute("loc_id"));
		map.put("emergency", (String) session.getAttribute("emergency"));
		map.put("productDivision", (String) session.getAttribute("productDivision"));
		map.put("productValue", (String) session.getAttribute("productValue"));
		map.put("productOrderby", (String) session.getAttribute("productOrderby"));

		PageDTO<ProductListDTO> pDTO = proService.selectProduct(map, curPage);
		List<ProductListDTO> list = pDTO.getList();
		
		int perPage = pDTO.getPerPage();
		int totalRecord = pDTO.getTotalRecord();
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
		mav.addObject("productList", list);
		mav.addObject("totalPage", totalPage);
		mav.addObject("curPage", curPage);
		mav.setViewName("product/productList");
		return mav;
	}

}
