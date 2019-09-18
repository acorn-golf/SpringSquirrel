package com.squirrel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.TestDAO;
import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.TestDTO;



@Service
public class TestService {
	
	@Autowired
	TestDAO dao;
	
	public int countList(String searchOption, String keyword) {
		// TODO Auto-generated method stub
		return dao.countList(searchOption, keyword);
	}
	
	public List<GolfCcDTO> listAll(int start, int end, String searchOption, String keyword) {
		return dao.listAll(start, end, searchOption, keyword);
	}

	

}
