<%@taglib prefix="timing" uri="http://mycompany.com" %>
<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/12/14
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@include file="/pages/common/header.jsp"%>

    <title>在线人数</title>
</head>
<body>
<div align="center" >

    <br>
    <br>
    <timing:TagUtils/>

    <span>当前在线用户:${applicationScope.onlineCount }人</span>
    <br>
    <br>
    <br>
    <a href="index.jsp">返回</a>
</div>
<%-- 每隔三秒刷新一次--%>

<%
    response.setHeader("refresh","3");
%>

</body>
</html>
