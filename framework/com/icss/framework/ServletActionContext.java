package com.icss.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.framework.filter.ActionModel;

public class ServletActionContext {
	// 获取servlet上下文
	private static ThreadLocal<ServletActionContext> servletActionContext = new ThreadLocal<ServletActionContext>();
	// 一些 servlet 中的属性
	private HttpServletRequest request; // 请求
	private HttpServletResponse response; // 相应
	private ActionModel actionModel; // action中Model
	private Object action; // 请求动作

	// 构造方法
	public ServletActionContext(HttpServletRequest request, HttpServletResponse response, ActionModel actionModel,
			Object action) {
		super();
		this.request = request;
		this.response = response;
		this.actionModel = actionModel;
		this.action = action;
		servletActionContext.set(this);
	}

	public static HttpServletRequest getRequest() {
		return servletActionContext.get().request;
	}

	public static HttpServletResponse getResponse() {
		return servletActionContext.get().response;
	}

	public static ActionModel getActionModel() {
		return servletActionContext.get().actionModel;
	}

	public static Object getAction() {
		return servletActionContext.get().action;
	}

}
