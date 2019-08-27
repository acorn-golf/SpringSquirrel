package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.squirrel.dto.ReCommentDTO;
import com.squirrel.dto.view.RecommentViewDTO;

public class ReCommentDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public int insertComment( ReCommentDTO dto) {
		int n = template.insert("ReCommentMapper.insertComment", dto);
		return n;
	}

	public List<RecommentViewDTO> selectRecomment( int score_no) {
		List<RecommentViewDTO> list = template.selectList("ReCommentMapper.selectRecomment", score_no);
		return list;
	}

	public int updateComment( HashMap<String, String> map) {
		int n = template.update("ReCommentMapper.updateComment", map);
		return n;
	}

	public int deleteComment( String re_no) {
		int n = template.update("ReCommentMapper.deleteComment", re_no);
		return n;
	}

}
