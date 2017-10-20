package com.chengtao.wisdomgarden.ui

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.chengtao.wisdomgarden.BaseActivity
import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.ui.main.setting.SettingActivity

class MainActivity : BaseActivity<BasePresenter>() {
  companion object {
    fun invoke(context: Context) {
      context.startActivity(Intent(context, MainActivity::class.java))
    }
  }

  override fun getLayoutId(): Int = R.layout.activity_main

  override fun initPresenter(): BasePresenter? = null

  override fun initView() {
  }

  override fun setListener() {
  }

  override fun start() {
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main, menu)
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
