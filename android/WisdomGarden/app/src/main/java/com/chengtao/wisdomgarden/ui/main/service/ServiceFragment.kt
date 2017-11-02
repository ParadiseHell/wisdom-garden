package com.chengtao.wisdomgarden.ui.main.service

import android.support.v4.widget.SwipeRefreshLayout
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
class ServiceFragment : BaseFragment<ServiceContract.Presenter>(), ServiceContract.View, SwipeRefreshLayout.OnRefreshListener {
  @BindView(R.id.srl_service)
  lateinit var srlService: SwipeRefreshLayout
  @BindView(R.id.rv_service)
  lateinit var rvService: RecyclerView

  override fun getLayoutId(): Int = R.layout.fagment_service

  override fun initPresenter(): Presenter = ServicePresenter(this, this.activity)

  override fun initView() {
    rvService.layoutManager = LinearLayoutManager(mContext!!)
    srlService.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary,
        R.color.colorPrimary)
  }

  override fun setListener() {
    srlService.setOnRefreshListener(this)
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun initAdapter(adapter: ServiceAndNameAdapter) {
    rvService.adapter = adapter
  }

  override fun onRefresh() {
    mPresenter?.init()
  }

  override fun showRefreshing() {
    srlService.isRefreshing = true
  }

  override fun hideRefreshing() {
    srlService.isRefreshing = false
  }
}