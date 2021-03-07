package com.suge.controller;

import com.suge.bean.User;
import com.suge.dao.impl.BaseDao;
import com.suge.service.UserService;
import com.suge.service.impl.UserServiceImpl;
import com.suge.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author suu
 * @create 2020-12-07 1:02
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();


    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.验证账户密码是否正确
        User user = userService.login(new User(null, username, password, null));
        //对象非空（数据库中存在相应的数据），说明登录成功
        if (user!=null){

            //记住用户名与密码
            Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            //向服务器发送cookie对象，生命周期为一个对话（关闭浏览器）
            resp.addCookie(usernameCookie);
            resp.addCookie(passwordCookie);

            req.getSession().setAttribute("user",user);

            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }else {

            //把错误信息回传到客户端（还有回显表单信息）
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁 Session 中用户登录的信息（或者销毁 Session）
        req.getSession().invalidate();
        //重定向到首页。
        resp.sendRedirect(req.getContextPath());

    }

    /**
     * 处理注册业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取（表单）请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");




        //把参数注入到bean对象
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //2、检查 验证码是否正确(忽略大小写) === 写死,要求验证码为:abcde
        if ("abcde".equalsIgnoreCase(code)){  //相等

            //3.验证用户名是否可用
            if (userService.existsUsername(username)){

                //把需要回显的信息保存到域中
                req.setAttribute("msg","用户名不可用");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                //不可用，跳回注册页面
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req,resp);



            }else{
                //可用，注册用户,并保存到数据库
                userService.registerUser(user);

                //保存至域中

                //注册成功后记住用户名与密码
                Cookie usernameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);
                //向服务器发送cookie对象，生命周期为一个对话（关闭浏览器）
                resp.addCookie(usernameCookie);
                resp.addCookie(passwordCookie);

                //注册成功，跳到注册成功页面
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req,resp);


            }
        }else {
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            req.getRequestDispatcher("/pages/user/register.jsp").forward(req,resp);
        }

    }


    protected void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/xml;charset=UTF-8");
        resp.setHeader("Cache-Control","no-cache");


        String username = req.getParameter("username");
        String message = "用户名可以使用！";
        PrintWriter out = resp.getWriter();

        if(userService.existsUsername(username)){
            message = "用户名已被占用！";
        }
        out.println("<response>");
        out.println("<message>"+message+"</message>");
        out.println("</response>");
    }


}
