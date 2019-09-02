package com.squirrel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.squirrel.dto.MemberDTO;
import com.squirrel.dto.NoticeListDTO;
import com.squirrel.dto.PageDTO;
import com.squirrel.dto.view.PickListviewDTO;
import com.squirrel.service.NoticeService;
import com.squirrel.service.PickService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService sevice;
	
	@RequestMapping("/NoteView")
	public ModelAndView NoteView() {
		ModelAndView mav = new ModelAndView();
		List<NoticeListDTO> NoticeList = sevice.notelist();
		mav.addObject("NoteView", NoticeList );
		mav.setViewName("note/Notice/NoticeList");
		
		return mav;
	}
	
	
}

