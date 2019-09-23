package com.squirrel.util.jwt;

public enum JwtState {

	END_ACCESS_TOKEN, //인증토큰의 기간 만료
	END_REFRESH_TOKEN, //재발행토큰의 기간 만료
	
	PUBLISH_ACCESS_TOKEN, //엑세스 토큰의 발행 (혹은 재발행)
	PUBLISH_TOKENS, //재발행토큰, 엑세스토큰 발행
	
	EXTEND_REFRESH_TOKEN, // 재발행 토큰의 기간 연장
	ACCESS_TOKEN_OK, // 엑세스 토큰 인증 선공
	ACCESS_TOKEN_FAIL, //엑세스 토큰 인증 실패
	REFRESH_TOKEN_OK, //재발행 토큰 인증 성공
	REFRESH_TOKEN_FAIL, //재발행 토큰 인증 실패
}
