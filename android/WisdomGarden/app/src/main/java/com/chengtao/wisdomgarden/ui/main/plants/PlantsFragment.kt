package com.chengtao.wisdomgarden.ui.main.plants

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseFragment
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.adapter.PlantsAdapter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 10:29 AM
 * Description :
 */
class PlantsFragment : BaseFragment<PlantsContract.Presenter>(), PlantsContract.View {
  @BindView(R.id.rv_plants)
  lateinit var rvPlants: RecyclerView

  override fun getLayoutId(): Int = R.layout.fagment_plants

  override fun initPresenter(): PlantsContract.Presenter = PlantsPresenter(this, mContext!!)

  override fun initView() {
    rvPlants.layoutManager = LinearLayoutManager(mContext!!)
  }

  override fun setListener() {
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun initAdapter(adapter: PlantsAdapter) {
    rvPlants.adapter = adapter
  }
}