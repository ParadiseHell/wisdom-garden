package com.chengtao.wisdomgarden.ui.login

import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.BaseView

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 1:05 PM
 * Description :
 */
interface LoginContract {
  interface LoginPresenter : BasePresenter {
    fun login(userName: String, password: String)
    fun init()
  }

  interface LoginView : BaseView {

  }
}