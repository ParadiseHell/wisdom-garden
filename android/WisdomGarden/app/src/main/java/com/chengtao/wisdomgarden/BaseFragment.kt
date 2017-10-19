package com.chengtao.wisdomgarden

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife

@Suppress("ProtectedInFinal")
/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 5:49 PM
 * Description :
 */
abstract class BaseFragment<P : BasePresenter> : Fragment(), BaseView {

  var toast: Toast? = null
  var mContext: Context? = null
  var mPresenter: P? = null

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    if (getLayoutId() == 0) {
      throw RuntimeException("layout id can not be 0");
    }
    return inflater?.inflate(getLayoutId(), container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    ButterKnife.bind(this.activity)
    mContext = this.activity
    mPresenter = initPresenter()
    mPresenter?.subscribe()
    initView()
    setListener()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    start()
  }

  override fun onDetach() {
    super.onDetach()
    mPresenter?.unSubscribe()
  }

  /**
   * 获取布局id
   */
  abstract fun getLayoutId(): Int

  /**
   * 初始化presenter
   */
  abstract fun initPresenter(): P

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