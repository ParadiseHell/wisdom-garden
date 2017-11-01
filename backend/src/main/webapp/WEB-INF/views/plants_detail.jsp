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
        <c:if test="${not empty plants}">
            <div class="card border-success mb-3">
                <div class="card-header text-success">
                    <i class="fa fa-pagelines"></i>&nbsp;植物详情
                    <c:if test="${not empty isManager}">
                        <a href="<%=Routers.PLANTS_DELETE%>/${plants.plantsId}"
                           class="btn btn-outline-danger float-right btn-sm" role="button">删除</a>
                        <a href="<%=Routers.PLANTS%>/${plants.plantsId}<%=Routers.UPDATE%>"
                           style="margin-right:5px"
                           class="btn btn-outline-success float-right btn-sm" role="button">更新</a>
                    </c:if>
                </div>
                <div class="card-body">
                    <h4 class="card-title text-success">植物名称&nbsp;:&nbsp;<span
                            class="text-dark">${plants.name}</span>
                    </h4>
                    <h5 class="card-title text-success">简介</h5>
                    <p class="card-text text-dark">${plants.description}</p>
                    <c:if test="${plants.sights != null && !plants.sights.isEmpty()}">
                        <h5 class="card-title text-success">所在景点</h5>
                        <c:forEach items="${plants.sights}" var="sight">
                            <a href="<%=Routers.SIGHT%>/${sight.id}"
                               class="badge badge-success" style="font-size: 15px">${sight.name}</a>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </c:if>
    </div>
    <jsp:include page="foot.jsp"/>
</div>
</body>

</html>

