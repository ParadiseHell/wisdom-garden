<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <div class="card border-primary mb-3">
                <div class="card-header text-primary">
                    <i class="fa fa-fw fa-area-chart"></i>&nbsp;景点详情
                    <c:if test="${not empty isManager}">
                        <a href="<%=Routers.SIGHT_DELETE%>/${sight.id}"
                           class="btn btn-outline-danger float-right btn-sm" role="button">删除</a>
                        <a href="<%=Routers.SIGHT%>/${sight.id}<%=Routers.UPDATE%>"
                           style="margin-right:5px"
                           class="btn btn-outline-primary float-right btn-sm" role="button">更新</a>
                    </c:if>
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
                    <c:if test="${sight.ecology != null}">
                        <h5 class="card-title text-primary">
                            周边生态
                            <a href="<%=Routers.SIGHT%>/${sight.id}<%=Routers.ECOLOGY%>"
                               class="btn btn-outline-primary btn-sm ml-5">更新生态</a>
                        </h5>
                        <table class="table table-bordered table-hover table-secondary">
                            <thead>
                            <tr>
                                <th>温度</th>
                                <th>湿度</th>
                                <th>PM2.5</th>
                                <th>风力</th>
                                <th>穿衣指数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${sight.ecology.temperature}</td>
                                <td>${sight.ecology.humidity}</td>
                                <td>${sight.ecology.pm25}</td>
                                <td>${sight.ecology.wind}</td>
                                <td>${sight.ecology.dressing}</td>
                            </tr>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${sight.ecology == null}">
                        <a href="<%=Routers.SIGHT%>/${sight.id}<%=Routers.ECOLOGY%>"
                           class="btn btn-outline-primary btn-sm">创建生态</a>
                    </c:if>
                    <c:if test="${fn:length(sight.images) gt 0}">
                        <h5 class="card-title text-success">相关图片</h5>
                        <div id="plantsImages" class="carousel slide w-50" data-ride="carousel">
                            <div class="carousel-inner w-100">
                                <c:forEach var="image" items="${sight.images}" varStatus="loop">
                                    <c:if test="${loop.index == 0}">
                                        <div class="carousel-item active img-wrapper w-100">
                                            <img class="d-block w-100" src="${image.url}"
                                                 style="margin: auto;text-align: center">
                                        </div>
                                    </c:if>
                                    <c:if test="${loop.index != 0}">
                                        <div class="carousel-item img-wrapper w-100">
                                            <img class="d-block w-100" src="${image.url}"
                                                 style="margin: auto;text-align: center">
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <a class="carousel-control-prev" href="#plantsImages" role="button"
                               data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#plantsImages" role="button"
                               data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${fn:length(sight.videos) gt 0}">
                        <h5 class="card-title text-success" style="margin-top: 10px">相关视频</h5>
                        <video id="my-player"
                               class="video-js vjs-big-play-centered video-player
                           vjs-paused vjs-controls-enabled vjs-user-inactive w-50"
                               controls preload="auto" data-setup="{ techOrder: ['flash','html5']}"
                               height="350">
                            <source src="${sight.videos[0].url}"/>
                            <p class="vjs-no-js">
                                您的浏览器不支持
                            </p>
                        </video>
                    </c:if>
                    <c:if test="${fn:length(sight.audios) gt 0}">
                        <h5 class="card-title text-success" style="margin-top: 10px">相关音频</h5>
                        <div class="row">
                            <c:forEach var="audio" items="${sight.audios}">
                                <div class="card border-success mb-3 mr-3 col-md-3">
                                    <audio controls>
                                        <source src="${audio.url}">
                                        您的浏览器不支持
                                    </audio>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:if>
    </div>
    <jsp:include page="foot.jsp"/>
</div>
</body>

</html>

