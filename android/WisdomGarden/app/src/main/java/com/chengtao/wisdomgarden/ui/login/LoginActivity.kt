package com.chengtao.wisdomgarden.ui.login

import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseActivity
import com.chengtao.wisdomgarden.R

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 1:05 PM
 * Description :
 */
class LoginActivity : BaseActivity<LoginContract.LoginPresenter>(), LoginContract.LoginView, OnClickListener {
  //控件
  @BindView(R.id.et_user_name)
  lateinit var etUserName: EditText
  @BindView(R.id.et_password)
  lateinit var etPassword: EditText
  @BindView(R.id.btn_login)
  lateinit var btnLogin: Button

  override fun getLayoutId(): Int = R.layout.activity_login

  override fun initPresenter(): LoginContract.LoginPresenter = LoginPresenter(this)

  override fun initView() {

  }

  override fun setListener() {
    btnLogin.setOnClickListener(this)
  }

  override fun start() {
  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.btn_login -> mPresenter?.login(etUserName.text.toString(), etPassword.text.toString())
    }
  }

}