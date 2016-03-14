package com.icss.tags;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.icss.vo.PageVO;

@SuppressWarnings("serial")
public class PageTag extends TagSupport {

	private PageVO<?> page;
	private String url;
	private String template="index";
	private int num = 10;
	private String var = "list";
	private final static String PATH="/WEB-INF/tags/page/";
	
	private Object temp = null;
	
	@Override
	public int doStartTag() throws JspException {
		if(page==null||page.getTotal()==0){
			return super.doStartTag();
		}
		
		List<PageItem> list = new ArrayList<PageItem>();
		temp = pageContext.getRequest().getAttribute(var);
		pageContext.getRequest().setAttribute(var, list);
		if(page.getPageMax()>num){
			if(page.getCurrent()!=1){
				PageItem p = new PageItem();
				list.add(p);
				p.setIsCurrent(false);
				p.setPage(1);
				p.setText("&laquo;");
				p.setUrl(url.replaceAll("\\{page\\}", "1"));
			}
			
			int startPage = page.getCurrent()-(num/2);
			if(startPage<1){
				startPage=1;
			}
			int endPage = startPage+num-1;
			
			if(endPage>page.getPageMax()){
				endPage=page.getPageMax();
				startPage = endPage-num+1;
			}
			
			for(int i=startPage;i<=endPage;i++){
				PageItem p = new PageItem();
				list.add(p);
				p.setIsCurrent(page.getCurrent()==i);
				p.setPage(i);
				p.setText(Integer.toString(i));
				p.setUrl(url.replaceAll("\\{page\\}", Integer.toString(i)));
			}
			
			if(page.getCurrent()!=page.getPageMax()){
				PageItem p = new PageItem();
				list.add(p);
				p.setIsCurrent(false);
				p.setPage(page.getPageMax());
				p.setText("&raquo;");
				p.setUrl(url.replaceAll("\\{page\\}", Integer.toString(page.getPageMax())));
			}
			
		}else{
			for(int i=1;i<=page.getPageMax();i++){
				PageItem p = new PageItem();
				list.add(p);
				p.setIsCurrent(page.getCurrent()==i);
				p.setPage(i);
				p.setText(Integer.toString(i));
				p.setUrl(url.replaceAll("\\{page\\}", Integer.toString(i)));
			}
		}
		
		try {
			
			pageContext.include(PATH+template+".jsp");
		} catch (Exception e) {
			throw new JspException(e);
		}
		
		pageContext.getRequest().setAttribute(var, temp);
		return super.doStartTag();
	}

	public PageVO<?> getPage() {
		return page;
	}

	public void setPage(PageVO<?> page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
