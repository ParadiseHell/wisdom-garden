package com.chengtao.wisdomgarden.ui.register

import android.content.Context
import com.chengtao.wisdomgarden.EventBusMessageID
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.api.ErrorType
import com.chengtao.wisdomgarden.entity.EventBusMessage
import com.chengtao.wisdomgarden.request.RegisterRequest
import com.chengtao.wisdomgarden.ui.MainActivity
import com.chengtao.wisdomgarden.utils.MD5Util
import com.chengtao.wisdomgarden.utils.StringUtils
import com.chengtao.wisdomgarden.utils.UserUtils
import org.greenrobot.eventbus.EventBus

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 10/19/17
 * Time : 5:18 PM
 * Description :
 */
class RegisterPresenter(view: RegisterContract.View,
    context: Context) : WisdomGardenPresenter<RegisterContract.View>(view,
    context), RegisterContract.Presenter {
  companion object {
    const val REGISTER_REQUEST: Short = 1
  }

  //请求
  private val registerRequest = RegisterRequest(this)
  //其他
  private var userName: String? = null
  private var password: String? = null
  override fun register(userName: String, password: String, passwordConfirm: String) {
    when {
      userName == "" -> {
        mView?.toast("用户名不能为空")
      }
      password == "" -> {
        mView?.toast("密码不能为空")
      }
      passwordConfirm == "" -> {
        mView?.toast("确认密码不能为空")
      }
      password != passwordConfirm -> {
        mView?.toast("密码和确认密码不一致")
      }
      else -> {
        this.userName = userName
        this.password = MD5Util.md5(password)
        registerRequest.requestId = REGISTER_REQUEST
        registerRequest.userName = this.userName
        registerRequest.password = this.password
        registerRequest.execute()
      }
    }
  }

  override fun onData(requestId: Short, response: Any?) {
    when (requestId) {
      REGISTER_REQUEST -> {
        if (response != null) {
          if (!StringUtils.isStringNull(userName, password)) {
            UserUtils.saveUser(userName!!, password!!)
          }
          mView?.toast("注册成功")
          EventBus.getDefault().post(EventBusMessage(EventBusMessageID.FINISH_ACTIVITY, true))
          MainActivity.invoke(mContext!!)
        }
      }
    }
  }

  override fun onSpecialError(requestId: Short, errorType: Short) {
    super.onSpecialError(requestId, errorType)
    when (requestId) {
      REGISTER_REQUEST -> {
        when (errorType) {
          ErrorType.USER_EXIST ->
            //结束注册界面
            finish()
        }
      }
    }
  }
}