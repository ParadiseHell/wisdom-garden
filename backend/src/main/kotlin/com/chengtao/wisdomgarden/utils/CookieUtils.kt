package com.chengtao.wisdomgarden.utils

import com.chengtao.wisdomgarden.Cookies
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

/**
 * Created by chengtao on 10/13/17.
 */
object CookieUtils {
  fun addUserNameAndPasswordCookie(userName: String, password: String, response: HttpServletResponse) {
    val cookieUserName = Cookie(Cookies.USER_NAME, userName)
    val cookiePassword = Cookie(Cookies.PASSWORD, password)
    addUserNameAndPasswordCookie(cookieUserName, cookiePassword, response)
  }

  fun addUserNameAndPasswordCookie(cookieUserName: Cookie, cookiePassword: Cookie, response: HttpServletResponse) {
    cookieUserName.maxAge = Cookies.COOKIES_EXPIRATION_DATE
    cookiePassword.maxAge = Cookies.COOKIES_EXPIRATION_DATE
    response.addCookie(cookieUserName)
    response.addCookie(cookiePassword)
  }

  fun clear(cookie: Cookie?, response: HttpServletResponse) {
    if (cookie != null) {
      cookie.maxAge = 0
      response.addCookie(cookie)
    }
  }
}