package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.squirrel.dto.MemberDTO;

public class MemberDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public int MemberAdd( MemberDTO dto) {
		
		int confirm = template.insert("MemberMapper.MemberAdd",dto);
		
		return confirm;
	}

	public MemberDTO login( HashMap<String, String> map) {
		
		MemberDTO dto = template.selectOne("MemberMapper.login", map);
		return dto;
	}

	public MemberDTO myPage( String nickname) {
		
		MemberDTO dto = template.selectOne("MemberMapper.myPage", nickname);
		return dto;
	}

	public int myPageUpdate( MemberDTO dto) {
		
		int confirm = template.update("MemberMapper.myPageUpdate",dto);
		
		return confirm;
	}

	public int multiCheck( HashMap<String, String> map) {

		int confirm = template.selectOne("MemberMapper.multiCheck",map);
		
		return confirm;
	}

	public List<MemberDTO> adminMemberSelect( HashMap<String, Object> map) {
			
		List<MemberDTO> list = template.selectList("MemberMapper.adminMemberSelect",map);
	
		return list;
	}

	public int adminModified( MemberDTO userdto) {
		
		int confirm = template.update("MemberMapper.adminModified",userdto);
		
		return confirm;
	}

	public MemberDTO getMemberInfo( int user_no) {
		// TODO Auto-generated method stub
		MemberDTO dto = template.selectOne("MemberMapper.getMemberInfo", user_no);
		return dto;
	}

	public int updateEmail( int user_no) {
		return template.update("MemberMapper.updateEmail", user_no);
		
	}

	public MemberDTO getUser( int user_no) {
		MemberDTO dto = template.selectOne("MemberMapper.getUser", user_no);
		return dto;
	}


	public int totalRecord() {
		int totalRecord = template.selectOne("MemberMapper.totalRecord");
		return totalRecord;
  }

	public MemberDTO getPhoneUser( String phone_id) {
		MemberDTO dto = template.selectOne("MemberMapper.getPhoneUser", phone_id);
		return dto;

	}

	public int updatePW( HashMap<String, String> map) {
		int confirm = template.update("MemberMapper.updatePW", map);
		return confirm;
	}

	public MemberDTO kakaoLogin( String string) {
		// TODO Auto-generated method stub
		return template.selectOne("kakaoLogin",string);
	}

	public int kakaoMemberAdd( MemberDTO dto) {
		// TODO Auto-generated method stub
		return template.insert("kakaoMemberAdd", dto);
	}

}
