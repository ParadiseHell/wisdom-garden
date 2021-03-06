package com.chengtao.wisdomgarden.ui.main.route

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseFragment
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.adapter.RouteAdapter
import com.chengtao.wisdomgarden.ui.main.route.RouteContract.Presenter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:29 AM
 * Description :
 */
class RouteFragment : BaseFragment<RouteContract.Presenter>(), RouteContract.View, SwipeRefreshLayout.OnRefreshListener {
  @BindView(R.id.srl_route)
  lateinit var srlRoute: SwipeRefreshLayout
  @BindView(R.id.rv_route)
  lateinit var rvRoute: RecyclerView

  override fun getLayoutId(): Int = R.layout.fagment_route

  override fun initPresenter(): Presenter = RoutePresenter(this, mContext!!)

  override fun initView() {
    rvRoute.layoutManager = LinearLayoutManager(mContext!!)
    srlRoute.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary,
        R.color.colorPrimary)
  }

  override fun setListener() {
    srlRoute.setOnRefreshListener(this)
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun initAdapter(adapter: RouteAdapter) {
    rvRoute.adapter = adapter
  }

  override fun onRefresh() {
    mPresenter?.init()
  }

  override fun showRefreshing() {
    srlRoute.isRefreshing = true
  }

  override fun hideRefreshing() {
    srlRoute.isRefreshing = false
  }
}