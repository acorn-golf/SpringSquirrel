package com.squirrel.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	ProductDAO proDao;

	public IsOrderListDTO selectIsOrder(HashMap<String, String> map) {

		return dao.selectIsOrder(map);
	}

	@Transactional
	public int addOrder(HashMap<String, Object> insertVal) throws Exception {
		// TODO Auto-generated method stub

		int result = 0;

		result = proDao.productDecrease(insertVal);
		if (result <= 0)
			throw new Exception("해당 상품은 마감되었습니다.");
		result = dao.addOrder(insertVal);

		return result;
	}

	public PageDTO<OrderInfoDTO> selectOrderList(int user_no, int curPage) {

		PageDTO<OrderInfoDTO> pdto = null;

		pdto = dao.selectOrderList(user_no, curPage);

		return pdto;
	}

	@Transactional
	public int  addOrderByCartTx(HashMap<String, Object> map) throws Exception {
		
		int result = 0;
		result = proDao.productDecrease(map);
		if (result <= 0)
			throw new Exception("해당 상품은 마감되었습니다.");
		result = dao.deletePickList(map);
		result = dao.addOrder(map);
		return 0;
	}

	public PageDTO<OrderInfoDTO> selectOrderListPayment(int user_no, int curPage) {
		PageDTO<OrderInfoDTO> pdto = null;

		pdto = dao.selectOrderListPayment(user_no, curPage);

		return pdto;
	}


}
