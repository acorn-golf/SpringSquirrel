package com.squirrel.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.RatingUpDTO;
import com.squirrel.dto.view.SelectRatingDTO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate template;

	public int MemberAdd(MemberDTO dto) {

		return template.insert("MemberMapper.MemberAdd", dto);
	}

	public MemberDTO login(HashMap<String, String> map) {

		return template.selectOne("MemberMapper.login", map);
	}

	public MemberDTO myPage(int user_no) {

		return template.selectOne("MemberMapper.myPage", user_no);
	}

	public int myPageUpdate(MemberDTO dto) {

		return template.update("MemberMapper.myPageUpdate", dto);
	}

	public int multiCheck(HashMap<String, String> map) {

		return template.selectOne("MemberMapper.multiCheck", map);
	}

	public List<MemberDTO> adminMemberSelect(HashMap<String, Object> map) {

		return template.selectList("MemberMapper.adminMemberSelect", map);
	}

	public int adminModified(MemberDTO userdto) {

		return template.update("MemberMapper.adminModified", userdto);
	}

	public MemberDTO getMemberInfo(int user_no) {

		return template.selectOne("MemberMapper.getMemberInfo", user_no);
	}

	public int updateEmail(int user_no) {

		return template.update("MemberMapper.updateEmail", user_no);

	}

	public MemberDTO getUser(int user_no) {

		return template.selectOne("MemberMapper.getUser", user_no);
	}

	public int totalRecord() {

		return template.selectOne("MemberMapper.totalRecord");
	}

	public MemberDTO getPhoneUser(String phone_id) {

		return template.selectOne("MemberMapper.getPhoneUser", phone_id);

	}

	public int updatePW(HashMap<String, String> insertmap) {

		return template.update("MemberMapper.updatePW", insertmap);
	}

	public MemberDTO kakaoLogin(String string) {

		return template.selectOne("kakaoLogin", string);
	}

	public int kakaoMemberAdd(MemberDTO dto) {

		return template.insert("kakaoMemberAdd", dto);
	}

	public int applyRatingUp(int user_no) {
		// TODO Auto-generated method stub
		return template.insert("MemberMapper.applyRatingUp", user_no);
	}

	public List<RatingUpDTO> selectRatingInfo(int user_no) {
		List<RatingUpDTO> rdto = template.selectList("MemberMapper.selectRatingInfo", user_no); 
		return rdto;
	}

	public int updateRatingUp(int num) {
		// TODO Auto-generated method stub
		return template.update("MemberMapper.updateRatingUp", num);
	}

	public List<SelectRatingDTO> selectRatingInfo() {
		List<SelectRatingDTO> list = template.selectList("MemberMapper.seeRatingInfo");
		return list;
	}

	public int updateMemberTable(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		return template.update("MemberMapper.updateForRating", map);
	}

	public int selectRatingTable(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		return template.update("MemberMapper.updateRatingTable", map);
	}

	public int deleteRatingTable(int num) {
		// TODO Auto-generated method stub
		return template.delete("MemberMapper.deleteRatingTable",num);
	}

}
