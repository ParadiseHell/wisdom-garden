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
        <jsp:include page="incon_card.jsp"/>
        <div style="width: 100%;height: 500px" id="map">

        </div>
    </div>
    <jsp:include page="foot.jsp"/>
    <script src="/statics/js/marker.js"></script>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.4.0&key=6ce14a4fe21aa2ee8937f25cbc3d5cd4&callback=initIndexMap"></script>
</div>
</body>
</html>

