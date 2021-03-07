package com.suge.controller;

import com.suge.bean.CartItem;
import com.suge.bean.Phone;
import com.suge.bean.User;
import com.suge.service.CartService;
import com.suge.service.PhoneService;
import com.suge.service.impl.CartServiceImpl;
import com.suge.service.impl.PhoneServiceImpl;
import com.suge.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author suu
 * @create 2020-12-13 18:21
 */
public class CartServlet extends BaseServlet{

    PhoneService phoneService = new PhoneServiceImpl();
    CartService cartService = new CartServiceImpl();


    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品id 这个值也将作为购物车商品项的id值
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //获取用户登录的用户的id(新增)
        //为null
        User user = (User) req.getSession().getAttribute("user");

        //没有登录无法添加商品
        if (user == null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = user.getId();

        //在数据库中找到该商品
        Phone phone = phoneService.queryPhoneById(id);
        //将商品信息转换为购物车商品项
        CartItem cartItem = new CartItem(phone.getId(), phone.getName(), 1, phone.getPrice(), phone.getPrice(), userId);

        //从session中查找cart对象，有就用，没有就创建
        //！！因为整个会话都是一个客户的一次购物，所以只能有一个购物车对象
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        if (cart == null){
//            cart = new Cart();
//            req.getSession().setAttribute("cart",cart);
//
//        }
//
//        cart.addItem(cartItem);


        //保存到数据库中（改）
        //需要判断有没有重复
        CartItem item = cartService.queryCartItemByIdAndUserId(id ,userId);
        if (item == null) {
            // 之前没添加过此商品
            cartService.addItem(cartItem);
        } else {
            // 已经 添加过的情况-->数量 累加

            cartService.updateCount(id , item.getCount() + 1 ,userId);
            // 更新商品项总金额

            cartService.updateTotalPrice(id , item.getPrice().multiply(new BigDecimal( item.getCount() +1 )  ) , userId);

        }
        

        /*
            存到session中的写法
            cart.addItem(cartItem);

         */

        // 重定向回原来商品所在的地址页面
        //Referer请求求头记录了 请求的网址
        resp.sendRedirect(req.getHeader("Referer"));


    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品id 这个值也将作为购物车商品项的id值
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 获取购物车对象
//        Cart cart = (Cart) req.getSession().getAttribute("cart");

        //获取用户登录的用户的id(新增)
        User user = (User) req.getSession().getAttribute("user");

        //没有登录无法添加商品
        if (user == null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = user.getId();

//        if (cart != null) {
            // 删除购物车商品项
//            cart.deleteItem(id);

            //删除购物车中的项
            cartService.deleteItem(id , userId);

//        }


        // 重定向回原来购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取用户登录的用户的id(新增)
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();


        // 1 获取购物车对象
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        if (cart != null) {
            // 清空购物车
//            cart.clear();
            cartService.clearByUserId(userId);
//        }


        // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        // 获取请求的参数 商品编号 、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        //获取用户登录的用户的id(新增)

        User user = (User) req.getSession().getAttribute("user");

        //没有登录无法添加商品
        if (user == null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = user.getId();

        // 获取 Cart 购物车对象
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        if (cart != null) {
//             修改商品数量
//            cart.updateCount(id,count);
            //修改购物车中id号的商品（id值与商品编号相同）
            cartService.updateCount(id, count, userId);
//        }



        // 重定向回原来购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void showCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        //获取用户登录的用户的id(新增)
        User user = (User) req.getSession().getAttribute("user");

        //没有登录无法添加商品
        if (user == null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = user.getId();

        //从数据据库取出数据
        List<CartItem> cartItems = cartService.showCartItems(userId);

        //保存到域中
        req.getSession().setAttribute("cartItems",cartItems);

        //获取购物车项的数以及金额
        Integer totalCount = cartService.getTotalCount(userId);
        req.getSession().setAttribute("cartItemTotalCount" ,totalCount);

        BigDecimal totalPrice = cartService.getTotalPrice(userId);
        req.getSession().setAttribute("cartItemTotalPrice",totalPrice);

        req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req,resp);
    }






}
