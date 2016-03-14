package com.icss.commons;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	
	Map<String, Cookie> map = new HashMap<String, Cookie>();
	
	public CookieUtil(HttpServletRequest request){
		if(request.getCookies()!=null){
			for(Cookie c : request.getCookies()){
				map.put(c.getName(), c);
			}
		}
	}
	
	public Cookie get(String name){
		return map.get(name);
	}

}
