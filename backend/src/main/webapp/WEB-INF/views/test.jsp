<%@ page import="com.chengtao.wisdomgarden.Parameters" %>
<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%@ page import="com.chengtao.wisdomgarden.entity.FileCategory" %>
<%--
  Created by IntelliJ IDEA.
  User: chengtao
  Date: 10/16/17
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/statics/css/dropzone.min.css"/>
    <script src="/statics/js/dropzone.min.js"></script>
</head>
<body>
<form class="dropzone" method="POST" enctype="multipart/form-data"
      id="plantsImages"
      action="<%=Routers.PLANTS%>/1<%=Routers.UPLOAD%>">
    <input type="text" name="<%=Parameters.FILE_CATEGORY%>"
           value="<%=FileCategory.IMAGE.getValue()%>"
           hidden/>
</form>
</body>
<script language="JavaScript">
  Dropzone.options.plantsImages = {
    maxFilesize: 5, // MB
    acceptedFiles: "image/*",
    dictDefaultMessage: "将图片拖拽到此区域(最大为5M)",
    dictFileTooBig: "文件大于5M",
    dictFallbackMessage: "你的浏览器不支持拖拽文件",
    dictInvalidFileType: "你不能上传此类型的文件"
  };
</script>
</html>
