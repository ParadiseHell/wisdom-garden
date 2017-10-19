package com.chengtao.wisdomgarden.ui.register

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseActivity
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.ui.register.RegisterContract.Presenter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 5:10 PM
 * Description :
 */
class RegisterActivity : BaseActivity<RegisterContract.Presenter>(), RegisterContract.View, OnClickListener {
  companion object {
    fun invoke(context: Context) {
      val intent = Intent(context, RegisterActivity::class.java)
      context.startActivity(intent)
    }
  }

  //控件
  @BindView(R.id.et_user_name)
  lateinit var etUserName: EditText
  @BindView(R.id.et_password)
  lateinit var etPassword: EditText
  @BindView(R.id.et_password_confirm)
  lateinit var etPasswordConfirm: EditText
  @BindView(R.id.btn_register)
  lateinit var btnRegister: Button

  override fun getLayoutId(): Int = R.layout.activity_register

  override fun initPresenter(): Presenter = RegisterPresenter(this, this)

  override fun initView() {}

  override fun setListener() {
    btnRegister.setOnClickListener(this)
  }

  override fun start() {}

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.btn_register -> {
        mPresenter?.register(etUserName.text.toString(), etPassword.text.toString(),
            etPasswordConfirm.text.toString())
      }
    }
  }

}