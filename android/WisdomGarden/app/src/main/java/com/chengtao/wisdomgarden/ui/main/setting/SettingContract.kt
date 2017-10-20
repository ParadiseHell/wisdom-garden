package com.chengtao.wisdomgarden.ui.main.setting

import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.BaseView

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 8:40 AM
 * Description :
 */
interface SettingContract {
  interface Presenter : BasePresenter {
    fun changeBaseUrl(domainOrIp: String, port: String)
    fun init()
  }

  interface View : BaseView {
    fun setDomainOrIp(domainOrIp: String)
    fun setPort(port: String)
  }
}