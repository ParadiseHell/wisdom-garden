package com.chengtao.wisdomgarden

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import butterknife.ButterKnife

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 4:02 AM
 * Description :
 */
@Suppress("MemberVisibilityCanPrivate", "UNCHECKED_CAST")
abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseView {
  var toast: Toast? = null
  var mContext: Context? = null
  var mPresenter: P? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (getLayoutId() == 0) {
      throw NullPointerException("layout id can not be 0")
    }
    setContentView(getLayoutId())
    ButterKnife.bind(this)
    mContext = this
    mPresenter = initPresenter()
    mPresenter?.subscribe()
    initView()
    setListener()
    start()
  }

  override fun onDestroy() {
    super.onDestroy()
    mPresenter?.unSubscribe()
  }

  /**
   * 获取布局id
   */
  abstract fun getLayoutId(): Int

  /**
   * 初始化presenter
   */
  abstract fun initPresenter(): P?

  /**
   * 初始化页面
   */
  abstract fun initView()

  /**
   * 设置监听
   */
  abstract fun setListener()

  /**
   * 开始
   */
  abstract fun start()

  protected fun showToast(stringId: Int) {
    try {
      showToast(getString(stringId))
    } catch (e: Exception) {
      //do nothing
    }
  }

  protected fun showToast(string: String) {
    if (!TextUtils.isEmpty(string)) {
      toast?.cancel()
      toast = Toast.makeText(mContext, string, Toast.LENGTH_SHORT)
      toast?.show()
    }
  }

  override fun toast(string: String) {
    showToast(string)
  }

  override fun toast(stringId: Int) {
    showToast(stringId)
  }
}