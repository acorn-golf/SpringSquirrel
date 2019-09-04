package com.squirrel.util.email;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.annotation.Loginchk;
import com.squirrel.dto.MemberDTO;
import com.squirrel.service.MemberService;
import com.squirrel.util.aes.AESManager;

@Controller
public class MailForUtil {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	MemberService memService;

	// mailSending 코드
	@RequestMapping(value = "/sendPWMail") // 회원 비밀번호 찾기 시 임시비밀번호 발송
	public ModelAndView mailSending(@RequestParam HashMap<String, String> map) {

		String sendMail = map.get("email");
		String phoneid = map.get("phoneid");

		Random random = new Random();
		String[] pwval = new String[7];
		String pw = "";
		for (int i = 0; i < pwval.length; i++) {
			pwval[i] = String.valueOf(random.nextInt(9));
			pw += pwval[i];
		}

		HashMap<String, String> insertmap = new HashMap<String, String>();
		insertmap.put("userpw", pw);
		insertmap.put("phone_id", phoneid);
		memService.updatePW(insertmap);

		String setfrom = "tlakffja@naver.com";
		String tomail = sendMail;
		String title = "GolfHi 임시비밀번호 발송";
		String content = "<h2>안녕하세요 MS :p GolfHi 입니다!</h2><br><br>" + "임시비밀번호: " + pw;

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("email", sendMail);
		mav.setViewName("email/sendEmail");
		return mav;
	}

	// 파라미터로 넘어온 문자열을 해쉬코드값으로 변경(MD5 : 128bit)
	public String testMD5(String str) {
		String MD5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			MD5 = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			MD5 = null;
		}
		return MD5;
	}

	@RequestMapping(value = "sendMail")
	@Loginchk
	public String sendMail(HttpSession session) {
		MemberDTO user = (MemberDTO) session.getAttribute("login");
		String username = user.getUsername();

		// 시간 변수 설정 나중에 db에서 가져올것.
		Date dateTmp = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 임시방편. 나중에 date 변수에 sql에서 가져온 시간값(문자열) 삽입할 것.
		// format.parse(source) -> 시간비교시 확인
		String date = format.format(dateTmp);
		// 시간 설정 끝
		String code = date + "/" + user.getUser_no();

		// 서블릿 ? 시간 = aes암호화값 & 개별코드 = 시간+유저아이디 해쉬값
		
		AESManager aes = new AESManager();
		String aesname = "1q2w3e4r5t6y7u8i";
		String enco = aes.enCodeText(aesname, date);
		String dnco = aes.deCodeText(aesname,enco);
		String iscode = testMD5(code);
		
//		HashMap<String, Integer> emailchk = (HashMap<String, Integer>) getServletContext().getAttribute("emailchk");
		
//		if (emailchk == null) {
//			emailchk = new HashMap<String, Integer>();
//			getServletContext().setAttribute("emailchk", emailchk);
//		}
//
//		emailchk.put(iscode, user.getUser_no());
		
		
		String setfrom = "tlakffja@naver.com";
		String tomail = "보낼메일";
		String title = "GolfHi 임시비밀번호 발송";
		String content = "보낼문자";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "";
	}

}