package com.squirrel.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.squirrel.dto.LocationDTO;

@Repository
public class LocationDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public List<LocationDTO> locationList() {
		List<LocationDTO> list = template.selectList("LocationMapper.LocationList");
		return list;
	}

}
