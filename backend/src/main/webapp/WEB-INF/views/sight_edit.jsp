<%@ page import="com.chengtao.wisdomgarden.Parameters" %>
<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%@ page import="com.chengtao.wisdomgarden.entity.FileCategory" %>
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
            <c:if test="${not empty sight}">
                <form action="<%=Routers.SIGHT%>/${sight.id}" method="post" id="sightForm">
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-6">
                                <label>景点名称</label>
                                <input type="text" class="form-control"
                                       placeholder="请输入景点名称" name="<%=Parameters.NAME%>"
                                       value="${sight.name}">
                            </div>
                            <div class="col-6">
                                <label>景点类型</label>
                                <select class="form-control" name="<%=Parameters.CATEGORY%>">
                                    <c:if test="${sight.type == 'ENTRANCE'}">
                                        <option value="1" selected>入口</option>
                                        <option value="2">出口</option>
                                        <option value="0">其他</option>
                                    </c:if>
                                    <c:if test="${sight.type == 'EXIT'}">
                                        <option value="1">入口</option>
                                        <option value="2" selected>出口</option>
                                        <option value="0">其他</option>
                                    </c:if>
                                    <c:if test="${sight.type == 'OTHER'}">
                                        <option value="1">入口</option>
                                        <option value="2">出口</option>
                                        <option value="0" selected>其他</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-6">
                                <label>景点经度</label>
                                <input type="number" class="form-control"
                                       placeholder="请输入景点经度" name="<%=Parameters.LONGITUDE%>"
                                       value="${sight.longitude}"/>
                            </div>
                            <div class="col-6">
                                <label>景点纬度</label>
                                <input type="number" class="form-control"
                                       placeholder="请输入景点纬度" name="<%=Parameters.LATITUDE%>"
                                       value="${sight.latitude}"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>景点描述</label>
                        <textarea class="form-control" rows="5"
                                  placeholder="请填写景点相关描述"
                                  name="<%=Parameters.DESCRIPTION%>"
                                  value="${sight.description}"></textarea>
                    </div>
                    <button type="submit" class="btn btn-success btn-lg btn-block">更新景点</button>
                </form>
                <div class="form-group">
                    <label>景点相关图片</label>
                    <form class="dropzone" method="POST" enctype="multipart/form-data"
                          id="sightImage"
                          action="<%=Routers.SIGHT%>/${sight.id}<%=Routers.UPLOAD%>">
                        <input type="text" name="<%=Parameters.FILE_CATEGORY%>"
                               value="<%=FileCategory.IMAGE.getValue()%>"
                               hidden/>
                    </form>
                </div>
                <div class="form-group">
                    <label>景点相关视频</label>
                    <form class="dropzone" method="POST" enctype="multipart/form-data"
                          id="sightVideo"
                          action="<%=Routers.SIGHT%>/${sight.id}<%=Routers.UPLOAD%>">
                        <input type="text" name="<%=Parameters.FILE_CATEGORY%>"
                               value="<%=FileCategory.VIDEO.getValue()%>"
                               hidden/>
                    </form>
                </div>
                <div class="form-group">
                    <label>景点相关音频</label>
                    <form class="dropzone" method="POST" enctype="multipart/form-data"
                          id="sightAudio"
                          action="<%=Routers.SIGHT%>/${sight.id}<%=Routers.UPLOAD%>">
                        <input type="text" name="<%=Parameters.FILE_CATEGORY%>"
                               value="<%=FileCategory.AUDIO.getValue()%>"
                               hidden/>
                    </form>
                </div>
            </c:if>
            <c:if test="${sight == null}">
                <form action="<%=Routers.SIGHT%>" method="post" id="sightForm">
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-6">
                                <label>景点名称</label>
                                <input type="text" class="form-control"
                                       placeholder="请输入景点名称" name="<%=Parameters.NAME%>">
                            </div>
                            <div class="col-6">
                                <label>景点类型</label>
                                <select class="form-control" name="<%=Parameters.CATEGORY%>">
                                    <option value="1">入口</option>
                                    <option value="2">出口</option>
                                    <option value="0" selected>其他</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-6">
                                <label>景点经度</label>
                                <input type="number" class="form-control"
                                       placeholder="请输入景点经度" name="<%=Parameters.LONGITUDE%>"/>
                            </div>
                            <div class="col-6">
                                <label>景点纬度</label>
                                <input type="number" class="form-control"
                                       placeholder="请输入景点纬度" name="<%=Parameters.LATITUDE%>"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>景点描述</label>
                        <textarea class="form-control" rows="5"
                                  placeholder="请填写景点相关描述"
                                  name="<%=Parameters.DESCRIPTION%>"></textarea>
                    </div>
                    <button type="submit" class="btn btn-success btn-lg btn-block">创建景点</button>
                </form>
            </c:if>
        </div>
    </div>
    <jsp:include page="foot.jsp"/>
    <script type="text/javascript">
      Dropzone.options.sightImage = {
        maxFilesize: 5, // MB
        acceptedFiles: "image/*",
        dictDefaultMessage: "将图片拖拽到此区域(最大为5M)",
        dictFileTooBig: "文件大于5M",
        dictFallbackMessage: "你的浏览器不支持拖拽文件",
        dictInvalidFileType: "你不能上传此类型的文件"
      };
      Dropzone.options.sightVideo = {
        maxFilesize: 10, // MB
        acceptedFiles: "video/*",
        dictDefaultMessage: "将视频拖拽到此区域(最大为10M)",
        dictFileTooBig: "文件大于10M",
        dictFallbackMessage: "你的浏览器不支持拖拽文件",
        dictInvalidFileType: "你不能上传此类型的文件"
      };
      Dropzone.options.sightAudio = {
        maxFilesize: 5, // MB
        acceptedFiles: "audio/*",
        dictDefaultMessage: "将音频拖拽到此区域(最大为5M)",
        dictFileTooBig: "文件大于5M",
        dictFallbackMessage: "你的浏览器不支持拖拽文件",
        dictInvalidFileType: "你不能上传此类型的文件"
      };
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
            }, description: {
              required: true
            }
          }
        });
      });
      ;
    </script>
</div>
</body>
</html>