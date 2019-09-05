package com.squirrel.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.NoticeListDTO;
import com.squirrel.dto.ProductDTO;


@Repository
public class NoticeDAO {

	@Autowired
	SqlSessionTemplate template;
	
		public int NoticeInsert(NoticeListDTO ndto) {

			return template.insert("NoticeMapper.NoticeInsert", ndto);
	}

		public List<NoticeListDTO> notelist() {
			// TODO Auto-generated method stub
			return template.selectList("NoticeMapper.NoteView");
		}

		public List<NoticeListDTO> notecontent() {
			
		return template.selectList("NoticeMapper.NoteContent");
		}

		public List<NoticeListDTO> noteAdd() {
			
			return template.selectList("NoticeMapper.NoticeInsert");
		}
}
