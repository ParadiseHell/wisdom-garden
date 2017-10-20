package com.chengtao.wisdomgarden.ui.login

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseActivity
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.ui.main.setting.SettingActivity
import com.chengtao.wisdomgarden.ui.register.RegisterActivity

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 1:05 PM
 * Description :
 */
class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.LoginView, OnClickListener {
  companion object {
    fun invoke(context: Context) {
      context.startActivity(Intent(context, LoginActivity::class.java))
    }
  }

  //控件
  @BindView(R.id.et_user_name)
  lateinit var etUserName: EditText
  @BindView(R.id.et_password)
  lateinit var etPassword: EditText
  @BindView(R.id.btn_login)
  lateinit var btnLogin: Button
  @BindView(R.id.tv_hint_register)
  lateinit var tvHitRegister: TextView

  override fun getLayoutId(): Int = R.layout.activity_login

  override fun initPresenter(): LoginContract.Presenter = LoginPresenter(this, this)

  override fun initView() {
    setActionBarTitle(R.string.login)
  }

  override fun setListener() {
    btnLogin.setOnClickListener(this)
    tvHitRegister.setOnClickListener(this)
    supportActionBar
  }

  override fun start() {
    mPresenter?.init()
  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.btn_login -> {
        mPresenter?.login(etUserName.text.toString(), etPassword.text.toString())
      }
      R.id.tv_hint_register -> {
        RegisterActivity.Companion.invoke(mContext!!)
      }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_setting, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    R.id.action_settings -> {
      SettingActivity.invoke(mContext!!)
      true
    }
    else -> {
      super.onOptionsItemSelected(item)
    }
  }
}