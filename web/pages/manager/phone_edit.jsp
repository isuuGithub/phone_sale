<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑商品</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@include file="/pages/common/header.jsp"%>

	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑商品</span>

			<%-- 静态包含 manager管理模块的菜单  --%>
			<%@include file="/pages/common/manager_menu.jsp"%>


		</div>
		
		<div id="main">
			<form action="manager/phoneServlet" method="get">

				<%--由于商品添加和修改是同一个jsp页面，所以要区分开。
				可通过参数域中有没有id值判断（因为修改商品要带上id值，而添加商品不用）--%>
				<input type="hidden" name="action" value="${empty param.id ? "add":"update"}" />
					<%--在 页面中使用隐藏域记录下phone_manager.jsp传过来的 pageTotal 参数	--%>
					<%--因为该页面两用，所以当前页码会根据请求来确定是 当前页（修改） 还是最后一页（添加操作）--%>
					<input type="hidden" name="pageNo" value="${param.pageNo}" />


					<input type="hidden" name="id" value="${requestScope.phone.id}" />
					<table>
					<tr>
						<td>名称</td>
						<td>品牌</td>
						<td>价格</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.phone.name}"/></td>
						<td><input name="brand" type="text" value="${requestScope.phone.brand}"/></td>
						<td><input name="price" type="text" value="${requestScope.phone.price}"/></td>
						<td><input name="sales" type="text" value="${requestScope.phone.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.phone.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>


		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>