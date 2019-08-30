package com.squirrel.util.curl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.function.BiFunction;

//import org.apache.http.HttpConnection;

import org.apache.commons.httpclient.HttpConnection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurlUtil {

	private ObjectMapper mapper = new ObjectMapper();

//	public String curlReturnJsonStr(String urlStr, boolean postChk, Map<String, String> parameter,
//			BiFunction<Integer, Map<String, Object>, Map<String, Object>> resultFun) {
//		Map<String, Object> result = null;
//		HttpURLConnection con = null;
//		try {
//
//			if (resultFun != null)
//				result = curl(urlStr, postChk, parameter, resultFun);
//			else
//				result = curl(urlStr, postChk, parameter, (code, returnparameter) -> {
//					return returnparameter;
//				});
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//
//		String JsonStr = null;
//		try {
//			JsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return JsonStr;
//	}

	public CurlUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public <T> T curlReturnClass(String urlStr, boolean postChk, Map<String, String> parameter,
			java.util.Map<String, String> harderSet, BiFunction<Integer, T, T> resultFun, Class<T> requiredType)
			throws CurlException, IOException {
		T result = null;
		StringBuffer buffer = null;
		HttpURLConnection con = null;
		int ResponseCode = 0;

		try {
			con = curl(urlStr, postChk, parameter,harderSet);
			ResponseCode = con.getResponseCode();
			buffer = connectionStrBuffer(con);
			result = mapper.readValue(buffer.toString(), requiredType);

			result = resultFun.apply(con.getResponseCode(), result);

		} catch (IOException e) {
			// TODO: handle exception
			// 직접 만든 에러 추가하여, 에러 파라미터 넘길것 ( 통신코드, 반환값 그거)
			java.util.Map<String, Object> returnParmeter = null;
			try {
				returnParmeter = mapper.readValue(buffer.toString(),
						new TypeReference<java.util.Map<String, Object>>() {
						});
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new CurlException("잘못된 전송", ResponseCode, returnParmeter);
		}

		return result;
	}

	public Map<String, Object> curlReturnMap(String urlStr, boolean postChk, Map<String, String> parameter,
			java.util.Map<String, String> harderSet,
			BiFunction<Integer, Map<String, Object>, Map<String, Object>> resultFun) {
		Map<String, Object> result = null;
		StringBuffer buffer = null;
		HttpURLConnection con = null;
		try {

			con = curl(urlStr, postChk, parameter, harderSet);
			buffer = connectionStrBuffer(con);

			java.util.Map<String, Object> returnParmeter = mapper.readValue(buffer.toString(),
					new TypeReference<java.util.Map<String, Object>>() {
					});
			if (resultFun != null)
				result = resultFun.apply(con.getResponseCode(), returnParmeter);
			else
				result = returnParmeter;
//
//	return 

//				});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private HttpURLConnection curl(String urlStr, boolean postChk, java.util.Map<String, String> parameter,
			java.util.Map<String, String> harderSet) throws IOException {

		BufferedReader br = null;
		StringBuffer paramBuffer = new StringBuffer();
		// urlBuffer.append(urlStr);

		URL url = null;
		HttpURLConnection con = null;

		if (postChk) {
			url = new URL(urlStr);
			con = (HttpURLConnection) url.openConnection();

			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, String> param : parameter.entrySet()) {
				if (postData.length() != 0)
					postData.append('&');
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
			con.setDoOutput(true);
			con.getOutputStream().write(postDataBytes); // POST 호출

		} else {

			boolean firstChk = true;

			for (String parameterName : parameter.keySet()) {

				if (firstChk) { // urlBuffer.append("?"); firstChk = !firstChk; } else
					paramBuffer.append("&");
				}
				paramBuffer.append(parameterName);
				paramBuffer.append("=");
				paramBuffer.append(parameter.get(parameterName));

			}

			for (String key : harderSet.keySet())
				con.setRequestProperty(key, harderSet.get(key));

			url = new URL(urlStr + "?" + paramBuffer.toString());
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
		}

		// 결과값 출력

		return con;

	}

	private StringBuffer connectionStrBuffer(HttpURLConnection con) {
		BufferedReader bufferedreader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			bufferedreader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = bufferedreader.readLine()) != null) {
				buffer.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return buffer;
	}

}
