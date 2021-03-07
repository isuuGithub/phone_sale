<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/12/6
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--动态获取服务器路径--%>
<%
    String basePath = request.getScheme()               //协议号
                     + "://"
                     + request.getServerName()        //ip地址
                     + ":"
                     + request.getServerPort()       //端口号
                     + request.getContextPath()      //工程路径(带有/加上工程名)
                     + "/";

    pageContext.setAttribute("basePath",basePath);
    pageContext.getSession().setAttribute("basePath",basePath);

%>

<!--写 base 标签，永远固定相对路径跳转的结果-->
<%--即只要用包含了这个jsp的页面所有地址都相当于用了绝对路径，因为这里给出了绝对路径前面的（所以页面只需要写相对的路径）--%>
<base href="<%=basePath%>">

<%--引入css样式--%>
<link type="text/css" rel="stylesheet" href="static/css/style.css">

<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>