<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@include file="/pages/common/header.jsp"%>


	<script type="text/javascript">
		$(function () {
			// 给删除的a标签绑定单击事件，用于删除的确认提示操作
			$("a.deleteClass").click(function () {
				// 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
				/**
				 * confirm是确认提示框函数
				 * 参数是它的提示内容
				 * 它有两个按钮，一个确认，一个是取消。
				 * 返回true表示点击了，确认，返回false表示点击取消。
				 */
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
				// return false// 阻止元素的默认行为===不提交请求
			});
		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">商品管理系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/pages/common/manager_menu.jsp"%>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>品牌</td>
				<td>价格</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		

			
			<c:forEach items="${requestScope.page.items}" var="phone">
				<tr>
					<td>${phone.name}</td>
					<td>${phone.brand}</td>
					<td>${phone.price}</td>
					<td>${phone.sales}</td>
					<td>${phone.stock}</td>
					<%--在修改的请求地址上追加当前页码参数--%>
					<td><a href="manager/phoneServlet?action=showPhone&id=${phone.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/phoneServlet?action=delete&id=${phone.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<%--因为这个超链接要经过jsp再到servlet，所以要在这里带上一个参数，带到jsp中再传给servlet--%>
				<%--当前页码跳到最后一页--%>
				<td><a href="pages/manager/phone_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加商品</a></td>
			</tr>	
		</table>


		<%--静态包含分页功能--%>
		<%@include file="/pages/common/page_nav.jsp"%>

	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>