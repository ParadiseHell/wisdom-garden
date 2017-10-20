package com.chengtao.wisdomgarden.ui.main.service

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseFragment
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.adapter.ServiceAndNameAdapter
import com.chengtao.wisdomgarden.ui.main.service.ServiceContract.Presenter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 2:08 PM
 * Description :
 */
class ServiceFragment : BaseFragment<ServiceContract.Presenter>(), ServiceContract.View {
  @BindView(R.id.rv_service)
  lateinit var rvService: RecyclerView

  override fun getLayoutId(): Int = R.layout.fagment_service

  override fun initPresenter(): Presenter = ServicePresenter(this, this.activity)

  override fun initView() {
    rvService.layoutManager = LinearLayoutManager(mContext!!)
  }

  override fun setListener() {
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun initAdapter(adapter: ServiceAndNameAdapter) {
    rvService.adapter = adapter
  }
}