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
        <c:if test="${serviceList != null && !serviceList.isEmpty()}">
            <h5 class="text-danger"><i class="fa fa-globe"></i>&nbsp;服务设施列表</h5>
            <hr/>
            <div class="row">
                <c:forEach items="${serviceList}" var="service">
                    <div class="col-sm-4 col-md-3">
                        <div class="card border-danger">
                            <div class="card-header text-danger">
                                <i class="fa fa-globe"></i>&nbsp;服务设施详情
                                <c:if test="${not empty isManager}">
                                    <a href="<%=Routers.SERVICE_DELETE%>/${service.serviceId}"
                                       class="btn btn-outline-danger float-right btn-sm" role="button">删除</a>
                                </c:if>
                            </div>
                            <div class="card-body">
                                <h4 class="card-title text-danger">服务设施&nbsp;:&nbsp;<span
                                        class="text-dark">${service.name}</span>
                                </h4>
                                <h5 class="card-title text-danger">位置</h5>
                                <p class="card-text text-dark">纬度&nbsp;:&nbsp;${service.latitude}
                                    &nbsp;&nbsp;
                                    经度&nbsp;:&nbsp;${service.longitude}</p>
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

