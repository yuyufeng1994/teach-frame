package com.icss.framework.filter;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.icss.framework.annotation.Result;
/**
 * 请求Action的模型
 * @author yyf
 *
 */
public class ActionModel {
	//请求地址
	private String uri;
	private Class<?> cls;
	private Method method;
	private Map<String,Result> results = new HashMap<String, Result>(0);
	
	public Class<?> getCls() {
		return cls;
	}
	public void setCls(Class<?> cls) {
		this.cls = cls;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
		
	}
	
	public Collection<Result> getResults(){
		return results.values();
	}
	
	public void addResult(Collection<Result> rs){
		for(Result r : rs){
			addResult(r);
		}
	}
	
	public void addResult(Result result){
		results.put(result.name(), result);
	}
	
	public Result getResult(String name){
		return results.get(name);
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	@Override
	public String toString() {
		return  uri + "=" + cls.getName() + "."
				+ method.getName() + "()";
	}
	
	
	
	
}
