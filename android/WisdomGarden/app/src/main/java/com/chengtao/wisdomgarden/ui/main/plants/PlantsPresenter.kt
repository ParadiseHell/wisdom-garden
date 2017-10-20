package com.chengtao.wisdomgarden.ui.main.plants

import android.content.Context
import com.chengtao.wisdomgarden.EventBusMessageID
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.adapter.PlantsAdapter
import com.chengtao.wisdomgarden.entity.EventBusMessage
import com.chengtao.wisdomgarden.entity.Plants
import com.chengtao.wisdomgarden.request.GetAllPlantsRequest

@Suppress("UNCHECKED_CAST")
/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:30 AM
 * Description :
 */
class PlantsPresenter(view: PlantsContract.View,
    context: Context) : WisdomGardenPresenter<PlantsContract.View>(view,
    context), PlantsContract.Presenter {
  companion object {
    const val GET_ALL_PLANTS_REQUEST: Short = 1
  }

  private val plantsList = ArrayList<Plants>()
  private var plantsAdapter: PlantsAdapter? = null
  private val getAllPlantsRequest = GetAllPlantsRequest(this)
  override fun init() {
    plantsAdapter = PlantsAdapter(plantsList)
    mView?.initAdapter(plantsAdapter!!)
    getAllPlants()
  }

  private fun getAllPlants() {
    getAllPlantsRequest.requestId = GET_ALL_PLANTS_REQUEST
    getAllPlantsRequest.execute()
  }

  override fun onData(requestId: Short, response: Any?) {
    when (requestId) {
      GET_ALL_PLANTS_REQUEST -> {
        if (response != null) {
          val list: ArrayList<Plants> = response as ArrayList<Plants>
          if (list.size > 0) {
            plantsList.clear()
            plantsList.addAll(list)
            plantsAdapter?.notifyDataSetChanged()
          } else {
            mView?.toast("没有植物")
          }
        }
      }
    }
  }

  override fun onEventBusMessage(message: EventBusMessage) {
    super.onEventBusMessage(message)
    when (message.messageId) {
      EventBusMessageID.CHANGE_BASE_URL -> {
        getAllPlants()
      }
    }
  }
}