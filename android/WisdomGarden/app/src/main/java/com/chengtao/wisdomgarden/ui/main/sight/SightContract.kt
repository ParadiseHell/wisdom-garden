package com.chengtao.wisdomgarden.ui.main.sight

import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.BaseView
import com.chengtao.wisdomgarden.adapter.SightAdapter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:29 AM
 * Description :
 */
interface SightContract {
  interface Presenter : BasePresenter {
    fun init()
  }

  interface View : BaseView {
    fun initAdapter(adapter: SightAdapter)
    fun hideRefreshing()
    fun showRefreshing()
  }
}