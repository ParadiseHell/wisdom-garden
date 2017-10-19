package com.chengtao.wisdomgarden.ui.register

import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.BaseView

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 5:10 PM
 * Description :
 */
interface RegisterContract {
  interface Presenter : BasePresenter {
    fun register(userName: String, password: String, passwordConfirm: String)
  }

  interface View : BaseView {

  }
}