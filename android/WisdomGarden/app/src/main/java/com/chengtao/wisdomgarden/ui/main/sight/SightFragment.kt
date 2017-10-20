package com.chengtao.wisdomgarden.ui.main.sight

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
class SightFragment : BaseFragment<SightContract.Presenter>(), SightContract.View {
  @BindView(R.id.rv_sight)
  lateinit var rvSight: RecyclerView

  override fun getLayoutId(): Int = R.layout.fagment_sight

  override fun initPresenter(): SightContract.Presenter = SightPresenter(this, mContext!!)

  override fun initView() {
    rvSight.layoutManager = LinearLayoutManager(mContext!!)
  }

  override fun setListener() {
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun initAdapter(adapter: SightAdapter) {
    rvSight.adapter = adapter
  }
}