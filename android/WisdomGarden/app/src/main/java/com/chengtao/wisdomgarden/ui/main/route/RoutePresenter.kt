package com.chengtao.wisdomgarden.ui.main.route

import android.content.Context
import com.chengtao.wisdomgarden.EventBusMessageID
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.adapter.RouteAdapter
import com.chengtao.wisdomgarden.entity.EventBusMessage
import com.chengtao.wisdomgarden.entity.Route
import com.chengtao.wisdomgarden.request.GetAllRoutesRequest

@Suppress("UNCHECKED_CAST")
/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:30 AM
 * Description :
 */
class RoutePresenter(view: RouteContract.View,
    context: Context) : WisdomGardenPresenter<RouteContract.View>(view,
    context), RouteContract.Presenter {
  companion object {
    const val GET_ALL_ROUTE_REQUEST: Short = 1
  }

  private val routeList = ArrayList<Route>()
  private var routeAdapter: RouteAdapter? = null
  private val getAllRouteRequest = GetAllRoutesRequest(this)
  override fun init() {
    routeAdapter = RouteAdapter(routeList)
    mView?.initAdapter(routeAdapter!!)
    getAllRoute()
  }

  private fun getAllRoute() {
    getAllRouteRequest.requestId = GET_ALL_ROUTE_REQUEST
    getAllRouteRequest.execute()
  }

  override fun onData(requestId: Short, response: Any?) {
    when (requestId) {
      GET_ALL_ROUTE_REQUEST -> {
        if (response != null) {
          val list: ArrayList<Route> = response as ArrayList<Route>
          if (list.size > 0) {
            routeList.clear()
            routeList.addAll(list)
            routeAdapter?.notifyDataSetChanged()
          } else {
            mView?.toast("没有路线")
          }
        }
      }
    }
  }

  override fun onEventBusMessage(message: EventBusMessage) {
    super.onEventBusMessage(message)
    when (message.messageId) {
      EventBusMessageID.CHANGE_BASE_URL -> {
        getAllRoute()
      }
    }
  }
}