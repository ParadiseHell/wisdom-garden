package com.chengtao.wisdomgarden.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.entity.ServiceNameAndCount

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 2:11 PM
 * Description :
 */
/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:02 AM
 * Description :
 */
class ServiceAndNameAdapter(
    serviceNameAndCountList: ArrayList<ServiceNameAndCount>) : BaseQuickAdapter<ServiceNameAndCount, BaseViewHolder>(
    R.layout.adpter_service_name_and_count, serviceNameAndCountList) {
  init {
    openLoadAnimation()
  }

  override fun convert(holder: BaseViewHolder?, item: ServiceNameAndCount?) {
    if (holder != null && item != null) {
      holder.setText(R.id.name, if (item.name == null) "" else item.name)
      holder.setText(R.id.count, if (item.count == null) "" else item.count.toString())
    }
  }
}