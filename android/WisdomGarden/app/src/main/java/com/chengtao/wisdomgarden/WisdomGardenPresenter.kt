package com.chengtao.wisdomgarden

import com.chengtao.wisdomgarden.entity.EventBusMessage
import com.chengtao.wisdomgarden.http.HttpResponseListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode.MAIN

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 7:24 AM
 * Description :
 */
abstract class WisdomGardenPresenter<V : BaseView>(view: V) : BasePresenter, HttpResponseListener {
  var mView: V? = view

  override fun subscribe() {
    EventBus.getDefault().register(this)
  }

  override fun unSubscribe() {
    EventBus.getDefault().unregister(this)
  }

  override fun onStart(requestId: Short) {
    println("onStart:$requestId")
  }

  override fun onComplete(requestId: Short) {
    println("onComplete:$requestId")
  }

  override fun onError(requestId: Short, stringId: Int) {
    println("onError:$requestId")
    mView?.toast(stringId)
  }

  override fun onSpecialError(requestId: Short, errorType: Short) {
    println("onSpecialError:$requestId")
  }

  //event bus 消息通知
  @Subscribe(threadMode = MAIN)
  fun onEventBusMessage(message: EventBusMessage) {

  }
}