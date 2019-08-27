package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import com.squirrel.dto.GolfCcDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.CcGolfScoreDTO;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class GolfccDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public List<GolfCcDTO> selectGolfccName( String loc_id) {
		List<GolfCcDTO> list = template.selectList("GolfccMapper.selectCCname", loc_id);
		return list;
	}

	public List<CcGolfScoreDTO> ccGolfScoreList( HashMap<String, Object> searchVal) {
		// TODO Auto-generated method stub
		return template.selectList("GolfccMapper.ccGolfScoreList", searchVal);
	}

	public PageDTO<CcGolfScoreDTO> ccGolfScoreList( HashMap<String, Object> searchVal,
			PageDTO<CcGolfScoreDTO> pageDto) {
		// TODO Auto-generated method stub
		RowBounds bounds = new RowBounds(pageDto.getCurPage()*pageDto.getPerPage(), pageDto.getPerPage());
		pageDto.setList(template.selectList("GolfccMapper.ccGolfScoreList",searchVal,bounds));
		return pageDto;
	}
	
	public int ccGolfScoreListCount( HashMap<String, Object> searchVal) {
		return template.selectOne("GolfccMapper.ccGolfScoreListCount",searchVal);
	}

	public CcGolfScoreDTO getGolfccScoreOne( String cc_id) {
		// TODO Auto-generated method stub
		return template.selectOne("GolfccMapper.getGolfccScoreOne",cc_id);
	}

	public int totalRecord() {
		
		int totalRecord = template.selectOne("GolfccMapper.totalRecord");
		
		return totalRecord;
	}
	public List<GolfCcDTO> adminGolfSelect(HashMap<String, Object> map) {
		
		List<GolfCcDTO> list = template.selectList("GolfccMapper.adminGolfSelect",map);
		
		return list;
	}

}
