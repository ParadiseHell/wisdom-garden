package com.chengtao.wisdomgarden.ui.login

import com.chengtao.wisdomgarden.WisdomGardenPresenter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 1:08 PM
 * Description :
 */
class LoginPresenter(
    loginView: LoginContract.LoginView) : WisdomGardenPresenter<LoginContract.LoginView>(
    loginView), LoginContract.LoginPresenter {
  override fun login(userName: String, password: String) {
    println("userName:$userName-->password:$password")
  }

  override fun onData(requestId: Short, response: Any?) {
  }

}