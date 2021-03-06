package com.chengtao.wisdomgarden

/**
 * Created by chengtao on 10/10/17.
 */
interface ApplicationConfig {
  companion object {
    const val APPLICATION_NAME = "智慧园林"
    const val AUTHOR = "程涛"
  }
}

//Cookies
interface Cookies {
  companion object {
    const val COOKIES_EXPIRATION_DATE = 60 * 60 * 24
    const val USER_NAME = "user_name"
    const val PASSWORD = "password"
  }
}

//请求参数
interface Parameters {
  companion object {
    const val USER_NAME = "userName"
    const val PASSWORD = "password"
    const val CONFIRM_PASSWORD = "confirmPassword"
    const val FILE = "file"
    const val FILE_CATEGORY = "file_category"
    const val NAME = "name"
    const val CATEGORY = "category"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val DESCRIPTION = "description"
    const val SIGHT_IDS = "sightIds"
    const val SIGHT_ID = "sightId"
    const val TEMPERATURE = "temperature"
    const val HUMIDITY = "humidity"
    const val PM25 = "pm25"
    const val WIND = "wind"
    const val DRESSING = "dressing"
  }
}

//属性
interface Attributes {
  companion object {
    const val ERROR_MESSAGE = "errorMessage"
    const val SUCCESS_MESSAGE = "successMessage"
    const val IS_MANAGER = "isManager"
    const val CURRENT_VIEW = "currentView"
    const val VIEW_AND_ROUTER = "viewAndRouter"
    const val VIEW_ROUTER = "viewRouter"
    const val SIGHT = "sight"
    const val SIGHT_ID = "sightId"
    const val SIGHT_COUNT = "sightCount"
    const val SIGHT_LIST = "sightList"
    const val PLANTS_COUNT = "plantsCount"
    const val PLANTS_LIST = "plantsList"
    const val PLANTS = "plants"
    const val ROUTE = "route"
    const val ROUTE_COUNT = "routeCount"
    const val ROUTE_LIST = "routeList"
    const val SERVICE = "service"
    const val SERVICE_COUNT = "serviceCount"
    const val SERVICE_NAME_COUNT_LIST = "serviceNameCountList"
    const val SERVICE_LIST = "serviceList"
    const val ECOLOGY = "ecology"
  }
}

//错误
interface Errors {
  companion object {
    const val PARAMETERS_ERROR = "参数错误"
    const val UNKNOWN_ERROR = "未知错误"
  }
}

//静态资源
interface UploadFilePath {
  companion object {
    const val REAL_PATH = "http://paradisehell.xyz:8080/files"
    const val UPLOAD_DIRECTORY = "/opt/tomcat/upload/wisdomgarden"
    /*const val REAL_PATH = "http://127.0.0.1:8888/files"
    const val UPLOAD_DIRECTORY = "/home/chengtao/wisdomgarden"*/
    const val UPLOAD_IMAGES = "/images"
    const val UPLOAD_AUDIO = "/audio"
    const val UPLOAD_VIDEO = "/video"
  }
}

//路由
interface Routers {
  companion object {
    //页面
    const val INDEX = "/"
    const val LOGIN = "/login"
    const val REGISTER = "/register"
    const val SIGHT = "/sight"
    const val SIGHT_EDIT = "/sight/edit"
    const val SIGHT_DELETE = "/sight/delete"
    const val PLANTS = "/plants"
    const val PLANTS_EDIT = "/plants/edit"
    const val PLANTS_DELETE = "/plants/delete"
    const val ROUTE = "/route"
    const val ROUTE_EDIT = "/route/edit"
    const val ROUTE_DELETE = "/route/delete"
    const val SERVICE = "/service"
    const val SERVICE_EDIT = "/service/edit"
    const val SERVICE_DELETE = "/service/delete"
    const val ECOLOGY = "/ecology"
    const val UPLOAD = "/upload"
    const val UPDATE = "/update"
    //静态资源
    const val STATICS = "/statics"
    //API
    const val API = "/api"
    //其他
    const val LOGOUT = "/logout"
  }
}

//视图
interface Views {
  companion object {
    const val INDEX = "index"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val SIGHT = "sight"
    const val SIGHT_EDIT = "sight_edit"
    const val SIGHT_DETAIL = "sight_detail"
    const val PLANTS = "plants"
    const val PLANTS_EDIT = "plants_edit"
    const val PLANTS_DETAIL = "plants_detail"
    const val ROUTE = "route"
    const val ROUTE_EDIT = "route_edit"
    const val ROUTE_DETAIL = "route_detail"
    const val SERVICE = "service"
    const val SERVICE_EDIT = "service_edit"
    const val SERVICE_DETAIL = "service_detail"
    const val ECOLOGY_EDIT = "ecology_edit"
  }
}

interface API {
  companion object {
    const val USER_LOGIN = "/api/user/login"
    const val USER_REGISTER = "/api/user/register"
    const val SIGHT = "/api/sight"
    const val GET_ALL_SIGHTS = "/api/sights"
    const val PLANT = "/api/plant"
    const val GET_ALL_PLANTS = "/api/plants"
    const val ROUTE = "/api/route"
    const val GET_ALL_ROUTES = "/api/routes"
    const val SERVICE = "/api/service"
    const val GET_ALL_SERVICE_NAME_AND_COUNT = "/api/services/name_count"
  }
}

interface APIErrorType {
  companion object {
    const val MISSING_PARAMETER = "missing parameter"
    const val INTERNAL_SERVER_ERROR = "internal server error"
    const val UNAUTHORIZED = "unauthorized"
    //用户
    const val USER_NAME_OR_PASSWORD_WRONG = "user name or password wrong"
    const val USER_EXIST = "user exist"
  }
}

interface APIParameters {
  companion object {
    const val USER_NAME = "userName"
    const val PASSWORD = "password"
  }
}