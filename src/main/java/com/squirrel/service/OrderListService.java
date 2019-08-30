package com.squirrel.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.OrderListDAO;
import com.squirrel.dao.ProductDAO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.IsOrderListDTO;
import com.squirrel.dto.view.OrderInfoDTO;

@Service
public class OrderListService {

	@Autowired
	OrderListDAO dao;

	@Autowired
	ProductDAO productDAO;

	public IsOrderListDTO selectIsOrder(HashMap<String, String> map) {

		return dao.selectIsOrder(map);
	}

	public int addOrder(HashMap<String, Object> insertVal) throws Exception {
		// TODO Auto-generated method stub

		int result = 0;

		result = productDAO.productDecrease(insertVal);
		if (result <= 0)
			throw new Exception("최대인원 넘음");
		result = dao.addOrder(insertVal);

		return result;
	}

	public PageDTO<OrderInfoDTO> selectOrderList(int user_no, int curPage) {

		PageDTO<OrderInfoDTO> pdto = null;

		pdto = dao.selectOrderList(user_no, curPage);

		return pdto;
	}

}
