package com.icss.framework.filter;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.framework.annotation.Action;
import com.icss.framework.annotation.Result;
import com.icss.framework.annotation.ResultType;
import com.icss.framework.annotation.Results;
import com.icss.framework.commons.ClassUtil;
import com.icss.framework.filter.ActionFilter;
import com.icss.framework.filter.ActionModel;
import com.icss.framework.ServletActionContext;
import com.icss.framework.commons.GetterSetter;
import com.icss.framework.convert.ConvertFactory;

/**
 * 自定义框架入口过滤器
 * @author yyf
 *
 */
public class ActionFilter implements Filter {
	//配置信息
	private Properties config = new Properties();
	//请求地址-请求对应Action类键值对
	private Map<String, ActionModel> map = new HashMap<String, ActionModel>();

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		//获取请求响应对象
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		request.setCharacterEncoding("UTF-8");
		
		//获取请求地址
		String uri = request.getRequestURI();
		if (uri.endsWith(".action")) {
			
			//请求地址字符串的截取
			uri = uri.substring(request.getContextPath().length());
			uri = uri.substring(0, uri.length() - 7);
			
			//从map获取请求的类
			ActionModel model = map.get(uri);
			
			
			if (model == null) {
				response.sendError(404);
			} else {
				try {
					
					//获取模型驱动的Class，给ServletActionContext这个对象重新构造
					Class<?> cls = model.getCls();
					Object action = cls.newInstance();
					new ServletActionContext(request, response, model, action);

					//加载页面传来的数据到model中
					loadParameter(action);
					

					//把数据填入模型驱动中
					try {
						Object dataModel = cls.getMethod("getModel").invoke(
								action);
						loadParameter(dataModel);
					} catch (Exception ex) {

					}
					
					//得到model的请求方法
					String result = (String) model.getMethod().invoke(action);
					//填充数据到页面中
					loadData(action);

					
					Result r = model.getResult(result);
					if (r == null) {
						String jsp = config.getProperty("result.base.path")
								+ model.getUri() + "-" + result + ".jsp";
						if (!new File(request.getSession().getServletContext()
								.getRealPath(jsp)).exists()) {
							jsp = config.getProperty("result.base.path")
									+ model.getUri() + ".jsp";
						}
						request.getRequestDispatcher(jsp).forward(request,
								response);
					} else {
						if (r.type() == ResultType.forward) {
							request.getRequestDispatcher(r.value()).forward(
									request, response);
						} else {
							response.sendRedirect(r.value());
						}
					}
					
					
				} catch (Exception ex) {
					response.sendError(505);
					throw new ServletException("执行action错误", ex);
				}
			}
		} else {
			arg2.doFilter(arg0, arg1);
		}
	}



	//获取页面传来的数据
	public void loadParameter(Object obj) throws Exception {
		for (Method method : GetterSetter.setter(obj)) {
			String propertyName = GetterSetter.getPropertyName(method);
			String value = ServletActionContext.getRequest().getParameter(
					propertyName);
			if (value == null) {
				continue;
			}
			Object valueObject = ConvertFactory.convert(
					method.getParameterTypes()[0], value);
			method.invoke(obj, valueObject);

		}
	}



	//装载数据传值到页面
	public void loadData(Object obj) throws Exception {
		for (Method method : GetterSetter.getter(obj)) {
			String propertyName = GetterSetter.getPropertyName(method);
			Object data = method.invoke(obj);
			ServletActionContext.getRequest().setAttribute(propertyName, data);
		}
	}

	
	//初始化，把配置信息载入
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		try {
			config.load(ActionFilter.class
					.getResourceAsStream("/config.properties"));
			System.out.println("装载了框架配置信息");
			loadClassInfo(config.getProperty("action.base.package"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	
	//类扫描器
	public void loadClassInfo(String packageName) {
		Set<Class<?>> clses = ClassUtil.getClasses(packageName);
		System.out.println("扫描到的action配置信息：");
		for (Class<?> cls : clses) {
			if (cls.getSimpleName().endsWith("Action")) {
				String key = "";
				if (cls.isAnnotationPresent(Action.class)) {
					Action a = cls.getAnnotation(Action.class);
					key = a.value().trim();
				}
				key = "".equals(key) ? getDefaultUri(packageName, cls) : key;
				
				ActionModel model = new ActionModel();
				try {
					model.setCls(cls);
					model.setUri(key);
					model.setMethod(cls.getMethod("execute"));
					model.addResult(getResults(cls));
					addMap(model);
				} catch (Exception ex) {
				}

				for (Method m : cls.getMethods()) {
					if (m.isAnnotationPresent(Action.class)) {
						Action a = m.getAnnotation(Action.class);
						key = a.value().trim();
						key = "".equals(key) ? getDefaultUri(packageName, cls)
								: key;

						ActionModel model1 = new ActionModel();
						model1.setUri(key);
						model1.setCls(cls);
						model1.setMethod(m);
						model1.addResult(model.getResults());
						model1.addResult(getResults(m));
						addMap(model1);
					}
				}
			}
		}
	}


	public void addMap(ActionModel model) {
		ActionModel m1 = map.get(model.getUri());
		if (m1 == null) {
			map.put(model.getUri(), model);
			System.out.println("成功：" + model);
		} else {
			System.err.println("失败：" + model);
		}
	}

	
	public String getDefaultUri(String packageName, Class<?> cls) {
		String namespace = cls.getPackage().getName()
				.substring(packageName.length());
		namespace = namespace.replace(".", "/");
		String name = cls.getSimpleName().substring(0,
				cls.getSimpleName().length() - 6);
		StringBuffer n = new StringBuffer();
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (i == 0) {
				n.append(Character.toLowerCase(c));
			} else if (c >= 'A' && c <= 'Z') {
				n.append("-");
				n.append(Character.toLowerCase(c));
			} else {
				n.append(c);
			}
		}
		return namespace + "/" + n.toString();
	}

	
	//得到注解信息
	public List<Result> getResults(AnnotatedElement obj) {
		List<Result> list = new ArrayList<Result>(0);
		if (obj.isAnnotationPresent(Result.class)) {
			list.add(obj.getAnnotation(Result.class));
		}
		if (obj.isAnnotationPresent(Results.class)) {
			java.util.Collections.addAll(list, obj.getAnnotation(Results.class)
					.value());
		}
		return list;
	}

}
