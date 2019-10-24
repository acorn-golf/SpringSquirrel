package com.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestHttpURLConnection
{
	public final static int GET = 0;
	public final static int POST = 1;
	public final static int PUT = 2;
	public final static int DELETE = 3;

	public void requestJsone(final int _method, final int _key, final String _url, final JSONObject _param, String token, final RequestHttpCallBack _callback) 
	{
		Thread thread = new Thread(new Runnable() 
		{
            @Override
            public void run() {
                try
                {
                	StringBuilder respData = new StringBuilder();
                    HttpURLConnection urlConn = null;
                    JSONObject jsonObject = null;

                    try {
                        URL url = new URL(_url);
                        urlConn = (HttpURLConnection) url.openConnection();
                        
                        urlConn.setDoOutput(true); 
                        
                        switch(_method)
                        {
	                        case GET:
	                        	urlConn.setRequestMethod("GET");
	                        	break;
	                        case POST:
	                        	urlConn.setRequestMethod("POST");
	                        	break;
	                        case PUT:
	                        	urlConn.setRequestMethod("PUT");
	                        	break;
	                        case DELETE:
	                        	urlConn.setRequestMethod("DELETE");
	                        	break;
                        }
                        
                        urlConn.setRequestProperty("Accept-Charset", "UTF-8");
                        urlConn.setRequestProperty("Context_Type", "Application/x-www-form-urlencoded");
                        urlConn.setRequestProperty("Authorization", token);
                        urlConn.setConnectTimeout(10000);
                        urlConn.setReadTimeout(10000);
                        
                        
                        
                        
                        //parameter 전달 및 데이터 읽어오기.
                        String strParams = _param.toString();
                        OutputStream os = urlConn.getOutputStream();
                        os.write(strParams.getBytes("UTF-8"));
                        os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
                        os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제.

                        //연결 요청 확인.
                        // 실패 시 null을 리턴하고 메서드를 종료.
                        if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
                        {
                            System.out.println(">>??>>??>>"+HttpURLConnection.HTTP_OK);
                        	BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                respData.append(line);
                            }
                        }else {
                        	System.out.println(">>??>>??>>안됨~~"+urlConn.getResponseCode());
                        }
                    } 
                    catch (Exception ex001){} 
                    finally 
                    {
                        if (urlConn != null)
                            urlConn.disconnect();
                    }

                    try
                    {
                        jsonObject = new JSONObject(respData.toString());
                        _callback.callBack(_key, jsonObject);
                    }
                    catch(Exception ex001)
                    {
                        _callback.callBack(_key,null);
                    }
                }
                catch(Exception ex001)
                {
                   
                }
            }
        });
        thread.start();
    }
	
	public void requestKeyValue(final int _method, final int _key, final String _url, final String _param, final RequestHttpCallBack _callback) 
	{
		Thread thread = new Thread(new Runnable() 
		{
            @Override
            public void run() {
                try
                {
                	StringBuilder respData = new StringBuilder();
                    HttpURLConnection urlConn = null;
                    JSONObject jsonObject = null;

                    try {
                        URL url = new URL(_url);
                        urlConn = (HttpURLConnection) url.openConnection();
                        
                        urlConn.setDoOutput(true); 
                        
                        switch(_method)
                        {
	                        case GET:
	                        	urlConn.setRequestMethod("GET");
	                        	break;
	                        case POST:
	                        	urlConn.setRequestMethod("POST");
	                        	break;
	                        case PUT:
	                        	urlConn.setRequestMethod("PUT");
	                        	break;
	                        case DELETE:
	                        	urlConn.setRequestMethod("DELETE");
	                        	break;
                        }
                        
                        urlConn.setRequestProperty("Accept-Charset", "UTF-8");
                        urlConn.setRequestProperty("Context_Type", "Application/x-www-form-urlencoded");
                        urlConn.setConnectTimeout(10000);
                        urlConn.setReadTimeout(10000);
                        

                        //parameter 전달 및 데이터 읽어오기.
                        String strParams = _param;
                        OutputStream os = urlConn.getOutputStream();
                        os.write(strParams.getBytes("UTF-8"));
                        os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
                        os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제.

                        //연결 요청 확인.
                        // 실패 시 null을 리턴하고 메서드를 종료.
                        if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
                        {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                respData.append(line);
                            }
                        }
                    } 
                    catch (Exception ex001){} 
                    finally 
                    {
                        if (urlConn != null)
                            urlConn.disconnect();
                    }

                    try
                    {
                        jsonObject = new JSONObject(respData.toString());
                        _callback.callBack(_key, jsonObject);
                    }
                    catch(Exception ex001)
                    {
                        _callback.callBack(_key,null);
                    }
                }
                catch(Exception ex001)
                {
                   
                }
            }
        });
        thread.start();
    }
	
    //리턴 코드
    public int getCode(JSONObject _jsonObject) {
        int returnVal = 500;
        try
        {
            returnVal = _jsonObject.getInt("result_code");
        }
        catch(Exception ex001)
        {
            returnVal = 500;
        }
        return returnVal;
    }

    //리턴 데이터 COUNT
    protected int getLength(JSONObject _jsonObject) {
        int returnVal = 0;
        try
        {
            returnVal = _jsonObject.getInt("result_length");
        }
        catch(Exception ex001)
        {
            returnVal = 0;
        }
        return returnVal;
    }

    //리턴 메세지
    public String getMessage(JSONObject _jsonObject) 
    {
        String returnVal = "";
        try
        {
            returnVal = _jsonObject.getString("result_message");
        }
        catch(Exception ex001)
        {
            returnVal = "";
        }
        return returnVal;
    }

    //리턴 데이타
    public ArrayList<HashMap<String,String>> getData(JSONObject _jsonObject) throws Exception
    {
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        JSONArray jsonArray = _jsonObject.getJSONArray("result_data");
        for (int i=0; i < jsonArray.length(); i++)
        {
        	HashMap<String,String> InputData = new HashMap<>();

            JSONObject eleObject = jsonArray.getJSONObject(i);
            Iterator key = eleObject.keys();
            while(key.hasNext())
            {
                String keyName = key.next().toString();
                InputData.put(keyName, eleObject.getString(keyName));
            }
            list.add(InputData);
        }
    
        return list;
    }

    public ArrayList<HashMap<String,String>> getData(JSONObject _jsonObject, String _key) throws Exception
    {
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

        JSONArray jsonArray = _jsonObject.getJSONArray(_key);
        for (int i=0; i < jsonArray.length(); i++)
        {
	    	HashMap<String,String> InputData = new HashMap<>();
	
	        JSONObject eleObject = jsonArray.getJSONObject(i);
	        Iterator key = eleObject.keys();
	        while(key.hasNext())
	        {
	            String keyName = key.next().toString();
	            InputData.put(keyName,eleObject.getString(keyName));
	        }
	        list.add(InputData);
        }
        return list;
    }
}
