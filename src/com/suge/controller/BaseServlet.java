package com.suge.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author suu
 * @create 2020-12-07 0:55
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");

        Method method = null;

        try {
            // 获取action业务鉴别字符串，获取相应的方法反射对象
            method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            // 调用 相应的方法反射对象 的目标业务方法
            method.invoke(this,req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
