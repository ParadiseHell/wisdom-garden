<%@ page import="com.chengtao.wisdomgarden.Parameters" %>
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
        <div class="card-body">
            <form action="<%=Routers.PLANTS%>" method="post" id="plantsForm">
                <div class="form-group">
                    <div class="form-row">
                        <label>植物名称</label>
                        <input type="text" class="form-control"
                               placeholder="请输入植物名称" name="<%=Parameters.NAME%>">
                    </div>
                </div>
                <div class="form-group">
                    <label>植物描述</label>
                    <textarea class="form-control" rows="5"
                              placeholder="请填写植物相关描述"
                              name="<%=Parameters.DESCRIPTION%>"></textarea>
                </div>
                <c:if test="${sightList != null && !sightList.isEmpty()}">
                    <div class="form-group">
                        <label>选择植物所在景点</label>
                        <select id="sightSelect" name="states[]" multiple="multiple"
                                class="form-control">
                            <c:forEach items="${sightList}" var="sight">
                                <option value="${sight.id}">${sight.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-success btn-lg btn-block">创建植物</button>
            </form>
        </div>
    </div>
    <jsp:include page="foot.jsp"/>
    <script type="text/javascript">
      $().ready(function () {
        $("#plantsForm").validate({
          rules: {
            name: {
              required: true, minlength: 2, maxlength: 30
            }, description: {
              required: true
            }
          }
        });
        $('#sightSelect').select2();
      });
    </script>
</div>
</body>
</html>