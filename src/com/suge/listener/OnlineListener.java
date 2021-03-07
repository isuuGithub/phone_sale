package com.suge.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author suu
 * @create 2020-12-14 21:28
 */
@WebListener
public class OnlineListener implements HttpSessionListener , ServletContextListener {


    int onlineCount=0;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {//创建在线人数
        ServletContext context=se.getSession().getServletContext();
        Integer onlineCount=(Integer)context.getAttribute("onlineCount");
        //找不到表示第一个人访问
        if(onlineCount==null){
            onlineCount=1;
            context.setAttribute("onlineCount", onlineCount);
        }else {
            onlineCount++;
            context.setAttribute("onlineCount", onlineCount);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {//销毁
        ServletContext context=se.getSession().getServletContext();
        Integer onlineCount=(Integer)context.getAttribute("onlineCount");
        onlineCount--;
        context.setAttribute("onlineCount", onlineCount);
    }


}
