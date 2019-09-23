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

import com.annotation.Loginchk;
import com.annotation.Loginchk.Role;
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

	@RequestMapping(value = "/insertReviewForm") // 게시글 삽입 폼
	public ModelAndView insertReviewForm() {
		List<LocationDTO> list = locService.locationList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("LocationList", list);
		mav.setViewName("review/insertReviewForm");
		return mav;
	}

	@RequestMapping(value = "/insertReview") // 게시글 삽입
	public String insertReview(CcScoreDTO dto, HttpSession session) {
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		dto.setUser_no(mDTO.getUser_no());
		// dto.setUser_no(28);
		int n = revService.insertReview(dto);

		return "redirect:/reviewList";
		
	}

	@RequestMapping(value = "/reviewList") 
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

		if (orderby != null) { // ���İ� �缼��
			if (!orderby.equals((String) session.getAttribute("orderby"))) {
				session.setAttribute("orderby", orderby);
			}
		}

		if (ReSearchChk) {// �˻��� �缼��
			if (searchName == null) {
				// �˻����� �ƹ��͵� �Է����� ���� ��� ���ΰ˻�
				session.removeAttribute("searchName");
				session.removeAttribute("searchValue");
			} else {
				// �˻��� �Է� ��
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

		int showBlock = 5; // ������ ������ 1,2,3,4,5 // 6,7,8,9,10
		int minBlock = (curPage / (showBlock)) * showBlock;
		int maxBlock = 0;
		// System.out.println("��Ż"+totalPage+" min:"+minBlock+" showBlock:"+showBlock);
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
		// golfcc �ڼ��� ����

		mav.setViewName("review/review");
		return mav;
	}

	@RequestMapping(value = "/reviewDetail") // ����Խ��� �Խñ� �ڼ��� ����
	@Loginchk(role = Role.USER)
	public ModelAndView reviewDetail(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			HttpServletResponse response) {
		String score_no = map.get("score_no");
		//String score_no = "160"; // test�� �ӽ� score_no
		String user_no = map.get("user_no");
//		if (score_no == null && user_no == null) {
//
//		}
		CcScoreDTO dto = revService.selectDetail(Integer.parseInt(score_no));
		Cookie[] cookies = request.getCookies();

		// compare for new cookie
		Cookie viewCookie = null;
		// Save name in view cookie if same name as existing cookie
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("cookie" + score_no)) {
					viewCookie = cookies[i];
				}
			}
		}
		// if don't have cookie : Create a new cookie, name it and save
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
	
	@RequestMapping(value = "/updateReviewform") // review's update comment form
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
	
	@RequestMapping(value = "/updateReviewDetail") // update post
	public String updateReviewDetail(CcScoreDTO cdto, RedirectAttributes ra) {
		int n = revService.updateReview(cdto);
		String message = "수정되었습니다";
		if(n==0) {
			message = "수정실패";
		}
		ra.addFlashAttribute("message", message);
		return "redirect:/reviewDetail";
	}
	
	@RequestMapping(value = "/deleteReviewDeatil") // delete post
	public String deleteReviewDeatil(@RequestParam("score_no") int score_no) {
		int n = revService.deleteReview(score_no);
		return "redirect:/reviewList";
	}

}
