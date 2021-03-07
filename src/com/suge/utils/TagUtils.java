package com.suge.utils;


import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import java.io.IOException;
import java.time.LocalTime;

/**
 * @author suu
 * @create 2020-12-15 20:05
 */
public class TagUtils  implements SimpleTag {

    JspContext jspContext = null;
    JspTag parent = null;
    @Override
    public void setJspContext(JspContext jspContext){
        this.jspContext = jspContext;
    }
    @Override
    public void setParent(JspTag parent){
        this.parent = parent;
    }
    @Override
    public void setJspBody(JspFragment jspBody){
    }
    @Override
    public JspTag getParent(){
        return parent;
    }
    @Override
    public void doTag() throws JspException, IOException{
        JspWriter out = jspContext.getOut();
        out.print("<p style=\"color:blue\">现在时间是："+LocalTime.now()+"</p>");

    }
}
