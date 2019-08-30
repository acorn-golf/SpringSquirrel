package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.squirrel.dto.PageDTO;
import com.squirrel.dto.PickListDTO;
import com.squirrel.dto.view.PickListviewDTO;
import com.squirrel.dto.view.ProductListDTO;

@Repository
public class PickDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public int insertPick( PickListDTO dto) {
		return template.insert("PickListMapper.insertPick", dto);
		
	}

	private int totalRecord( int user_no) {
		return template.selectOne("PickListMapper.totalRecord",user_no);
	}
	
	public PageDTO<PickListviewDTO> selectPickList( int user_no, int curPage) {
		PageDTO<PickListviewDTO> pdto = new PageDTO<PickListviewDTO>();
		pdto.setPerPage(10);
		int perPage = pdto.getPerPage();
		int offset = (curPage)*perPage;
		int totalRecord = totalRecord(user_no);
		List<PickListviewDTO> list = template.selectList("PickListMapper.selectPickList", user_no, new RowBounds(offset, perPage));
		
		pdto.setList(list);
		pdto.setCurPage(curPage);
		pdto.setTotalRecord(totalRecord);
		
		return pdto;
	}

	public int deletePick( List<String> list) {
		return template.delete("PickListMapper.deletePick", list);
		
	}

}
