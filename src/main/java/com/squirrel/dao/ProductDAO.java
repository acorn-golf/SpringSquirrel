package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.squirrel.dto.PageDTO;
import com.squirrel.dto.ProductDTO;
import com.squirrel.dto.view.OrderInfoDTO;
import com.squirrel.dto.view.ProductDealHistoryDTO;
import com.squirrel.dto.view.ProductListDTO;

@Repository
public class ProductDAO {
	
	@Autowired
	SqlSessionTemplate template;

	public int productInsert( ProductDTO dto) {
		return template.insert("ProductMapper.productInsert", dto);
	}
	
	private int totalRecord( HashMap<String, String> map) {
		return template.selectOne("ProductMapper.totalRecord",map);
	}
	
	public PageDTO<ProductListDTO> selectProduct( HashMap<String, String> map, int curPage) {
		PageDTO<ProductListDTO> pdto = new PageDTO<ProductListDTO>();
		pdto.setPerPage(5);
		int perPage = pdto.getPerPage();
		int offset = (curPage)*perPage;
		int totalRecord = totalRecord(map);
		List<ProductListDTO> list = template.selectList("ProductMapper.selectProduct", map, new RowBounds(offset, perPage));
		
		pdto.setList(list);
		pdto.setCurPage(curPage);
		pdto.setTotalRecord(totalRecord);
		
		return pdto;
	}
	
	

	public ProductDTO productRetrieve( String p_id) {
		// TODO Auto-generated method stub
		return template.selectOne("productRetrieve", p_id);
	}

	public int productDecrease( HashMap<String, Object> insertVal) {
		// TODO Auto-generated method stub
		return template.update("ProductMapper.productDecrease",insertVal);
	}


	private int totalRecordDealHistory(int user_no) {
		return template.selectOne("ProductMapper.totalRecordDealHistory",user_no);
	}
	
	public PageDTO<ProductDealHistoryDTO> selectDealHistory(int user_no, int curPage) {
		PageDTO<ProductDealHistoryDTO> pdto = new PageDTO<ProductDealHistoryDTO>();
		pdto.setPerPage(3);
		int perPage = pdto.getPerPage();
		int offset = (curPage)*perPage;
		int totalRecord = totalRecordDealHistory(user_no);
		List<ProductDealHistoryDTO> list = template.selectList("ProductMapper.selectDealHistory", user_no, new RowBounds(offset, perPage));
		
		pdto.setList(list);
		pdto.setCurPage(curPage);
		pdto.setTotalRecord(totalRecord);
		
		return pdto;
	}

	private int totalRecordEditProduct(int user_no) {
		return template.selectOne("ProductMapper.totalRecordEditProduct",user_no);
	}
	
	public PageDTO<ProductListDTO> selectEditProduct(int user_no, int curPage) {
		PageDTO<ProductListDTO> pdto = new PageDTO<ProductListDTO>();
		pdto.setPerPage(3);
		int perPage = pdto.getPerPage();
		int offset = (curPage)*perPage;
		int totalRecord = totalRecordEditProduct(user_no);
		List<ProductListDTO> list = template.selectList("ProductMapper.selectEditProduct", user_no, new RowBounds(offset, perPage));
		
		pdto.setList(list);
		pdto.setCurPage(curPage);
		pdto.setTotalRecord(totalRecord);
		
		return pdto;
	}

	public ProductListDTO editProduct(String p_id) {
		ProductListDTO dto = template.selectOne("ProductMapper.editProduct", p_id);
		return dto;
	}

	public int deleteProduct(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return template.delete("ProductMapper.deleteProduct", map);
	}

	public int updateProduct(ProductDTO dto) {
		// TODO Auto-generated method stub
		return template.update("ProductMapper.updateProduct", dto);
	}

}
