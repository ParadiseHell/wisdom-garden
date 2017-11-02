package com.chengtao.wisdomgarden.ui.main.map

import android.os.Bundle
import butterknife.BindView
import com.amap.api.maps2d.MapView
import com.chengtao.wisdomgarden.BaseFragment
import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.R

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 11/2/17
 * Time : 8:42 PM
 * Description :
 */
class MapFragment : BaseFragment<BasePresenter>() {
  @BindView(R.id.map)
  lateinit var mapView: MapView

  override fun getLayoutId(): Int {
    return R.layout.fragment_map
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    mapView.onCreate(savedInstanceState)
  }

  override fun initPresenter(): BasePresenter? {
    return null
  }

  override fun initView() {

  }

  override fun setListener() {
  }

  override fun start() {
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }

}