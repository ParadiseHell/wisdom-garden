package com.chengtao.wisdomgarden.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.entity.Sight

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
      holder.setText(R.id.name, item.name ?: "")
      holder.setText(R.id.description, item.description ?: "")
      val ecology = item.ecology
      if (ecology == null) {
        holder.getView<View>(R.id.ll_ecology).visibility = View.GONE
      } else {
        holder.setText(R.id.temperature, ecology.temperature?.toString() ?: "")
        holder.setText(R.id.humidity, ecology.humidity?.toString() ?: "")
        holder.setText(R.id.pm25, ecology.pm25?.toString() ?: "")
        holder.setText(R.id.wind, ecology.wind ?: "")
        holder.setText(R.id.wind, ecology.dressing ?: "")
      }
    }
  }
}