package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.TestDTO;



@Repository
public class TestDAO {
	
	SqlSessionTemplate template;

	public int countList(String searchOption, String keyword) {
		
		return template.selectOne("TestMapper.countList");
	}
	
	public List<GolfCcDTO> listAll(int start, int end, String searchOption, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		return template.selectList("TestMapper.listAll", map);
	}

	

}
