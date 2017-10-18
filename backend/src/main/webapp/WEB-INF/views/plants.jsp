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
        <h5 class="text-success ">
            <i class="fa fa-pagelines"></i>&nbsp;植物列表
            <c:if test="${not empty isManager}">
                <a href="<%=Routers.PLANTS_CREATE%>"
                   class="btn btn-outline-success btn-sm float-right">创建植物</a>
            </c:if>
        </h5>
        <hr/>
        <c:if test="${plantsList != null && !plantsList.isEmpty()}">
            <div class="row">
                <c:forEach items="${plantsList}" var="plants">
                    <div class="col-sm-4 col-md-3 mb-2">
                        <div class="card">
                            <c:if test="${plants.files != null && !plants.files.isEmpty()}">
                                <img class="card-img-top" src="..." alt="Card image cap">
                            </c:if>
                            <c:if test="${plants.files == null || !plants.files.isEmpty()}">
                                <img class="card-img-top" src="../statics/images/no_image.png"
                                     alt="Card image cap">
                            </c:if>
                            <div class="card-body">
                                <h4 class="card-title">${plants.name}</h4>
                                <p class="card-text text-secondary multiline-ellipsis"
                                   style="height: 12rem">${plants.description}</p>
                                <a href="<%=Routers.PLANTS%>/${plants.plantsId}"
                                   class="btn btn-success" role="button"
                                   style="margin-top: 4px">详情</a>
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

