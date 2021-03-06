package com.chengtao.wisdomgarden.ui.main.sight

import android.content.Context
import android.util.Log
import com.chengtao.wisdomgarden.EventBusMessageID
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.adapter.SightAdapter
import com.chengtao.wisdomgarden.entity.EventBusMessage
import com.chengtao.wisdomgarden.entity.Sight
import com.chengtao.wisdomgarden.request.GetAllSightsRequest

@Suppress("UNCHECKED_CAST")
/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:30 AM
 * Description :
 */
class SightPresenter(view: SightContract.View,
    context: Context) : WisdomGardenPresenter<SightContract.View>(view,
    context), SightContract.Presenter {
  companion object {
    const val GET_ALL_SIGHT_REQUEST: Short = 1
  }

  private val sightList = ArrayList<Sight>()
  private var sightAdapter: SightAdapter? = null
  private val getAllSightRequest = GetAllSightsRequest(this)
  override fun init() {
    if (sightAdapter == null) {
      sightAdapter = SightAdapter(sightList)
      mView?.initAdapter(sightAdapter!!)
    }
    getAllSight()
  }

  private fun getAllSight() {
    mView?.showRefreshing()
    getAllSightRequest.requestId = GET_ALL_SIGHT_REQUEST
    getAllSightRequest.execute()
  }

  override fun onData(requestId: Short, response: Any?) {
    when (requestId) {
      GET_ALL_SIGHT_REQUEST -> {
        mView?.hideRefreshing()
        if (response != null) {
          Log.e("TAG", response.toString())
          val list: ArrayList<Sight> = response as ArrayList<Sight>
          if (list.size > 0) {
            sightList.clear()
            sightList.addAll(list)
            sightAdapter?.notifyDataSetChanged()
          } else {
            mView?.toast("没有景点")
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
        getAllSight()
      }
    }
  }
}