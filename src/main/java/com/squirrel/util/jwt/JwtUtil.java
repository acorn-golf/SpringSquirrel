package com.squirrel.util.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
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



	public String TestGetAccessToken(int user_no,String rating) { //태스트용 코드 추후 삭제
		return getAccessToken(user_no,rating);
	}
	


	private String getAccessToken(int user_no,String rating) {
		// SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		// 암호화 시간에 따른 변경
		
		Date nowTime =  new Date();
		Date expTime = new Date(nowTime.getTime()+accessToken_Interval); //추후에 별도 빈으로 변경하면서 xml에서 수정가능하게 할 것.
		
		SecretKey key = getKey(nowTime);
		
		String jws = Jwts.builder().
				setIssuer("http://golfhi.com").
				setIssuedAt(nowTime).
				setExpiration(expTime).
				claim("uid", user_no).
				claim("rat", rating).
				signWith(key).compact();
		
		return jws;
	}
	
	
	private SecretKey getKey(Date date)
	{
		byte[] tmp = keyStr.getBytes();
		byte[] tmpdate = String.valueOf(date.getTime()).getBytes();
		for (int i = 0; i<tmp.length;i++) {
			 tmp[i] = (byte) (tmp[i]^tmpdate[i%tmpdate.length]);
		}

		return Keys.hmacShaKeyFor(tmp);
	}
}
