package com.suge.controller;

import com.suge.bean.Page;
import com.suge.bean.Phone;
import com.suge.service.PhoneService;
import com.suge.service.impl.PhoneServiceImpl;
import com.suge.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author suu
 * @create 2020-12-07 23:55
 */
public class PhoneServlet extends  BaseServlet{

    PhoneService phoneService = new PhoneServiceImpl();




    /**
     * 添加商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //跳转页码永远加一（因为有页码边界校验，即使添加商品没有用到新的一页，加一页 边界校验也会吧页码设为pageTotal）
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;

        // 1、获取请求的参数==封装成为 Phone对象（用weebutils工具类）
        Phone phone = WebUtils.copyParamToBean(req.getParameterMap(), new Phone());

        //保存到数据库
        phoneService.addPhone(phone);

        //响应重定向
        // （这里不能用请求转发，因为请求转发是一次请求 如果刷新页面会重新提交表单 即重复添加相同商品
        // （因为刷新时浏览器会将上一次请求的内容（添加商品+到phoneServlet）发送到服务器））
        resp.sendRedirect(req.getContextPath()+"/manager/phoneServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取要删除商品的id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //在数据库删除
        phoneService.deletePhone(id);

        //响应重定向，同add
        resp.sendRedirect(req.getContextPath()+"/manager/phoneServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }



    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Phone phone = WebUtils.copyParamToBean(req.getParameterMap(), new Phone());
        phoneService.updataPhone(phone);

            //跳转回修改商品所在的页面（利用隐藏域传过来的参数实现）
        resp.sendRedirect(req.getContextPath()+"/manager/phoneServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }


    /**
     * 展示商品信息 用于修改业务的回显
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showPhone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //1 获取请求的参数商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2 查询商品
        Phone phone = phoneService.queryPhoneById(id);
        //3 保存到商品到 Request 域中
        req.setAttribute("phone",phone);

        //请求转发（只查询 没有对数据库数据进行操作，不用怕重复执行  即不用响应重定向）

        req.getRequestDispatcher("/pages/manager/phone_edit.jsp").forward(req,resp);
    }

    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //获得分页对象
        Page<Phone> page = phoneService.doPage(pageNo, pageSize);
        //用于商品管理页码请求分页功能的url
        page.setUrl("manager/phoneServlet?action=page");

        req.setAttribute("page", page);

        req.getRequestDispatcher("/pages/manager/phone_manager.jsp").forward(req, resp);

    }
}
