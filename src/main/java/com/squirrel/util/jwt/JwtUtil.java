package com.squirrel.util.jwt;

import java.util.Date;
import java.util.HashMap;

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

	//xml 셋팅값
	private String issStr;
	private String keyStr;
	private int accessToken_IntervalMin;
	private int refresh_IntervalDay;
	
	private long  accessToken_Interval;
	private long  refresh_Interval;
	
	
	
	
	
	//refresh token
	//access token
	
	public JwtUtil(String issStr, String keyStr, int accessToken_IntervalMin, int refresh_IntervalDay) {
		super();
		this.issStr = issStr;
		this.keyStr = keyStr;
		this.accessToken_IntervalMin = accessToken_IntervalMin;
		this.refresh_IntervalDay = refresh_IntervalDay;
		
		accessToken_Interval = (long)accessToken_IntervalMin*(1000*60);
		refresh_Interval = (long)refresh_IntervalDay*(1000*60*60*24);
	}


	public Jws<Claims> testRead(String jwsString ) throws JwtException {
		
		
		//에러처리 예제
		Jws<Claims> jws = null;
	    jws = Jwts.parser()         // (1)
	    .setSigningKey(getKey())
	    .parseClaimsJws(jwsString); // (3)
	    
	    //에러처리 예제
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
	    jws = Jwts.parser()         // (1)
	    .setSigningKey(getKey())
	    .requireSubject("Access")
	    .parseClaimsJws(jstr); // (3)
	    
		
		return jws.getBody();
	}
	
	public Claims AuthenticateRefreshToken(String jstr) throws JspException {
		Jws<Claims> jws = null;
	    jws = Jwts.parser()         // (1)
	    .setSigningKey(getKey())
	    .requireSubject("Refresh")
	    .parseClaimsJws(jstr); // (3)  
	    
		return jws.getBody();
	}
	
	public String TestGetAccessToken(int user_no,String rating) { //태스트용 코드 추후 삭제

		//return getAccessToken(user_no,rating);
		return null;
	}
	
	private String getAccessToken(int user_no,String rating,Date nowTime,Date expTime) {
		// SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		// 암호화 시간에 따른 변경
		
	//	Date nowTime =  new Date();
	//	Date expTime = new Date(nowTime.getTime()+accessToken_Interval); //추후에 별도 빈으로 변경하면서 xml에서 수정가능하게 할 것.
		
		SecretKey key = getKey();
		
		String jws = Jwts.builder().
				setIssuedAt(nowTime).
				setExpiration(expTime).
				setSubject("Access").
				claim("uid", user_no).
				claim("rat", rating).
				signWith(key).compact();
		
		return jws;
	}
	
	private HashMap<String, String> getRefreshToken(int user_no,String rating) {
		// SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		// 암호화 시간에 따른 변경
		
		Date nowTime =  new Date();
		Date expTime = new Date(nowTime.getTime()+refresh_Interval);
		Date nbfTime = new Date(nowTime.getTime()+accessToken_Interval);
		
		SecretKey key = getKey();
		
		String jwsR = Jwts.builder().
				setIssuedAt(nowTime).
				setExpiration(expTime).
				setNotBefore(nbfTime).
				setSubject("Refresh").
				claim("uid", user_no).
				claim("rat", rating).
				signWith(key).compact();
		
		String jwsA = getAccessToken(user_no, rating, nowTime, expTime);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("A", jwsA);
		map.put("R", jwsR);
		
		return map;
	}
	
	private SecretKey getKey()
	{
		byte[] tmp = keyStr.getBytes();
//		byte[] tmpdate = String.valueOf(date.getTime()).getBytes();
//		for (int i = 0; i<tmp.length;i++) {
//			 tmp[i] = (byte) (tmp[i]^tmpdate[i%tmpdate.length]);
//		}

		return Keys.hmacShaKeyFor(tmp);
	}


}
