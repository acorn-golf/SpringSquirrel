package com.squirrel.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpConnection;
import org.springframework.beans.factory.annotation.Autowired;
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

		String xmlUrl = "http://localhost:8090/golfhi";
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

	    URL url = null;
	    InputStream is = null;
	    String result = "";
		try {
			url = new URL("localhost:8090/golfhi/xml/mainRatingAdmin.xml");
	        System.out.println("url=[" + url + "]");
	        System.out.println("protocol=[" + url.getProtocol() + "]");
	        System.out.println("host=[" + url.getHost() + "]");
	        System.out.println("content=[" + url.getContent() + "]");
	 
	        is= url.openStream();
	        int ch;
	        while ((ch = is.read()) != -1) {
	            System.out.print((char) ch);
	            result += (char) ch;
	        }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    



		System.out.println(result);

		
		
//		URL url = null;
//		HttpURLConnection con = null;
//		int ResponseCode = 0;
//		StringBuffer buffer = new StringBuffer();
//		BufferedReader bufferedreader = null;
//
//		try {
//			url = new URL(xmlUrl);
//			con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET");
//			ResponseCode = con.getResponseCode();
//			bufferedreader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			while ((inputLine = bufferedreader.readLine()) != null) {
//				buffer.append(inputLine);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		System.out.println(buffer.toString());
//		System.out.println(ResponseCode);

		return "";
	}

}
