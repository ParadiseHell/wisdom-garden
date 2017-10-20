package com.chengtao.wisdomgarden.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.entity.Route

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:02 AM
 * Description :
 */
class RouteAdapter(routeList: ArrayList<Route>) : BaseQuickAdapter<Route, BaseViewHolder>(
    R.layout.adpter_plants, routeList) {
  init {
    openLoadAnimation()
  }

  override fun convert(holder: BaseViewHolder?, item: Route?) {
    if (holder != null && item != null) {
      holder.setText(R.id.name, item.name ?: "")
      holder.setText(R.id.description, item.description ?: "")
      val sightList = item.sightChain
      if (sightList != null) {
        var sightListString: String? = ""
        for (index in sightList.indices) {
          sightListString += sightList[index]
          if (index != sightList.size - 1) {
            sightListString + ","
          }
        }
        holder.setText(R.id.sight_chain, sightListString)
      }
    }
  }
}