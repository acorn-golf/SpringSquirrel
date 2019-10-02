package com.squirrel.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.squirrel.dao.MemberDAO;
import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.RatingUpDTO;
import com.squirrel.dto.view.SelectRatingDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;

	public int MemberAdd(MemberDTO dto) {

		return dao.MemberAdd(dto);
	}

	public MemberDTO login(HashMap<String, String> map) {

		return dao.login(map);
	}

	public MemberDTO myPage(int user_no) {

		return dao.myPage(user_no);
	}

	public int myPageUpdate(MemberDTO dto) {

		return dao.myPageUpdate(dto);
	}

	public int multiCheck(HashMap<String, String> map) {

		return dao.multiCheck(map);
	}

	public List<MemberDTO> adminMemberSelect(HashMap<String, Object> map) {

		return dao.adminMemberSelect(map);
	}

	public int adminModified(MemberDTO userdto) {

		return dao.adminModified(userdto);
	}

	public MemberDTO getMemberInfo(int user_no) {

		return dao.getMemberInfo(user_no);
	}

	public int updateEmail(int user_no) {

		return dao.updateEmail(user_no);

	}

	public MemberDTO getUser(int user_no) {

		return dao.getUser(user_no);
	}

	public int totalRecord() {

		return dao.totalRecord();
	}

	public MemberDTO getPhoneUser(String phone_id) {

		return dao.getPhoneUser(phone_id);

	}

	public MemberDTO kakaoLogin(String string) {

		return dao.kakaoLogin(string);
	}

	public int updatePW(HashMap<String, String> insertmap) {

		return dao.updatePW(insertmap);

	}

	public int kakaoMemberAdd(MemberDTO dto) {

		return dao.kakaoMemberAdd(dto);
	}

	public int applyRatingUp(int user_no) {
		// TODO Auto-generated method stub
		return dao.applyRatingUp(user_no);
	}

	public List<RatingUpDTO> selectRatingInfo(int user_no) {
		// TODO Auto-generated method stub
		return dao.selectRatingInfo(user_no);
	}

	public int updateRatingUp(int num) {
		// TODO Auto-generated method stub
		return dao.updateRatingUp(num);
	}

	public List<SelectRatingDTO> selectRatingInfo() {
		// TODO Auto-generated method stub
		return dao.selectRatingInfo();
	}

	@Transactional
	public int updateRatinTX(HashMap<String, Integer> map) throws Exception {
		int result = 0;
		result = dao.updateMemberTable(map);
		result = dao.selectRatingTable(map);
		if(result<=0) {
			throw new Exception("등업 실패");
		}
		return result;
	}

	public int deleteRatingTable(int num) {
		// TODO Auto-generated method stub
		return dao.deleteRatingTable(num);
	}
	

}
