<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <c:if test="${fn:length(plants.images) gt 0}">
                        <h5 class="card-title text-success">相关图片</h5>
                        <div id="plantsImages" class="carousel slide w-50" data-ride="carousel">
                            <div class="carousel-inner w-100">
                                <c:forEach var="image" items="${plants.images}" varStatus="loop">
                                    <c:if test="${loop.index == 0}">
                                        <div class="carousel-item active img-wrapper w-100">
                                            <img class="d-block w-100" src="${image.url}">
                                        </div>
                                    </c:if>
                                    <c:if test="${loop.index != 0}">
                                        <div class="carousel-item img-wrapper w-100">
                                            <img class="d-block w-100" src="${image.url}">
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <a class="carousel-control-prev" href="#plantsImages" role="button"
                               data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#plantsImages" role="button"
                               data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:if>
    </div>
    <jsp:include page="foot.jsp"/>
</div>
</body>

</html>

