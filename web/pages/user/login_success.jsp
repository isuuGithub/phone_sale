<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@include file="/pages/common/header.jsp"%>

	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 20px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >

<%--			&lt;%&ndash;静态包含，登录 成功之后的菜单 &ndash;%&gt;--%>
<%--			<%@ include file="/pages/common/login_success_menu.jsp"%>--%>
			<h1>欢迎光临购物系统</h1>

		</div>
		
		<div id="main">

			<c:choose>
				<c:when test="${sessionScope.user.username eq 'admin'}">
					<h1>欢迎回来</h1>
					<h1><a href="pages/manager/manager.jsp">点击进入管理员端</a></h1>

				</c:when>
				<c:otherwise>
					<h1>欢迎回来</h1>
					<h1> <a href="index.jsp">点击进入客户端</a></h1>

				</c:otherwise>

			</c:choose>


		</div>


		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>