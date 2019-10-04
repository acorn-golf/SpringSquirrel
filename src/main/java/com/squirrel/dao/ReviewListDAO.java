package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.squirrel.dto.CcScoreDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.MyReviewDTO;
import com.squirrel.dto.view.ProductListDTO;
import com.squirrel.dto.view.ReviewListDTO;

@Repository
public class ReviewListDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	private int totalRecord( HashMap<String, String> map) {
		return template.selectOne("ReviewViewMapper.totalRecord",map);
	}
	
	public PageDTO<ReviewListDTO> reviewList( HashMap<String, String> map, int curPage) {
		PageDTO<ReviewListDTO> pDTO = new PageDTO<ReviewListDTO>();
		pDTO.setPerPage(10);
		int perPage = pDTO.getPerPage();
		int offset = (curPage)*perPage;
		int totalRecord = totalRecord(map);
		List<ReviewListDTO> list = template.selectList("ReviewViewMapper.reviewList", map,new RowBounds(offset, perPage));
		
		pDTO.setList(list);
		pDTO.setCurPage(curPage);
		pDTO.setTotalRecord(totalRecord);
		return pDTO;
	}

	public int insertReview( CcScoreDTO dto) {
		return template.insert("ReviewMapper.insertReview", dto);
		
	}

	public CcScoreDTO selectDetail( int score_no) {
		CcScoreDTO dto = template.selectOne("ReviewMapper.selectDetail", score_no);
		return dto;
	}
	
	public int rv_vcount( int score_no) {
		return template.update("ReviewMapper.rv_vcount", score_no);
	}

	public ReviewListDTO selectNickname( int score_no) {
		ReviewListDTO rdto = template.selectOne("ReviewViewMapper.selectNick", score_no);
		return rdto;
	}

	public int updateReview( CcScoreDTO cdto) {
		return template.update("ReviewMapper.updateReview", cdto);
		
	}

	public int deleteReview( int score_no) {
		return template.delete("ReviewMapper.deleteReview", score_no);
		
	}

	private int myReviewtotalRecord( HashMap<String, String> map) {
		return template.selectOne("ReviewMapper.myReviewtotalRecord",map);
	}
	
	public PageDTO<MyReviewDTO> selectMyReview(HashMap<String, String> map, int curPage) {
		PageDTO<MyReviewDTO> pdto = new PageDTO<MyReviewDTO>();
		pdto.setPerPage(10);
		int perPage = pdto.getPerPage();
		int offset = (curPage)*perPage;
		int totalRecord = myReviewtotalRecord(map);
		List<MyReviewDTO> list = template.selectList("ReviewMapper.selectMyReview", map, new RowBounds(offset, perPage));
		
		pdto.setList(list);
		pdto.setCurPage(curPage);
		pdto.setTotalRecord(totalRecord);
		
		return pdto;
	}
	
}
