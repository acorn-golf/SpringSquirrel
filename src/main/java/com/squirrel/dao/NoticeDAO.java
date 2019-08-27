package com.squirrel.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.NoticeListDTO;
import com.squirrel.dto.ProductDTO;



public class NoticeDAO {

	@Autowired
	SqlSessionTemplate template;
	
		public int NoticeInsert(NoticeListDTO ndto) {

			return template.insert("NoticeMapper.NoticeInsert", ndto);
	}
}
