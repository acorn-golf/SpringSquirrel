package com.squirrel.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.NoticeDAO;
import com.squirrel.dao.ProductDAO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.NoticeListDTO;
import com.squirrel.dto.ProductDTO;

@Service
public class NoticeService {
	@Autowired
	NoticeDAO dao;

	public int NoticeInsert(NoticeListDTO ndto) {

		int result = 	dao.NoticeInsert(ndto);

		return result;
	
		
	
	}
	
	public List<NoticeListDTO> notelist() {
		return dao.notelist();
	}

	public List<NoticeListDTO> noteconent(int note_no) {
		return dao.notecontent(note_no);
	}

	public List<NoticeListDTO> noteAdd() {
		return dao.noteAdd();
	}
	
}
