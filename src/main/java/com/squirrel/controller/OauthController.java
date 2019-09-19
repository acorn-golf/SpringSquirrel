package com.squirrel.controller;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpSession;
import javax.swing.JComboBox.KeySelectionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.squirrel.dto.MemberDTO;
import com.squirrel.service.MemberService;
import com.squirrel.util.aes.AESManager;
import com.squirrel.util.curl.CurlUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Controller
public class OauthController {

	@Autowired
	CurlUtil curlcall;

	@Autowired
	MemberService memberService;

	@RequestMapping("/Oauth/kakao")
	@ResponseBody
	public HashMap<String, Object> kakaoLogin(@RequestParam HashMap<String, String> logininfo,
			HttpSession httpSession) {
		// SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		SecretKey key;
		byte[] tmp = "아 너무 졸리다 배고프고 춥고 졸리고 그냥 뒤질거같다".getBytes();
		byte[] tmpdate = String.valueOf(new Date().getTime()).getBytes();
		for (int i = 0; i<tmp.length;i++) {
			System.out.println("변경전" + tmp[i]);
			 tmp[i] = (byte) (tmp[i]^tmpdate[i%tmpdate.length]);
			 System.out.println("변경후" + tmp[i]);
		}

		byte[] keyBytes = tmp;
		key = Keys.hmacShaKeyFor(tmp);

		String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();

		System.out.println(jws);

		// test
		for (Map.Entry<String, String> entry : logininfo.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());

		HashMap<String, Object> kakaoLoginInfo = null;

		HashMap<String, String> parmeterMap = new HashMap<String, String>();
		parmeterMap.put("access_token", logininfo.get("access_token"));

		kakaoLoginInfo = (HashMap<String, Object>) curlcall.curlReturnMap(
				"https://kapi.kakao.com/v1/user/access_token_info", false, parmeterMap, null,
				(code, resultparmeter) -> {

					MemberDTO dto = null;

					java.util.Map<String, Object> resultmap = new HashMap<String, Object>();
					int errer_code = 0;
					boolean login_success;
					boolean refresh_chk;
					String err_mesg = null;

					switch (code) {
					case 200:
						login_success = true;
						refresh_chk = false;
						System.out.println(resultparmeter.get("id"));
						dto = memberService.kakaoLogin(resultparmeter.get("id").toString());

						if (dto == null) {
							int chk = kakaoMemberAdd("Bearer " + logininfo.get("access_token"));
							System.out.println("씨발" + logininfo.get("access_token"));
							if (chk > 0) {
								dto = memberService.kakaoLogin(resultparmeter.get("id").toString());
								httpSession.setAttribute("login", dto);
							} else {
								login_success = false;
								err_mesg = "회원가입 실패 관리자에게 문의하세요";
								errer_code = 666;
							}
						} else
							httpSession.setAttribute("login", dto);

						break;

					case -401:
						login_success = false;
						refresh_chk = true;
						errer_code = -401;
						break;

					case -2:
						login_success = false;
						refresh_chk = false;
						errer_code = -2;
						err_mesg = "잘못된 토큰 형식";
						break;

					case -1:
						login_success = false;
						refresh_chk = false;
						errer_code = -1;
						err_mesg = "카카오 서버 오류 잠시후에 다시 시도해주세요.";
						break;

					default:
						errer_code = code;
						login_success = false;
						refresh_chk = false;
						err_mesg = "알수 없는 오류. 관리자에게 문의해주세요";
						break;
					}

					resultmap.put("errer_code", String.valueOf(errer_code));
					resultmap.put("login_success", String.valueOf(login_success));
					resultmap.put("refresh_chk", String.valueOf(refresh_chk));
					if (err_mesg != null)
						resultmap.put("err_mesg", err_mesg);

					httpSession.setAttribute("resultmap", resultmap);

					return resultmap;
				});

		return kakaoLoginInfo;
	}

	private int kakaoMemberAdd(String access_token) {
		HashMap<String, String> inValue = new HashMap<String, String>();
		inValue.put("Authorization", access_token);
		int chk = 0;
		System.out.println("왜 씨발" + access_token);

		java.util.Map<String, Object> resultMap = null;
		resultMap = curlcall.curlReturnMap("https://kapi.kakao.com/v2/user/me", true, null, inValue, null);

		HashMap<String, Object> properties = (HashMap<String, Object>) resultMap.get("properties");
		HashMap<String, Object> kakao_account = (HashMap<String, Object>) resultMap.get("kakao_account");

		MemberDTO dto = new MemberDTO();
		if ((boolean) kakao_account.get("has_email")) {

			dto.setEmail((String) kakao_account.get("email"));
			System.out.println(dto.getEmail());

			String email_chk = "Y";
			if (kakao_account.get("is_email_verified") != null)
				email_chk = ((boolean) kakao_account.get("is_email_verified")) ? "Y" : "N";
			dto.setEmail_chk(email_chk);
		}

		dto.setUsername((String) properties.get("nickname"));
		dto.setNickname((String) properties.get("nickname"));// gender_needs_agreement

		dto.setKakao_id(String.valueOf((Integer) resultMap.get("id")));

		{
			String tmpGender = (String) kakao_account.get("gender");

			if (tmpGender != null)// female
			{
				String i = "1";

				if (((String) kakao_account.get("gender")).equals("female"))
					i = "2";
				dto.setGender(i);
			}
		}

		chk = memberService.kakaoMemberAdd(dto);

		return chk;

	}
}
