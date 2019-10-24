package com.request;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

public abstract class RequestHttpCallBack 
{
	public abstract void callBack(int _key, JSONObject _json);
}
