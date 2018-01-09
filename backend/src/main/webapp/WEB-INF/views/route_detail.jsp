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
<script src="/statics/js/marker.js"></script>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<jsp:include page="main_nav.jsp"/>
<div class="content-wrapper">
    <div class="container-fluid">
        <jsp:include page="bread_nav.jsp"/>
        <c:if test="${not empty route}">
            <div class="card border-warning mb-3">
                <div class="card-header text-warning">
                    <i class="fa fa-pagelines"></i>&nbsp;线路详情
                    <c:if test="${not empty isManager}">
                        <a href="<%=Routers.ROUTE_DELETE%>/${route.routeId}"
                           class="btn btn-outline-danger float-right btn-sm" role="button">删除</a>
                        <a href="<%=Routers.ROUTE%>/${route.routeId}<%=Routers.UPDATE%>"
                           style="margin-right:5px"
                           class="btn btn-outline-warning float-right btn-sm" role="button">更新</a>
                    </c:if>
                </div>
                <div class="card-body">
                    <h4 class="card-title text-warning">线路名称&nbsp;:&nbsp;<span
                            class="text-dark">${route.name}</span>
                    </h4>
                    <h5 class="card-title text-warning">简介</h5>
                    <p class="card-text text-dark">${route.description}</p>
                    <c:if test="${route.sightChain != null && !route.sightChain.isEmpty()}">
                        <h5 class="card-title text-warning">所经过景点</h5>
                        <c:forEach items="${route.sightChain}" var="sight" varStatus="status">
                            <c:if test="${status.first}">
                                <script>
                                  initStartLocation(${sight.longitude}, ${sight.latitude});
                                </script>
                            </c:if>
                            <c:if test="${status.last}">
                                <script>
                                  initEndLocation(${sight.longitude}, ${sight.latitude});
                                </script>
                            </c:if>
                            <a href="<%=Routers.SIGHT%>/${sight.id}"
                               class="badge badge-warning" style="font-size: 15px">${sight.name}</a>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div style="width: 100%;height: 500px" id="map">

            </div>
            <div id="panel"></div>
        </c:if>
    </div>
    <jsp:include page="foot.jsp"/>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.4.0&key=6ce14a4fe21aa2ee8937f25cbc3d5cd4&callback=loadRoute"></script>
</div>
</body>

</html>

