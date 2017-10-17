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
            <form action="<%=Routers.SIGHT%>" method="post" id="sightForm">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-6">
                            <label>景点名称</label>
                            <input type="text" class="form-control"
                                   placeholder="请输入景点名称" name="<%=Parameters.SIGHT_NAME%>">
                        </div>
                        <div class="col-6">
                            <label>景点类型</label>
                            <select class="form-control" name="<%=Parameters.SIGHT_CATEGORY%>">
                                <option value="1">入口</option>
                                <option value="2">出口</option>
                                <option value="0">其他</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-6">
                            <label>景点经度</label>
                            <input type="number" class="form-control"
                                   placeholder="请输入景点经度" name="<%=Parameters.SIGHT_LONGITUDE%>"/>
                        </div>
                        <div class="col-6">
                            <label>景点纬度</label>
                            <input type="number" class="form-control"
                                   placeholder="请输入景点纬度" name="<%=Parameters.SIGHT_LATITUDE%>"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label>景点描述</label>
                    <textarea class="form-control" rows="5"
                              placeholder="请填写景点相关描述"
                              name="<%=Parameters.SIGHT_DESCRIPTION%>"></textarea>
                </div>
                <button type="submit" class="btn btn-success btn-lg btn-block">创建景点</button>
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
            }, category: {
              required: true, number: true
            }, latitude: {
              required: true, number: true, range: [-90, 90]
            }, longitude: {
              required: true, number: true, range: [-180, 180]
            }
          }, description: {
            required: true
          }
        });
      });
      ;
    </script>
</div>
</body>
</html>