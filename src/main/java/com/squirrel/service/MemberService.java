package com.squirrel.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.squirrel.dao.MemberDAO;
import com.squirrel.dto.MemberDTO;

public class MemberService {

	@Autowired
	MemberDAO dao;

	public int MemberAdd(MemberDTO dto) {

		return dao.MemberAdd(dto);
	}

	public MemberDTO login(HashMap<String, String> map) {

		return dao.login(map);
	}

	public MemberDTO myPage(String nickname) {

		return dao.myPage(nickname);
	}

	public int myPageUpdate(MemberDTO dto) {
		int confirm = 0;

		confirm = dao.myPageUpdate(dto);

		return confirm;
	}

	public int multiCheck(HashMap<String, String> map) {

		int confirm;

		confirm = dao.multiCheck(map);

		return confirm;
	}

	public List<MemberDTO> adminMemberSelect(HashMap<String, Object> map) {

		List<MemberDTO> list = null;

		list = dao.adminMemberSelect(map);

		return list;
	}

	public int adminModified(MemberDTO userdto) {

		int confirm = 0;

		confirm = dao.adminModified(userdto);

		return confirm;
	}

	public MemberDTO getMemberInfo(int user_no) {
		// TODO Auto-generated method stub

		MemberDTO dto = null;

		dto = dao.getMemberInfo(user_no);

		return dto;
	}

	public int updateEmail(int user_no) {

		int result;

		result = dao.updateEmail(user_no);

		return result;

	}

	public MemberDTO getUser(int user_no) {

		MemberDTO dto = null;

		dto = dao.getUser(user_no);

		return dto;
	}

	public int totalRecord() {

		int totalRecord = 0;

		totalRecord = dao.totalRecord();

		return totalRecord;
	}

	public MemberDTO getPhoneUser(String phone_id) {

		MemberDTO dto = null;

		dto = dao.getPhoneUser(phone_id);

		return dto;

	}

//지한뉘-08/14 카카오 로그인 체크 및 로그인 용 생성
	public MemberDTO kakaoLogin(String string) {
		// TODO Auto-generated method stub

		MemberDTO dto = null;

		dto = dao.kakaoLogin(string);

		return dto;
	}

	public int updatePW(HashMap<String, String> map) {

		int result = 0;

		result = dao.updatePW(map);

		return result;

	}

	public int kakaoMemberAdd(MemberDTO dto) {
		// TODO Auto-generated method stub

		int confirm = 0;

		confirm = dao.kakaoMemberAdd(dto);

		return confirm;
	}

}
