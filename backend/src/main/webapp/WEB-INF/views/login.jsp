<%@ page import="com.chengtao.wisdomgarden.Login" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<body class="bg-dark">
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header"><%=Login.TITLE%>
        </div>
        <div class="card-body">
            <form method="post" action="/<%=Login.PATH%>">
                <div class="form-group">
                    <label>
                        <%=Login.LABEL_USER_NAME%>
                    </label>
                    <input class="form-control" type="text"
                           placeholder="<%=Login.PLACEHOLDER_USER_NAME%>">
                </div>
                <div class="form-group">
                    <label><%=Login.LABEL_PASSWORD%>
                    </label>
                    <input class="form-control" type="password"
                           placeholder="<%=Login.PLACEHOLDER_PASSWORD%>">
                </div>
                <button class="btn btn-primary btn-block" type="submit"><%=Login.TITLE%>
                </button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="register.jsp">Register an Account</a>
                <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="foot_js.jsp"/>
</body>
</html>
