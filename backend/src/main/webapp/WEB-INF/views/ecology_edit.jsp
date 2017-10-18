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
        <c:if test="${not empty sightId}">
            <div class="card-body">
                <form action="<%=Routers.ECOLOGY%>" method="post" id="ecologyForm">
                    <input hidden name="<%=Parameters.SIGHT_ID%>" value="${sightId}"/>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-6">
                                <label>温度</label>
                                <c:if test="${ecology != null}">
                                    <input type="number" class="form-control"
                                           placeholder="请输入温度" name="<%=Parameters.TEMPERATURE%>"
                                           value="${ecology.temperature}"/>
                                </c:if>
                                <c:if test="${ecology == null}">
                                    <input type="number" class="form-control"
                                           placeholder="请输入温度" name="<%=Parameters.TEMPERATURE%>"/>
                                </c:if>
                            </div>
                            <div class="col-6">
                                <label>湿度</label>
                                <c:if test="${ecology != null}">
                                    <input type="number" class="form-control"
                                           placeholder="请输入湿度" name="<%=Parameters.HUMIDITY%>"
                                           value="${ecology.humidity}"/>
                                </c:if>
                                <c:if test="${ecology == null}">
                                    <input type="number" class="form-control"
                                           placeholder="请输入湿度" name="<%=Parameters.HUMIDITY%>"/>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-6">
                                <label>PM2.5</label>
                                <c:if test="${ecology != null}">
                                    <input type="number" class="form-control"
                                           placeholder="请输入PM2.5" name="<%=Parameters.PM25%>"
                                           value="${ecology.pm25}"/>
                                </c:if>
                                <c:if test="${ecology == null}">
                                    <input type="number" class="form-control"
                                           placeholder="请输入PM2.5" name="<%=Parameters.PM25%>"/>
                                </c:if>
                            </div>
                            <div class="col-6">
                                <label>风力</label>
                                <c:if test="${ecology != null}">
                                    <input type="text" class="form-control"
                                           placeholder="请输入风力" name="<%=Parameters.WIND%>"
                                           value="${ecology.wind}"/>
                                </c:if>
                                <c:if test="${ecology == null}">
                                    <input type="text" class="form-control"
                                           placeholder="请输入风力" name="<%=Parameters.WIND%>"/>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>穿衣指数</label>
                        <c:if test="${ecology != null}">
                            <input type="text" class="form-control"
                                   placeholder="请填写穿衣指数" name="<%=Parameters.DRESSING%>"
                                   value="${ecology.dressing}">
                        </c:if>
                        <c:if test="${ecology == null}">
                            <input type="text" class="form-control"
                                   placeholder="请填写穿衣指数" name="<%=Parameters.DRESSING%>">
                        </c:if>
                    </div>
                    <c:if test="${ecology != null}">
                        <button type="submit" class="btn btn-success btn-lg btn-block">更新生态</button>
                    </c:if>
                    <c:if test="${ecology == null}">
                        <button type="submit" class="btn btn-success btn-lg btn-block">创建生态</button>
                    </c:if>
                </form>
            </div>
        </c:if>
    </div>
    <jsp:include page="foot.jsp"/>
    <script type="text/javascript">
      $().ready(function () {
        $("#ecologyForm").validate({
          rules: {
            temperature: {
              required: true, number: true, range: [-100, 100]
            }, humidity: {
              required: true, number: true, range: [0, 1]
            }, pm25: {
              required: true, number: true, range: [0, 500]
            }, wind: {
              required: true, minlength: 2
            }, dressing: {
              required: true, minlength: 2
            }
          }
        });
      });
      ;
    </script>
</div>
</body>
</html>