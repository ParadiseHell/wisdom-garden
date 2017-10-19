package com.chengtao.wisdomgarden.ui.login

import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.request.LoginRequest
import com.chengtao.wisdomgarden.utils.MD5Util

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 1:08 PM
 * Description :
 */
class LoginPresenter(
    loginView: LoginContract.LoginView) : WisdomGardenPresenter<LoginContract.LoginView>(
    loginView), LoginContract.LoginPresenter {
  companion object {
    val LOGIN_REQUEST: Short = 1
  }

  private val loginRequest = LoginRequest(this)

  override fun login(userName: String, password: String) {
    when {
      userName == "" -> {
        mView?.toast("用户名不能为空")
      }
      password == "" -> {
        mView?.to("密码不能为空")
      }
      else -> {
        loginRequest.userName = userName
        loginRequest.password = MD5Util.md5(password)
        loginRequest.requestId = LOGIN_REQUEST
        loginRequest.execute()
      }
    }
  }

  override fun onData(requestId: Short, response: Any?) {
    when (requestId) {
      LOGIN_REQUEST -> {
        if (response != null) {
          TODO("保存用户信息,登录成功,跳转主界面")
        }
      }
    }
  }
}