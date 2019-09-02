package com.squirrel.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.squirrel.dto.MemberDTO;
import com.squirrel.util.curl.CurlUtil;

@Controller
public class XmlMapingController {

	@RequestMapping(value = "/mainMenuXml", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String xmlMainGet(HttpSession session) {

		System.out.println("확인용");

		String xmlUrl = "com/squirrel/menuXml/";
		MemberDTO dto = (MemberDTO) session.getAttribute("login");

		if (dto == null)
			xmlUrl += "mainNot_login.xml";
		else
			switch (dto.getRating()) {
			case "U":
				xmlUrl += "mainRatingUser.xml";
				break;

			case "M":
				xmlUrl += "mainRatingManager.xml";
				break;

			case "A":
				xmlUrl += "mainRatingAdmin.xml";
				break;
			default:
				break;
			}
		ClassPathResource resource = new ClassPathResource(xmlUrl);

		StringBuffer buffer = new StringBuffer();
		try {
			for (String str : Files.readAllLines(Paths.get(resource.getURI()))) {
				buffer.append(str);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return buffer.toString();
	}

}
