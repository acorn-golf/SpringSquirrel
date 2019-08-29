package com.squirrel.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.ProductDAO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.ProductDTO;
import com.squirrel.dto.view.ProductListDTO;

@Service
public class ProductService {

	@Autowired
	ProductDAO dao;

	public int productInsert(ProductDTO dto) {

		int result;
		result = dao.productInsert(dto);

		return result;

	}

	public ProductDTO productRetrieve(String p_id) {

		ProductDTO dto = null;

		dto = dao.productRetrieve(p_id);

		return dto;

	}

	public PageDTO<ProductListDTO> selectProduct(HashMap<String, String> map, int curPage) {
		PageDTO<ProductListDTO> pdto = null;

		pdto = dao.selectProduct(map, curPage);

		return pdto;
	}

	public List<ProductListDTO> adminProductSelect(HashMap<String, Object> map) {

		List<ProductListDTO> dto = null;

		dto = dao.adminProductSelect(map);

		return dto;
	}

	public int totalRecord() {
		int totalRecord = 0;

		totalRecord = dao.totalRecord();

		return totalRecord;
	}

}
