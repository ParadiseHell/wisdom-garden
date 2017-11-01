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
            <form action="<%=Routers.SERVICE%>" method="post" id="sightForm">
                <div class="form-group">
                    <label>服务设施名称</label>
                    <input type="text" class="form-control"
                           placeholder="请输入服务设施名称" name="<%=Parameters.NAME%>">
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-6">
                            <label>服务设施经度</label>
                            <input type="number" class="form-control"
                                   placeholder="请输入服务设施经度" name="<%=Parameters.LONGITUDE%>"/>
                        </div>
                        <div class="col-6">
                            <label>服务设施纬度</label>
                            <input type="number" class="form-control"
                                   placeholder="请输入服务设施纬度" name="<%=Parameters.LATITUDE%>"/>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-success btn-lg btn-block">创建服务设施</button>
            </form>
        </div>
    </div>
    <jsp:include page="foot.jsp"/>
    <script type="text/javascript">
      $().ready(function () {
        $("#sightForm").validate({
          rules: {
            name: {
              required: true, minlength: 2, maxlength: 30
            }, latitude: {
              required: true, number: true, range: [-90, 90]
            }, longitude: {
              required: true, number: true, range: [-180, 180]
            }
          }
        });
      });
      ;
    </script>
</div>
</body>
</html>