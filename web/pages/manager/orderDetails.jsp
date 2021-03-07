<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@include file="/pages/common/header.jsp"%>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif" >
    <span class="wel_word">订单管理系统</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp"%>


</div>

<div id="main">

    <table>
        <tr>
            <td>订单号</td>
            <td>名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
        </tr>


            <c:forEach items="${sessionScope.orderItems}" var="orderItem">
                <tr>
                    <%--使用自定义参数，在servlet中可以用getparameter获取--%>
                    <td>${orderItem.orderId} </td>
                    <td>${orderItem.name}</td>
                    <td>${orderItem.count}</td>
                    <td>${orderItem.price}</td>
                    <td>${orderItem.totalPrice}</td>
                </tr>
            </c:forEach>





    </table>


</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>


</body>
</html>