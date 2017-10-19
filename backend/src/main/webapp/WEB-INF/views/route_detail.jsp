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
        <c:if test="${not empty route}">
            <div class="card border-warning mb-3 w-50">
                <div class="card-header text-warning"><i class="fa fa-pagelines"></i>&nbsp;线路详情
                </div>
                <div class="card-body">
                    <h4 class="card-title text-warning">线路名称&nbsp;:&nbsp;<span
                            class="text-dark">${route.name}</span>
                    </h4>
                    <h5 class="card-title text-warning">简介</h5>
                    <p class="card-text text-dark">${route.description}</p>
                    <c:if test="${route.sightChain != null && !route.sightChain.isEmpty()}">
                        <h5 class="card-title text-warning">所经过景点</h5>
                        <c:forEach items="${route.sightChain}" var="sight">
                            <a href="<%=Routers.SIGHT%>/${sight.id}"
                               class="badge badge-warning" style="font-size: 15px">${sight.name}</a>
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
