<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chengtao.wisdomgarden.ApplicationConfig" %>
<%@ page import="com.chengtao.wisdomgarden.Attributes" %>
<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%--
  Created by IntelliJ IDEA.
  User: chengtao
  Date: 10/10/17
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--底部--%>
<%
    String justIncludeJsFilesValue = request.getParameter("justIncludeJsFiles");
    boolean justIncludeJsFiles = false;
    if (justIncludeJsFilesValue != null && !justIncludeJsFilesValue.equals("")) {
        justIncludeJsFiles = Boolean.parseBoolean(justIncludeJsFilesValue);
    }
    if (!justIncludeJsFiles) {%>
<footer class="sticky-footer">
    <div class="container">
        <div class="text-center">
            <small>Copyright © <%=ApplicationConfig.APPLICATION_NAME%> 2017</small>
        </div>
    </div>
</footer>
<!-- 滚到顶部-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fa fa-angle-up"></i>
</a>
<!-- 登出模态框-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
     aria-labelledby="logoutModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="logoutModalLabel">确定登出?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">如果你确定结束当前会话,选择 "登出".
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">取消
                </button>
                <a class="btn btn-primary" href="<%=Routers.LOGOUT%>">登出</a>
            </div>
        </div>
    </div>
</div>
<%}%>
<script src="../statics/js/jquery.min.js" type="text/javascript"></script>
<script src="../statics/js/popper.min.js" type="text/javascript"></script>
<script src="../statics/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../statics/js/jquery.easing.min.js" type="text/javascript"></script>
<script src="../statics/js/Chart.min.js" type="text/javascript"></script>
<script src="../statics/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="../statics/js/dataTables.bootstrap4.js" type="text/javascript"></script>
<script src="../statics/js/sb-admin.min.js" type="text/javascript"></script>
<script src="../statics/js/sb-admin-datatables.min.js" type="text/javascript"></script>
<script src="../statics/js/sb-admin-charts.min.js" type="text/javascript"></script>
<script src="../statics/js/simply-toast.min.js" type="text/javascript"></script>
<script src="../statics/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="../statics/js/messages_zh.min.js" type="text/javascript"></script>
<script src="../statics/js/select2.min.js" type="text/javascript"></script>
<%
    Object errorMessage = session.getAttribute(Attributes.ERROR_MESSAGE);
    Object successMessage = session.getAttribute(Attributes.SUCCESS_MESSAGE);
    session.setAttribute(Attributes.ERROR_MESSAGE, null);
    session.setAttribute(Attributes.SUCCESS_MESSAGE, null);
    if (errorMessage != null && errorMessage instanceof String) {
%>
<script>
  $.simplyToast('<%=(String)errorMessage%>', 'danger');
</script>
<%
} else if (successMessage != null && successMessage instanceof String) {%>
<script>
  $.simplyToast('<%=(String)successMessage%>', 'success');
</script>
<%
    }
%>
<c:if test="${not empty errorMessage}">
    <script>
      $.simplyToast('${errorMessage}', 'danger');
    </script>
</c:if>
<c:if test="${not empty successMessage}">
    <script>
      $.simplyToast('${successMessage}', 'success');
    </script>
</c:if>