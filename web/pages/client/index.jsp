<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商城首页</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/header.jsp"%>

	<Script type="text/javascript">
		$(function () {
			// 给加入购物车按钮绑定单击事件
			$("button.addToCart").click(function () {
				/**
				 * 在事件响应的function函数 中，有一个this对象，这个this对象，是当前正在响应事件的dom对象
				 * @type {jQuery}
				 */
				var phoneId = $(this).attr("phoneId");
				//？？
				location.href = "${sessionScope.basePath}cartServlet?action=addItem&id=" + phoneId;

			});
		});
	</Script>



</head>
<body>
	
	<div id="header">

		<a href="pages/listener/OnlineNum.jsp">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</a>
			<span class="wel_word">网上商城</span>

		<div>
			<%--如果用户还没有登录，显示     【登录 和注册的菜单】 --%>
			<c:if test="${empty sessionScope.user}">
				<a href="pages/user/login.jsp">登录</a> |
				<a href="pages/user/register.jsp">注册</a> &nbsp;&nbsp;
			</c:if>

			<%--如果已经登录，则显示 登录 成功之后的用户信息。--%>
			<c:if test="${not empty sessionScope.user}">
				<span>
					欢迎&nbsp;&nbsp;
					<a href="pages/user/uploadPhoto.jsp" >
						<img src="static/img/${sessionScope.user.username}.jpg" alt="上传头像" width="40px" height="40px" >

					</a>
					<span class="um_span">${sessionScope.user.username}</span>光临苏歌商城

				</span>
				<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;&nbsp;
				<a href="orderServlet?action=showMyOrders">我的订单</a>
			</c:if>

<%--			<a href="pages/cart/cart.jsp">购物车</a>--%>
			<a href="cartServlet?action=showCartItem">购物车</a>
<%--			<a href="pages/manager/manager.jsp">后台管理</a>--%>
		</div>


	</div>

	<div id="main">
		<div id="phone">
			<br/>
			<div class="phone_cond">
				<form action="client/phoneServlet" method="get">
					<input type="hidden" name="action" value="pageByKey">
					<%--查询的时候已经把参数发送到服务器了 ，再取出来就行--%>
					搜索：<input style="width: 100px"  id="key" type="text" name="key" value="${param.key}">
						<input type="submit" value="查询" />
				</form>
			</div>

			<br/>

			<%--				这里的数据由index转发到servlet再转发到这页得来--%>
			<c:forEach items="${requestScope.page.items}" var="phone">
			<div class="b_list">
				<div class="img_div">
					<img class="phone_img" alt="" src="${phone.imgPath}" />
				</div>
				<div class="phone_info">
					<div class="phone_name">
						<span class="sp1">名称:</span>
						<span class="sp2">${phone.name}</span>
					</div>
					<div class="phone_author">
						<span class="sp1">品牌:</span>
						<span class="sp2">${phone.brand}</span>
					</div>
					<div class="phone_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${phone.price}</span>
					</div>
					<div class="phone_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${phone.sales}</span>
					</div>
					<div class="phone_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${phone.stock}</span>
					</div>
					<div class="phone_add">
						<%-- 自定义属性可以记录商品的id值，点击按钮的时候可以传到服务器--%>
						<button phoneId="${phone.id}" class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>

		<%--静态包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>




	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>