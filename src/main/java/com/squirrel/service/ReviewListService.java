package com.squirrel.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.ReviewListDAO;
import com.squirrel.dto.CcScoreDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.ReviewListDTO;

@Service
public class ReviewListService {

	@Autowired
	ReviewListDAO dao;

	public PageDTO<ReviewListDTO> reviewList(HashMap<String, String> map, int curPage) {
		PageDTO<ReviewListDTO> pDTO = null;

		pDTO = dao.reviewList(map, curPage);

		return pDTO;
	}

	public int insertReview(CcScoreDTO dto) {

		return dao.insertReview(dto);

	}

	public CcScoreDTO selectDetail(int score_no) {

		CcScoreDTO dto = null;

		dto = dao.selectDetail(score_no);

		return dto;
	}

	public ReviewListDTO selectNickname(int score_no) {

		ReviewListDTO rdto = null;

		rdto = dao.selectNickname(score_no);

		return rdto;
	}

	public int updateReview(CcScoreDTO cdto) {

		return dao.updateReview(cdto);

	}

	public int deleteReview(int score_no) {

		return dao.deleteReview(score_no);

	}

	public int rv_vcount(int score_no) {

		return dao.rv_vcount(score_no);

	}

}
