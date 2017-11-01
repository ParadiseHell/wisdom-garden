<%--suppress ALL --%>
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
            <c:if test="${not empty plants}">
                <form action="<%=Routers.PLANTS%>/${plants.plantsId}" method="post" id="plantsForm">
                    <div class="form-group">
                        <div class="form-row">
                            <label>植物名称</label>
                            <input type="text" class="form-control"
                                   placeholder="请输入植物名称" name="<%=Parameters.NAME%>"
                                   value="${plants.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>植物描述</label>
                        <textarea class="form-control" rows="5"
                                  placeholder="请填写植物相关描述"
                                  name="<%=Parameters.DESCRIPTION%>"
                                  defaultValue="${plants.description}"></textarea>
                    </div>
                    <c:if test="${sightList != null && !sightList.isEmpty()}">
                        <div class=" orm-group">
                            <label>选择植物所在景点</label>
                            <input type="text" hidden name="sightIds" id="sightIds">
                            <select id="sightSelect" name="states[]" multiple="multiple"
                                    class="form-control">
                                <c:forEach items="${sightList}" var="sight">
                                    <option value="${sight.id}">${sight.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-success btn-lg btn-block"
                            style="margin-top: 15px;margin-bottom: 15px">更新植物
                    </button>
                </form>
                <div class="form-group">
                    <label>植物相关图片</label>
                    <form class="dropzone" method="POST" enctype="multipart/form-data"
                          id="plantsImage"
                          action="<%=Routers.PLANTS%>/${plants.plantsId}<%=Routers.UPLOAD%>">
                        <input type="text" name="<%=Parameters.FILE_CATEGORY%>"
                               value="<%=FileCategory.IMAGE.getValue()%>"
                               hidden/>
                    </form>
                </div>
                <div class="form-group">
                    <label>植物相关视频</label>
                    <form class="dropzone" method="POST" enctype="multipart/form-data"
                          id="plantsVideo"
                          action="<%=Routers.PLANTS%>/${plants.plantsId}<%=Routers.UPLOAD%>">
                        <input type="text" name="<%=Parameters.FILE_CATEGORY%>"
                               value="<%=FileCategory.VIDEO.getValue()%>"
                               hidden/>
                    </form>
                </div>
                <div class="form-group">
                    <label>植物相关音频</label>
                    <form class="dropzone" method="POST" enctype="multipart/form-data"
                          id="plantsAudio"
                          action="<%=Routers.PLANTS%>/${plants.plantsId}<%=Routers.UPLOAD%>">
                        <input type="text" name="<%=Parameters.FILE_CATEGORY%>"
                               value="<%=FileCategory.AUDIO.getValue()%>"
                               hidden/>
                    </form>
                </div>
            </c:if>
            <c:if test="${plants == null}">
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
                            <input type="text" hidden name="sightIds" id="sightIds">
                            <select id="sightSelect" name="states[]" multiple="multiple"
                                    class="form-control">
                                <c:forEach items="${sightList}" var="sight">
                                    <option value="${sight.id}">${sight.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-success btn-lg btn-block"
                            style="margin-top: 15px">创建植物
                    </button>
                </form>
            </c:if>
        </div>
    </div>
    <jsp:include page="foot.jsp"/>
    <script type="text/javascript">
      Dropzone.options.plantsImage = {
        maxFilesize: 5, // MB
        acceptedFiles: "image/*",
        dictDefaultMessage: "将图片拖拽到此区域(最大为5M)",
        dictFileTooBig: "文件大于5M",
        dictFallbackMessage: "你的浏览器不支持拖拽文件",
        dictInvalidFileType: "你不能上传此类型的文件"
      };
      Dropzone.options.plantsVideo = {
        maxFilesize: 10, // MB
        acceptedFiles: "video/*",
        dictDefaultMessage: "将视频拖拽到此区域(最大为10M)",
        dictFileTooBig: "文件大于10M",
        dictFallbackMessage: "你的浏览器不支持拖拽文件",
        dictInvalidFileType: "你不能上传此类型的文件"
      };
      Dropzone.options.plantsAudio = {
        maxFilesize: 5, // MB
        acceptedFiles: "audio/*",
        dictDefaultMessage: "将音频拖拽到此区域(最大为5M)",
        dictFileTooBig: "文件大于5M",
        dictFallbackMessage: "你的浏览器不支持拖拽文件",
        dictInvalidFileType: "你不能上传此类型的文件"
      };
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
        var sightIdsInput = $("#sightIds");
        if (sightIdsInput.length > 0) {//存在景点
          $('#sightSelect').select2({
            placeholder: "请选择景点", closeOnSelect: false
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