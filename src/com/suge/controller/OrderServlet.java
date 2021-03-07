package com.suge.controller;

import com.suge.bean.Order;
import com.suge.bean.OrderItem;
import com.suge.service.OrderService;
import com.suge.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author suu
 * @create 2020-12-14 15:01
 */
public class OrderServlet extends  BaseServlet{

    OrderService orderService = new OrderServiceImpl();

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> allOrders = orderService.showAllOrders();
        req.getSession().setAttribute("allOrders",allOrders);

        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);

    }

    /**
     * 显示订单详情（订单项）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");

        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);

        req.getSession().setAttribute("orderItems",orderItems);

        req.getRequestDispatcher("/pages/manager/orderDetails.jsp").forward(req,resp);
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");

        orderService.sendOrder(orderId);

        // 重定向到订单页面
        resp.sendRedirect(req.getHeader("referer"));
    }
}
