package com.squirrel.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.squirrel.dao.LocationDAO;
import com.squirrel.dto.LocationDTO;

public class LocationService {

	@Autowired
	LocationDAO dao;
	
	public List<LocationDTO> locationList() {

		return dao.locationList();
	}
	
	
}
