package com.squirrel.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.ReCommentDAO;
import com.squirrel.dto.ReCommentDTO;
import com.squirrel.dto.view.RecommentViewDTO;

@Service
public class ReCommentService {

	@Autowired
	ReCommentDAO dao;

	public int insertComment(ReCommentDTO dto) {

		int n = 0;

		n = dao.insertComment(dto);

		return n;
	}

	public List<RecommentViewDTO> selectRecomment(int score_no) {

		List<RecommentViewDTO> list = null;

		list = dao.selectRecomment(score_no);

		return list;
	}

	public int updateComment(HashMap<String, String> map) {

		int n = 0;

		n = dao.updateComment(map);

		return n;
	}

	public int deleteComment(String re_no) {

		int n = 0;

		n = dao.deleteComment(re_no);

		return n;
	}

}
