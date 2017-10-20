package com.chengtao.wisdomgarden.ui.main.service

import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.BaseView
import com.chengtao.wisdomgarden.adapter.ServiceAndNameAdapter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 2:08 PM
 * Description :
 */
interface ServiceContract {
  interface Presenter : BasePresenter {
    fun init()
  }

  interface View : BaseView {
    fun initAdapter(adapter: ServiceAndNameAdapter)
  }
}