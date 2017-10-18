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
        <h5 class="text-danger">
            <i class="fa fa-globe"></i>&nbsp;服务设施列表
            <c:if test="${not empty isManager}">
                <a href="<%=Routers.SERVICE_CREATE%>"
                   class="btn btn-outline-danger btn-sm float-right">创建服务设施</a>
            </c:if>
        </h5>
        <hr/>
        <c:if test="${serviceNameCountList != null && !serviceNameCountList.isEmpty()}">
            <div class="row">
                <c:forEach items="${serviceNameCountList}" var="item">
                    <a href="<%=Routers.SERVICE%>/${item.name}" class="col-2">
                        <button class="btn btn-danger" style="cursor: pointer">
                                ${item.name} <span
                                class="badge badge-light">${item.count}</span>
                        </button>
                    </a>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <jsp:include page="foot.jsp"/>
</div>
</body>

</html>

