<%@ page import="com.chengtao.wisdomgarden.ApplicationConfig" %><%--
  Created by IntelliJ IDEA.
  User: chengtao
  Date: 10/10/17
  Time: 4:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="<%=ApplicationConfig.APPLICATION_NAME%>">
    <meta name="author" content="<%=ApplicationConfig.AUTHOR%>">
    <title><%=ApplicationConfig.APPLICATION_NAME%>
    </title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
          crossorigin="anonymous">
    <link href="/statics/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/dataTables.bootstrap4.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/sb-admin.min.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/simply-toast.min.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/dropzone.min.css" rel="stylesheet" type="text/css"/>
    <%--videoJs--%>
    <!-- cdnjs -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.3.3/video-js.css"
          rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.3.3/video.js"></script>
</head>
<style type="text/css">
    label.error {
        color: red;
        margin-top: 2px;
    }

    .multiline-ellipsis {
        display: block;
        display: -webkit-box;
        margin: 0 auto;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    img.card-img {
        height: 300px;
        width: 100%;
    }

    div.img-wrapper {
        height: 500px;
    }
</style>