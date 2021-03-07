<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/12/14
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户头像</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@include file="/pages/common/header.jsp"%>


</head>
<body>
    <div>
            <form action="fileServlet" enctype="multipart/form-data" method="post" >
                <input type="hidden" name="action" value="upload">
                <br>
                <div align="center" >
                    <img src="static/img/${sessionScope.user.username}.jpg" height="400px" width="600px">
                    <br/>
                    <br/>
                    上传头像：<input type="file" name="fileName" size="20" >
                    <input type="submit" value="上传"><br>
                    <a href="fileServlet?action=download"> 下载头像</a><br><br>
                    <a href="index.jsp">返回</a>

                </div>


            </form>
    </div>
</body>
</html>
