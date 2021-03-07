package com.suge.controller;

import com.suge.bean.Order;
import com.suge.bean.OrderItem;
import com.suge.bean.User;
import com.suge.service.OrderService;
import com.suge.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author suu
 * @create 2020-12-13 21:04
 */
public class ClientOrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取 Cart 购物车对象
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取 Userid
        User loginUser = (User) req.getSession().getAttribute("user");
        //域中没有 说明还没有登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();

        // 调用 orderService.createOrder(Userid);生成订单 存到数据库
        String orderId = orderService.createOrder(userId);

        //供jsp显示用
        req.getSession().setAttribute("orderId",orderId);
        //不能用请求转发，怕表单重复提交
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     * 查看我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 Userid
        User loginUser = (User) req.getSession().getAttribute("user");
        //域中没有 说明还没有登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();

        List<Order> orders = orderService.showMyOrders(userId);

        req.getSession().setAttribute("orders",orders);

        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

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

        req.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(req,resp);
    }

    /**
     * 确认收货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receivedOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");

        orderService.receivedOrder(orderId);

        // 重定向到订单页面
       resp.sendRedirect(req.getHeader("referer"));
    }
}
