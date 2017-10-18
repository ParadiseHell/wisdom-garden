package com.chengtao.wisdomgarden.controller

import com.chengtao.wisdomgarden.Errors
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
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * Created by chengtao on 10/10/17.
 */
@Controller
class UserController : BaseController() {
  //登录
  @GetMapping(Routers.LOGIN)
  fun getLoginView(request: HttpServletRequest, response: HttpServletResponse): String {
    CookieUtils.clearAll(request, response)
    return Views.LOGIN
  }

  @PostMapping(Routers.LOGIN)
  fun login(@RequestParam(value = Parameters.USER_NAME, defaultValue = "") userName: String,
            @RequestParam(value = Parameters.PASSWORD, defaultValue = "") password: String,
            response: HttpServletResponse, session: HttpSession): String {
    if (!StringUtils.isStringNull(userName, password)) {
      val md5Password = MD5Util.md5(password)
      if (md5Password != null) {
        val userDao = UserDaoImpl()
        if (userDao.isUserExist(userName)) {
          //创建cookies
          CookieUtils.addUserNameAndPasswordCookie(userName, md5Password, response)
          return Routers.INDEX.redirect()
        } else {
          addErrorMessage(session, "用户名或密码错误")
        }
      }
    } else {
      addErrorMessage(session, "用户已存在")
    }
    return Routers.LOGIN.redirect()
  }

  //注册
  @GetMapping(Routers.REGISTER)
  fun getRegisterView(request: HttpServletRequest, response: HttpServletResponse): String {
    CookieUtils.clearAll(request, response)
    return Views.REGISTER
  }

  @PostMapping(Routers.REGISTER)
  fun register(@RequestParam(value = Parameters.USER_NAME, defaultValue = "") userName: String,
               @RequestParam(value = Parameters.PASSWORD, defaultValue = "") password: String,
               @RequestParam(value = Parameters.CONFIRM_PASSWORD, defaultValue = "") confirmPassword: String,
               response: HttpServletResponse, session: HttpSession): String {
    println("userName:$userName->password:$password->confirmPassword:$confirmPassword")
    if (!StringUtils.isStringNull(userName, password, confirmPassword) && password == confirmPassword) {
      val md5Password = MD5Util.md5(password)
      if (md5Password != null) {
        val userDao = UserDaoImpl()
        if (userDao.queryUserByNameAndPassword(userName, md5Password) == null) {
          if (userDao.createUser(userName, md5Password, UserType.COMMON_USER.value) != null) {
            //创建cookies
            CookieUtils.addUserNameAndPasswordCookie(userName, md5Password, response)
            return Routers.INDEX.redirect()
          } else {
            addErrorMessage(session, Errors.UNKNOWN_ERROR)
          }
        } else {
          addErrorMessage(session, "用户已存在")
        }
      }
    } else {
      addErrorMessage(session, Errors.PARAMETERS_ERROR)
    }
    return Routers.REGISTER.redirect()
  }

  //登出
  @GetMapping(Routers.LOGOUT)
  fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
    val cookies = request.cookies
    cookies?.forEach {
      CookieUtils.clear(it, response)
    }
    return Routers.INDEX.redirect()
  }
}