<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chengtao
  Date: 10/10/17
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"/>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<jsp:include page="main_nav.jsp"/>
<div class="content-wrapper">
    <div class="container-fluid">
        <jsp:include page="bread_nav.jsp"/>
        <jsp:include page="incon_card.jsp"/>
        <h5 class="text-warning ">
            <i class="fa fa-map"></i>&nbsp;线路列表
            <c:if test="${not empty isManager}">
                <a href="<%=Routers.ROUTE_EDIT%>"
                   class="btn btn-outline-warning btn-sm float-right">创建线路</a>
            </c:if>
        </h5>
        <hr/>
        <c:if test="${routeList != null && !routeList.isEmpty()}">
            <div class="row">
                <c:forEach items="${routeList}" var="route">
                    <div class="col-sm-4 col-md-3 mb-2">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">${route.name}</h4>
                                <p class="card-text text-secondary multiline-ellipsis"
                                   style="height: 6rem">${route.description}</p>
                                <div style="margin-top: 4px">
                                    <c:if test="${not empty isManager}">
                                        <a href="<%=Routers.ROUTE_DELETE%>/${route.routeId}"
                                           class="btn btn-danger float-left" role="button">删除</a>
                                    </c:if>
                                    <a href="<%=Routers.ROUTE%>/${route.routeId}"
                                       class="btn btn-warning float-right" role="button">详情</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <jsp:include page="foot.jsp"/>
</div>
</body>

</html>

