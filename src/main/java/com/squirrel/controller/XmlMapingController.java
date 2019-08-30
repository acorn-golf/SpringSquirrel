package com.squirrel.controller;

import java.io.File;
import java.io.FileReader;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.squirrel.dto.MemberDTO;

@Controller
public class XmlMapingController {

	@RequestMapping(value = "/mainMenuXml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String xmlMainGet(HttpSession session) {
		
		System.out.println("확인용");
		
		String xmlUrl = "";
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		

		
		if (dto == null)
			xmlUrl = ".";
		else
			switch (dto.getRating()) {
			case "U":
				xmlUrl = "/xml/mainRatingUser.xml";
				break;

			case "M":
				xmlUrl = "/xml/mainRatingManager.xml";
				break;

			case "A":
				xmlUrl = "/xml/mainRatingAdmin.xml";
				break;
			default:
				break;
			}

		
		try {
	        File file = new File(xmlUrl);
	        System.out.println(file.getAbsolutePath());
	        System.out.println(file.getCanonicalPath());
	        //입력 스트림 생성
	        FileReader file_reader = new FileReader(file);
	        int cur = 0;
	        while((cur = file_reader.read()) != -1){
	           System.out.print((char)cur);
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		return xmlUrl;
	}

}
