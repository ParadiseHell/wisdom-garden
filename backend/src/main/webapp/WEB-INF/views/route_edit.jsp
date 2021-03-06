<%--suppress ALL --%>
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
            <c:if test="${not empty route}">
                <form action="<%=Routers.ROUTE%>/${route.routeId}" method="post" id="routeForm">
                    <div class="form-group">
                        <div class="form-row">
                            <label>路线名称</label>
                            <input type="text" class="form-control"
                                   placeholder="请输入路线名称" name="<%=Parameters.NAME%>"
                                   value="${route.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>路线描述</label>
                        <textarea class="form-control" rows="5"
                                  placeholder="请填写路线相关描述"
                                  name="<%=Parameters.DESCRIPTION%>"
                                  value="${route.description}"></textarea>
                    </div>
                    <c:if test="${sightList != null && !sightList.isEmpty()}">
                        <div class="form-group">
                            <label>路线所经过景点</label>
                            <input type="text" hidden name="<%=Parameters.SIGHT_IDS%>"
                                   id="sightIds">
                            <select id="sightSelect" name="states[]" multiple="multiple"
                                    class="form-control">
                                <c:forEach items="${sightList}" var="sight">
                                    <option value="${sight.id}">${sight.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-success btn-lg btn-block">更新路线</button>
                </form>
            </c:if>
            <c:if test="${route == null}">
                <form action="<%=Routers.ROUTE%>" method="post" id="routeForm">
                    <div class="form-group">
                        <div class="form-row">
                            <label>路线名称</label>
                            <input type="text" class="form-control"
                                   placeholder="请输入路线名称" name="<%=Parameters.NAME%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>路线描述</label>
                        <textarea class="form-control" rows="5"
                                  placeholder="请填写路线相关描述"
                                  name="<%=Parameters.DESCRIPTION%>"></textarea>
                    </div>
                    <c:if test="${sightList != null && !sightList.isEmpty()}">
                        <div class="form-group">
                            <label>路线所经过景点</label>
                            <input type="text" hidden name="<%=Parameters.SIGHT_IDS%>"
                                   id="sightIds">
                            <select id="sightSelect" name="states[]" multiple="multiple"
                                    class="form-control">
                                <c:forEach items="${sightList}" var="sight">
                                    <option value="${sight.id}">${sight.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-success btn-lg btn-block">创建路线</button>
                </form>
            </c:if>
        </div>
    </div>
    <jsp:include page="foot.jsp"/>
    <script type="text/javascript">
      $().ready(function () {
        $("#routeForm").validate({
          rules: {
            name: {
              required: true, minlength: 2, maxlength: 30
            }, description: {
              required: true
            }
          }
        });
        var sightIdsInput = $("#sightIds");
        if (sightIdsInput.length > 0) {//存在景点
          $('#sightSelect').select2({
            placeholder: "请选择路线所经过景点", closeOnSelect: false
          }).on("select2:selecting", function (e) {
            console.log('Selecting: ', e.params.args.data);
            var value = sightIdsInput.val();
            if (value === "") {
              value += e.params.args.data.id;
            } else {
              value += "," + e.params.args.data.id;
            }
            console.log("Selecting:" + value);
            sightIdsInput.val(value);
          }).on("select2:unselecting", function (e) {
            var value = sightIdsInput.val();
            var splitStr = e.params.args.data.id + ",";
            if (value.indexOf(splitStr) < 0) {//没有带逗号的字符串
              splitStr = e.params.args.data.id;
            }
            var items = value.split(splitStr);
            value = items.join("");
            if (value.length % 2 === 0) {//id数组字符串只能为单数
              value = value.substring(0, value.length - 1);
            }
            console.log("unselecting:" + value);
            sightIdsInput.val(value);
          }).on("select2:select", function (evt) {//静止排序
            var element = evt.params.data.element;
            var $element = $(element);
            $element.detach();
            $(this).append($element);
            $(this).trigger("change");
          });
        }
      });
    </script>
</div>
</body>
</html>