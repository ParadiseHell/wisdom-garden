package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.utils.StringUtils
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by chengtao on 10/10/17.
 */
class WisdomGardenInterceptor : HandlerInterceptor {
  companion object {
    val UN_INTERCEPTOR_ROUTERS: Array<String> =
        arrayOf(Routers.LOGIN, Routers.REGISTER, Routers.STATICS, Routers.API)
  }

  override fun preHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?): Boolean {
    UN_INTERCEPTOR_ROUTERS
        .filter { request!!.requestURI.contains(it) }
        .forEach { return true }
    var cookieUserName: String? = null
    var cookiePassword: String? = null
    for (cookie in request!!.cookies) {
      if (cookie!!.name == Cookies.USER_NAME) {
        cookieUserName = cookie.value
      }
      if (cookie.name == Cookies.PASSWORD) {
        cookiePassword = cookie.value
      }
    }
    if (!StringUtils.isStringNull(cookieUserName, cookiePassword)) {
      println("没有被拦截")
      return true
    }
    response!!.sendRedirect(Routers.LOGIN)
    return false
  }

  override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
    println("处理请求后,渲染页面前")
  }

  override fun afterCompletion(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, ex: Exception?) {
    println("视图渲染结束了,请求处理完毕")
  }
}