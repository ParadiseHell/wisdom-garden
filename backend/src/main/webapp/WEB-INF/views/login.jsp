<%@ page import="com.chengtao.wisdomgarden.Login" %>
<%@ page import="com.chengtao.wisdomgarden.Parameters" %>
<%@ page import="com.chengtao.wisdomgarden.Register" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body class="bg-dark">
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header"><%=Login.TITLE%>
        </div>
        <div class="card-body">
            <form method="post" action="<%=Login.PATH%>">
                <div class="form-group">
                    <label><%=Login.USER_NAME%>
                    </label>
                    <input aria-describedby="nameHelp" class="form-control" type="text" name="<%=Parameters.USER_NAME%>"
                           placeholder="<%=Login.PLACEHOLDER_USER_NAME%>">
                </div>
                <div class="form-group">
                    <label><%=Login.PASSWORD%>
                    </label>
                    <input class="form-control" type="password" name="<%=Parameters.PASSWORD%>"
                           placeholder="<%=Login.PLACEHOLDER_PASSWORD%>">
                </div>
                <button class="btn btn-primary btn-block" type="submit"><%=Login.TITLE%>
                </button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="<%=Register.PATH%>"><%=Login.REGISTER_ACCOUNT%>
                </a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="foot_js.jsp"/>
</body>
</html>
