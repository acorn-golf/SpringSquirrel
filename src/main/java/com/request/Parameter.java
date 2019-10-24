package com.request;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

public class Parameter 
{
	HashMap<String, Object> parameter;
	
	public Parameter()
	{
		parameter = new HashMap<>();
	}
    //인자값 입력
    public void set(String _key, Object _value) {
        parameter.put(_key, _value);
    }

    // 인자값 리턴
    public StringBuffer getParameter() {
        StringBuffer parm = new StringBuffer();
        try
        {
        	boolean firstCheck = true;
            Iterator<String> keySetIterator = parameter.keySet().iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                if (firstCheck) {
            		parm.append(key + "=" + parameter.get(key).toString());
                    firstCheck = false;
                } 
                else 
                {
               		parm.append("&"+key + "=" + parameter.get(key).toString());
                }
            }
        }
        catch(Exception ex001){}
        return parm;
    }

    public void clear() {
        try
        {
            parameter.clear();
        }
        catch(Exception ex001)
        {
        }
    }

    public JSONObject toJson()
    {
    	JSONObject result = null;
    	
    	try
    	{
    		result = new JSONObject(parameter);
    	}
    	catch(Exception ex001){
    		
    	}
    	return result;
    }
    
    @Override
    public String toString() {
        return getParameter().toString();
    }
}
