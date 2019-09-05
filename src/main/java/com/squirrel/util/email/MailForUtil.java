package com.squirrel.util.email;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
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
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Autowired
	AESManager aesManager;
	
	

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
			messageHelper.setText(content,true); // 메일 내용

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

	@RequestMapping(value = "/sendMail")
	@Loginchk
	public String sendMail(HttpSession session) {
		MemberDTO user = (MemberDTO) session.getAttribute("login");
		String username = user.getUsername();
		int user_no = user.getUser_no();
		Date dateTmp = new Date(); 
		long date = dateTmp.getTime(); // 발송시간
		long endDate = date + (24*60*60*1000); // 유효시간 : 발송시간 + 24시간 

		// 시간 설정 끝
		String code = date+"/"+endDate+"/"+user_no; // isTime : 암호화할 값(발송시간+유효시간+유저)

		String enco = aesManager.enCodeText("email", code); // 암호화값
//		System.out.println("암호화>>>>"+enco);
		String deco = aesManager.deCodeText("email", enco);
//		System.out.println("복호화>>>>"+deco);
		String iscode = testMD5(enco+"golfHi"); // isCode : 해쉬값(암호값 + 별도문자)
				
		String setfrom = "tlakffja@naver.com";
		String tomail = user.getEmail();
		String title = "GolfHi 이메일 인증";
		String content = "<h2>안녕하세요 MS :p GolfHi 입니다!</h2><br><br>" + "<h3>" + username + "님</h3>"
				+"<p>인증하기 버튼을 누르시면 비밀번호 분실 시 이메일을 통해 확인할 수 있습니다</p>"
				+"<a href='localhost:8090/golfhi/emailCheck?isTime="+enco+"&isCode="+iscode+"'>인증하기</a>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content,true); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "email/sendEmail";
	}

	@RequestMapping(value = "/emailCheck")
	public String emailCheck(@RequestParam("isTime") String isTime,
			@RequestParam("isCode") String isCode, HttpSession session,
			RedirectAttributes redData) {
		System.out.println(">>>인증한 암호화값>>>"+isTime);
		System.out.println(">>>인증한 해쉬값>>>"+isCode);
		String compareHash = isTime;
		isTime = isTime.replace(" ", "+");
		isTime = aesManager.deCodeText("email", isTime); // 복호화
		
		Date curDate = new Date(); 
		long serverTime = curDate.getTime(); // 유저가 인증한 시간(서버시간)
		String[] data = isTime.split("/");
		
		long sendTime = Long.parseLong(data[0]); // 발송시간
		System.out.println(">>>발송시간 : "+sendTime);
		long checkTime = Long.parseLong(data[1]); // 유효시간
		System.out.println(">>>유효시간~~~"+checkTime);
		System.out.println(">>>인증시간~~~"+serverTime);
		if(checkTime - serverTime > 0) { // 유효시간(발송시간으로부터24시간) - 유저가인증하기누른 시간
			// isCode 해시 값 비교해야됨
			if(testMD5(compareHash+"golfHi")==isCode) {
				int user_no = Integer.parseInt(data[2]);
				memService.updateEmail(user_no);
				if(session.getAttribute("login") == null) { // 기존 브라우저 끄거나 달라졌을 때 로그인이 null일 수 있다
					MemberDTO dto = memService.getUser(user_no);
					session.setAttribute("login", dto);
				}
			}else {
				redData.addFlashAttribute("mesg","실패 \n 인증코드가 다릅니다");
			}
			
		}else {
			redData.addFlashAttribute("mesg", "실패 \n 인증 시간이 지났습니다\n 인증시간 : 24시간\n 마이페이지에서 이메일인증을 진행하세요");
		}
		return "redirect:/";
	}
	
}