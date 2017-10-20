package com.chengtao.wisdomgarden.ui.main.setting

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseActivity
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.ui.main.setting.SettingContract.Presenter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/20/17
 * Time : 8:40 AM
 * Description :
 */
class SettingActivity : BaseActivity<SettingContract.Presenter>(), SettingContract.View, OnClickListener {

  companion object {
    fun invoke(context: Context) {
      context.startActivity(Intent(context, SettingActivity::class.java))
    }
  }

  //控件
  @BindView(R.id.et_domain_or_ip)
  lateinit var etDomainOrIp: EditText
  @BindView(R.id.et_port)
  lateinit var etPort: EditText
  @BindView(R.id.btn_setting)
  lateinit var btnSetting: Button

  override fun getLayoutId(): Int = R.layout.activity_setting

  override fun initPresenter(): Presenter = SettingPresenter(this, this)

  override fun initView() {
    setActionBarTitle(R.string.setting)
  }

  override fun setListener() {
    btnSetting.setOnClickListener(this)
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.btn_setting -> {
        mPresenter?.changeBaseUrl(etDomainOrIp.text.toString(), etPort.text.toString())
      }
    }
  }

  override fun setDomainOrIp(domainOrIp: String) {
    etDomainOrIp.setText(domainOrIp)
  }

  override fun setPort(port: String) {
    etPort.setText(port)
  }
}