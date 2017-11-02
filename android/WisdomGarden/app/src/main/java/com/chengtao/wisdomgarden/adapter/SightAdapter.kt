package com.chengtao.wisdomgarden.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.entity.Sight
import com.chengtao.wisdomgarden.entity.WisdomGardenFile
import com.jaeger.ninegridimageview.NineGridImageView

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:02 AM
 * Description :
 */
class SightAdapter(sightsList: ArrayList<Sight>) : BaseQuickAdapter<Sight, BaseViewHolder>(
    R.layout.adpter_sight, sightsList) {
  init {
    openLoadAnimation()
  }

  override fun convert(holder: BaseViewHolder?, item: Sight?) {
    if (holder != null && item != null) {
      holder.setText(R.id.name, if (item.name == null) "" else item.name)
      val nineGridView = holder.getView<NineGridImageView<WisdomGardenFile>>(R.id.nine_grid_sight)
      if (item.images != null && item.images!!.size > 0) {
        nineGridView.visibility = View.VISIBLE
        nineGridView.setAdapter(ImageAdapter())
        nineGridView.setImagesData(item.images)
      } else {
        nineGridView.visibility = View.GONE
      }
      holder.setText(R.id.description, if (item.description == null) "" else item.description)
      if (item.latitude != null && item.longitude != null) {
        holder.setText(R.id.location, "(${item.latitude},${item.longitude})")
      }
      val ecology = item.ecology
      if (ecology == null) {
        holder.getView<View>(R.id.ll_ecology).visibility = View.GONE
      } else {
        holder.setText(R.id.temperature,
            if (ecology.temperature == null) "" else ecology.temperature.toString())
        holder.setText(R.id.humidity,
            if (ecology.humidity == null) "" else ecology.humidity.toString())
        holder.setText(R.id.pm25,
            if (ecology.pm25 == null) "" else ecology.pm25.toString())
        holder.setText(R.id.wind,
            if (ecology.wind == null) "" else ecology.wind)
        holder.setText(R.id.dressing,
            if (ecology.dressing == null) "" else ecology.dressing)
      }
    }
  }
}