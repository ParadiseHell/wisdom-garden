package com.chengtao.wisdomgarden

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 4:07 AM
 * Description :
 */
interface BasePresenter {
  fun subscribe()
  fun unSubscribe()
}

interface BaseView {
  fun toast(string: String)
  fun toast(stringId: Int)
}