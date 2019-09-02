package com.squirrel.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrel.dao.MemberDAO;
import com.squirrel.dto.MemberDTO;

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

	public int updatePW(HashMap<String, String> map) {

		return dao.updatePW(map);

	}

	public int kakaoMemberAdd(MemberDTO dto) {

		return dao.kakaoMemberAdd(dto);
	}

}
