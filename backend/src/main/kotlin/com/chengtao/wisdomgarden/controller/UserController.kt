package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Parameters
import com.chengtao.wisdomgarden.Routers
import com.chengtao.wisdomgarden.Views
import com.chengtao.wisdomgarden.db.impl.UserDaoImpl
import com.chengtao.wisdomgarden.entity.UserType
import com.chengtao.wisdomgarden.utils.CookieUtils
import com.chengtao.wisdomgarden.utils.MD5Util
import com.chengtao.wisdomgarden.utils.StringUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletResponse

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class UserController {
  @RequestMapping(value = Routers.LOGIN, method = arrayOf(RequestMethod.GET))
  fun getLoginView(): String {
    return Views.LOGIN
  }

  @RequestMapping(value = Routers.LOGIN, method = arrayOf(RequestMethod.POST))
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
          return Views.INDEX
        }
      }
    }
    return Views.LOGIN
  }

  @RequestMapping(value = Routers.REGISTER, method = arrayOf(RequestMethod.GET))
  fun getRegisterView(): String {
    return Views.REGISTER
  }

  @RequestMapping(value = Routers.REGISTER, method = arrayOf(RequestMethod.POST))
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
          return Views.INDEX
        }
      }
    }
    return Views.REGISTER
  }
}