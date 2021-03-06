package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.db.impl.UserDaoImpl
import com.chengtao.wisdomgarden.entity.UserType
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
        arrayOf(Routers.LOGIN, Routers.REGISTER, Routers.STATICS, Routers.API, Routers.LOGOUT)
  }

  override fun preHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?): Boolean {
    if (request != null && response != null) {
      val uri = request.requestURI
      val routerArray = uri.split("/")
      if (routerArray.size >= 2) {
        val router = "/${routerArray[1]}"
        if (UN_INTERCEPTOR_ROUTERS.contains(router)) {
          return true
        }
      }
      var userName: String? = null
      var password: String? = null
      var cookieUserName: Cookie? = null
      var cookiePassword: Cookie? = null
      val cookies = request.cookies
      if (cookies != null) {
        for (cookie in cookies) {
          if (cookie != null) {
            if (cookie.name == Cookies.USER_NAME) {
              userName = cookie.value
              cookieUserName = cookie
            }
            if (cookie.name == Cookies.PASSWORD) {
              password = cookie.value
              cookiePassword = cookie
            }
          }
        }
      }
      if (!StringUtils.isStringNull(userName, password)) {
        val userDao = UserDaoImpl()
        //存在用户不拦截
        val user = userDao.queryUserByNameAndPassword(userName!!, password!!)
        if (user != null) {
          //重新设置cookie过期时间
          CookieUtils.addUserNameAndPasswordCookie(cookieUserName!!, cookiePassword!!, response)
          if (request.session != null) {
            if (user.type == UserType.MANAGER) {
              request.session.setAttribute(Attributes.IS_MANAGER, true)
            } else {
              request.session.setAttribute(Attributes.IS_MANAGER, null)
            }
          }
          return true
        }
      }
      CookieUtils.clear(cookieUserName, response)
      CookieUtils.clear(cookiePassword, response)
      response.sendRedirect(Routers.LOGIN)
      return false
    }
    return true
  }

  override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
  }

  override fun afterCompletion(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, ex: Exception?) {
  }
}