package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.db.impl.UserDaoImpl
import com.chengtao.wisdomgarden.utils.CookieUtils
import com.chengtao.wisdomgarden.utils.StringUtils
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.Cookie
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
    var userName: String? = null
    var password: String? = null
    var cookieUserName: Cookie? = null
    var cookiePassword: Cookie? = null
    for (cookie in request!!.cookies) {
      if (cookie!!.name == Cookies.USER_NAME) {
        userName = cookie.value
        cookieUserName = cookie
      }
      if (cookie.name == Cookies.PASSWORD) {
        password = cookie.value
        cookiePassword = cookie
      }
    }
    if (!StringUtils.isStringNull(userName, password)) {
      val userDao = UserDaoImpl()
      //存在用户不拦截
      if (userDao.isUserExist(userName!!, password!!)) {
        //重新设置cookie过期时间
        CookieUtils.addUserNameAndPasswordCookie(cookieUserName!!, cookiePassword!!, response!!)
        return true
      }
    }
    CookieUtils.clear(cookieUserName, response!!)
    CookieUtils.clear(cookiePassword, response)
    response.sendRedirect(Routers.LOGIN)
    return false
  }

  override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
  }

  override fun afterCompletion(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, ex: Exception?) {
  }
}