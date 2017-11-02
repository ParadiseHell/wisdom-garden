package com.chengtao.wisdomgarden.ui.main.service

import android.content.Context
import com.chengtao.wisdomgarden.EventBusMessageID
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.adapter.ServiceAndNameAdapter
import com.chengtao.wisdomgarden.entity.EventBusMessage
import com.chengtao.wisdomgarden.entity.ServiceNameAndCount
import com.chengtao.wisdomgarden.request.GetAllServicesNameAndCountRequest

@Suppress("UNCHECKED_CAST")
/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 2:08 PM
 * Description :
 */
class ServicePresenter(view: ServiceContract.View,
    context: Context) : WisdomGardenPresenter<ServiceContract.View>(view,
    context), ServiceContract.Presenter {
  companion object {
    const val GET_ALL_SERVICE_NAME_AND_COUNT_REQUEST: Short = 1
  }

  private val serviceNameAndCountList = ArrayList<ServiceNameAndCount>()
  private var serviceNameAndCountAdapter: ServiceAndNameAdapter? = null
  private val getAllServiceNameAndCountRequest = GetAllServicesNameAndCountRequest(this)
  override fun init() {
    if (serviceNameAndCountAdapter == null) {
      serviceNameAndCountAdapter = ServiceAndNameAdapter(serviceNameAndCountList)
      mView?.initAdapter(serviceNameAndCountAdapter!!)
    }
    getAllServiceNameAndCount()
  }

  private fun getAllServiceNameAndCount() {
    mView?.showRefreshing()
    getAllServiceNameAndCountRequest.requestId = GET_ALL_SERVICE_NAME_AND_COUNT_REQUEST
    getAllServiceNameAndCountRequest.execute()
  }

  override fun onData(requestId: Short, response: Any?) {
    when (requestId) {
      GET_ALL_SERVICE_NAME_AND_COUNT_REQUEST -> {
        mView?.hideRefreshing()
        if (response != null) {
          val list: ArrayList<ServiceNameAndCount> = response as ArrayList<ServiceNameAndCount>
          if (list.size > 0) {
            serviceNameAndCountList.clear()
            serviceNameAndCountList.addAll(list)
            serviceNameAndCountAdapter?.notifyDataSetChanged()
          } else {
            mView?.toast("没有服务设施")
          }
        }
      }
    }
  }

  override fun onError(requestId: Short, stringId: Int) {
    super.onError(requestId, stringId)
    mView?.hideRefreshing()
  }

  override fun onSpecialError(requestId: Short, errorType: Short) {
    super.onSpecialError(requestId, errorType)
    mView?.hideRefreshing()
  }

  override fun onEventBusMessage(message: EventBusMessage) {
    super.onEventBusMessage(message)
    when (message.messageId) {
      EventBusMessageID.CHANGE_BASE_URL -> {
        getAllServiceNameAndCount()
      }
    }
  }
}