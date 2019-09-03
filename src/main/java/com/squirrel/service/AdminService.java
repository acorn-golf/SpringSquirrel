package com.squirrel.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.AdminDAO;
import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.ProductDTO;

@Service
public class AdminService {
	
	@Autowired
	AdminDAO dao;

	public List<MemberDTO> adminMemberSelect(HashMap<String, Object> map) {
		
		return dao.adminMemberSelect(map);
	}

	public int totalRecord(HashMap<String, Object> map) {
				
		return dao.totalRecord(map);
	}

	public List<ProductDTO> adminProductSelect(HashMap<String, Object> map) {
		
		return dao.adminProductSelect(map);
	}

	public List<GolfCcDTO> adminGolfCcSelect(HashMap<String, Object> map) {
		
		return dao.adminGolfCcSelect(map);
	}

}
