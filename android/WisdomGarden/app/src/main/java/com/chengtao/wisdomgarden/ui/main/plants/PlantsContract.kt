package com.chengtao.wisdomgarden.ui.main.plants

import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.BaseView
import com.chengtao.wisdomgarden.adapter.PlantsAdapter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:29 AM
 * Description :
 */
interface PlantsContract {
  interface Presenter : BasePresenter {
    fun init()
  }

  interface View : BaseView {
    fun initAdapter(adapter: PlantsAdapter)
    fun hideRefreshing()
    fun showRefreshing()
  }
}