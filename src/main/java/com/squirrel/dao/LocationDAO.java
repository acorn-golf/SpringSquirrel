package com.squirrel.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.squirrel.dto.LocationDTO;

public class LocationDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public List<LocationDTO> locationList() {
		List<LocationDTO> list = template.selectList("LocationMapper.LocationList");
		return list;
	}

}
