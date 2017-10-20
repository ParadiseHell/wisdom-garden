package com.chengtao.wisdomgarden.ui

import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import com.chengtao.wisdomgarden.BaseActivity
import com.chengtao.wisdomgarden.BasePresenter
import com.chengtao.wisdomgarden.R
import com.chengtao.wisdomgarden.ui.login.LoginActivity
import com.chengtao.wisdomgarden.ui.main.plants.PlantsFragment
import com.chengtao.wisdomgarden.ui.main.route.RouteFragment
import com.chengtao.wisdomgarden.ui.main.service.ServiceFragment
import com.chengtao.wisdomgarden.ui.main.setting.SettingActivity
import com.chengtao.wisdomgarden.ui.main.sight.SightFragment
import com.chengtao.wisdomgarden.utils.UserUtils


class MainActivity : BaseActivity<BasePresenter>(), BottomNavigationView.OnNavigationItemSelectedListener {

  companion object {
    const val TAB_SIGHT: Short = 1
    const val TAB_PLANTS: Short = 2
    const val TAB_ROUTE: Short = 3
    const val TAB_SERVICE: Short = 4
    fun invoke(context: Context) {
      context.startActivity(Intent(context, MainActivity::class.java))
    }
  }

  //控件
  @BindView(R.id.bnv_main)
  lateinit var mainNav: BottomNavigationView
  private var currentTab: Short = TAB_SIGHT
  private var sightFragment: SightFragment? = null
  private var plantsFragment: PlantsFragment? = null
  private var routeFragment: RouteFragment? = null
  private var serviceFragment: ServiceFragment? = null

  override fun getLayoutId(): Int = R.layout.activity_main

  override fun initPresenter(): BasePresenter? = null

  override fun initView() {
  }

  override fun setListener() {
    mainNav.setOnNavigationItemSelectedListener(this)
  }

  override fun start() {
    changeFragment(TAB_SIGHT)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    R.id.action_settings -> {
      SettingActivity.invoke(mContext!!)
      true
    }
    R.id.action_logout -> {
      UserUtils.clean()
      LoginActivity.invoke(mContext!!)
      finish()
      true
    }
    else -> {
      super.onOptionsItemSelected(item)
    }
  }

  private fun changeFragment(tab: Short) {
    val transaction = supportFragmentManager.beginTransaction()
    hideAllFragment(transaction)
    when (tab) {
      TAB_SIGHT -> {
        mainNav.menu.findItem(R.id.sight).isChecked = true
        setActionBarTitle(R.string.sight)
        currentTab = TAB_SIGHT
        if (sightFragment == null) {
          sightFragment = SightFragment()
          transaction.add(R.id.fl_main, sightFragment)
        } else {
          transaction.show(sightFragment)
        }
      }
      TAB_PLANTS -> {
        mainNav.menu.findItem(R.id.plants).isChecked = true
        setActionBarTitle(R.string.plants)
        currentTab = TAB_PLANTS
        if (plantsFragment == null) {
          plantsFragment = PlantsFragment()
          transaction.add(R.id.fl_main, plantsFragment)
        } else {
          transaction.show(plantsFragment)
        }
      }
      TAB_ROUTE -> {
        mainNav.menu.findItem(R.id.route).isChecked = true
        setActionBarTitle(R.string.route)
        currentTab = TAB_ROUTE
        if (routeFragment == null) {
          routeFragment = RouteFragment()
          transaction.add(R.id.fl_main, routeFragment)
        } else {
          transaction.show(routeFragment)
        }
      }
      TAB_SERVICE -> {
        mainNav.menu.findItem(R.id.service).isChecked = true
        setActionBarTitle(R.string.service)
        currentTab = TAB_SERVICE
        if (serviceFragment == null) {
          serviceFragment = ServiceFragment()
          transaction.add(R.id.fl_main, serviceFragment)
        } else {
          transaction.show(serviceFragment)
        }
      }
    }
    transaction.commitAllowingStateLoss()
  }

  /**
   * 清楚所有的fragment
   *
   * @param transaction 事务
   */
  private fun hideAllFragment(transaction: FragmentTransaction) {
    if (sightFragment != null) {
      transaction.hide(sightFragment)
    }
    if (plantsFragment != null) {
      transaction.hide(plantsFragment)
    }
    if (routeFragment != null) {
      transaction.hide(routeFragment)
    }
    if (serviceFragment != null) {
      transaction.hide(serviceFragment)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.sight -> changeFragment(TAB_SIGHT)
      R.id.plants -> changeFragment(TAB_PLANTS)
      R.id.route -> changeFragment(TAB_ROUTE)
      R.id.service -> changeFragment(TAB_SERVICE)
    }
    return true
  }
}
