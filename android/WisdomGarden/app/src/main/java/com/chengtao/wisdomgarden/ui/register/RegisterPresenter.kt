package com.chengtao.wisdomgarden.ui.register

import android.content.Context
import com.chengtao.wisdomgarden.WisdomGardenPresenter
import com.chengtao.wisdomgarden.api.ErrorType
import com.chengtao.wisdomgarden.request.RegisterRequest
import com.chengtao.wisdomgarden.utils.MD5Util

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
        registerRequest.requestId = REGISTER_REQUEST
        registerRequest.userName = userName
        registerRequest.password = MD5Util.md5(password)
        registerRequest.execute()
      }
    }
  }

  override fun onData(requestId: Short, response: Any?) {
    when (requestId) {
      REGISTER_REQUEST -> {
        if (response != null) {
          TODO("保存用户信息,注册成功,跳转主界面")
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