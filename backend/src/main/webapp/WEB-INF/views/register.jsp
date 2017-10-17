<%@ page import="com.chengtao.wisdomgarden.Parameters" %>
<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body class="bg-dark">
<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">注册</div>
        <div class="card-body">
            <form action="<%=Routers.REGISTER%>" method="post" id="registerForm">
                <div class="form-group">
                    <div>
                        <label>用户名</label>
                        <input class="form-control" type="text"
                               aria-describedby="nameHelp"
                               name="<%=Parameters.USER_NAME%>"
                               placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label>密码</label>
                            <input class="form-control" type="password"
                                   name="<%=Parameters.PASSWORD%>"
                                   placeholder="请输入密码" id="password">
                        </div>
                        <div class="col-md-6">
                            <label>确认密码</label>
                            <input class="form-control" type="password"
                                   name="<%=Parameters.CONFIRM_PASSWORD%>"
                                   placeholder="确认密码">
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary btn-block" type="submit">注册</button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="<%=Routers.LOGIN%>">登录</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="foot.jsp">
    <jsp:param name="justIncludeJsFiles" value="true"/>
</jsp:include>
<script type="text/javascript">
  $().ready(function () {
    $("#registerForm").validate({
      rules: {
        userName: {
          required: true, minlength: 2, maxlength: 20
        }, password: {
          required: true, minlength: 6, maxlength: 20
        },confirmPassword:{
          required: true, minlength: 6, maxlength: 20,equalTo:"#password"
        }
      }
    });
  });
</script>
</body>
</html>