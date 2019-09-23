package com.squirrel.util.jwt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.crypto.SecretKey;
import javax.servlet.jsp.JspException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	// xml 셋팅값
	private String issStr;
	private String keyStr;
	private int accessToken_IntervalMin;
	private int refresh_IntervalDay;

	private long accessToken_Interval;
	private long refresh_Interval;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");

	private HashMap<String, HashMap<String, Object>> refresh_table = new HashMap<String, HashMap<String, Object>>();
	/*
	 * 현재 생각하고 있는 구조
	 * 
	 * //토큰 id / 유저_no/ ip / 만료 시간 / 유효 여부 (강제 종료시) /
	 * 
	 * 
	 * 해당 데이터를 db로 만약 구현한다고 하면, 로그인 기기를 관리하는 것이 가능하다. 모바일 환경을 기준으로 구현하니, 해당 기기의
	 * 로그아웃을 사용자가 자유롭게 할수있도록 구현할수 있도록 추가 db를 파도록 하자.
	 * 
	 * 일단 현재의 db를 단순란 리스트로 구현하여 세션을 대용하는 용도로 사용.
	 * 
	 * 
	 * 
	 * -----토큰아이디의 작명 규칙
	 * 
	 * 유저아이디 +등급 +날짜(년월일 분)
	 * 
	 * 
	 * 
	 */

	// refresh token
	// access token

	public JwtUtil(String issStr, String keyStr, int accessToken_IntervalMin, int refresh_IntervalDay) {
		super();
		this.issStr = issStr;
		this.keyStr = keyStr;
		this.accessToken_IntervalMin = accessToken_IntervalMin;
		this.refresh_IntervalDay = refresh_IntervalDay;

		accessToken_Interval = (long) accessToken_IntervalMin * (1000 * 60);
		refresh_Interval = (long) refresh_IntervalDay * (1000 * 60 * 60 * 24);
	}

	public Jws<Claims> testRead(String jwsString) throws JwtException {

		// 에러처리 예제
		Jws<Claims> jws = null;
		jws = Jwts.parser() // (1)
				.setSigningKey(getKey()).parseClaimsJws(jwsString); // (3)

		//

		// 에러처리 예제
//		try {
//		    jws = Jwts.parser()         // (1)
//		    .setSigningKey(getKey())         // (2)
//		    .parseClaimsJws(jwsString); // (3)
//		}  catch(MissingClaimException mce) {
//		    // 클레임이 없는 경우 에러 처리
//		} catch(IncorrectClaimException ice) {
//		    // 지정된 클레임이 없는 경우 에러 처리 (컬럼, 값)
//		}   
//		catch (ExpiredJwtException ex) {       // (4)
//			
//		//타임 아웃인 경우 (만료된 토큰 )
//
//		}catch (JwtException e) {
//			System.out.println(e.fillInStackTrace());
//			// TODO: 모르는 에러찿기
//		}

		return jws;
	}

	public Claims AuthenticateAccessToken(String jstr) throws JspException {

		Jws<Claims> jws = null;
		jws = Jwts.parser() // (1)
				.setSigningKey(getKey()).requireSubject("Access").parseClaimsJws(jstr); // (3)

		return jws.getBody();
	}

	private String AuthenticateRefreshToken(String jstr) throws JspException {

		Jws<Claims> jws = null;
		jws = Jwts.parser() // (1)
				.setSigningKey(getKey()).requireSubject("Refresh").parseClaimsJws(jstr); // (3)

		String jti = jws.getBody().getId();
		HashMap<String, Object> map;

		if ((map = refresh_table.get(jti)) != null && (boolean) map.get("chk"))
			if ((new Date().getTime() - jws.getBody().getExpiration().getTime()) <= (long) 1000 * 60 * 60 * 24 * 5) {
				Date extendExp = new Date(jws.getBody().getExpiration().getTime() + (long) 1000 * 60 * 60 * 24 * 7);
				jstr = getRefreshToken(jws.getBody().getIssuedAt(), extendExp, jti);
			} else
				throw new ExpiredJwtException(jws.getHeader(), jws.getBody(), "로그아웃 혹은 만료된 토큰");
	
		return jstr;
	}

	public String TestGetAccessToken(int user_no, String rating) { // 태스트용 코드 추후 삭제

		// return getAccessToken(user_no,rating);
		return null;
	}

	// public HashMap<String, String>
	// 맨처음 발급

	// 리퀘스트 토큰을 통한 토큰발급
//	public String () {
//		
//	}


	private String getAccessToken(int user_no, String rating, Date nowTime, Date expTime) {
		// SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		// 암호화 시간에 따른 변경

		// Date nowTime = new Date();
		// Date expTime = new Date(nowTime.getTime()+accessToken_Interval); //추후에 별도 빈으로
		// 변경하면서 xml에서 수정가능하게 할 것.

		SecretKey key = getKey();

		String jws = Jwts.builder().setIssuedAt(nowTime).setExpiration(expTime).claim("uid", user_no)
				.claim("rat", rating).signWith(key).compact();

		return jws;
	}

	private String getRefreshToken(Date nowTime, Date expTime, String jti) {
		// SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		// 암호화 시간에 따른 변경

		Date nbfTime = new Date(nowTime.getTime() + accessToken_Interval);

		// String jti = user_no + rating + dateFormat.format(nowTime);
		SecretKey key = getKey();

		String jwsR = Jwts.builder().setIssuedAt(nowTime).setExpiration(expTime).setNotBefore(nbfTime).setId(jti)
				.signWith(key).compact();

//		{
//			// refresh_table
//			// jti / 유저_no/ ip(미구현) / 만료 시간 / 유효 여부 (강제 종료시) /
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			//map.put("jti", jti);
//			map.put("userNo", user_no);
//			map.put("expTime", expTime);
//			map.put("chk", true);
//
//			refresh_table.put(jti,map);
//		}

		return jwsR;
	}

	private SecretKey getKey() {
		byte[] tmp = keyStr.getBytes();
//		byte[] tmpdate = String.valueOf(date.getTime()).getBytes();
//		for (int i = 0; i<tmp.length;i++) {
//			 tmp[i] = (byte) (tmp[i]^tmpdate[i%tmpdate.length]);
//		}

		return Keys.hmacShaKeyFor(tmp);
	}

}
