package com.squirrel.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.GolfccDAO;
import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.CcGolfScoreDTO;

@Service
public class GolfccService {
	
	@Autowired
	GolfccDAO dao;


	public List<GolfCcDTO> selectGolfccName(String loc_id) {


		return dao.selectGolfccName(loc_id);
	}
	
	public List<CcGolfScoreDTO> ccGolfScoreList(HashMap<String, Object> searchVal ) {
		
		return dao.ccGolfScoreList(searchVal);
	}

	
	public PageDTO<CcGolfScoreDTO> ccGolfScoreList(HashMap<String, Object> searchVal, PageDTO<CcGolfScoreDTO> pageDto) {
		// TODO Auto-generated method stub

			pageDto.setTotalRecord(dao.ccGolfScoreListCount(searchVal));
			pageDto = dao.ccGolfScoreList(searchVal,pageDto);

		return pageDto;
	}

	public CcGolfScoreDTO getGolfccScoreOne(String cc_id) {
		// TODO Auto-generated method stub
		
		return dao.getGolfccScoreOne(cc_id);
	}

	public void LikeGolfccRemove(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	public void LikeGolfccAdd(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	public List<GolfCcDTO> adminGolfSelect(HashMap<String, Object> map) {

		return dao.adminGolfSelect(map);
	}

	public int totalRecord() {

		return dao.totalRecord();
	}

}
