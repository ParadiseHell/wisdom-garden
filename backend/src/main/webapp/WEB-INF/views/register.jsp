<%@ page import="com.chengtao.wisdomgarden.Login" %>
<%@ page import="com.chengtao.wisdomgarden.Parameters" %>
<%@ page import="com.chengtao.wisdomgarden.Register" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body class="bg-dark">
<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header"><%=Register.TITLE%>
        </div>
        <div class="card-body">
            <form action="<%=Register.PATH%>" method="post">
                <div class="form-group">
                    <div>
                        <label><%=Register.USER_NAME%>
                        </label>
                        <input class="form-control" type="text"
                               aria-describedby="nameHelp"
                               name="<%=Parameters.USER_NAME%>"
                               placeholder="<%=Register.PLACEHOLDER_USER_NAME%>">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label><%=Register.PASSWORD%>
                            </label>
                            <input class="form-control" type="password"
                                   name="<%=Parameters.PASSWORD%>"
                                   placeholder="<%=Register.PASSWORD%>">
                        </div>
                        <div class="col-md-6">
                            <label><%=Register.CONFIRM_PASSWORD%>
                            </label>
                            <input class="form-control" type="password"
                                   name="<%=Parameters.CONFIRM_PASSWORD%>"
                                   placeholder="<%=Register.PLACEHOLDER_CONFIRM_PASSWORD%>">
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary btn-block" type="submit"><%=Register.TITLE%>
                </button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="<%=Login.PATH%>"><%=Login.TITLE%>
                </a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="foot_js.jsp"/>
</body>

</html>
