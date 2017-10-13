package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Parameters
import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import com.chengtao.wisdomgarden.db.impl.UserDaoImpl
import com.chengtao.wisdomgarden.entity.UserType
import com.chengtao.wisdomgarden.utils.CookieUtils
import com.chengtao.wisdomgarden.utils.MD5Util
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.redirect
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletResponse

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class UserController {
  @GetMapping(Routers.LOGIN)
  fun getLoginView(): String {
    println("getLoginView")
    return Views.LOGIN
  }

  @PostMapping(Routers.LOGIN)
  fun login(@RequestParam(value = Parameters.USER_NAME, defaultValue = "") userName: String,
            @RequestParam(value = Parameters.PASSWORD, defaultValue = "") password: String,
            response: HttpServletResponse): String {
    if (!StringUtils.isStringNull(userName, password)) {
      val md5Password = MD5Util.md5(password)
      if (md5Password != null) {
        val userDao = UserDaoImpl()
        if (userDao.isUserExist(userName, md5Password)) {
          //创建cookies
          CookieUtils.addUserNameAndPasswordCookie(userName, md5Password, response)
          return Routers.INDEX.redirect()
        }
      }
    }
    return Routers.LOGIN.redirect()
  }

  @GetMapping(Routers.REGISTER)
  fun getRegisterView(): String {
    return Views.REGISTER
  }

  @PostMapping(Routers.REGISTER)
  fun register(@RequestParam(value = Parameters.USER_NAME, defaultValue = "") userName: String,
               @RequestParam(value = Parameters.PASSWORD, defaultValue = "") password: String,
               @RequestParam(value = Parameters.CONFIRM_PASSWORD, defaultValue = "") confirmPassword: String,
               response: HttpServletResponse): String {
    if (!StringUtils.isStringNull(userName, password, confirmPassword) && password == confirmPassword) {
      val md5Password = MD5Util.md5(password)
      if (md5Password != null) {
        val userDao = UserDaoImpl()
        if (userDao.createUser(userName, md5Password, UserType.COMMON_USER.typeNumber) != null) {
          //创建cookies
          CookieUtils.addUserNameAndPasswordCookie(userName, md5Password, response)
          return Routers.INDEX.redirect()
        }
      }
    }
    return Routers.REGISTER.redirect()
  }
}