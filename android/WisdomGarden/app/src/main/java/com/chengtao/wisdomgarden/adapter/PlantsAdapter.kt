package com.chengtao.wisdomgarden.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.entity.Plants
import com.chengtao.wisdomgarden.entity.WisdomGardenFile
import com.jaeger.ninegridimageview.NineGridImageView

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:02 AM
 * Description :
 */
class PlantsAdapter(plantsList: ArrayList<Plants>) : BaseQuickAdapter<Plants, BaseViewHolder>(
    R.layout.adpter_plants, plantsList) {
  init {
    openLoadAnimation()
  }

  override fun convert(holder: BaseViewHolder?, item: Plants?) {
    if (holder != null && item != null) {
      holder.setText(R.id.name, if (item.name == null) "" else item.name)
      val nineGridView = holder.getView<NineGridImageView<WisdomGardenFile>>(R.id.nine_grid_plants)
      if (item.images != null && item.images!!.size > 0) {
        nineGridView.visibility = View.VISIBLE
        nineGridView.setAdapter(ImageAdapter())
        nineGridView.setImagesData(item.images)
      } else {
        nineGridView.visibility = View.GONE
      }
      holder.setText(R.id.description, if (item.description == null) "" else item.description)
      val sightList = item.sights
      if (sightList != null) {
        var sightListString: String? = ""
        for (index in sightList.indices) {
          sightListString += sightList[index].name
          if (index != sightList.size - 1) {
            sightListString += ","
          }
        }
        holder.setText(R.id.sight_list, sightListString)
      }
    }
  }
}