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
        <c:if test="${not empty sight}">
            <div class="card border-primary mb-3 w-50">
                <div class="card-header text-primary"><i class="fa fa-fw fa-area-chart"></i>&nbsp;景点详情
                </div>
                <div class="card-body">
                    <h4 class="card-title text-primary">景点名称&nbsp;:&nbsp;<span
                            class="text-dark">${sight.name}</span>
                    </h4>
                    <h5 class="card-title text-primary">简介</h5>
                    <p class="card-text text-dark">${sight.description}</p>
                    <h5 class="card-title text-primary">位置</h5>
                    <p class="card-text text-dark">纬度&nbsp;:&nbsp;${sight.latitude} &nbsp;&nbsp;
                        经度&nbsp;:&nbsp;${sight.longitude}</p>
                </div>
            </div>
        </c:if>
    </div>
    <jsp:include page="foot.jsp"/>
</div>
</body>

</html>

