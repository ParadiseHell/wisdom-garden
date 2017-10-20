package com.chengtao.wisdomgarden.ui.main.sight

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseFragment
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.adapter.RouteAdapter
import com.chengtao.wisdomgarden.ui.main.route.RouteContract
import com.chengtao.wisdomgarden.ui.main.route.RouteContract.Presenter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:29 AM
 * Description :
 */
class RouteFragment : BaseFragment<RouteContract.Presenter>(), RouteContract.View {
  @BindView(R.id.rv_route)
  lateinit var rvRoute: RecyclerView

  override fun getLayoutId(): Int = R.layout.fagment_route

  override fun initPresenter(): Presenter = RoutePresenter(this, mContext!!)

  override fun initView() {
    rvRoute.layoutManager = LinearLayoutManager(mContext!!)
  }

  override fun setListener() {
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun initAdapter(adapter: RouteAdapter) {
    rvRoute.adapter = adapter
  }
}