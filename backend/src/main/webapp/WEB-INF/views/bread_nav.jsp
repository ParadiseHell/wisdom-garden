<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chengtao.wisdomgarden.Routers" %><%--
  Created by IntelliJ IDEA.
  User: chengtao
  Date: 10/16/17
  Time: 1:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 面包屑导航-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<%=Routers.INDEX%>">首页</a>
    </li>
    <c:if test="${not empty currentView}">
        <li class="breadcrumb-item">
            <a href="${viewRouter}">${currentView}</a>
        </li>
    </c:if>
    <c:if test="${viewAndRouter != null && !viewAndRouter.isEmpty()}">
        <c:forEach items="${viewAndRouter}" var="item">
            <li class="breadcrumb-item">
                <a href="${item.viewRouter}">${item.viewName}</a>
            </li>
        </c:forEach>
    </c:if>
</ol>
