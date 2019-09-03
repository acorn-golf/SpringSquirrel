package com.squirrel.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.squirrel.dto.ReCommentDTO;
import com.squirrel.service.ReCommentService;

@Controller
public class Re_CommentController {
	
	@Autowired
	ReCommentService recService;
	
	@RequestMapping(value = "/insertComment", produces = "text/plain;charset=utf-8") // 댓글 삽입
	@ResponseBody
	public String insertComment(ReCommentDTO dto) {
		int n = recService.insertComment(dto);
		String mesg = "등록되었습니다";
		if(n==0) {
			mesg = "등록 실패";
		}
		return mesg;
	}
	
	@RequestMapping(value = "/updateComment", produces = "text/plain;charset=utf-8") // 댓글 수정
	@ResponseBody
	public String updateComment(@RequestParam HashMap<String, String> map) {
		int n = recService.updateComment(map);
		String mesg = "수정되었습니다";
		if(n==0) {
			mesg = "수정 실패";
		}
		return mesg;
	}
	
	@RequestMapping(value = "/deleteComment", produces = "text/plain;charset=utf-8") // 댓글 수정
	@ResponseBody
	public String deleteComment(@RequestParam("re_no") String re_no) {
		int n = recService.deleteComment(re_no);
		String mesg = "삭제되었습니다";
		if(n==0) {
			mesg = "삭제 실패";
		}
		return mesg;
	}
	
}
