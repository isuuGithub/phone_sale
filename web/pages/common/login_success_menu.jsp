<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/12/6
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>


<div>
	<%--如果已经登录，则显示 登录 成功之后的用户信息。--%>

	<c:if test="${not empty sessionScope.user}">
				<span>
					欢迎&nbsp;&nbsp;
					<a href="pages/user/uploadPhoto.jsp" >
						<img src="static/img/${sessionScope.user.username}.jpg" alt="上传头像" width="30px" height="30px" >
					</a>
					<span class="um_span">${sessionScope.user.username}</span>光临苏歌商城
				</span>

		<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
		<a href="orderServlet?action=showMyOrders">我的订单</a>

	</c:if>


    <a href="index.jsp">返回</a>
</div>