package com.chengtao.wisdomgarden

import android.app.Activity
import android.content.Context
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
@Suppress("AddVarianceModifier", "MemberVisibilityCanPrivate")
abstract class WisdomGardenPresenter<V : BaseView>(view: V,
    context: Context) : BasePresenter, HttpResponseListener {
  val mView: V? = view
  val mContext: Context? = context
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

  protected fun finish() {
    if (mContext != null && mContext is Activity) {
      mContext.finish()
    }
  }

  //event bus 消息通知
  @Subscribe(threadMode = MAIN)
  fun onEventBusMessage(message: EventBusMessage) {

  }
}