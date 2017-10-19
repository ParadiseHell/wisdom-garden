package com.chengtao.wisdomgarden.ui.login

import android.content.Context
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.request.LoginRequest
import com.chengtao.wisdomgarden.utils.MD5Util
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.UserUtils

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 1:08 PM
 * Description :
 */
class LoginPresenter(
    loginView: LoginContract.LoginView,
    context: Context) : WisdomGardenPresenter<LoginContract.LoginView>(
    loginView, context), LoginContract.LoginPresenter {
  companion object {
    val LOGIN_REQUEST: Short = 1
  }

  private val loginRequest = LoginRequest(this)

  private var userName: String? = null
  private var password: String? = null
  override fun login(userName: String, password: String) {
    when {
      userName == "" -> {
        mView?.toast("用户名不能为空")
      }
      password == "" -> {
        mView?.toast("密码不能为空")
      }
      else -> {
        this.userName = userName
        this.password = MD5Util.md5(password)
        loginRequest.userName = this.userName
        loginRequest.password = this.password
        loginRequest.requestId = LOGIN_REQUEST
        loginRequest.execute()
      }
    }
  }

  override fun onData(requestId: Short, response: Any?) {
    when (requestId) {
      LOGIN_REQUEST -> {
        if (response != null) {
          if (!StringUtils.isStringNull(userName, password)) {
            UserUtils.saveUser(userName!!, password!!)
          }
          mView?.toast("登录成功")
        }
      }
    }
  }
}