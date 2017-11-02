package com.chengtao.wisdomgarden.ui.main.sight

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseFragment
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.adapter.SightAdapter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:29 AM
 * Description :
 */
class SightFragment : BaseFragment<SightContract.Presenter>(), SightContract.View, SwipeRefreshLayout.OnRefreshListener {
  @BindView(R.id.srl_sight)
  lateinit var srlSight: SwipeRefreshLayout
  @BindView(R.id.rv_sight)
  lateinit var rvSight: RecyclerView

  override fun getLayoutId(): Int = R.layout.fagment_sight

  override fun initPresenter(): SightContract.Presenter = SightPresenter(this, mContext!!)

  override fun initView() {
    rvSight.layoutManager = LinearLayoutManager(mContext!!)
    srlSight.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary,
        R.color.colorPrimary)
  }

  override fun setListener() {
    srlSight.setOnRefreshListener(this)
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun initAdapter(adapter: SightAdapter) {
    rvSight.adapter = adapter
  }

  override fun onRefresh() {
    mPresenter?.init()
  }

  override fun showRefreshing() {
    srlSight.isRefreshing = true
  }

  override fun hideRefreshing() {
    srlSight.isRefreshing = false
  }
}