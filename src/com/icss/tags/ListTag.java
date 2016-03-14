package com.icss.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class ListTag extends TagSupport {

	private List<?> list;
	private String varIndex="index",var="vo";
	

	
	   
	@Override
	public int doStartTag() throws JspException {
		if(list==null||list.size()==0){
			return SKIP_BODY;
		}
		int index = 0;
    	pageContext.setAttribute(varIndex, index);
    	pageContext.setAttribute(var, list.get(index));
		return EVAL_BODY_INCLUDE;
	}
	
	
	@Override
	public int doAfterBody() throws JspException {
		int index = (Integer) pageContext.getAttribute(varIndex);
    	++index;
    	if(list.size()>index){
    	    pageContext.setAttribute(varIndex, index);
    		pageContext.setAttribute(var, list.get(index));
    		return EVAL_BODY_AGAIN;
    	}else{
    		return SKIP_BODY;
    	}
	}


	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}


	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}


	public String getVarIndex() {
		return varIndex;
	}


	public void setVarIndex(String varIndex) {
		this.varIndex = varIndex;
	}


	public String getVar() {
		return var;
	}


	public void setVar(String var) {
		this.var = var;
	}

	   
	   
}
