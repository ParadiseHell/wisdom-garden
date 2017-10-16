<%@ page import="com.chengtao.wisdomgarden.ApplicationConfig" %>
<%@ page import="com.chengtao.wisdomgarden.Routers" %>
<%--
  Created by IntelliJ IDEA.
  User: chengtao
  Date: 10/16/17
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 导航-->
<%
    String indexTitle = "首页";
    String sightTitle = "景点";
    String plantsTitle = "植物";
    String routeTitle = "线路";
    String serviceTitle = "服务设施";
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="<%=Routers.INDEX%>"><%=ApplicationConfig.APPLICATION_NAME%>
    </a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
            <li class="nav-item" data-toggle="tooltip" data-placement="right"
                title="<%=indexTitle%>">
                <a class="nav-link" href="<%=Routers.INDEX%>">
                    <i class="fa fa-home"></i>
                    <span class="nav-link-text"><%=indexTitle%></span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right"
                title="<%=sightTitle%>">
                <a class="nav-link" href="<%=Routers.SIGHT%>">
                    <i class="fa fa-fw fa-area-chart"></i>
                    <span class="nav-link-text"><%=sightTitle%></span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right"
                title="<%=plantsTitle%>">
                <a class="nav-link" href="<%=Routers.PLANTS%>">
                    <i class="fa fa-pagelines"></i>
                    <span class="nav-link-text"><%=plantsTitle%></span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right"
                title="<%=routeTitle%>">
                <a class="nav-link" href="<%=Routers.ROUTE%>">
                    <i class="fa fa-map"></i>
                    <span class="nav-link-text"><%=routeTitle%></span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right"
                title="<%=serviceTitle%>">
                <a class="nav-link" href="<%=Routers.SERVICE%>">
                    <i class="fa fa-globe"></i>
                    <span class="nav-link-text"><%=serviceTitle%></span>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav sidenav-toggler">
            <li class="nav-item">
                <a class="nav-link text-center" id="sidenavToggler">
                    <i class="fa fa-fw fa-angle-left"></i>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form class="form-inline my-2 my-lg-0 mr-lg-2">
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="搜索...">
                        <span class="input-group-btn">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>
                    </div>
                </form>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" data-target="#logoutModal">
                    <i class="fa fa-fw fa-sign-out"></i>登出</a>
            </li>
        </ul>
    </div>
</nav>
