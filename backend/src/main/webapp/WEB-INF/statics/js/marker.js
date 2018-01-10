/**
 * Created by chengtao on 12/28/17.
 */
const API_BASE_URL = "http://paradisehell.xyz:8080";
/*
 const API_BASE_URL = "http://localhost:8888";*/
const GET_ALL_SIGHTS_API = API_BASE_URL + "/api/sights";

//
const COOKIE_USER_NAME = "user_name";
const COOKIE_PASSWORD = "password";
//
var map;
var infoWindow;
var startLocation = [];
var endLocation = [];
//
function initMap() {
  map = new AMap.Map('map', {
    resizeEnable: true, zoom: 18, center: [116.395, 39.9106]
  });
  map.plugin(["AMap.ToolBar"], function () {
    map.addControl(new AMap.ToolBar());
  });
}
function initIndexMap() {
  initMap();
  initSightsMarker();
}

function initSightsMarker() {
  ajaxGet(GET_ALL_SIGHTS_API, function (data) {
    for (var i = 0; i < data.length; i++) {
      addMarker(data[i], function (itemData) {
        //实例化信息窗体
        var content = [];
        content.push("<div class='sight'>");
        content.push("<p class='title'><span>名称 : </span>"
            + "<a href='/sight/"
            + itemData.id
            + "'>"
            + itemData.name
            + "</a>"
            + "</p>");
        content.push("<p class='description'><span>简介 : </span>" + itemData.description + "</p>");
        content.push("<a class='close' onclick='closeInfoWindow();'>x</a>");
        content.push("</div>");
        infoWindow = new AMap.InfoWindow({
          isCustom: true,  //使用自定义窗体
          content: content.join(""), offset: new AMap.Pixel(16, -50)
        });
        infoWindow.open(map, [itemData.longitude, itemData.latitude]);
      });
    }
  });
}

function closeInfoWindow() {
  infoWindow.close();
}

function initStartLocation(longitude, latitude) {
  startLocation = [longitude, latitude];
  console.log(startLocation);
}

function initEndLocation(longitude, latitude) {
  endLocation = [longitude, latitude];
  console.log(startLocation);
}
function loadRoute() {
  initMap();
  initSightsMarker();
  //步行导航
  if (startLocation !== [] && endLocation !== []) {
    AMap.service(["AMap.Walking"], function () {
      var mWalk = new AMap.Walking({
        map: map, panel: "panel"
      }); //构造路线导航类
      //根据起终点坐标规划步行路线
      mWalk.search(startLocation, endLocation, function (status, result) {
        startLocation = [];
        endLocation = [];
      });
    });
  }
}

function addMarker(data, callback) {
  marker = new AMap.Marker({
    icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
    position: [data.longitude, data.latitude]
  });
  marker.setAnimation('AMAP_ANIMATION_DROP');
  marker.on("click", function () {
    callback(data);
  });
  marker.setMap(map);
}

function ajaxGet(url, callback) {
  $.ajax({
    url: url, type: 'GET', dataType: 'json', headers: {
      "userName": getCookie(COOKIE_USER_NAME), 'password': getCookie(COOKIE_PASSWORD)
    }, contentType: 'application/json; charset=utf-8', success: function (result) {
      console.log(result);
      callback(result);
    }, error: function (error) {

    }
  });
}

function getCookie(name) {
  var cookie = document.cookie;
  if (cookie.length > 0) {
    var start = cookie.indexOf(name + "=");
    if (start !== -1) {
      start = start + name.length + 1;
      var end = cookie.indexOf(";", start);
      if (end === -1) {
        end = cookie.length;
      }
      return cookie.substring(start, end);
    }
  }
  return "";
}