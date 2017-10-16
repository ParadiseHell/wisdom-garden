<%@ page import="com.chengtao.wisdomgarden.Routers" %><%--
  Created by IntelliJ IDEA.
  User: chengtao
  Date: 10/16/17
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 图标卡片-->
<%
    String detail = "查看详情";
%>
<div class="row">
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-primary o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-fw fa-area-chart"></i>
                </div>
                <div class="mr-5">10个景点</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=Routers.SIGHT%>">
                <span class="float-left"><%=detail%></span>
                <span class="float-right">
                            <i class="fa fa-angle-right"></i>
                        </span>
            </a>
        </div>
    </div>
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-pagelines"></i>
                </div>
                <div class="mr-5">100种植物</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=Routers.PLANTS%>">
                <span class="float-left">><%=detail%></span>
                <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
        </div>
    </div>
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-map"></i>
                </div>
                <div class="mr-5">3条线路</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="<%=Routers.ROUTE%>">
                <span class="float-left">><%=detail%></span>
                <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
        </div>
    </div>
    <div class="col-xl-3 col-sm-6 mb-3">
        <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
                <div class="card-body-icon">
                    <i class="fa fa-globe"></i>
                </div>
                <div class="mr-5">10种服务设施</div>
            </div>
            <a class="card-footer text-white clearfix small z-1"
               href="<%=Routers.SERVICE%>">
                <span class="float-left">><%=detail%></span>
                <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
        </div>
    </div>
</div>
