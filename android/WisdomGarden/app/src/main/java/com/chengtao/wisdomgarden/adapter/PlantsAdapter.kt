package com.chengtao.wisdomgarden.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.R.id
import com.chengtao.wisdomgarden.R.layout
import com.chengtao.wisdomgarden.entity.Plants

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:02 AM
 * Description :
 */
class PlantsAdapter(plantsList: ArrayList<Plants>) : BaseQuickAdapter<Plants, BaseViewHolder>(
    layout.adpter_plants, plantsList) {
  init {
    openLoadAnimation()
  }

  override fun convert(holder: BaseViewHolder?, item: Plants?) {
    if (holder != null && item != null) {
      holder.setText(id.name, item.name ?: "")
      holder.setText(id.description, item.description ?: "")
      val sightList = item.sights
      if (sightList != null) {
        var sightListString: String? = ""
        for (index in sightList.indices) {
          sightListString += sightList[index]
          if (index != sightList.size - 1) {
            sightListString + ","
          }
        }
        holder.setText(R.id.sight_list, sightListString)
      }
    }
  }
}