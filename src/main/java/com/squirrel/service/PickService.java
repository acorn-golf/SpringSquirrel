package com.squirrel.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.PickDAO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.PickListDTO;
import com.squirrel.dto.view.PickListviewDTO;

@Service
public class PickService {

	@Autowired
	PickDAO dao;

	public int insertPick(PickListDTO dto) {

		int result = dao.insertPick(dto);

		return result;
	}

	public PageDTO<PickListviewDTO> selectPickList(int user_no, int curPage) {

		PageDTO<PickListviewDTO> pdto = null;

		pdto = dao.selectPickList(user_no, curPage);

		return pdto;
	}

	public int deletePick(List<String> list) {

		int result =

				dao.deletePick(list);

		return result;
	}

}
