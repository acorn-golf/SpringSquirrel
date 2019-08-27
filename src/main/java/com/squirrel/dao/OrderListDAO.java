package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.IsOrderListDTO;
import com.squirrel.dto.view.OrderInfoDTO;

public class OrderListDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public IsOrderListDTO selectIsOrder( HashMap<String, String> map) {
		IsOrderListDTO dto = template.selectOne("OrderListMapper.selectIsOrder", map);
		return dto;
	}

	public int addOrder( HashMap<String, Object> insertVal) {
		// TODO Auto-generated method stub
		return template.insert("OrderListMapper.addOrder", insertVal);
	}

	private int totalRecord( int user_no) {
		return template.selectOne("OrderListMapper.totalRecord",user_no);
	}
	
	public PageDTO<OrderInfoDTO> selectOrderList( int user_no, int curPage) {
		PageDTO<OrderInfoDTO> pdto = new PageDTO<OrderInfoDTO>();
		pdto.setPerPage(10);
		int perPage = pdto.getPerPage();
		int offset = (curPage)*perPage;
		int totalRecord = totalRecord(user_no);
		List<OrderInfoDTO> list = template.selectList("OrderListMapper.selectOrderList", user_no, new RowBounds(offset, perPage));
		
		pdto.setList(list);
		pdto.setCurPage(curPage);
		pdto.setTotalRecord(totalRecord);
		
		return pdto;
	}

}
