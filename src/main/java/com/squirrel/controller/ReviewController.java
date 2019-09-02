package com.squirrel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.squirrel.dto.CcScoreDTO;
import com.squirrel.dto.LocationDTO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.RecommentViewDTO;
import com.squirrel.dto.view.ReviewListDTO;
import com.squirrel.service.LocationService;
import com.squirrel.service.ReCommentService;
import com.squirrel.service.ReviewListService;

@Controller
public class ReviewController {

	@Autowired
	LocationService locService;

	@Autowired
	ReviewListService revService;
	
	@Autowired
	ReCommentService recService;

	@RequestMapping(value = "/insertReviewForm") // 리뷰 보여주는 폼
	public ModelAndView insertReviewForm() {
		List<LocationDTO> list = locService.locationList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("LocationList", list);
		mav.setViewName("review/insertReviewForm");
		return mav;
	}

	@RequestMapping(value = "/insertReview") // 리뷰 삽입
	public String insertReview(CcScoreDTO dto, HttpSession session) {
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		dto.setUser_no(mDTO.getUser_no());
		// dto.setUser_no(28); // 확인을 위한 임시pk
		int n = revService.insertReview(dto);

		return "redirect:/reviewList";
		// 리뷰게시판 메서드로 보냄
	}

	@RequestMapping(value = "/reviewList") // 리뷰게시판
	public ModelAndView reviewList(@RequestParam Map<String, String> reqParam, HttpSession session) {
		boolean ReSearchChk = false;
		int curPage;
		{
			String curPageStr = reqParam.get("curPage");
			if (curPageStr == null) {
				curPage = 0;
				ReSearchChk = true;
			} else
				curPage = Integer.parseInt(curPageStr) - 1;
		}
		String cc_id = reqParam.get("cc_id");
		if (cc_id != null)
			session.setAttribute("Rcc_id", cc_id);
		else
			cc_id = (String) session.getAttribute("Rcc_id");

		String searchName = reqParam.get("searchName");
		String searchValue = reqParam.get("searchValue");
		String orderby = reqParam.get("orderby");

		if (orderby != null) { // 정렬값 재세팅
			if (!orderby.equals((String) session.getAttribute("orderby"))) {
				session.setAttribute("orderby", orderby);
			}
		}

		if (ReSearchChk) {// 검색값 재세팅
			if (searchName == null) {
				// 검색값을 아무것도 입력하지 않은 경우 전부검색
				session.removeAttribute("searchName");
				session.removeAttribute("searchValue");
			} else {
				// 검색값 입력 시
				session.setAttribute("searchName", searchName);
				session.setAttribute("searchValue", searchValue);
			}
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cc_id", cc_id);
		map.put("searchName", (String) session.getAttribute("searchName"));
		map.put("searchValue", (String) session.getAttribute("searchValue"));
		map.put("orderby", (String) session.getAttribute("orderby"));

		PageDTO<ReviewListDTO> pDTO = revService.reviewList(map, curPage);
		List<ReviewListDTO> list = pDTO.getList();

		int perPage = pDTO.getPerPage();
		int totalRecord = pDTO.getTotalRecord();
		int totalPage = totalRecord / perPage;

		if (totalRecord % (float) perPage != 0) {
			totalPage++;
		}

		int showBlock = 5; // 보여줄 페이지 1,2,3,4,5 // 6,7,8,9,10
		int minBlock = (curPage / (showBlock)) * showBlock;
		int maxBlock = 0;
		// System.out.println("토탈"+totalPage+" min:"+minBlock+" showBlock:"+showBlock);
		if (curPage == totalPage || totalPage < minBlock + showBlock) {
			maxBlock = totalPage;
		} else if (curPage < totalPage) {
			maxBlock = minBlock + showBlock;
		}

		int perBlock = 0;// totalPage/showBlock;
		if (totalPage % showBlock == 0) {
			perBlock = (totalPage / showBlock) - 1;
		} else {
			perBlock = totalPage / showBlock;
		}

		ModelAndView mav = new ModelAndView();

		mav.addObject("perBlock", perBlock);
		mav.addObject("minBlock", minBlock);
		mav.addObject("maxBlock", maxBlock);
		mav.addObject("showBlock", showBlock);
		mav.addObject("reviewList", list);
		mav.addObject("totalPage", totalPage);
		mav.addObject("curPage", curPage);
		mav.addObject("cc_id", cc_id);

		// GolfCCcurPage
		{
			// by hanyi

		}
		// golfcc 자세히 보기

		mav.setViewName("review/review");
		return mav;
	}

	@RequestMapping(value = "/reviewDetail") // 리뷰게시판 게시글 자세히 보기
	public ModelAndView reviewDetail(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			HttpServletResponse response) {
		//String score_no = map.get("score_no");
		String score_no = "140"; // test용 임시 score_no
		//String user_no = map.get("user_no");
//		if (score_no == null && user_no == null) {
//
//		}
		CcScoreDTO dto = revService.selectDetail(Integer.parseInt(score_no));
		Cookie[] cookies = request.getCookies();

		// 비교하기 위한 새로운 쿠키
		Cookie viewCookie = null;
		// 쿠키가 있을 경우 : 존재하는 쿠키와 비교할 이름이 같으면 viewCookie에 이름 저장
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("cookie" + score_no)) {
					viewCookie = cookies[i];
				}
			}
		}
		// 쿠키가 없을 경우 : 새 쿠키를 생성하여 이름을 넣고 쿠키를 저장, 쿠키 저장시간 : 24시간, 조회수 증가
		if (viewCookie == null) {
			Cookie newCookie = new Cookie("cookie" + score_no, score_no);
			response.addCookie(newCookie);
			newCookie.setMaxAge(1 * 24 * 60 * 60);
			revService.rv_vcount(Integer.parseInt(score_no));
		}
		ReviewListDTO rdto = revService.selectNickname(Integer.parseInt(score_no));
		String nickname = rdto.getNickname();
		List<RecommentViewDTO> list = recService.selectRecomment(Integer.parseInt(score_no));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewdetail", dto);
		mav.addObject("recommentList", list);
		mav.addObject("nickname", nickname);
		mav.setViewName("review/reviewDetail");
		return mav;
	}
	
	@RequestMapping(value = "/updateReviewform") // 리뷰게시판에서의 수정 폼
	public ModelAndView updateReviewform(@RequestParam("score_no") int score_no) {
				
		CcScoreDTO cdto = revService.selectDetail(score_no);
		ReviewListDTO rdto = revService.selectNickname(score_no);
		String nickname = rdto.getNickname();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewdetail", cdto);
		mav.addObject("nickname", nickname);
		mav.setViewName("review/updateReviewform");
		return mav;
	}

}
