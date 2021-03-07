package com.suge.controller;

import com.suge.bean.OrderItem;
import com.suge.bean.Page;
import com.suge.bean.Phone;
import com.suge.service.PhoneService;
import com.suge.service.impl.PhoneServiceImpl;
import com.suge.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * @author suu
 * @create 2020-12-13 0:29
 */
public class ClientPhoneServlet  extends BaseServlet{

    private PhoneService phoneService = new PhoneServiceImpl();


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //获得分页对象

        Page<Phone> page = phoneService.doPage(pageNo, pageSize);

        //用于首页请求分页功能的url
        page.setUrl("client/phoneServlet?action=page");

        req.setAttribute("page", page);


        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByKey(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String key = req.getParameter("key");


        //调用sercice方法获得Page对象
        Page<Phone> page = phoneService.doPageByKey(pageNo,pageSize,key);

        StringBuilder sb = new StringBuilder("client/phoneServlet?action=pageByKey");
        // 如果有关键字的参数,追加到分页条的地址参数中
        //如果没有后台加上参数的话，点击搜索得出商品的 分页条页码 的的时候只有pageNo一个参数，
        // 意味着客户端没有传入key的值，即key为空值，所以会导致展示所有商品了 而不是搜索得出的商品
        if (req.getParameter("key") != null) {
            sb.append("&key=").append(req.getParameter("key"));
        }


        page.setUrl(sb.toString());


        //保存Page对象到Request域中
        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
