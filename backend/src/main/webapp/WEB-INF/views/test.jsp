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
</head>
<body>
<form method="POST" enctype="multipart/form-data" action="<%=Routers.SIGHT%>/1<%=Routers.UPLOAD%>">
    File to upload:<input type="file" name="<%=Parameters.FILE%>"/>
    <input type="text" name="<%=Parameters.FILE_CATEGORY%>"
           value="<%=FileCategory.IMAGE.getValue()%>"
           hidden/>
    <input type="submit" value="Upload"/>
</form>
</body>
</html>
