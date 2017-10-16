<%@ page import="com.chengtao.wisdomgarden.Parameters" %>
<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body class="bg-dark">
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">登录</div>
        <div class="card-body">
            <form method="post" action="<%=Routers.LOGIN%>">
                <div class="form-group">
                    <label>用户名</label>
                    <input aria-describedby="nameHelp" class="form-control" type="text"
                           name="<%=Parameters.USER_NAME%>"
                           placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input class="form-control" type="password" name="<%=Parameters.PASSWORD%>"
                           placeholder="请输入密码">
                </div>
                <button class="btn btn-primary btn-block" type="submit">登录</button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="<%=Routers.REGISTER%>">注册账号</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="foot.jsp">
    <jsp:param name="justIncludeJsFiles" value="true"/>
</jsp:include>
</body>
</html>
